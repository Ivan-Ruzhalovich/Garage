-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: auto_helper
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `authority` varchar(25) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  KEY `username` (`username`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('IvanRuzhalovich','ROLE_USER'),('ValeriaRuzhalovich','ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Brand` varchar(10) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Model` varchar(15) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Year` int DEFAULT NULL,
  `Kilometrage` int DEFAULT NULL,
  `Engine` varchar(20) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Transmission` varchar(20) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `User_id` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `User_id` (`User_id`),
  CONSTRAINT `car_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'Volkswagen','Tiguan',2013,119000,'petrol','AMT',5);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refuel`
--

DROP TABLE IF EXISTS `refuel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refuel` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Car_id` int DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Type_fuel` varchar(15) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Volume` decimal(5,2) DEFAULT NULL,
  `Kilometrage` int DEFAULT NULL,
  `Price` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Car_id` (`Car_id`),
  CONSTRAINT `refuel_ibfk_1` FOREIGN KEY (`Car_id`) REFERENCES `car` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refuel`
--

LOCK TABLES `refuel` WRITE;
/*!40000 ALTER TABLE `refuel` DISABLE KEYS */;
INSERT INTO `refuel` VALUES (17,1,'2024-10-01','95',60.00,116011,58),(18,1,'2024-10-15','95',60.00,116456,58),(19,1,'2024-10-29','95',60.00,117145,58),(20,1,'2024-11-05','95',60.00,117564,58),(21,1,'2024-11-06','95',64.00,118200,58),(23,1,'2024-11-07','95',60.00,118500,58),(24,1,'2024-11-12','95',60.00,118900,58),(25,1,'2024-08-26','95',60.00,118000,58),(27,1,'2024-10-28','95',60.00,118000,58);
/*!40000 ALTER TABLE `refuel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tech_car`
--

DROP TABLE IF EXISTS `tech_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tech_car` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Car_id` int DEFAULT NULL,
  `Work_id` int DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Kilometrage` int DEFAULT NULL,
  `Price` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Car_id` (`Car_id`),
  KEY `Work_id` (`Work_id`),
  CONSTRAINT `tech_car_ibfk_1` FOREIGN KEY (`Car_id`) REFERENCES `car` (`Id`),
  CONSTRAINT `tech_car_ibfk_2` FOREIGN KEY (`Work_id`) REFERENCES `tech_work` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tech_car`
--

LOCK TABLES `tech_car` WRITE;
/*!40000 ALTER TABLE `tech_car` DISABLE KEYS */;
INSERT INTO `tech_car` VALUES (3,1,2,'2024-10-01',118000,10000),(7,1,3,'2024-06-05',110000,600),(8,1,26,'2021-01-06',95000,10000),(9,1,25,'2021-04-06',73000,10000),(10,1,27,'2021-09-16',74000,6000),(12,1,24,'2021-03-09',73000,12000),(13,1,24,'2024-08-07',115000,10000),(14,1,24,'2024-08-07',115000,10000),(15,1,5,'2024-11-20',119000,4500),(17,1,1,'2024-10-29',118000,950),(19,1,1,'2024-10-28',118000,600),(21,1,26,'2023-10-10',102000,6500);
/*!40000 ALTER TABLE `tech_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tech_work`
--

DROP TABLE IF EXISTS `tech_work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tech_work` (
  `Id` int NOT NULL,
  `Description` varchar(500) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tech_work`
--

LOCK TABLES `tech_work` WRITE;
/*!40000 ALTER TABLE `tech_work` DISABLE KEYS */;
INSERT INTO `tech_work` VALUES (1,'Замена маслянного фильтра'),(2,'Замена масла в двигателе'),(3,'Замена воздушного фильтра двигателя'),(4,'Замена воздушного фильтра салона'),(5,'Замена свечей зажигания'),(6,'Замена катушек зажигания'),(7,'Замена передних тормозных колодок'),(8,'Замена задних тормозных колодок'),(9,'Замена передних тормозных дисков'),(10,'Замена задних тормозных дисков'),(11,'Замена передних щеток стеклоочистителя'),(12,'Замена задних щеток стеклоочистителя'),(13,'Замена передних стоек стабилизатора'),(14,'Замена задних стоек стабилизатора'),(15,'Замена передних амортизаторов'),(16,'Замена передних пружин'),(17,'Замена задних амортизаторов'),(18,'Замена задних пружин'),(19,'Замена передних ступичных подшипников'),(20,'Замена задних ступичных подшипников'),(21,'Замена передних опорных подшипников'),(22,'Замена задних опорных подшипников'),(24,'Обслуживание коробки передач'),(25,'Обслуживание системы полного привода'),(26,'Замена тормозной жидкости'),(27,'Замена охлаждающей жидкости (антифриз)');
/*!40000 ALTER TABLE `tech_work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Surname` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `Date_of_birth` date DEFAULT NULL,
  `City` varchar(20) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `user_name_auth` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `phone_number` varchar(16) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `activationCode` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `user_name_auth` (`user_name_auth`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_name_auth`) REFERENCES `authorities` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'Иван','Ruzhalovich','2024-10-30','Ryazan','IvanRuzhalovich','89156213889','ruzhalovich@bk.ru',NULL),(70,'Valeria','Ruzhalovich',NULL,NULL,'ValeriaRuzhalovich',NULL,'garage.avto@list.ru',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(100) COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('IvanRuzhalovich','$2a$10$.djDrS/Ah2DGpcUERCCrWu7ZVESR3zTHLp5PR87XnNVysXb19P.sS',1),('ValeriaRuzhalovich','$2a$10$2nn6LwB0EkvV.ieJvUFQd.KRvhFR7nsgE7BDNCxEMMvrxIFyECbc6',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-28 16:24:00
