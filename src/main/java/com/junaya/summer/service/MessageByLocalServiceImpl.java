package com.junaya.summer.service;/*
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by aya on 02/04/2017.
 */

@Component
public class MessageByLocalServiceImpl implements MessageByLocaleService {

    @Autowired
    MessageSource messageSource;

    @Override
    public String getMessage(String key) {
        return getMessage(key,null);
    }

    @Override
    public String getMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key,args,locale);
    }
}
