SHOW TABLES;
DROP TABLE IF EXISTS users, events, tickets;
SHOW TABLES;

CREATE TABLE users(
	id SERIAL UNIQUE,
	username VARCHAR(255) UNIQUE,
	password VARCHAR(255),
	phone VARCHAR(255),
	lname VARCHAR(255),
	fname VARCHAR(255),
	email VARCHAR(255),
	isAdmin BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE events(
	eventId SERIAL UNIQUE,
	title VARCHAR(255),
	eventDate DATE,
	description VARCHAR(255)
);

CREATE TABLE tickets(
	ticketId SERIAL UNIQUE,
	sellerId INT,
	eventId INT,
	purchased BOOLEAN DEFAULT false,
	price DECIMAL(8, 2),
	buyerId INT,
	availability VARCHAR(255)
);

SHOW TABLES;
