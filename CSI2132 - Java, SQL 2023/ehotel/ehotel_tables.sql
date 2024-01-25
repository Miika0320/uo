-- 4. (10%) Implement the database according to your relational database schema and the constraints
-- that you have defined.
CREATE TABLE hotel_chain (
    chain_id int not null,
    name varchar(255) not null,
    address varchar(255),
    num_hotels int not null,
    email varchar(255) not null,
    -- phone_number int not null, need to be varchar, phone num can't be int
    phone_number varchar(10),
    PRIMARY KEY (chain_id)
);
CREATE TABLE employee (
    employee_id int not null,
    full_name varchar(255) not null,
    address varchar(255),
    -- sin int not null,
    sin char(9), --assuming canadian
    role varchar(255) not null,
    manager_id int,
    PRIMARY KEY (employee_id)
);
CREATE TABLE hotel (
    hotel_id int not null,
    name varchar(255) not null,
    address varchar(255),
    rating int not null,
    email varchar(255) not null,
    manager_id int not null,
    -- phone int not null,
    phone varchar(10),
    num_rooms int not null,
    chain_id int not null,
    PRIMARY KEY (hotel_id),
    FOREIGN KEY (chain_id) REFERENCES hotel_chain(chain_id),
    FOREIGN KEY (manager_id) REFERENCES employee(employee_id)
);
CREATE TABLE room (
    room_id int not null,
    hotel_id int not null,
    price numeric(10,2) not null,
    amenities varchar(255),
    capacity varchar(255) not null,
    extendibility varchar(255) not null, 
    view_type varchar(255) not null, 
    problems varchar(255) not null,
    PRIMARY KEY (room_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);
CREATE TABLE customer (
    customer_id int not null,
    name varchar(255) not null,
    address varchar(255),
    -- sin int not null check (sin between 0 and 999999999),
    sin char(9),
    registration_date date not null,
    PRIMARY KEY (customer_id)
);
CREATE TABLE booking (
    booking_id int not null,
    room_id int not null,
    hotel_id int not null,
    customer_id int not null,
    date_in date not null,
    date_out date not null,
    rental_id int,
    PRIMARY KEY (booking_id),
    FOREIGN KEY (room_id) REFERENCES room(room_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);
CREATE TABLE renting (
    rental_id int not null,
    room_id int not null,
    hotel_id int not null,
    customer_id int not null,
    date_in date not null,
    date_out date not null,
    payment numeric(10,2) not null,
    booking_id int,
    PRIMARY KEY (rental_id),
    FOREIGN KEY (room_id) REFERENCES Room(room_id),
    FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);
CREATE TABLE archive (
    archive_id int not null,
    booking_id int,
    rental_id int,
    room_id int not null,
    hotel_id int not null,
    customer_id int not null,
    date_in date not null,
    date_out date not null,
    PRIMARY KEY (archive_id),
    FOREIGN KEY (room_id) REFERENCES room(room_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);
