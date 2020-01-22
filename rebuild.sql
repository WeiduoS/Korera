-- MySQL Script generated by MySQL Workbench
-- Wed Jan 22 10:13:53 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema KoreraDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `KoreraDB` ;

-- -----------------------------------------------------
-- Schema KoreraDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `KoreraDB` DEFAULT CHARACTER SET utf8 ;
USE `KoreraDB` ;

-- -----------------------------------------------------
-- Table `KoreraDB`.`sys_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`sys_permission` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`sys_permission` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `premission_name` VARCHAR(45) NULL DEFAULT NULL COMMENT 'manu name',
  `premission_url` VARCHAR(100) NULL DEFAULT NULL COMMENT 'manu url',
  `parent_id` INT NULL DEFAULT 0 COMMENT 'parent menu id',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KoreraDB`.`sys_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`sys_role` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`sys_role` (
  `ID` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ROLE_NAME` VARCHAR(45) NULL DEFAULT NULL COMMENT 'name of role',
  `ROLE_DESC` VARCHAR(60) NULL DEFAULT NULL COMMENT 'description of role',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 2;


-- -----------------------------------------------------
-- Table `KoreraDB`.`sys_role_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`sys_role_permission` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`sys_role_permission` (
  `RID` INT NOT NULL COMMENT 'role id',
  `PID` INT NOT NULL COMMENT 'permission id',
  INDEX `fk_sys_role_permission_sys_permission_idx` (`PID` ASC),
  PRIMARY KEY (`RID`, `PID`),
  INDEX `fk_sys_role_permission_sys_role1_idx` (`RID` ASC),
  CONSTRAINT `fk_sys_role_permission_sys_permission`
    FOREIGN KEY (`PID`)
    REFERENCES `KoreraDB`.`sys_permission` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_role_permission_sys_role1`
    FOREIGN KEY (`RID`)
    REFERENCES `KoreraDB`.`sys_role` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KoreraDB`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`user` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NULL,
  `password` VARCHAR(255) NULL,
  `icon` BLOB NULL,
  `join_date` DATE NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KoreraDB`.`project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`project` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`project` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(45) NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`project_id`),
  INDEX `fk_Project_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_Project_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `KoreraDB`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KoreraDB`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`category` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`category` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KoreraDB`.`resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`resource` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`resource` (
  `resource_id` INT NOT NULL AUTO_INCREMENT,
  `resource_code` VARCHAR(45) NULL,
  `resource_name` VARCHAR(45) NULL,
  `category_id` INT NULL,
  PRIMARY KEY (`resource_id`),
  INDEX `fk_Resource_Category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_Resource_Category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `KoreraDB`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KoreraDB`.`project_resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`project_resource` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`project_resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `project_id` INT NULL,
  `resource_id` INT NULL,
  INDEX `fk_pro_res_mapping_Project1_idx` (`project_id` ASC),
  INDEX `fk_pro_res_mapping_Resource1_idx` (`resource_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_pro_res_mapping_Project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `KoreraDB`.`project` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pro_res_mapping_Resource1`
    FOREIGN KEY (`resource_id`)
    REFERENCES `KoreraDB`.`resource` (`resource_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KoreraDB`.`cols`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`cols` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`cols` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `field` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `formula` VARCHAR(45) NULL,
  `value` VARCHAR(45) NULL,
  `pr_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cols_project_resource1_idx` (`pr_id` ASC),
  CONSTRAINT `fk_cols_project_resource1`
    FOREIGN KEY (`pr_id`)
    REFERENCES `KoreraDB`.`project_resource` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KoreraDB`.`sys_user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`sys_user_role` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`sys_user_role` (
  `UID` INT NOT NULL,
  `RID` INT NOT NULL,
  INDEX `fk_sys_user_role_user1_idx` (`UID` ASC),
  INDEX `fk_sys_user_role_sys_role1_idx` (`RID` ASC),
  PRIMARY KEY (`UID`, `RID`),
  CONSTRAINT `fk_sys_user_role_user1`
    FOREIGN KEY (`UID`)
    REFERENCES `KoreraDB`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_user_role_sys_role1`
    FOREIGN KEY (`RID`)
    REFERENCES `KoreraDB`.`sys_role` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KoreraDB`.`persistent_logins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `KoreraDB`.`persistent_logins` ;

CREATE TABLE IF NOT EXISTS `KoreraDB`.`persistent_logins` (
  `username` VARCHAR(64) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
