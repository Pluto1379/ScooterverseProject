DROP DATABASE IF EXISTS Scooter_Rental;
CREATE DATABASE Scooter_Rental;
USE Scooter_Rental;

drop table if exists customer;
drop table if exists inventory;
drop table if exists scooter;
drop table if exists rental;
drop table if exists fee;
drop table if exists rentalLog;

create table customer (
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    licenseNumber varchar(50) not null,
    username varchar(50) not null,
    password varchar(50) not null,
    admin boolean default false,
    primary key(username)
);

create table fee(
    scooterType varchar(50) not null,
    price float not null,
    primary key(scooterType)
);

create table scooter(
    scooterID int auto_increment not null,
    scooterType char(50),
    brand char(50) not null,
    model varchar(50) not null,
    primary key(scooterID),
    foreign key(scooterType)
        references fee(scooterType)
        ON DELETE SET NULL
);

create table inventory (
    inventoryID varchar(25) not null,
    scooterID int not null,
    foreign key(scooterID)
            references scooter(scooterID)
            ON DELETE CASCADE
);

create table rental(
    username varchar(50),
    scooterID int,
    returnDate date not null,
    scooterReturned boolean default false,
    revisedOn timestamp not null default current_timestamp on update current_timestamp,
    primary key(ScooterID),
    foreign key(username)
      references customer(username)
      ON DELETE CASCADE,
    foreign key(scooterID)
        references scooter(scooterID)
        ON delete cascade
);


create table rentalLog(
    username varchar(50),
    scooterID int not null,
    returnDate date not null,
    scooterReturned boolean default false,
    revisedOn timestamp not null
);

drop procedure if exists spGetCustomerScooterAndPrice;
create procedure spGetCustomerScooterAndPrice
(UN varchar(50))
select * 
from fee
where scooterType in
    (select scooterType from scooter where scooterID in
        (select scooterID from rental where username = UN));
        
DROP PROCEDURE
IF EXISTS log;
delimiter //
CREATE PROCEDURE
log(IN revisedOn DATE)
begin 
insert into rentalLog
(select * from rental where rental.revisedOn <= revisedOn);
delete from rental where rental.revisedOn <= revisedOn;
end //
delimiter ;


DROP TRIGGER IF EXISTS DeleteScooter;
delimiter //
CREATE TRIGGER DeleteScooter
AFTER DELETE ON scooter
FOR EACH ROW
BEGIN
delete from inventory where ScooterID = Old.ScooterID;
END//
delimiter ;

DROP TRIGGER IF EXISTS AddScooter;
delimiter //
CREATE TRIGGER AddScooter
AFTER INSERT ON scooter
FOR EACH ROW
BEGIN
INSERT into inventory values ('100', New.scooterID);
END//
delimiter ;


insert into customer VALUES('Alex', 'Brown', 'A1112223', 'alexBrown', 'alex123', false);
INSERT INTO customer VALUES ('Ben', 'Smith', 'B2223334', 'benSmith', 'ben123', false);
INSERT INTO customer VALUES ('Kate', 'Johnson', 'C3334445', 'kateJohnson', 'kate123', false);

/* new inserts */
insert into customer VALUES('Daniel', 'Miller', 'D4445556', 'danielMiller', 'daniel123', true);
INSERT INTO customer VALUES ('David', 'Jones', 'E5556667', 'davidJones', 'david123', false);
INSERT INTO customer VALUES ('Henry', 'Garcia', 'F6667778', 'henryGarcia', 'henry123', false);
insert into customer VALUES('Pedro', 'Martin', 'G7778889', 'pedroMartin', 'pedro123', true);
INSERT INTO customer VALUES ('Hanna', 'Williams', 'H8889990', 'hannaWilliams', 'hanna123', false);
INSERT INTO customer VALUES ('Emma', 'Wilson', 'I9990001', 'emmaWilson', 'emma123', false);


INSERT INTO fee VALUES ('ks', 50);
INSERT INTO fee VALUES ('el', 70);
INSERT INTO fee VALUES ('hb', 100);
INSERT INTO fee VALUES ('fb', 120);
INSERT INTO fee VALUES ('mp', 150);

INSERT INTO scooter VALUES (101, 'ks', 'Razor', 'r77x'); /*Kickscooter*/
INSERT INTO scooter VALUES (102, 'el', 'Razor', 'r125x'); /*Electric*/
INSERT INTO scooter VALUES (103, 'hb', 'Gotrax', 'go88');
INSERT INTO scooter VALUES (104, 'fb', 'Hilboy', 'h97');
INSERT INTO scooter VALUES (105, 'mp', 'Micro', 'm450'); /*Moped*/

/* new inserts */
INSERT INTO scooter (scooterType, brand, model)  VALUES (105, 'mp', 'Micro', 'm450'); /*Moped*/
INSERT INTO scooter (scooterType, brand, model)  VALUES (101, 'ks', 'Razor', 'r77x'); /*Kickscooter*/
INSERT INTO scooter (scooterType, brand, model)  VALUES (106, 'fb', 'Aeon', 'a43');
INSERT INTO scooter (scooterType, brand, model)  VALUES (102, 'el', 'Razor', 'r125x'); /*Electric*/
INSERT INTO scooter (scooterType, brand, model)  VALUES (107, 'el', 'Forza', 'f23z'); /*Electric*/
INSERT INTO scooter (scooterType, brand, model)  VALUES (108, 'el', 'Lifan', 'l3400'); /*Electric*/
INSERT INTO scooter (scooterType, brand, model)  VALUES (109, 'hb', 'Turbo', 't2500');
INSERT INTO scooter (scooterType, brand, model)  VALUES (110, 'mp', 'Vespa', 'v7800');
INSERT INTO scooter (scooterType, brand, model)  VALUES (111, 'ks', 'Xootr', 'x550'); /*Kickscooter*/

INSERT INTO rental VALUES ('alexBrown', 101, '2023-12-31', false, CURRENT_DATE);
INSERT INTO rental VALUES ('benSmith', 102, '2023-12-31', false, CURRENT_DATE);
INSERT INTO rental VALUES ('kateJohnson', 105, '2023-12-31', false, CURRENT_DATE);

/* new inserts */
INSERT INTO rental VALUES ('davidJones', 106, '2023-12-10', false, CURRENT_DATE);
INSERT INTO rental VALUES ('danielMiller', 107, '2023-12-23', false, CURRENT_DATE);
INSERT INTO rental VALUES ('hannaWilliams', 109, '2023-12-16', false, CURRENT_DATE);
INSERT INTO rental VALUES ('emmaWilson', 108, '2023-12-5', false, CURRENT_DATE);
INSERT INTO rental VALUES ('pedroMartin', 110, '2023-12-28', false, CURRENT_DATE);
INSERT INTO rental VALUES ('henryGarcia', 111, '2023-12-19', false, CURRENT_DATE);

INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 101);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 102);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 103);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 104);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 105);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 106);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 107);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 108);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 109);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 110);
INSERT INTO inventory (inventoryID, scooterID) VALUES ('100', 111);

INSERT INTO rentalLog (username, scooterID, returnDate, scooterReturned, revisedOn)
VALUES ('alexBrown', 101, '2023-12-31', false, CURRENT_TIMESTAMP);

INSERT INTO rentalLog (username, scooterID, returnDate, scooterReturned, revisedOn)
VALUES ('benSmith', 102, '2023-12-31', false, CURRENT_TIMESTAMP);

INSERT INTO rentalLog (username, scooterID, returnDate, scooterReturned, revisedOn)
VALUES ('kateJohnson', 105, '2023-12-31', false, CURRENT_TIMESTAMP);

/* new inserts */
INSERT INTO rentalLog (username, scooterID, returnDate, scooterReturned, revisedOn)
VALUES ('davidJones', 106, '2023-12-10', false, CURRENT_TIMESTAMP);

INSERT INTO rentalLog (username, scooterID, returnDate, scooterReturned, revisedOn)
VALUES ('danielMiller', 107, '2023-12-23', false, CURRENT_TIMESTAMP);

INSERT INTO rentalLog (username, scooterID, returnDate, scooterReturned, revisedOn)
VALUES ('hannaWilliams', 109, '2023-12-16', false, CURRENT_TIMESTAMP);

INSERT INTO rentalLog (username, scooterID, returnDate, scooterReturned, revisedOn)
VALUES ('emmaWilson', 108, '2023-12-5', false, CURRENT_TIMESTAMP);

INSERT INTO rentalLog (username, scooterID, returnDate, scooterReturned, revisedOn)
VALUES ('pedroMartin', 110, '2023-12-28', false, CURRENT_TIMESTAMP);

INSERT INTO rentalLog (username, scooterID, returnDate, scooterReturned, revisedOn)
VALUES ('henryGarcia', 111, '2023-12-19', false, CURRENT_TIMESTAMP);
