DROP TABLE IF EXISTS sys_user;

CREATE TABLE sys_user
(
	id int NOT NULL,
	name VARCHAR(30) NULL DEFAULT NULL ,
	age INT NULL DEFAULT NULL,
	email varchar(50) NULL DEFAULT NULL,
    user_status int NOT NULL default  0,
    is_delete int NOT NULL default  0,
	PRIMARY KEY (id)
);