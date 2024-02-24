DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `planned_payment`;
DROP TABLE IF EXISTS `transaction`;
DROP TABLE IF EXISTS `account`;

CREATE TABLE `user`(
    `user_id` VARCHAR(60) NOT NULL,
    `name` VARCHAR(60) NOT NULL,
    `last_name` VARCHAR(60) NOT NULL,
    `username` VARCHAR(60) NOT NULL,
    `email` VARCHAR(254) NOT NULL,
    `phone` VARCHAR(20),
    `password` VARCHAR(60) NOT NULL,
    PRIMARY KEY(`user_id`)
);

CREATE TABLE `category`(
    `category_id` VARCHAR(60) NOT NULL,
    `name` VARCHAR(60) NOT NULL,
    `color` VARCHAR(60) NOT NULL,
    PRIMARY KEY(`category_id`)
);

CREATE TABLE `account`(
    `account_id` VARCHAR(60) NOT NULL,
    `alias` VARCHAR(60) NOT NULL,
    `balance` DECIMAL(10,2) NOT NULL,
    `created_on` TIMESTAMP NOT NULL,
    `updated_on` TIMESTAMP NOT NULL,
    `user_id` VARCHAR(60) NOT NULL,
    PRIMARY KEY(`account_id`),
    FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`)
);

CREATE TABLE `planned_payment`(
    `planned_payment_id` VARCHAR(60) NOT NULL,
    `title` VARCHAR(60) NOT NULL,
    `amount` VARCHAR(60) NOT NULL,
    `start_date` TIMESTAMP NOT NULL,
    `end_date` TIMESTAMP NOT NULL,
    `interval` ENUM('DAY','WEEK','MONTH','YEAR') NOT NULL,
    `category_id` VARCHAR(60) NOT NULL,
    `account_id` VARCHAR(60) NOT NULL,
    PRIMARY KEY(`planned_payment_id`),
    FOREIGN KEY(`category_id`) REFERENCES `category`(`category_id`),
    FOREIGN KEY(`account_id`) REFERENCES `account`(`account_id`)
);

CREATE TABLE `transaction`(
    `transaction_id` VARCHAR(60) NOT NULL,
    `title` VARCHAR(60) NOT NULL,
    `type` ENUM('EXPENSE','INCOME') NOT NULL,
    `date` TIMESTAMP NOT NULL,
    `amount` DECIMAL(10,2) NOT NULL,
    `account_id` VARCHAR(60) NOT NULL,
    `category_id` VARCHAR(60) NOT NULL,
    PRIMARY KEY(`transaction_id`),
    FOREIGN KEY(`account_id`) REFERENCES `account`(`account_id`),
    FOREIGN KEY(`category_id`) REFERENCES `category`(`category_id`)
);

