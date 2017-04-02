
CREATE DATABASE summer;

CREATE TABLE user (
    uid       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    mobile    VARCHAR(255) ,
    password  VARCHAR(255) NOT NULL,
    appId     INT       DEFAULT 1,
    loginAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiresAt TIMESTAMP NULL DEFAULT NULL,
    isLocked  BOOLEAN   DEFAULT FALSE,
    UNIQUE KEY user_username (username) USING BTREE,
    UNIQUE KEY user_mobile (mobile) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_detail (
    uid        INT UNSIGNED PRIMARY KEY,
    nickname   VARCHAR(255) DEFAULT 'ANONYMOUS',
    avatar     VARCHAR(255),
    bio        VARCHAR(255),
    location   VARCHAR(255),
    website    VARCHAR(255),
    age        INTEGER      DEFAULT 0,
    gender     INT          DEFAULT 2
    COMMENT '2 unknown 0 male 1 female',
    role       INT          DEFAULT 1
    COMMENT '1 user 2 admin 3 super admin',
    registerAt TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
    COMMENT 'use first insert timestamp',
    updatedAt  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    COMMENT 'update as current time each time row updated',
    FOREIGN KEY (uid) REFERENCES user (uid)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;