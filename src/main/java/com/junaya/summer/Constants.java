package com.junaya.summer;/*
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

import org.springframework.security.access.method.P;

/**
 * Created by aya on 02/04/2017.
 */
public class Constants {

    public static final String APP_NAME = "summer";

    public static final Long TOKEN_LIFE = 3153600000L;

    public static class STATUS_CODE{
        public static final int SUCCESS = 0;
        public static final int ERROR_COMMON = -1;
        public static final int ERROR_TOKEN_INVALID = -1000;
        public static final int ERROR_RE_LOGIN = -1001;
        public static final int ERROR_PERFECT_PROFILE =-1010;
        public static final int ERROR_BIND_USER_NAME = -1020;
        public static final int ERROR_APP_ID_INVALID = -9990;
        public static final int ERROR_DATA_DECODE = -9999;
        public static final int ERROR_NOT_EXISTED = -404;
        public static final int ERROR_UNKNOWN = -999;
    }

    public static class HTTP_HEADER{
        public static final String TOKEN = "TOKEN";

        public static final String APP_ID ="APP-ID";
        public static final String APP_VERSION = "APP-VERSION";
        public static final String NETWORK = "NETWORK";
        public static final String SCREEN_SCALE = "SCREEN-SCALE";
        public static final String SCREEN_WIDTH = "SCREEN-WIDTH";
        public static final String SCREEN_HEIGHT = "SCREEN-HEIGHT";
        public static final String LANGUAGE = "Accept-Language";
    }

    public static class PageInfo {
        public static final Integer PAGE_SIZE = 5;
    }
}
