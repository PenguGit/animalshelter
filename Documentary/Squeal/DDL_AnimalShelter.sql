-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema animalshelter
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `animalshelter` ;

-- -----------------------------------------------------
-- Schema animalshelter
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `animalshelter` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `animalshelter` ;

-- -----------------------------------------------------
-- Table `animalshelter`.`AnimalType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`AnimalType` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`AnimalType` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `animalshelter`.`Patron`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`Patron` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`Patron` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `animalshelter`.`Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`Room` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`Room` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `animalshelter`.`Animal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`Animal` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`Animal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `gender` TINYINT(5) NULL,
  `date_of_birth` DATE NOT NULL,
  `additional_info` TEXT NULL,
  `AnimalType_id` INT NOT NULL,
  `Patron_id` INT NULL,
  `Room_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Animal_AnimalType`
    FOREIGN KEY (`AnimalType_id`)
    REFERENCES `animalshelter`.`AnimalType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Animal_Patron1`
    FOREIGN KEY (`Patron_id`)
    REFERENCES `animalshelter`.`Patron` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Animal_Room1`
    FOREIGN KEY (`Room_id`)
    REFERENCES `animalshelter`.`Room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `animalshelter`.`Adopter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`Adopter` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`Adopter` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `animalshelter`.`Caretaker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`Caretaker` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`Caretaker` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `animalshelter`.`Incident`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`Incident` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`Incident` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `description` TEXT NULL,
  `Caretaker_id` INT NOT NULL,
  `Animal_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Incident_Caretaker1`
    FOREIGN KEY (`Caretaker_id`)
    REFERENCES `animalshelter`.`Caretaker` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Incident_Animal1`
    FOREIGN KEY (`Animal_id`)
    REFERENCES `animalshelter`.`Animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `animalshelter`.`Vet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`Vet` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`Vet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `animalshelter`.`Examination`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`Examination` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`Examination` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `description` TEXT NULL,
  `Vet_id` INT NOT NULL,
  `Animal_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Examination_Vet1`
    FOREIGN KEY (`Vet_id`)
    REFERENCES `animalshelter`.`Vet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Examination_Animal1`
    FOREIGN KEY (`Animal_id`)
    REFERENCES `animalshelter`.`Animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `animalshelter`.`Adoption`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `animalshelter`.`Adoption` ;

CREATE TABLE IF NOT EXISTS `animalshelter`.`Adoption` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `Adopter_id` INT NOT NULL,
  `Animal_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Adoption_Adopter1`
    FOREIGN KEY (`Adopter_id`)
    REFERENCES `animalshelter`.`Adopter` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Adoption_Animal1`
    FOREIGN KEY (`Animal_id`)
    REFERENCES `animalshelter`.`Animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
