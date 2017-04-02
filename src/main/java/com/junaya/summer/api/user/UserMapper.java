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

import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by aya on 02/04/2017.
 */

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE uid=#{uid}")
    User findByUid(@Param("uid") int uid);

    @Select("SELECT * FROM user WHERE username=#{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Insert("INSERT INTO user (username,mobile,password,appId,loginAt,expiresAt,isLocked)" +
            " VALUES(#{username},#{mobile},#{password},#{appId},#{loginAt},#{expiresAt},#{isLocked})")
    int insert(User token);


    int updateUserIfNecessary(@Param("uid") Long uid,
                              @Param("password") String password,
                              @Param("loginAt") Timestamp loginAt,
                              @Param("mobile") String mobile,
                              @Param("appId") int appId);


    @Delete("DELETE FROM user WHERE uid=#{uid}")
    int deleteByUid(Long uid);

    @Delete("DELETE FROM  user WHERE username=#{username}")
    int deleteByUsername(String username);

}
