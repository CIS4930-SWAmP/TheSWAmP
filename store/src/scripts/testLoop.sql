DELIMITER //
CREATE PROCEDURE getLastName
(IN fname CHAR(20))
BEGIN
  SELECT lname FROM customers
  WHERE Continent = con;
END //
DELIMITER ;
CALL country_hos('Europe');
