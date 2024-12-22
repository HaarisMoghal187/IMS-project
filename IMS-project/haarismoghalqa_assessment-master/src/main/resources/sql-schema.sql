drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

create table if not exists `ims`.`item` (
	`id` int  auto_increment, 
    `item_name` varchar(40), 
    `price` DECIMAL(10, 2),
    PRIMARY KEY (`id`)
    );
    
    
create table if not exists `ims`.`orders` (
	`orderId` int  auto_increment NOT NULL, 
   `customerId` INT NOT NULL, 
	PRIMARY KEY (`orderId`),
    FOREIGN KEY (customerId) 
    REFERENCES ims.customers(id) on delete cascade on update cascade
    );
    
create table if not exists `ims`.`orderlines`(
	`orderlineId` int auto_increment NOT NULL, 
	`itemId` int, 
	`orderId` int, 
	`cost` DECIMAL(19,2), 
	`quantity` INT, 
    PRIMARY KEY (`orderlineId`),
	foreign key(itemId) references ims.item(id) on delete cascade on update cascade, 
	foreign key(orderId) References ims.orders(orderId)on delete cascade on update cascade
);