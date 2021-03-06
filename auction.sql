-- MySQL Script generated by MySQL Workbench
-- Fri Oct 19 01:14:00 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema auction
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema auction
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `auction` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `auction` ;

-- -----------------------------------------------------
-- Table `auction`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction`.`user` (
  `id_user` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL DEFAULT NULL COMMENT 'First name of user.',
  `last_name` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Last name of user.',
  `username` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Username.',
  `email` VARCHAR(50) NULL DEFAULT NULL COMMENT 'Email of user.',
  `password` VARCHAR(100) NULL DEFAULT NULL COMMENT 'Password of user.',
  `role` ENUM('user', 'admin') NOT NULL,
  `is_banned` TINYINT(1) NULL DEFAULT '0',
  `balance` DECIMAL(10,2) NULL DEFAULT '0.00',
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = 'user characteristics';


-- -----------------------------------------------------
-- Table `auction`.`lot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction`.`lot` (
  `id_lot` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(10,2) NULL DEFAULT NULL COMMENT 'Price of lot.',
  `date_of_start` TIMESTAMP NULL DEFAULT NULL COMMENT 'Start date of the lot.',
  `date_of_end` TIMESTAMP NULL DEFAULT NULL COMMENT 'End date of the lot.',
  `brand` VARCHAR(45) NULL DEFAULT NULL,
  `model` VARCHAR(45) NULL DEFAULT NULL,
  `class` VARCHAR(45) NULL DEFAULT NULL,
  `year_of_issue` INT(11) NULL DEFAULT NULL,
  `color` VARCHAR(45) NULL DEFAULT NULL,
  `engine_volume` DECIMAL(2,1) NULL DEFAULT NULL,
  `is_damaged` TINYINT(1) NULL DEFAULT NULL,
  `auction_type` ENUM('direct', 'reverse') NOT NULL,
  `owner_id` BIGINT(20) NOT NULL,
  `status` ENUM('processing', 'confirmed', 'refused', 'not-purchased', 'payment-waiting', 'paid') NULL DEFAULT 'processing',
  PRIMARY KEY (`id_lot`),
  INDEX `fk_lot_user1_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `fk_lot_user1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `auction`.`user` (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = 'lot characteristics';


-- -----------------------------------------------------
-- Table `auction`.`bidder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction`.`bidder` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `lot_id_lot` BIGINT(20) NOT NULL,
  `user_id_user` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lot_has_user_user1_idx` (`user_id_user` ASC) VISIBLE,
  INDEX `fk_lot_has_user_lot1_idx` (`lot_id_lot` ASC) VISIBLE,
  CONSTRAINT `fk_lot_has_user_lot1`
    FOREIGN KEY (`lot_id_lot`)
    REFERENCES `auction`.`lot` (`id_lot`),
  CONSTRAINT `fk_lot_has_user_user1`
    FOREIGN KEY (`user_id_user`)
    REFERENCES `auction`.`user` (`id_user`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `auction`.`lot_photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auction`.`lot_photo` (
  `id_photo` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `photo_url` VARCHAR(45) NULL DEFAULT NULL COMMENT 'URL adress of photo of car',
  `lot_id_lot` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_photo`),
  INDEX `fk_car_photo_lot1_idx` (`lot_id_lot` ASC) VISIBLE,
  CONSTRAINT `fk_car_photo_lot1`
    FOREIGN KEY (`lot_id_lot`)
    REFERENCES `auction`.`lot` (`id_lot`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = 'car photos';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
