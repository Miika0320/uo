-- 6. (10%) Create the necessary SQL modifications (use queries and triggers): Your database should allow insert, delete and update operations of data in your database according to the referential integrity constraints that you have defined. Give the SQL code for at least 4 queries and 2
-- triggers of your choice in your report.

-- Sample sql 1. sample sql for querying ocean view 1 guest rooms in hotels in new york

SELECT *
FROM hotel h
INNER JOIN room r ON h.hotel_id = r.hotel_id
WHERE h.address LIKE '%New York%'
AND r.view_type = 'ocean view'
AND r.capacity = '1 guest';

-- Sample sql 2. sql code for a hotel database, querying all rooms with 'none' problems and hotel rating of 3 in Washington, DC

SELECT h.name AS hotel_name, r.amenities, r.capacity, r.view_type, r.extendibility
FROM hotel h
INNER JOIN room r ON h.hotel_id = r.hotel_id
WHERE h.address LIKE '%Washington, DC%'
AND h.rating = 3
AND r.problems = 'none';

-- Sample sql 3. sql code for customer name and registration_date for all bookings of hotels of the same hotel_chain ex. Hilton

SELECT c.name, b.date_in
FROM customer c
JOIN booking b ON c.customer_id = b.customer_id
WHERE b.hotel_id IN (
  SELECT h.hotel_id
  FROM hotel h
  WHERE h.chain_id = 1
)

-- Sample sql 4. sql code for getting information for Tres Grand Hyatt hotel bookings for customers before 2023-01-01

SELECT c.name AS customer_name, b.date_in, b.date_out, h.name AS hotel_name
FROM customer c
JOIN booking b ON c.customer_id = b.customer_id
JOIN hotel h ON b.hotel_id = h.hotel_id
WHERE b.date_in < '2023-01-01'
AND h.name = 'Tres Grand Hyatt';


-- Sample trigger 1. once a booking is deleted, transfer the deleted tuple to archive
-- same can be applied for renting but with a null booking id
-- trigger is applied on booking table, and the intention is that once a delete operation has been called on booking
-- the row that is deleted from booking is meant to be inserted into archive
-- in this way the record is 'archived'
-- to be automated further it may be possible to archive bookings once 

DROP TRIGGER archive_trigger ON "booking" ;

CREATE OR REPLACE FUNCTION booking_fnc()
  RETURNS trigger AS
$$
BEGIN
    INSERT INTO "archive" ("booking_id","room_id","hotel_id","customer_id","date_in","date_out","rental_id")
    VALUES(OLD."booking_id", OLD."rental_id", OLD."room_id", OLD."hotel_id", OLD."customer_id", OLD."date_in", OLD."date_out");
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER archive_trigger
  BEFORE DELETE
  ON "booking"
  FOR EACH ROW
  EXECUTE PROCEDURE booking_fnc();


-- Sample trigger 2. protect the integrity of archive, make sure no archive can be altered or dropped
-- in order to serve its purpose as archive

DROP TRIGGER safety_trigger ON "archive" ;

CREATE OR REPLACE FUNCTION safety_func()
RETURNS TRIGGER AS 
$$
BEGIN
	RAISE EXCEPTION 'You cannot delete archived bookings or rentings';
END;
$$ language 'plpgsql';

CREATE TRIGGER safety_trigger
BEFORE DELETE
ON archive
FOR EACH ROW EXECUTE PROCEDURE safety_func('history');
END;