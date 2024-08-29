CREATE SCHEMA IF NOT EXISTS stage;
SET NAMES 'UTF8MB4';
SET TIME_ZONE ='+1:00';
USE stage;

DROP TABLE IF EXISTS UserRoles;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Users;


CREATE TABLE  Users (
                        id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                        first_name   VARCHAR(50)NOT NULL ,
                        last_name    VARCHAR(50)NOT NULL ,
                        email        VARCHAR(100) NOT NULL,
                        password     VARCHAR(255) DEFAULT NULL,
                        address       VARCHAR(255) DEFAULT NULL,
                        phone        VARCHAR(30) DEFAULT NULL,
                        bio          VARCHAR(255) DEFAULT NULL,
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                        image_url    VARCHAR(255) DEFAULT 'https://as2.ftcdn.net/v2/jpg/02/84/29/83/1000_F_284298344_AqJ1RZXUufMnxcuVjCE5wVrhSa8Lft9g.jpg',
                        CONSTRAINT UQ_Users_Email UNIQUE (email)
);


CREATE TABLE  Roles (
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name         VARCHAR(50)NOT NULL ,
    permission   VARCHAR(255)NOT NULL ,
    CONSTRAINT   UQ_Roles_name UNIQUE (name)
);


CREATE TABLE  UserRoles
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    user_id      BIGINT UNSIGNED NOT NULL ,
    Role_id      BIGINT UNSIGNED NOT NULL ,
    FOREIGN KEY  (user_id) REFERENCES Users(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY  (Role_id) REFERENCES Roles(id) ON DELETE RESTRICT ON UPDATE  CASCADE ,
    CONSTRAINT   UQ_UsersRoles_User_ID UNIQUE (user_id)
);



