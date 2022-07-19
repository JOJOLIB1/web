USE dept;
DELIMITER #
CREATE PROCEDURE get_info (IN sum INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    b:LOOP
        INSERT INTO dept(dept_no,dept_name,dept_loc) VALUES (10 * i,i,i);
        SET i = i + 1;
        IF i = sum
            THEN LEAVE b ;
        END IF;
    end loop;
END #
DELIMITER ;
CALL get_info(20);
DROP PROCEDURE get_info;