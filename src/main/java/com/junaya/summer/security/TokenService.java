package com.junaya.summer.security;/*
 * Copyright (C) 02/04/2017 JunaYa (http://JunaYa.cn).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.junaya.summer.Constants;
import com.junaya.summer.Constants.HTTP_HEADER;
import com.junaya.summer.api.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by aya on 02/04/2017.
 */
public class TokenService extends TokenBasedRememberMeServices {
    public static final String HEADER_SECURITY_TOKEN = HTTP_HEADER.TOKEN;
    private static final String DELIMITER = ":";

    public TokenService(UserDetailsService userDetailsService) {
        super(HEADER_SECURITY_TOKEN, userDetailsService);
        setAlwaysRemember(true);
        setCookieName(HEADER_SECURITY_TOKEN);
    }

    @Override
    protected String extractRememberMeCookie(HttpServletRequest request) {
        return request.getHeader(getCookieName());
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {
        if (cookieTokens.length != 3) {
            throw new InvalidCookieException("Cookie token did not contain 3  tokens, but contain '" + Arrays.asList(cookieTokens) + "'");
        }

        long tokenExpiryTime;
        try {
            tokenExpiryTime = new Long(cookieTokens[1]).longValue();
        } catch (NumberFormatException e) {
            throw new InvalidCookieException(
                    "Cookie token[1] did not contain a valid number (contained '" + cookieTokens[1] + "')");
        }

        if (isTokenExpired(tokenExpiryTime)) {
            throw new InvalidCookieException("Cookie token[1} has expired (expired on '"
                    + new Date(tokenExpiryTime) + "'; current time is '" + new Date() + "')");
        }

        User userDetails = (User) getUserDetailsService().loadUserByUsername(cookieTokens[0]);
        String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, userDetails.getUsername(), userDetails.getPassword(), userDetails.getAppId());


        if (!equals(expectedTokenSignature,cookieTokens[2])){
            throw new InvalidCookieException("Cookie token[2] contained signature '"
                    + cookieTokens[2] + "' but expected '" + expectedTokenSignature + "'");
        }
        return userDetails;
    }

    public static String encodeToken(long tokenExpiryTime, String username, String password, Integer appId) {
        String signatureValue = makeTokenSignature(tokenExpiryTime, username, password, appId);
        return encodeToken(new String[]{username, Long.toString(tokenExpiryTime), signatureValue});
    }

    private static String makeTokenSignature(long tokenExpiryTime, String username, String password, Integer appId) {
        String data = username + ":" + tokenExpiryTime + ":" + password + ":" + appId + ":" + Constants.APP_NAME;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(data.getBytes())));
    }

    public static Long tokenExpireDate(Long loginTime) {
        return loginTime + Constants.TOKEN_LIFE;
    }

    private static String encodeToken(String[] cookieTokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cookieTokens.length; i++) {
            sb.append(cookieTokens[i]);

            if (i < cookieTokens.length - 1) {
                sb.append(DELIMITER);
            }
        }

        String value = sb.toString();

        sb = new StringBuilder(new String(Base64.encode(value.getBytes())));

        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    private static boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        if (expectedBytes.length != actualBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expectedBytes.length; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }

    private static byte[] bytesUtf8(String s) {
        if (s == null) {
            return null;
        }
        return Utf8.encode(s);
    }


}
