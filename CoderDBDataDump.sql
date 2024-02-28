CREATE DATABASE  IF NOT EXISTS `coderdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `coderdb`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 192.168.1.20    Database: coderdb
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `idClient` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Sergio','Ruiz Caballero',0,'2024-02-23 02:26:25','2024-02-23 05:10:04'),(2,'Juan','Ramírez',1,'2024-02-23 05:59:10','2024-02-23 05:59:10'),(3,'Johnny','Aguirre',1,'2024-02-23 06:00:29','2024-02-27 20:19:45'),(4,'Juan','Valdez',1,'2024-02-27 18:53:17','2024-02-27 20:25:58'),(5,'Pedro','Pérez',1,'2024-02-27 20:17:43','2024-02-27 20:17:43'),(6,'Pedro','Pérez',1,'2024-02-27 20:17:55','2024-02-27 20:17:55');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `idInvoice` int unsigned NOT NULL AUTO_INCREMENT,
  `idClient` int unsigned NOT NULL,
  `total` double NOT NULL DEFAULT '0',
  `status` int NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idInvoice`),
  KEY `fk_Invoice_Client_idx` (`idClient`),
  CONSTRAINT `fk_Invoice_Client` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,1,20,1,'2024-02-27 05:51:36','2024-02-27 06:00:26'),(2,2,124,1,'2024-02-28 03:14:59','2024-02-28 16:32:16');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoicehasproduct`
--

DROP TABLE IF EXISTS `invoicehasproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoicehasproduct` (
  `idInvoice` int unsigned NOT NULL,
  `idProduct` int unsigned NOT NULL,
  `amount` int unsigned NOT NULL,
  `price` double NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idInvoice`,`idProduct`),
  KEY `fk_InvoiceHasProduct_Product_idx` (`idProduct`),
  CONSTRAINT `fk_InvoiceHasProduct_Invoice` FOREIGN KEY (`idInvoice`) REFERENCES `invoice` (`idInvoice`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_InvoiceHasProduct_Product` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoicehasproduct`
--

LOCK TABLES `invoicehasproduct` WRITE;
/*!40000 ALTER TABLE `invoicehasproduct` DISABLE KEYS */;
INSERT INTO `invoicehasproduct` VALUES (1,1,2,10,1,'2024-02-27 05:54:46','2024-02-27 05:54:46'),(2,1,10,10,1,'2024-02-28 16:11:53','2024-02-28 16:11:53'),(2,2,2,12,1,'2024-02-28 16:32:16','2024-02-28 16:32:16');
/*!40000 ALTER TABLE `invoicehasproduct` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`admin`@`%`*/ /*!50003 TRIGGER `InvoiceHasProduct_BEFORE_INSERT` AFTER INSERT ON `invoicehasproduct` FOR EACH ROW BEGIN
    UPDATE Invoice
    SET total = (
        SELECT SUM(amount * price)
        FROM InvoiceHasProduct
        WHERE idInvoice = NEW.idInvoice
    )
    WHERE idInvoice = NEW.idInvoice;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`admin`@`%`*/ /*!50003 TRIGGER `InvoiceHasProduct_AFTER_UPDATE` AFTER UPDATE ON `invoicehasproduct` FOR EACH ROW BEGIN
    UPDATE Invoice
    SET total = (
        SELECT SUM(amount * price)
        FROM InvoiceHasProduct
        WHERE idInvoice = NEW.idInvoice
    )
    WHERE idInvoice = NEW.idInvoice;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`admin`@`%`*/ /*!50003 TRIGGER `InvoiceHasProduct_AFTER_DELETE` AFTER DELETE ON `invoicehasproduct` FOR EACH ROW BEGIN
    UPDATE Invoice
    SET total = (
        SELECT SUM(amount * price)
        FROM InvoiceHasProduct
        WHERE idInvoice = OLD.idInvoice
    )
    WHERE idInvoice = OLD.idInvoice;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `idProduct` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `sku` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Prueba1',NULL,'1',10,1,'2024-02-27 05:50:54','2024-02-27 05:50:54'),(2,'Producto2',NULL,'2',12,1,'2024-02-27 05:51:19','2024-02-27 05:51:19'),(3,'producto 3','Este producto sí tiene una descripción. Es bastante larga porque la idea es probar el límite de la descripción y cómo se vería en vivo. Por cierto, ésta clase debe estar borrada','404',5,1,'2024-02-27 23:57:05','2024-02-27 23:57:05'),(4,'Coca Cola 150ml','Tal y como lo indica el nombre, así es la descripción','755',10,1,'2024-02-28 00:42:39','2024-02-28 00:42:39'),(5,'Coca Cola 500ml','Tal y como lo indica el nombre, así es la descripción','755',10,0,'2024-02-28 00:46:03','2024-02-28 01:12:29');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'coderdb'
--

--
-- Dumping routines for database 'coderdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-28 14:25:54
