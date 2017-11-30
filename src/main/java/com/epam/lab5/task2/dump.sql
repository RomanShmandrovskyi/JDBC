-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: university
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(20) DEFAULT NULL,
  `region` varchar(20) DEFAULT NULL,
  `city` varchar(25) NOT NULL,
  `street` varchar(100) NOT NULL,
  `building` varchar(100) NOT NULL,
  `flat` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Ukraine','Lviv','Lviv','Bandery','15','225'),(2,'Ukraine','Lviv','Lviv','Shevhenka','25','102'),(3,'Ukraine','Lviv','Lviv','Shevhenka','42','45'),(4,'Ukraine','Lviv','Lviv','Bandery','89','47'),(5,'Ukraine','Lviv','Lviv','Bandery','10','15'),(6,'Ukraine','Lviv','Sambir','V. Velygoho','18','36'),(7,'Ukraine','Lviv','Zhovkva','Kopernyka','29','20'),(8,'Ukraine','Lviv','Zhovkva','Konovaltsia','48','45'),(9,'Ukraine','Lviv','Stryi','Puliuya','62','81'),(10,'Ukraine','Lviv','Lviv','Franka','59','99'),(11,'Ukraine','Lviv','Zhovkva','Kopernyka','57','68'),(12,'Ukraine','Lviv','Lviv','Franka','65','44'),(13,'Ukraine','Lviv','Drohobych','Franka','26','15'),(14,'Ukraine','Lviv','Zhovkva','O. Honchara','57','19'),(15,'Ukraine','Lviv','Drohobych','Kopernyka','62','11'),(16,'Ukraine','Lviv','Zhovkva','Kopernyka','14','66'),(17,'Ukraine','Lviv','Lviv','Kopernyka','42','50'),(18,'Ukraine','Lviv','Stryi','Franka','4','81'),(19,'Ukraine','Lviv','Stryi','O. Honchara','65','44'),(20,'Ukraine','Lviv','Lviv','V. Velygoho','111','36'),(21,'Ukraine','Lviv','Lviv','Konovaltsia','116','18'),(22,'Ukraine','Lviv','Stryi','V. Velygoho','56','11'),(23,'Ukraine','Lviv','Zhovkva','Franka','25','90'),(24,'Ukraine','Lviv','Sambir','Puliuya','73','41'),(25,'Ukraine','Lviv','Lviv','Franka','110','19'),(26,'Ukraine','Lviv','Drohobych','Franka','117','60'),(27,'Ukraine','Lviv','Lviv','Franka','12','7'),(28,'Ukraine','Lviv','Zhovkva','V. Velygoho','35','90'),(29,'Ukraine','Lviv','Stryi','V. Velygoho','78','40'),(30,'Ukraine','Lviv','Drohobych','Franka','74','59'),(31,'Ukraine','Lviv','Stryi','Konovaltsia','17','94'),(32,'Ukraine','Lviv','Drohobych','Konovaltsia','4','60'),(33,'Ukraine','Lviv','Sambir','V. Velygoho','100','71'),(34,'Ukraine','Lviv','Stryi','O. Honchara','4','89'),(35,'Ukraine','Lviv','Stryi','Franka','39','9');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cathedra`
--

DROP TABLE IF EXISTS `cathedra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cathedra` (
  `cathedra_id` int(11) NOT NULL AUTO_INCREMENT,
  `cathedra_name` varchar(100) NOT NULL,
  `cathedra_email` varchar(50) NOT NULL,
  `cathedra_phone` varchar(15) NOT NULL,
  `cathedra_description` varchar(500) DEFAULT NULL,
  `cathedra_address` int(11) DEFAULT NULL,
  PRIMARY KEY (`cathedra_id`),
  KEY `cathedra_address` (`cathedra_address`),
  CONSTRAINT `cathedra_ibfk_1` FOREIGN KEY (`cathedra_address`) REFERENCES `address` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cathedra`
--

LOCK TABLES `cathedra` WRITE;
/*!40000 ALTER TABLE `cathedra` DISABLE KEYS */;
INSERT INTO `cathedra` VALUES (1,'Architecture','1@gmail.com','+380124578963',NULL,1),(2,'Economy','2@gmail.com','+380976431258',NULL,2),(3,'English','3@gmail.com','+380070809123',NULL,3),(4,'Philosophy','4@gmail.com','+380986532147',NULL,4),(5,'Math','5@gmail.com','+380676723103',NULL,5);
/*!40000 ALTER TABLE `cathedra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(20) NOT NULL,
  `student_surname` varchar(30) NOT NULL,
  `student_gender` varchar(6) NOT NULL,
  `student_birth_day` varchar(10) DEFAULT NULL,
  `student_phone` varchar(15) NOT NULL,
  `student_exam_book_number` int(11) DEFAULT NULL,
  `student_start_day` varchar(10) DEFAULT NULL,
  `student_study_form` varchar(10) NOT NULL,
  `student_course` int(11) NOT NULL,
  `student_address` int(11) NOT NULL,
  `cathedra_id` int(11) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `student_id` (`student_id`),
  UNIQUE KEY `student_phone` (`student_phone`),
  KEY `student_address` (`student_address`),
  KEY `cathedra_id` (`cathedra_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`student_address`) REFERENCES `address` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`cathedra_id`) REFERENCES `cathedra` (`cathedra_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Petro','Barabash','female',NULL,'+1384171270',NULL,NULL,'full',1,13,4),(2,'Olena','Yanukovych','female',NULL,'+1038467248',NULL,NULL,'part',1,34,3),(3,'Nadia','Pupkin','female',NULL,'+1620453022',NULL,NULL,'full',4,19,5),(4,'Olia','Lychtei','male',NULL,'+1905792241',NULL,NULL,'part',4,31,3),(5,'Ira','Shmandrovskyi','female',NULL,'+1496316466',NULL,NULL,'part',6,29,2),(6,'Olena','Barabash','male',NULL,'+1942672928',NULL,NULL,'part',1,23,4),(7,'Roman','Yanukovych','female',NULL,'+1206226165',NULL,NULL,'full',2,30,2),(8,'Bohdan','Barabash','male',NULL,'+1803694531',NULL,NULL,'part',6,14,4),(9,'Ira','Shmandrovskyi','female',NULL,'+1538126741',NULL,NULL,'full',2,14,1),(10,'Olia','Barabash','male',NULL,'+1579973299',NULL,NULL,'part',4,25,3),(11,'Olia','Poroshenko','male',NULL,'+1013727474',NULL,NULL,'full',4,27,1),(12,'Andriy','Lychtei','female',NULL,'+1806376893',NULL,NULL,'part',1,34,1),(13,'Ivan','Shmandrovskyi','female',NULL,'+1864134305',NULL,NULL,'full',1,35,2),(14,'Petro','Barabash','male',NULL,'+1646616247',NULL,NULL,'full',2,14,3),(15,'Olena','Shmandrovskyi','female',NULL,'+1892155912',NULL,NULL,'full',6,8,1),(16,'Nadia','Yanukovych','female',NULL,'+1587748305',NULL,NULL,'full',1,7,2),(17,'Olena','Shmandrovskyi','female',NULL,'+1477912767',NULL,NULL,'full',2,14,2),(18,'Anya','Shmandrovskyi','female',NULL,'+1261825843',NULL,NULL,'full',3,35,5),(19,'Ira','Yaceniuk','female',NULL,'+1557995903',NULL,NULL,'full',6,18,4),(20,'Andriy','Savchyn','male',NULL,'+1984330791',NULL,NULL,'full',5,13,3),(21,'Roman','Lychtei','female',NULL,'+1225978401',NULL,NULL,'full',3,12,2),(22,'Petro','Poroshenko','male',NULL,'+1592816404',NULL,NULL,'full',2,23,3),(23,'Roman','Pupkin','male',NULL,'+1295482268',NULL,NULL,'part',1,35,1),(24,'Ira','Lychtei','male',NULL,'+1119639940',NULL,NULL,'full',6,20,2),(25,'Petro','Hilary','male',NULL,'+1071797769',NULL,NULL,'full',1,28,2),(26,'Ira','Shmandrovskyi','female',NULL,'+1140571166',NULL,NULL,'full',6,34,2),(27,'Roman','Yanukovych','male',NULL,'+1949498018',NULL,NULL,'part',2,22,2),(28,'Bohdan','Barabash','male',NULL,'+1537671339',NULL,NULL,'part',3,6,5),(29,'Anya','Barabash','female',NULL,'+1893205950',NULL,NULL,'part',3,26,2),(30,'Ira','Hilary','male',NULL,'+1919322643',NULL,NULL,'part',2,8,3),(31,'Petro','Lychtei','male',NULL,'+1377600213',NULL,NULL,'part',2,30,3),(32,'Andriy','Hilary','male',NULL,'+1527486048',NULL,NULL,'full',3,22,3),(33,'Bohdan','Poroshenko','male',NULL,'+1737689729',NULL,NULL,'part',6,26,3),(34,'Petro','Poroshenko','female',NULL,'+1488416953',NULL,NULL,'full',3,28,2),(35,'Ivan','Hilary','female',NULL,'+1399316090',NULL,NULL,'part',5,21,2),(36,'Ira','Barabash','female',NULL,'+1393370983',NULL,NULL,'full',4,34,3),(37,'Olia','Poroshenko','male',NULL,'+1406779288',NULL,NULL,'part',5,12,4),(38,'Bohdan','Savchyn','male',NULL,'+1844303827',NULL,NULL,'full',4,22,3),(39,'Olena','Poroshenko','male',NULL,'+1104196563',NULL,NULL,'full',6,17,1),(40,'Olena','Pupkin','female',NULL,'+1647159429',NULL,NULL,'full',4,14,4),(41,'Roman','Poroshenko','male',NULL,'+1231035462',NULL,NULL,'part',2,16,5),(42,'Petro','Barabash','female',NULL,'+1886655781',NULL,NULL,'full',1,18,4),(43,'Roman','Pupkin','female',NULL,'+1214689063',NULL,NULL,'full',1,13,3),(44,'Ira','Hilary','male',NULL,'+1786388093',NULL,NULL,'part',4,20,5),(45,'Nadia','Yaceniuk','female',NULL,'+1404341711',NULL,NULL,'part',3,6,1),(46,'Petro','Poroshenko','male',NULL,'+1219449460',NULL,NULL,'part',3,13,2),(47,'Nadia','Pavlenko','male',NULL,'+1840153271',NULL,NULL,'full',3,10,4),(48,'Petro','Lychtei','male',NULL,'+1764418067',NULL,NULL,'part',3,32,3),(49,'Olena','Shmandrovskyi','female',NULL,'+1016013687',NULL,NULL,'full',1,13,1),(50,'Andriy','Yanukovych','male',NULL,'+1411078019',NULL,NULL,'part',4,22,3),(51,'Petro','Shmandrovskyi','female',NULL,'+1138212553',NULL,NULL,'full',6,34,4),(52,'Andriy','Poroshenko','male',NULL,'+1456457740',NULL,NULL,'part',4,11,3),(53,'Nadia','Yanukovych','male',NULL,'+1314860675',NULL,NULL,'part',3,19,3),(54,'Olia','Savchyn','male',NULL,'+1934720022',NULL,NULL,'part',1,9,1),(55,'Olena','Lychtei','female',NULL,'+1809699232',NULL,NULL,'full',5,18,4),(56,'Anya','Yanukovych','male',NULL,'+1375098667',NULL,NULL,'full',2,13,3),(57,'Olia','Yanukovych','female',NULL,'+1671064630',NULL,NULL,'full',3,28,5),(58,'Bohdan','Barabash','male',NULL,'+1485026802',NULL,NULL,'full',4,19,1),(59,'Nadia','Savchyn','female',NULL,'+1731131776',NULL,NULL,'part',5,14,3),(60,'Ira','Pavlenko','female',NULL,'+1971687988',NULL,NULL,'part',1,8,5),(61,'Roman','Yanukovych','female',NULL,'+1212462037',NULL,NULL,'full',1,14,1),(62,'Nadia','Pavlenko','male',NULL,'+1659537222',NULL,NULL,'full',5,6,2),(63,'Andriy','Shmandrovskyi','female',NULL,'+1777314433',NULL,NULL,'full',4,16,5),(64,'Ivan','Poroshenko','male',NULL,'+1424391049',NULL,NULL,'full',1,27,3),(65,'Ivan','Barabash','male',NULL,'+1741401273',NULL,NULL,'full',2,30,2),(66,'Andriy','Poroshenko','male',NULL,'+1315439930',NULL,NULL,'full',5,9,3),(67,'Ira','Savchyn','male',NULL,'+1011871803',NULL,NULL,'full',2,35,2),(68,'Bohdan','Poroshenko','male',NULL,'+1285721143',NULL,NULL,'full',2,14,5),(69,'Ivan','Pavlenko','female',NULL,'+1316012084',NULL,NULL,'part',5,8,1),(70,'Andriy','Yaceniuk','female',NULL,'+1324328704',NULL,NULL,'part',5,29,4),(71,'Andriy','Savchyn','male',NULL,'+1352591476',NULL,NULL,'part',4,30,5),(72,'Petro','Pavlenko','female',NULL,'+1523331251',NULL,NULL,'part',4,28,2),(73,'Nadia','Yaceniuk','male',NULL,'+1478744425',NULL,NULL,'full',4,11,3),(74,'Anya','Yanukovych','male',NULL,'+1828189453',NULL,NULL,'part',3,12,5),(75,'Andriy','Yaceniuk','male',NULL,'+1786846753',NULL,NULL,'full',1,9,2),(76,'Nadia','Barabash','female',NULL,'+1260784239',NULL,NULL,'part',2,35,1),(77,'Ira','Savchyn','female',NULL,'+1587576907',NULL,NULL,'part',1,6,1),(78,'Olena','Poroshenko','male',NULL,'+1628129237',NULL,NULL,'full',2,10,5),(79,'Ira','Barabash','female',NULL,'+1359445558',NULL,NULL,'full',3,23,4),(80,'Bohdan','Shmandrovskyi','male',NULL,'+1432136927',NULL,NULL,'part',6,28,4),(81,'Anya','Poroshenko','female',NULL,'+1222340303',NULL,NULL,'full',2,35,2),(82,'Bohdan','Pupkin','male',NULL,'+1772286411',NULL,NULL,'full',1,31,3),(83,'Nadia','Barabash','male',NULL,'+1659045286',NULL,NULL,'part',4,19,3),(84,'Roman','Yaceniuk','female',NULL,'+1915206008',NULL,NULL,'full',4,7,5),(85,'Petro','Pavlenko','female',NULL,'+1731122408',NULL,NULL,'part',3,20,3),(86,'Bohdan','Poroshenko','male',NULL,'+1396760421',NULL,NULL,'part',3,8,5),(87,'Andriy','Savchyn','female',NULL,'+1734339430',NULL,NULL,'full',3,30,4),(88,'Olena','Pupkin','male',NULL,'+1929561006',NULL,NULL,'part',1,34,1),(89,'Nadia','Lychtei','female',NULL,'+1236093580',NULL,NULL,'part',6,31,1),(90,'Andriy','Yaceniuk','male',NULL,'+1202714388',NULL,NULL,'full',1,28,3),(91,'Olia','Pupkin','male',NULL,'+1711568489',NULL,NULL,'part',4,23,4),(92,'Olia','Pupkin','male',NULL,'+1985240821',NULL,NULL,'full',2,32,2),(93,'Olia','Pupkin','male',NULL,'+1827200428',NULL,NULL,'part',4,19,1),(94,'Ira','Lychtei','female',NULL,'+1333981007',NULL,NULL,'part',5,7,4),(95,'Bohdan','Yaceniuk','male',NULL,'+1989286308',NULL,NULL,'full',3,35,4),(96,'Olena','Shmandrovskyi','male',NULL,'+1538648256',NULL,NULL,'full',3,35,4),(97,'Petro','Pupkin','male',NULL,'+1067796543',NULL,NULL,'full',5,19,1),(98,'Olena','Pavlenko','female',NULL,'+1005939336',NULL,NULL,'part',4,25,3),(99,'Nadia','Savchyn','female',NULL,'+1871868939',NULL,NULL,'full',6,27,3),(100,'Nadia','Shmandrovskyi','male',NULL,'+1461749892',NULL,NULL,'full',1,30,4);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(100) NOT NULL,
  `subject_description` varchar(255) DEFAULT NULL,
  `cathedra_id` int(11) NOT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `subject_id` (`subject_id`),
  KEY `cathedra_id` (`cathedra_id`),
  CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`cathedra_id`) REFERENCES `cathedra` (`cathedra_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Linear Algebra',NULL,5),(2,'Analytic Geometry',NULL,5),(3,'Statistical Economy',NULL,2),(4,'International Economics',NULL,2),(5,'English Grammar',NULL,3),(6,'English Literature',NULL,3),(7,'Kant\'s Philosophy',NULL,4),(8,'Philosphy History',NULL,4),(9,'The Theory of Byilding',NULL,1),(10,'Town Planing',NULL,1);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-27 17:32:11
