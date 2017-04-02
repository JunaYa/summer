package com.junaya.summer.api.user;/*
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

import com.junaya.summer.exception.CommonException;
import com.junaya.summer.exception.DataDecodeError;
import com.junaya.summer.security.TokenService;
import com.junaya.summer.service.MessageByLocaleService;
import com.junaya.summer.wrapper.SummerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * Created by aya on 02/04/2017.
 */

@RequestMapping("/user")
@RestController
public class UserController {


    @Autowired
    MessageByLocaleService messageByLocaleService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public SummerResponse login(@RequestHeader(value = "APP-ID") Integer appId,
                                @RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password) throws CommonException {

        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().endsWith(password)) {
            Long loginTime = System.currentTimeMillis();
            Long expiredTime = TokenService.tokenExpireDate(loginTime);
            String token = TokenService.encodeToken(expiredTime, username, username, appId);
            userMapper.updateUserIfNecessary(user.getUid(), null, user.getLoginAt(), null, user.getAppId());
            return new SummerResponse<>(new LoginResult(loginTime, token));
        }
        throw new CommonException(messageByLocaleService.getMessage("error.login.wrongUsername"));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public SummerResponse register(@RequestHeader(value = "APP-ID") Integer appId,
                                   @RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "zone", required = false, defaultValue = "86") String zone,
                                   @RequestParam(value = "code") String code) throws Exception {


        if (userMapper.findByUsername(username) != null) {
            throw new CommonException(messageByLocaleService.getMessage("error.register.existedUsername"));
        } else {
            if (StringUtils.isEmpty(username) || username.trim().length() == 0) {
                throw new DataDecodeError(messageByLocaleService.getMessage("error.register.illegalUsername"));
            }
            if (StringUtils.isEmpty(password) || username.trim().length() == 0) {
                throw new DataDecodeError(messageByLocaleService.getMessage("error.register.illegalPassword"));
            }

            if (!"5665".equals(code)) {
                throw new CommonException("验证码错误");
            }

            Long loginAt = System.currentTimeMillis();
            Long expiresAt = TokenService.tokenExpireDate(loginAt);
            User user = new User(username, password, appId, new Timestamp(loginAt), new Timestamp(expiresAt));
            String token = TokenService.encodeToken(loginAt, username, password, appId);

            userMapper.insert(user);
            return new SummerResponse(new LoginResult(userMapper.findByUsername(username).getUid(), token));
        }


    }

}
