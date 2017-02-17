DROP DATABASE IF EXISTS test;

CREATE DATABASE test;

USE test;

CREATE TABLE `user`
(
	`id` INT(8) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(25) NOT NULL DEFAULT '0',
	`age` INT NOT NULL DEFAULT '0',
	`isAdmin` BIT(1) NOT NULL DEFAULT b'0',
	`createdDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci';


INSERT INTO `user` (`id`,`name`,`age`,`isAdmin`,`createdDate`) VALUES (1,"Dima",60,0,"2017-02-16 10:50:27"),(2,"Pavel",31,1,"2017-02-16 10:52:24"),(3,"Marina",30,0,"2017-02-16 10:53:19"),(4,"Polina",6,0,"2017-02-16 10:55:42"),(5,"Arina",3,0,"2017-02-16 10:59:21"),(6,"Ariana",30,0,"2017-02-16 10:59:23"),(7,"Mariana",39,0,"2017-02-16 10:59:28"),(8,"Karina",23,0,"2017-02-16 10:59:32"),(9,"Larina",90,0,"2017-02-16 10:59:42");