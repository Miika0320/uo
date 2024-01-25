-- 7. (10%) Implement at least three indexes on the relations of your database

-- Booking date indexes, can be applied to renting and archive as well
/* explain what type of queries and data updates you are expecting onyour database 
and how these indexes are useful to accelerate querying of the database:

A sample query that could use the following index is:
SELECT * FROM booking WHERE check_in_date >= '2020-01-01';

This is useful for accessing the database if the number of bookings are large, as
it would be in a real hotel booking system. This can be used by an employee to quickly find
bookings after a certain date to further refine searches, such as for the booking
by a specific customer to confirm them.

A similar thing can be done for checkout dates.

*/

CREATE INDEX check_in_date_index ON booking(date_in);


-- Rating of hotels
/* explain what type of queries and data updates you are expecting onyour database 
and how these indexes are useful to accelerate querying of the database:

A sample query that could use the following index is:
SELECT * FROM hotel WHERE rating >= 3
AND city like '%New York%';

An index of hotel ratings is useful for quickly filtering hotels that people want to be in,
for potential customers using the website. It is common sense that this type of filtering
will be used a lot 

*/

CREATE INDEX rating_index ON hotel(rating);


-- City of hotels
/* explain what type of queries and data updates you are expecting on your database 
and how these indexes are useful to accelerate querying of the database:

A sample query that could use the following index is:
SELECT * FROM hotel WHERE num_rooms = '3 guests';

An index of room capcity is useful for quickly filtering hotel rooms by capacity,
by making this an index it will allow fast searching when there are tons of rooms

*/

CREATE INDEX num_rooms_index ON room(capacity);
