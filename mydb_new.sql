-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb_new
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb_new` ;

-- -----------------------------------------------------
-- Schema mydb_new
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb_new` DEFAULT CHARACTER SET utf8 ;
USE `mydb_new` ;

-- -----------------------------------------------------
-- Table `mydb_new`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb_new`.`User` ;

CREATE TABLE IF NOT EXISTS `mydb_new`.`User` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `icon` BLOB NULL,
  `join_date` DATE NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb_new`.`Project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb_new`.`Project` ;

CREATE TABLE IF NOT EXISTS `mydb_new`.`Project` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`project_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb_new`.`Resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb_new`.`Resource` ;

CREATE TABLE IF NOT EXISTS `mydb_new`.`Resource` (
  `resource_id` INT NOT NULL AUTO_INCREMENT,
  `resource_code` VARCHAR(45) NOT NULL,
  `resource_name` VARCHAR(45) NOT NULL,
#   `project_id` INT NULL,
  PRIMARY KEY (`resource_id`))
#   INDEX `project_id_idx` (`project_id` ASC) VISIBLE,
#   CONSTRAINT `r-project_id`
#     FOREIGN KEY (`project_id`)
#     REFERENCES `mydb_new`.`Project` (`project_id`)
#     ON DELETE NO ACTION
#     ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb_new`.`CustomizedColumn`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb_new`.`CustomizedColumn` ;

CREATE TABLE IF NOT EXISTS `mydb_new`.`CustomizedColumn` (
  `column_id` INT NOT NULL AUTO_INCREMENT,
  `column_name` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NULL,
  `content` VARCHAR(45) NULL,
  PRIMARY KEY (`column_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb_new`.`Resource-Column`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb_new`.`Resource-Column` ;

CREATE TABLE IF NOT EXISTS `mydb_new`.`Resource-Column` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NULL,
  `column_id` INT NOT NULL,
  `resource_id` INT NOT NULL,
  `project_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `column_id_idx` (`column_id` ASC) VISIBLE,
  INDEX `resource_id_idx` (`resource_id` ASC) VISIBLE,
  INDEX `project_id_idx` (`project_id` ASC) VISIBLE,
  CONSTRAINT `column_id`
    FOREIGN KEY (`column_id`)
    REFERENCES `mydb_new`.`CustomizedColumn` (`column_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `resource_id`
    FOREIGN KEY (`resource_id`)
    REFERENCES `mydb_new`.`Resource` (`resource_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `rc-project_id`
    FOREIGN KEY (`project_id`)
    REFERENCES `mydb_new`.`Project` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
