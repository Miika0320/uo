-- 5. (5%) Insert in your database data for each one of the 5 hotel chains. Each one of them has at
-- least 8 hotels, which belong to at least 3 categories. Two of the hotels at least should be in the
-- same area. Each hotel should have at least 5 rooms of different capacity.
INSERT INTO hotel_chain
VALUES
-- 5 hotel chains
-- chain_id, name, address, num_hotels, emails, phone_number
(1, 'Hilton', '123 Main St, Anytown, USA', 8, 'hilton@hotelchain.ca', '5551234567'),
(2, 'Marriott', '456 Oak St, Wherever, USA', 8, 'marriott@hotelchain.ca', '5552345678'),
(3, 'Hyatt', '768 Maple St, Chipmunkville, USA', 8, 'hyatt@hotelchain.ca', '5553456789'),
(4, 'Best Western', '321 Elm St, Starcity, Canada', 8, 'bestwester@hotelchain.ca', '5554567890'),
(5, 'Four Seasons', '111 Pine Rd, Pinecone, Canada', 8, 'fourseasons@hotelchain.ca', '555567891');

INSERT INTO employee
VALUES
-- employee_id, full_name, address, sin, role, manager_id
(1, 'John Smith', '545 Main St, Anytown, USA', '123456789', 'Janitor', 4),
(2, 'Mary Johnson', '213 ASDW St, Somewhere, USA', '231456878', 'Cleaner', 5),
(3, 'David Lee', '789 Oak St, Nowhere, USA', '516879543', 'Concierge', 6),
(4, 'Robert Kim', '246 Birch St, Anytown, USA', '328495847', 'Manager', Null),
(5, 'Jessica Chen', '135 Walnut St, Somewhere, USA', '292182304', 'Manager', Null),
(6, 'Daniel Brown', '864 Cherry St, Nowhere, USA', '223948503', 'Manager', Null);


INSERT INTO hotel
VALUES
-- hotel_id, name, address, rating, email, manager_id, phone, num_rooms, chain_id
(1, 'Grand Hyatt', '109 E 42nd St, New York, NY', 3, 'info@grandhyatt.com', 4, '212548654', 4, 1),
(2, 'The Ritz-Carlton', '600 Stockton St, San Francisco, CA', 2, 'info@ritzcarlton.com', 4, '845324841', 4, 2),
(3, 'Hilton Garden Inn', '1100 South Ave, Staten Island, NY', 1, 'info@hiltongardeninn.com', 4, '879216433', 4, 3),
(4, 'Courtyard by Marriott', '4355 Ashford Dunwoody Rd, Atlanta, GA', 3, 'info@courtyardbymarriott.com', 4, 5, '645987123', 4),
(5, 'Four Seasons Hotel', '120 E Delaware Pl, Chicago, IL', 2, 'info@fourseasons.com', 4, '695874123', 5, 5),
(6, 'Hyatt Regency', '1200 Louisiana St, Houston, TX', 3, 'info@hyattregency.com', 5, '987156213', 5, 1),
(7, 'The Peninsula', '108 E Superior St, Chicago, IL', 2, 'info@peninsula.com', 4, '321564789', 5, 2),
(8, 'W New York', '541 Lexington Ave, New York, NY', 1, 'info@wny.com', 5, '514236877', 5, 3),
(9, 'The St. Regis', '2 E 55th St, New York, NY', 4, 'info@stregis.com', 5, '879451132', 5, 4),
(10, 'Marriott Marquis', '901 Massachusetts Ave NW, Washington, DC', 5, 'info@marriottmarquis.com', 5, '245168459', 4, 5),
(11, 'Sheraton Grand', '1230 J St, Sacramento, CA', 3, 'info@sheratongrand.com', 5, '2155046970', 4, 1),
(12, 'Hilton', '2055 South Park Pl NW, Atlanta, GA', 2, 'info@hilton.com', 5, '121365487', 4, 2),
(13, 'Le Meridien', '717 Battery St, San Francisco, CA', 1, 'info@lemeridien.com', 6, '987451263', 4, 3),
(14, 'Westin', '2121 N Pearl St, Dallas, TX', 2, 'info@westin.com', 6, '302546987', 4, 4),
(15, 'Tres Grand Hyatt', '123 Main St, New York, NY', 3, 'info@grandhyatt.com', 6, '212454787', 6, 5),
(16, 'The Waltz Carlton', '456 Elm St, New York, NY', 2, 'info@wcarlton.com', 6, '212454787', 6, 1),
(17, 'Four Seasons Landscaping', '1011 Maple Ave, Los Angeles, CA', 5, 'info@4seasons.com', 6, '212454787', 6, 2),
(18, 'Hyatt Whats A Regency', '1617 Spruce St, Houston, TX', 5, 'info@whathyatt.com', 6, '212454787', 6, 3),
(19, 'The Petticoat', '1617 Spruce St, Dallas, TX', 4, 'info@petticoat.com', 4, '212454787', 6, 4),
(20, 'E New York', '2223 Maplewood Dr, Sacramento, CA', 4, 'info@eyork.com', 4, '212454787', 6, 5),
(21, 'The St. Legolas', '1213 Cedar St, Los Angeles, CA', 3, 'info@stlegolas.com', 4, '212454787', 1, 4),
(22, 'Marriott Marfett', '200 University Ave, Palo Alto, CA ', 3, 'info@mariottmarfett.com', 4, '212454787', 6, 2),
(23, 'Sheraton Petite', '350 5th Ave, New York, NY', 3, 'info@sheratonpetite.com', 4, '212454787', 6, 3),
(24, 'La Flour De Lis', '9500 Gilman Dr, La Jolla, CA', 3, 'info@laflourdelis.com', 4, '212454787', 4, 4),
(25, 'Eastin', '1 Times Square, New York, NY', 2, 'info@eastin.com', 5, '212454787', 4, 5),
(26, 'Northin', '1151 Oxford Road, San Marino, CA', 3, 'info@northin.com', 5, '212454787', 4, 1),
(27, 'Southin', '44 Charles St, Boston, MA', 3, 'info@southin.com', 5, '212454787', 4, 2),
(28, 'N New York', '1601 Vine St, Los Angeles, CA', 3, 'info@nyork.com', 5, '212454787', 4, 3),
(29, 'S New York', '2001 E Lohman Ave, Las Cruces, NM', 3, 'info@syork.com', 5, '212454787', 5, 4),
(30, 'Kim Kardashian Hostel', '6300 N River Rd, Rosemont, IL', 3, 'info@kimkardashian.com', 5, '212454787', 5, 1),
(31, 'The St. Petersbourgh', '1 N State St, Chicago, IL', 2, 'info@stpeter.com', 5, '212454787', 5, 1),
(32, 'Sesame Stree Hotel', '900 W Olympic Blvd, Los Angeles, CA', 4, 'info@sesamestr.com', 5, '212454787', 5, 2),
(33, 'Not A Hotel Hotel', '1600 Pennsylvania Ave NW, Washington, DC', 5, 'info@notahotel.com', 6, '212454787', 5, 3),
(34, 'Sheraton Tres Petite', '450 Serra Mall, Stanford, CA', 4, 'info@sheratonpetite.com', 6, '212454787', 4, 4),
(35, 'Some Snobby Hotel', '225 E 47th St, New York, NY', 3, 'info@snobbyhotel.com', 6, '212454787', 4, 5),
(36, 'Hotel McHotelface', '1 California St, San Francisco, CA', 2, 'info@hotelmchotelface.com', 6, '212454787', 4, 1),
(37, 'The Peter Pettigrew', '1001 Bissonnet St, Houston, TX', 1, 'info@pettigrew.com', 6, '212454787', 4, 2),
(38, 'Backyard by Marriott', '225 N Michigan Ave, Chicago', 2, 'info@backyardmariott.com', 6, '212454787', 4, 3),
(39, 'Hilton Broomcloset Inn', '701 S 4th St, Louisville, KY', 3, 'info@hiltonbroomcloset.com', 6, '212454787', 6, 4),
(40, 'Mid Hyatt', '1 Jets Dr, Florham Park, NJ ', 4, 'info@midhyatt.com', 6, '212454787', 6, 5);

INSERT INTO room
VALUES
--  , price, amenities, capacity, extendability, view_type, problems
(1, 1, 250, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(2, 1, 500, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(3, 1, 150, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(4, 1, 600, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(5, 1, 400, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(6, 2, 250, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(7, 2, 500, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(8, 2, 250, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(9, 2, 234, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(10, 2, 679, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 10
(11, 3, 312, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(12, 3, 466, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(13, 3, 1000, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(14, 3, 456, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(15, 3, 200, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(16, 4, 100, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(17, 4, 300, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(18, 4, 756, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(19, 4, 1020, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(20, 4, 320, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 20
(21, 5, 752, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(22, 5, 201, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(23, 5, 125, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(24, 5, 456, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(25, 5, 214, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(26, 6, 473, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(27, 6, 203, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(28, 6, 107, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(29, 6, 560, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(30, 6, 752, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 30
(31, 7, 102, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(32, 7, 620, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(33, 7, 785, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(34, 7, 302, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(35, 7, 120, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(36, 8, 456, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(37, 8, 236, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(38, 8, 201, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(39, 8, 778, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(40, 8, 320, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 40
(41, 9, 120, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(42, 9, 625, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(43, 9, 354, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(44, 9, 420, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(45, 9, 142, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(46, 10, 100, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(47, 10, 687, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(48, 10, 120, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(49, 10, 300, 'desk, lamp', '2 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(50, 10, 107, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 50
(51, 11, 3124, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(52, 11, 124, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(53, 11, 112, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(54, 11, 100, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(55, 11, 687, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(56, 12, 120, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(57, 12, 300, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(58, 12, 107, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(59, 12, 250, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(60, 12, 456, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 60
(61, 13, 236, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(62, 13, 201, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(63, 13, 778, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(64, 13, 320, 'coffee maker, safe', '2 guests', 'river view', 'extendable', 'loose doorknob'),
(65, 13, 250, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(66, 14, 125, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(67, 14, 456, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(68, 14, 214, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(69, 14, 473, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(70, 14, 203, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 70
(71, 15, 250, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(72, 15, 214, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(73, 15, 473, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(74, 15, 203, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(75, 15, 107, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(76, 16, 560, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(77, 16, 752, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(78, 16, 102, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(79, 16, 620, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(80, 16, 785, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 80
(81, 17, 302, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(82, 17, 214, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(83, 17, 473, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(84, 17, 203, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(85, 17, 107, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(86, 18, 560, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(87, 18, 752, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(88, 18, 102, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(89, 18, 620, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(90, 18, 785, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 90
(91, 19, 302, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(92, 19, 232, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(93, 19, 432, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(94, 19, 123, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(95, 19, 634, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(96, 20, 234, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(97, 20, 643, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(98, 20, 854, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(99, 20, 876, 'desk, lamp', '2 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(100, 20, 473, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 100
(101, 21, 203, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(102, 21, 107, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(103, 21, 560, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(104, 21, 752, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(105, 21, 102, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(106, 22, 620, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(107, 22, 785, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(108, 22, 302, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(109, 22, 120, 'desk, lamp', '2 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(110, 22, 300, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 110
(111, 23, 107, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(112, 23, 250, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(113, 23, 456, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(114, 23, 236, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(115, 23, 201, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(116, 24, 778, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(117, 24, 320, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(118, 24, 250, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(119, 24, 120, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(120, 24, 300, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 120
(121, 25, 107, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(122, 25, 250, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(123, 25, 456, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(124, 25, 236, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(125, 25, 201, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(126, 26, 778, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(127, 26, 320, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(128, 26, 250, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(129, 26, 250, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(130, 26, 250, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 130
(131, 27, 120, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(132, 27, 300, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(133, 27, 107, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(134, 27, 250, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(135, 27, 456, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(136, 28, 236, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(137, 28, 201, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(138, 28, 778, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(139, 28, 320, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(140, 28, 250, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 140
(141, 29, 120, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(142, 29, 300, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(143, 29, 107, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(144, 29, 250, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(145, 29, 456, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(146, 30, 236, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(147, 30, 201, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(148, 30, 778, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(149, 30, 320, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(150, 30, 250, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 150
(151, 31, 250, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(152, 31, 120, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(153, 31, 300, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(154, 31, 107, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(155, 31, 250, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(156, 32, 456, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(157, 32, 236, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(158, 32, 201, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(159, 32, 778, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(160, 32, 320, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 160
(161, 33, 250, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(162, 33, 250, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(163, 33, 250, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(164, 33, 120, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(165, 33, 300, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(166, 34, 107, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(167, 34, 250, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(168, 34, 456, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(169, 34, 236, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(170, 34, 201, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 170
(171, 35, 778, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(172, 35, 320, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(173, 35, 250, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(174, 35, 250, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(175, 35, 250, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(176, 36, 120, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(177, 36, 300, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(178, 36, 107, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(179, 36, 250, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(180, 36, 456, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 180
(181, 37, 236, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(182, 37, 201, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(183, 37, 778, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(184, 37, 320, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(185, 37, 250, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(186, 38, 250, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(187, 38, 120, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(188, 38, 300, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(189, 38, 107, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(190, 38, 250, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead'), -- 190
(191, 39, 456, 'WiFi, TV, minibar', '2 guests', 'city view', 'extendable', 'none'),
(192, 39, 236, 'AC, refrigerator', '4 guests', 'ocean view', 'not extendable', 'leaky faucet'),
(193, 39, 201, 'cable TV, iron', '1 guest', 'mountain view', 'extendable', 'no hot water'),
(194, 39, 778, 'coffee maker, safe', '5 guests', 'river view', 'extendable', 'loose doorknob'),
(195, 39, 320, 'free breakfast, hair dryer', '3 guests', 'garden view', 'not extendable', 'clogged drain'),
(196, 40, 323, 'shower, bathtub', '2 guests', 'pool view', 'extendable', 'noisy air conditioning'),
(197, 40, 113, 'kitchenette, microwave', '4 guests', 'beach view', 'not extendable', 'broken lamp'),
(198, 40, 533, 'telephone, alarm clock', '1 guest', 'park view', 'not extendable', 'stained carpet'),
(199, 40, 452, 'desk, lamp', '5 guests', 'city view', 'extendable', 'slow Wi-Fi'),
(200, 40, 677, 'sofa, armchair', '3 guests', 'mountain view', 'extendable', 'broken showerhead') -- 200

INSERT INTO customer
VALUES
(1, 'Karen Brown', '321 Pine St, Anytown, USA', 136548795, '1988-12-01'),
(2, 'Mike Garcia', '654 Maple St, Somewhere, USA', 516487921, '1995-06-17'),
(3, 'Emily Wilson', '987 Cedar St, Nowhere, USA', 213546874, '1982-11-07');

INSERT INTO booking
VALUES
(1, 1, 1, 1, '2022-03-15', '2023-03-17', 1),
(2, 6, 2, 2, '2023-01-20', '2023-01-27', 2),
(3, 38, 8, 2, '2022-02-23', '2022-02-27', Null);

INSERT INTO renting
VALUES
(1, 1, 1, 1, '2022-03-15', '2023-03-17', 500, 1),
(2, 6, 2, 2, '2023-01-20', '2023-01-27', 1750, 2);

INSERT INTO archive
VALUES
(1, 1, 1, 41, 9, 1, '2020-06-07', '2020-06-09'),
(2, 2, 2, 78, 16, 2, '2019-03-01', '2019-04-01');
