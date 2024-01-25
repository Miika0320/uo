-- 9. (10%) The user should be able to see also two specific Views. 

-- View 1: the first view is the number of available rooms per area. 
/* to implement this on our database, available rooms can be extracted from 
room_ids that are not present in either booking and renting tables
assuming expired bookings and rentings are kicked to archive
*/

CREATE VIEW available_rooms_by_address AS
SELECT r.room_id as available_rooms_id, r.amenities, r.capacity, r.view_type, r.extendibility, r.problems, h.name, h.address as hotel_address, h.rating
FROM room r
JOIN hotel h ON r.hotel_id = h.hotel_id
WHERE r.room_id NOT IN (
  SELECT room_id FROM booking
  UNION
  SELECT room_id FROM renting
);

SELECT available_rooms_id
FROM available_rooms_by_address
WHERE hotel_address like '%Some area in the address%';



-- View 2: the second view is the capacity of all the rooms of a specific hotel.


CREATE VIEW all_room_capacities_by_hotel AS
SELECT r.room_id as room_id, r.capacity as room_capacity, h.name as hotel_name
FROM room r
LEFT JOIN hotel h ON r.hotel_id = h.hotel_id;

SELECT room_id, room_capacity
FROM all_room_capacities_by_hotel
WHERE hotel_name = 'Some specific hotel';
