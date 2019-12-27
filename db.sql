-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`User` ;

CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `user_id` INT NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `icon` BLOB NULL,
  `join_date` DATETIME NOT NULL,
  `project_id` INT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Category` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Category` (
  `category_id` INT NOT NULL,
  `category_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `mydb`.`Project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Project` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Project` (
  `project_id` INT NOT NULL,
  `project_name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`project_id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`User` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Resource` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Resource` (
  `resource_id` INT NOT NULL,
  `resource_code` VARCHAR(45) NOT NULL,
  `resource_name` VARCHAR(45) NOT NULL,
  `category_id` INT NOT NULL,
  `project_id` INT NULL,
  PRIMARY KEY (`resource_id`),
  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,
  INDEX `project_id_idx` (`project_id` ASC) VISIBLE,
  CONSTRAINT `category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `mydb`.`Category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `project_id`
    FOREIGN KEY (`project_id`)
    REFERENCES `mydb`.`Project` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
