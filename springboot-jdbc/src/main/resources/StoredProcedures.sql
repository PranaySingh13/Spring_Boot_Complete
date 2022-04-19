/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `springbootjdbcdb.insertUser`;

DELIMITER $$
CREATE PROCEDURE springbootjdbcdb.insertUser(IN uid INTEGER(11),IN uname VARCHAR(255),IN uage INTEGER(11),IN ucity VARCHAR(255))
BEGIN
insert into USER values(uid, uname, uage, ucity);
END $$
DELIMITER ;

CALL springbootjdbcdb.insertUser(@uid,@uname,@uage,@ucity);

/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `springbootjdbcdb.getUser`;

DELIMITER $$
CREATE PROCEDURE springbootjdbcdb.getUser(IN no INTEGER(11),OUT uid INTEGER(11),OUT uname VARCHAR(255),OUT uage INTEGER(11),OUT ucity VARCHAR(255))
BEGIN
select id, name, age, city into uid, uname, uage, ucity from USER where id= no;
END $$
DELIMITER ;

/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `springbootjdbcdb.getAllUsers`;

DELIMITER $$
CREATE PROCEDURE springbootjdbcdb.getAllUsers(OUT uid INTEGER(11),OUT uname VARCHAR(255),OUT uage INTEGER(11),OUT ucity VARCHAR(255))
BEGIN
select id, name, age, city into uid, uname, uage, ucity from USER;
END $$
DELIMITER ;

/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `springbootjdbcdb.updateUser`;

DELIMITER $$
CREATE PROCEDURE springbootjdbcdb.updateUser(IN uid INTEGER(11),IN uname VARCHAR(255),IN uage INTEGER(11),IN ucity VARCHAR(255))
BEGIN
update USER set name = uname, age = uage, city = ucity where id = uid;
END $$
DELIMITER ;

/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `springbootjdbcdb.deleteUser`;

DELIMITER $$
CREATE PROCEDURE springbootjdbcdb.deleteUser(IN uid INTEGER(11))
BEGIN
delete from USER where id = uid;
END $$
DELIMITER ;

/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `getSalary`;

DELIMITER $$
CREATE PROCEDURE getSalary(IN eid INTEGER(11),OUT esal FLOAT(10,2))
BEGIN
select EMPSALARY into esal from EMPLOYEE where EMPID = eid;
END $$
DELIMITER ;