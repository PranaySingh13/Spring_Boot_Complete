DROP PROCEDURE IF EXISTS `getUser`;

DELIMITER $$
CREATE PROCEDURE getUser(IN id INTEGER(11),OUT name VARCHAR(11),OUT age INTEGER(11),OUT city VARCHAR(45))
BEGIN
select id, name, age, city into id, name, age, city from USER where id= id;
END $$
DELIMITER ;