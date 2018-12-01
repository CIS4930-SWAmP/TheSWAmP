INSERT INTO users (username, password, fname,lname,phone, email, isAdmin) VALUES ("alberta1", "gogators", "Alberta", "Gator", "555-666-1234", "alberta1@gmail.com", 0),
("albert3", "gogators", "Albert", "Gator", "555-666-1234", "albert3@gmail.com", 0),
("Admin", "buynsell123", "admin", "admin", "555-555-5555", "admin@admin.com", 1);
SELECT * FROM users;

INSERT INTO events(title, eventDate, description)
VALUES
("Gator Growl", "2019-11-03", "Come show your school spirit at Gator Growl ft. Lil' Baby and Cardi B."),
("Big Orange", "2019-03-21", "UF's annual comedy show/concert");
SELECT * FROM events; 

INSERT INTO tickets(price, sellerId, buyerId, availability, eventId)
VALUES
(25, 1, 2, 'Monday 3pm',1),
(15, 2, 1, 'Friday 8am',2);
SELECT * FROM tickets;
