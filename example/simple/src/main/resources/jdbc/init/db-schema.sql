CREATE TABLE User
(
	ID int  NOT NULL PRIMARY KEY,
	FIRST_NAME varchar(50),
	LAST_NAME varchar(50),
	Birthday DATE,
	ADDRESS varchar(255),
	RANK INT ,
	Score DOUBLE
);