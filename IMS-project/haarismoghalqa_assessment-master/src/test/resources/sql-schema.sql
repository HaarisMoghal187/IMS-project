drop table if exists orderlines;
drop table if exists orders;
drop table if exists item;
drop table if exists customers;




CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

create table if not exists `item` (
	`id` int  auto_increment, 
    `item_name` varchar(40), 
    `price` DOUBLE,
    PRIMARY KEY (`id`)
    );
    
    
create table if not exists `orders` (
	`orderId` int  auto_increment NOT NULL, 
   `customerId` INT NOT NULL, 
	PRIMARY KEY (`orderId`),
    FOREIGN KEY (customerId) 
    REFERENCES customers(id) on delete cascade
    );
    
create table if not exists `orderlines`(
	`orderlineId` int NOT NULL AUTO_INCREMENT,
 	`orderId` int DEFAULT NULL,
 	`itemId` int DEFAULT NULL,
  	`quantity` int DEFAULT NULL,
     PRIMARY KEY (`orderlineId`),
	foreign key(itemId) references item(id) on delete cascade,
	foreign key(orderId) references orders(OrderID) on delete cascade
);