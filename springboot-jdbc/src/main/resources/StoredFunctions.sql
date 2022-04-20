USE `springbootjdbcdb`;
DROP function IF EXISTS `get_user_name`;

DELIMITER $$
USE `springbootjdbcdb`$$
CREATE FUNCTION get_user_name(uid INTEGER)
RETURNS VARCHAR(45)
DETERMINISTIC
BEGIN
DECLARE response_name VARCHAR(45);
SELECT name into response_name FROM USER where id=uid; 
RETURN response_name;
END$$

DELIMITER ;
