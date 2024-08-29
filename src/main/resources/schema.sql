CREATE SCHEMA IF NOT EXISTS stage;
SET NAMES 'UTF8MB4';
SET TIME_ZONE ='+1:00';
USE stage;
CREATE TABLE  Users (
                        id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                        first_name   VARCHAR(50)NOT NULL ,
                        last_name    VARCHAR(50)NOT NULL ,
                        email        VARCHAR(100) NOT NULL,
                        password     VARCHAR(255) DEFAULT NULL,
                        address       VARCHAR(255) DEFAULT NULL,
                        phone        VARCHAR(30) DEFAULT NULL,
                        title        VARCHAR(50) DEFAULT NULL,
                        bio          VARCHAR(255) DEFAULT NULL,
                        enabled      BOOLEAN DEFAULT FALSE,
                        non_locked   BOOLEAN DEFAULT FALSE,
                        using_mfa    BOOLEAN DEFAULT FALSE,
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                        image_url    VARCHAR(255) DEFAULT 'https://as2.ftcdn.net/v2/jpg/02/84/29/83/1000_F_284298344_AqJ1RZXUufMnxcuVjCE5wVrhSa8Lft9g.jpg',
                        CONSTRAINT UQ_Users_Email UNIQUE (email)
);

DROP TABLE IF EXISTS Roles;
CREATE TABLE  Roles (
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name         VARCHAR(50)NOT NULL ,
    permission   VARCHAR(255)NOT NULL ,
    CONSTRAINT   UQ_Roles_name UNIQUE (name)
);

DROP TABLE IF EXISTS UserRoles;
CREATE TABLE  UserRoles
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    user_id      BIGINT UNSIGNED NOT NULL ,
    Role_id      BIGINT UNSIGNED NOT NULL ,
    FOREIGN KEY  (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY  (Role_id) REFERENCES Roles(id) ON DELETE RESTRICT ON UPDATE  CASCADE ,
    CONSTRAINT   UQ_UsersRoles_User_ID UNIQUE (user_id)
);

DROP TABLE IF EXISTS Events;
CREATE TABLE  Events (
    id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    type           VARCHAR(50)NOT NULL CHECK (type IN ('LOGIN_ATTEMPT' ,'LOGIN_ATTEMPT_FAILURE' , 'LOGIN_ATTEMPT_SUCCESS' , 'LOGIN_ATTEMPT_FAILURE' ,'PROFILE_UPDATE' ,'PROFILE_PICTURE_UPDATE', 'ROLE_UPDATE', 'ACCOUNT_SETTINGS_UPDATE','PASSWORD_UPDATE' 'MFA_UPDATE' )),
    description   VARCHAR(255)NOT NULL ,
    CONSTRAINT   UQ_Events_type UNIQUE (type)
);
DROP TABLE IF EXISTS UserEvents;
CREATE TABLE  UserEvents
(
    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    user_id    BIGINT UNSIGNED NOT NULL ,
    event_id   BIGINT UNSIGNED NOT NULL ,
    device     VARCHAR(100) DEFAULT NULL,
    ip_address VARCHAR(100) DEFAULT NULL,
    Created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY  (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY  (event_id) REFERENCES Events(id) ON DELETE RESTRICT ON UPDATE  CASCADE

);
DROP TABLE IF EXISTS AccountVerifications;
CREATE TABLE  AccountVerifications
(
    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    user_id    BIGINT UNSIGNED NOT NULL ,
    url   VARCHAR(255 )NOT NULL ,
    FOREIGN KEY  (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT   UQ_AccountVerification_url UNIQUE (url),
    CONSTRAINT   UQ_AccountVerification_User_id UNIQUE (user_id)

);
DROP TABLE IF EXISTS ResetPasswordVerifications;
CREATE TABLE  ResetPasswordVerifications
(
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    user_id         BIGINT UNSIGNED NOT NULL ,
    url             VARCHAR(255 )NOT NULL ,
    expiration_date DATETIME NOT NULL ,
    FOREIGN KEY  (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT   UQ_ResetPasswordVerifications_url UNIQUE (url),
    CONSTRAINT   UQ_ResetPasswordVerifications_id UNIQUE (user_id)

);
DROP TABLE IF EXISTS TwoFactorVerifications;
CREATE TABLE  TwoFactorVerifications
(
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    user_id         BIGINT UNSIGNED NOT NULL ,
    code            VARCHAR(10)NOT NULL ,
    expiration_date DATETIME NOT NULL ,
    FOREIGN KEY  (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT   UQ_TwoFactorVerifications_code UNIQUE (code),
    CONSTRAINT   UQ_TwoFactorVerifications_id UNIQUE (user_id)

);


