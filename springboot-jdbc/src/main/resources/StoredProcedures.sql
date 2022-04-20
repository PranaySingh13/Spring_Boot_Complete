/*---------------------------------------*/
USE `springbootjdbcdb`;
DROP procedure IF EXISTS `insertUser`;

DELIMITER $$
USE `springbootjdbcdb`$$
CREATE PROCEDURE insertUser(IN uid INTEGER(11),IN uname VARCHAR(255),IN uage INTEGER(11),IN ucity VARCHAR(255))
BEGIN
insert into USER values(uid, uname, uage, ucity);
END$$

DELIMITER ;

/*---------------------------------------*/
USE `springbootjdbcdb`;
DROP procedure IF EXISTS `getUser`;

DELIMITER $$
USE `springbootjdbcdb`$$
CREATE PROCEDURE getUser(IN no INTEGER(11),OUT uid INTEGER(11),OUT uname VARCHAR(255),OUT uage INTEGER(11),OUT ucity VARCHAR(255))
BEGIN
select id, name, age, city into uid, uname, uage, ucity from USER where id= no;
END$$

DELIMITER ;

/*---------------------------------------*/
USE `springbootjdbcdb`;
DROP procedure IF EXISTS `getAllUsers`;

DELIMITER $$
USE `springbootjdbcdb`$$
CREATE PROCEDURE getAllUsers(OUT uid INTEGER(11),OUT uname VARCHAR(255),OUT uage INTEGER(11),OUT ucity VARCHAR(255))
BEGIN
select id, name, age, city into uid, uname, uage, ucity from USER;
END$$

DELIMITER ;

/*---------------------------------------*/
USE `springbootjdbcdb`;
DROP procedure IF EXISTS `updateUser`;

DELIMITER $$
USE `springbootjdbcdb`$$
CREATE PROCEDURE updateUser(IN uid INTEGER(11),IN uname VARCHAR(255),IN uage INTEGER(11),IN ucity VARCHAR(255))
BEGIN
update USER set name = uname, age = uage, city = ucity where id = uid;
END$$

DELIMITER ;

/*---------------------------------------*/
USE `springbootjdbcdb`;
DROP procedure IF EXISTS `deleteUser`;

DELIMITER $$
USE `springbootjdbcdb`$$
CREATE PROCEDURE deleteUser(IN uid INTEGER(11))
BEGIN
delete from USER where id = uid;
END$$

DELIMITER ;

/*---------------------------------------*/
