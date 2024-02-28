-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CoderDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CoderDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CoderDB` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `CoderDB` ;

-- -----------------------------------------------------
-- Table `CoderDB`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoderDB`.`Client` (
  `idClient` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `lastname` VARCHAR(100) NOT NULL,
  `status` INT NOT NULL DEFAULT 1,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idClient`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoderDB`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoderDB`.`Product` (
  `idProduct` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `sku` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `status` INT NOT NULL DEFAULT 1,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idProduct`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoderDB`.`Invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoderDB`.`Invoice` (
  `idInvoice` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT UNSIGNED NOT NULL,
  `total` DOUBLE NOT NULL DEFAULT 0,
  `status` INT NOT NULL DEFAULT 1,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idInvoice`),
  INDEX `fk_Invoice_Client_idx` (`idClient` ASC) VISIBLE,
  CONSTRAINT `fk_Invoice_Client`
    FOREIGN KEY (`idClient`)
    REFERENCES `CoderDB`.`Client` (`idClient`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoderDB`.`InvoiceHasProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoderDB`.`InvoiceHasProduct` (
  `idInvoice` INT UNSIGNED NOT NULL,
  `idProduct` INT UNSIGNED NOT NULL,
  `amount` INT UNSIGNED NOT NULL,
  `price` DOUBLE NOT NULL,
  `status` INT NOT NULL DEFAULT 1,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idInvoice`, `idProduct`),
  INDEX `fk_InvoiceHasProduct_Product_idx` (`idProduct` ASC) VISIBLE,
  CONSTRAINT `fk_InvoiceHasProduct_Invoice`
    FOREIGN KEY (`idInvoice`)
    REFERENCES `CoderDB`.`Invoice` (`idInvoice`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_InvoiceHasProduct_Product`
    FOREIGN KEY (`idProduct`)
    REFERENCES `CoderDB`.`Product` (`idProduct`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `CoderDB`;

DELIMITER $$
USE `CoderDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `CoderDB`.`InvoiceHasProduct_BEFORE_INSERT` AFTER INSERT ON `InvoiceHasProduct` FOR EACH ROW
BEGIN
    UPDATE Invoice
    SET total = (
        SELECT SUM(amount * price)
        FROM InvoiceHasProduct
        WHERE idInvoice = NEW.idInvoice
    )
    WHERE idInvoice = NEW.idInvoice;
END;$$

USE `CoderDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `CoderDB`.`InvoiceHasProduct_AFTER_UPDATE` AFTER UPDATE ON `InvoiceHasProduct` FOR EACH ROW
BEGIN
    UPDATE Invoice
    SET total = (
        SELECT SUM(amount * price)
        FROM InvoiceHasProduct
        WHERE idInvoice = NEW.idInvoice
    )
    WHERE idInvoice = NEW.idInvoice;
END$$

USE `CoderDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `CoderDB`.`InvoiceHasProduct_AFTER_DELETE` AFTER DELETE ON `InvoiceHasProduct` FOR EACH ROW
BEGIN
    UPDATE Invoice
    SET total = (
        SELECT SUM(amount * price)
        FROM InvoiceHasProduct
        WHERE idInvoice = OLD.idInvoice
    )
    WHERE idInvoice = OLD.idInvoice;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
