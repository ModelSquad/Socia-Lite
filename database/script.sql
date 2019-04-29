-- MySQL Script generated by MySQL Workbench
-- mar 23 abr 2019 13:23:28 CEST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema socialite
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema socialite
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `socialite` DEFAULT CHARACTER SET utf8 ;
USE `socialite` ;

-- -----------------------------------------------------
-- Table `socialite`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialite`.`User` ;

CREATE TABLE IF NOT EXISTS `socialite`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NULL,
  `birthDate` DATE NOT NULL,
  `birthPlace` VARCHAR(45) NULL,
  `job` VARCHAR(100) NULL,
  `jobPlace` VARCHAR(45) NULL,
  `studyPlace` VARCHAR(45) NULL,
  `website` VARCHAR(100) NULL,
  `profilePic` VARCHAR(100) NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUser` ASC) VISIBLE,
  UNIQUE INDEX `correo_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialite`.`Association`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialite`.`Association` ;

CREATE TABLE IF NOT EXISTS `socialite`.`Association` (
  `idGroup` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(300) NULL,
  `profilePic` VARCHAR(100) NULL,
  `admin` INT NOT NULL,
  PRIMARY KEY (`idGroup`),
  UNIQUE INDEX `idGroup_UNIQUE` (`idGroup` ASC) VISIBLE,
  INDEX `Administrator_idx` (`admin` ASC) VISIBLE,
  CONSTRAINT `Administrator`
    FOREIGN KEY (`admin`)
    REFERENCES `socialite`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialite`.`Visibility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialite`.`Visibility` ;

CREATE TABLE IF NOT EXISTS `socialite`.`Visibility` (
  `idVisibility` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idVisibility`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialite`.`Post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialite`.`Post` ;

CREATE TABLE IF NOT EXISTS `socialite`.`Post` (
  `idPost` INT NOT NULL AUTO_INCREMENT,
  `user` INT NOT NULL,
  `visibility` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `title` VARCHAR(45) NULL,
  `text` VARCHAR(500) NULL,
  `likes` INT NULL,
  PRIMARY KEY (`idPost`),
  UNIQUE INDEX `idPost_UNIQUE` (`idPost` ASC) VISIBLE,
  CONSTRAINT `fk_Post_Visibility`
    FOREIGN KEY (`visibility`)
    REFERENCES `socialite`.`Visibility` (`idVisibility`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Post_User`
    FOREIGN KEY (`user`)
    REFERENCES `socialite`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialite`.`Media`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialite`.`Media` ;

CREATE TABLE IF NOT EXISTS `socialite`.`Media` (
  `idMedia` INT NOT NULL AUTO_INCREMENT,
  `mediaUrl` VARCHAR(100) NOT NULL,
  `post` INT NOT NULL,
  PRIMARY KEY (`idMedia`),
  UNIQUE INDEX `idMedia_UNIQUE` (`idMedia` ASC) VISIBLE,
  CONSTRAINT `fk_Mefia_Post`
    FOREIGN KEY (`post`)
    REFERENCES `socialite`.`Post` (`idPost`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialite`.`FriendshipRequest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialite`.`FriendshipRequest` ;

CREATE TABLE IF NOT EXISTS `socialite`.`FriendshipRequest` (
  `user_sender` INT NOT NULL,
  `user_receiver` INT NOT NULL,
  `dateTime` DATETIME NOT NULL,
  `text` VARCHAR(200) NULL,
  PRIMARY KEY (`user_sender`, `user_receiver`),
  INDEX `fk_UserReceiver_idx` (`user_receiver` ASC) VISIBLE,
  CONSTRAINT `fk_UserReceiver`
    FOREIGN KEY (`user_receiver`)
    REFERENCES `socialite`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_UserSender`
    FOREIGN KEY (`user_sender`)
    REFERENCES `socialite`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialite`.`UserAssociation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialite`.`UserAssociation` ;

CREATE TABLE IF NOT EXISTS `socialite`.`UserAssociation` (
  `user` INT NOT NULL,
  `group` INT NOT NULL,
  PRIMARY KEY (`user`, `group`),
  INDEX `fk_UserGroup_Group_idx` (`group` ASC) VISIBLE,
  CONSTRAINT `fk_UserGroup_User`
    FOREIGN KEY (`user`)
    REFERENCES `socialite`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_UserGroup_Group`
    FOREIGN KEY (`group`)
    REFERENCES `socialite`.`Association` (`idGroup`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `socialite`.`UserFriend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialite`.`UserFriend` ;

CREATE TABLE IF NOT EXISTS `socialite`.`UserFriend` (
  `idUser` INT NOT NULL,
  `idFriend` INT NOT NULL,
  PRIMARY KEY (`idUser`, `idFriend`),
  INDEX `fk_UserFriend_Friend_idx` (`idFriend` ASC) VISIBLE,
  CONSTRAINT `fk_UserFriend_User`
    FOREIGN KEY (`idUser`)
    REFERENCES `socialite`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_UserFriend_Friend`
    FOREIGN KEY (`idFriend`)
    REFERENCES `socialite`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `socialite` ;

-- -----------------------------------------------------
-- Placeholder table for view `socialite`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `socialite`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `socialite`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `socialite`.`view1`;
DROP VIEW IF EXISTS `socialite`.`view1` ;
USE `socialite`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `socialite`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `socialite`;
INSERT INTO `socialite`.`User` (`idUser`, `email`, `password`, `name`, `surname`, `nickname`, `birthDate`, `birthPlace`, `job`, `jobPlace`, `studyPlace`, `website`, `profilePic`) VALUES (1, 'juan@gmail.es', 'juan', 'Juan', 'Martinez', 'juan97', '1997-05-22', 'Antequera', 'Mecánico', 'Antequera', 'I.E.S. Pedro Espinosa', '', NULL);
INSERT INTO `socialite`.`User` (`idUser`, `email`, `password`, `name`, `surname`, `nickname`, `birthDate`, `birthPlace`, `job`, `jobPlace`, `studyPlace`, `website`, `profilePic`) VALUES (2, 'jose@gmail.com', 'jose', 'Jose', 'Jimenez', 'jose778', '1996-06-30', 'Estepona', 'Pintor', 'Estepona', 'I.E.S. Vino', NULL, NULL);
INSERT INTO `socialite`.`User` (`idUser`, `email`, `password`, `name`, `surname`, `nickname`, `birthDate`, `birthPlace`, `job`, `jobPlace`, `studyPlace`, `website`, `profilePic`) VALUES (3, 'pepe@gmail.com', 'pepe', 'Pepe', 'Ruiz', 'pepe777', '1990-02-22', 'Guadix', 'Electricista', 'Guadix', '', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `socialite`.`Visibility`
-- -----------------------------------------------------
START TRANSACTION;
USE `socialite`;
INSERT INTO `socialite`.`Visibility` (`idVisibility`, `name`) VALUES (1, 'PUBLIC');

COMMIT;


-- -----------------------------------------------------
-- Data for table `socialite`.`Post`
-- -----------------------------------------------------
START TRANSACTION;
USE `socialite`;
INSERT INTO `socialite`.`Post` (`idPost`, `user`, `visibility`, `date`, `title`, `text`, `likes`) VALUES (1, 1, 1, '2019-02-22', 'Incendio en la catedral de Notre Dame', 'La catedral de Notre Dame de París sufrió un incendio que se inició en la tarde del 15 de abril de 2019 en el tejado del edificio y ocasionó daños considerables. La aguja de la catedral y el tejado se derrumbaron y tanto el espacio interior como muchos bienes muebles se dañaron gravemente. El incendio se produjo accidentalmente, por causas aún no conocidas. En 24 horas, se recaudaron más de 800 millones de euros para la reconstrucción.', NULL);
INSERT INTO `socialite`.`Post` (`idPost`, `user`, `visibility`, `date`, `title`, `text`, `likes`) VALUES (2, 2, 1, '2019-03-22', 'Mensaje bienvenida', 'Hola!!!! Soy nuevo aquí, un saludo a todos!!', NULL);
INSERT INTO `socialite`.`Post` (`idPost`, `user`, `visibility`, `date`, `title`, `text`, `likes`) VALUES (3, 2, 1, '2019-03-23', 'Me aburro', 'Buenas... quiero conocer gente nueva, estoy muy aburrido en mi casa!! :(', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `socialite`.`UserFriend`
-- -----------------------------------------------------
START TRANSACTION;
USE `socialite`;
INSERT INTO `socialite`.`UserFriend` (`idUser`, `idFriend`) VALUES (1, 2);
INSERT INTO `socialite`.`UserFriend` (`idUser`, `idFriend`) VALUES (2, 1);

COMMIT;

