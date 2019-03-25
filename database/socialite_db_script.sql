-- MySQL Script generated by MySQL Workbench
-- Mon Mar 25 11:28:27 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema socialitedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema socialitedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `socialitedb` DEFAULT CHARACTER SET utf8 ;
USE `socialitedb` ;

-- -----------------------------------------------------
-- Table `socialitedb`.`Visibility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialitedb`.`Visibility` ;

CREATE TABLE IF NOT EXISTS `socialitedb`.`Visibility` (
  `idVisibility` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idVisibility`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialitedb`.`Post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialitedb`.`Post` ;

CREATE TABLE IF NOT EXISTS `socialitedb`.`Post` (
  `idPost` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `text` VARCHAR(500) NULL,
  `user` INT NOT NULL,
  `visibility` INT NOT NULL,
  PRIMARY KEY (`idPost`),
  UNIQUE INDEX `idPost_UNIQUE` (`idPost` ASC) VISIBLE,
  UNIQUE INDEX `user_UNIQUE` (`user` ASC) VISIBLE,
  UNIQUE INDEX `visibilidad_UNIQUE` (`visibility` ASC) VISIBLE,
  CONSTRAINT `fk_Post_Visibility`
    FOREIGN KEY (`visibility`)
    REFERENCES `socialitedb`.`Visibility` (`idVisibility`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialitedb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialitedb`.`User` ;

CREATE TABLE IF NOT EXISTS `socialitedb`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NULL,
  `birthDate` DATE NOT NULL,
  `birthPlace` VARCHAR(45) NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUser` ASC) VISIBLE,
  UNIQUE INDEX `correo_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  CONSTRAINT `fk_User_Post`
    FOREIGN KEY (`idUser`)
    REFERENCES `socialitedb`.`Post` (`user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialitedb`.`Group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialitedb`.`Group` ;

CREATE TABLE IF NOT EXISTS `socialitedb`.`Group` (
  `idGroup` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idGroup`),
  UNIQUE INDEX `idGroup_UNIQUE` (`idGroup` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialitedb`.`Media`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialitedb`.`Media` ;

CREATE TABLE IF NOT EXISTS `socialitedb`.`Media` (
  `idMedia` INT NOT NULL AUTO_INCREMENT,
  `mediaUrl` VARCHAR(45) NOT NULL,
  `post` INT NOT NULL,
  PRIMARY KEY (`idMedia`),
  UNIQUE INDEX `idMedia_UNIQUE` (`idMedia` ASC) VISIBLE,
  UNIQUE INDEX `post_UNIQUE` (`post` ASC) VISIBLE,
  CONSTRAINT `fk_Mefia_Post`
    FOREIGN KEY (`post`)
    REFERENCES `socialitedb`.`Post` (`idPost`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialitedb`.`FriendshipRequest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialitedb`.`FriendshipRequest` ;

CREATE TABLE IF NOT EXISTS `socialitedb`.`FriendshipRequest` (
  `idFriendshipRequest` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  PRIMARY KEY (`idFriendshipRequest`),
  UNIQUE INDEX `idFriendshipRequest_UNIQUE` (`idFriendshipRequest` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialitedb`.`UserGroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialitedb`.`UserGroup` ;

CREATE TABLE IF NOT EXISTS `socialitedb`.`UserGroup` (
  `user` INT NOT NULL,
  `group` INT NOT NULL,
  PRIMARY KEY (`user`, `group`),
  INDEX `fk_UserGroup_Group_idx` (`group` ASC) VISIBLE,
  CONSTRAINT `fk_UserGroup_User`
    FOREIGN KEY (`user`)
    REFERENCES `socialitedb`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_UserGroup_Group`
    FOREIGN KEY (`group`)
    REFERENCES `socialitedb`.`Group` (`idGroup`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `socialitedb` ;

-- -----------------------------------------------------
-- Placeholder table for view `socialitedb`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `socialitedb`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `socialitedb`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialitedb`.`view1`;
DROP VIEW IF EXISTS `socialitedb`.`view1` ;
USE `socialitedb`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
