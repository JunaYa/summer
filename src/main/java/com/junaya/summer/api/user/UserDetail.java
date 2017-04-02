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

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by aya on 02/04/2017.
 */

public class UserDetail implements Serializable {

    private static final long serialVersionUID = -6851012666455124870L;
    private long uid;
    private String nickname;
    private String avatar;
    private String bio;
    private String location;
    private String website;
    private int role;
    private int age;
    private int gender;
    private Timestamp registerAt;
    private Timestamp updatedAt;

    public UserDetail(Long uid, String nickname, String avatar,
                      String bio, String location, String website, Integer age, Integer gender,
                      Integer role, Timestamp registerAt, Timestamp updatedAt) {
        this.uid = uid;
        this.nickname = nickname;
        this.avatar = avatar;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.registerAt = registerAt;
        this.updatedAt = updatedAt;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Timestamp getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(Timestamp registerAt) {
        this.registerAt = registerAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", website='" + website + '\'' +
                ", role=" + role +
                ", age=" + age +
                ", gender=" + gender +
                ", registerAt=" + registerAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
