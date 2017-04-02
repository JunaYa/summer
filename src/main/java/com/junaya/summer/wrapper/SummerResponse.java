package com.junaya.summer.wrapper;/*
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

import com.junaya.summer.Constants.STATUS_CODE;

import java.io.Serializable;

/**
 * Created by aya on 02/04/2017.
 */
public class SummerResponse<T> implements Serializable {

    private int statusCode;

    private String statusMessage;

    private T data;

    private Long timestamp = System.currentTimeMillis();

    public SummerResponse() {
        this(STATUS_CODE.SUCCESS);
    }

    public SummerResponse(int statusCode) {
        this(statusCode, "");
    }

    public SummerResponse(String statusMessage) {
        this(STATUS_CODE.SUCCESS, statusMessage);
    }

    public SummerResponse(int statusCode, String statusMessage) {
        this(null, statusCode, statusMessage);
    }

    public SummerResponse(T data) {
        this(data, STATUS_CODE.SUCCESS);
    }

    public SummerResponse(T data, int statusCode) {
        this(data, statusCode, "");
    }

    public SummerResponse(T data, int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
