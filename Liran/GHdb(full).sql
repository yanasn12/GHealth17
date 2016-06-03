-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: ghealth
-- ------------------------------------------------------
-- Server version	5.6.28-log

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments` (
  `appointment_num` int(11) NOT NULL AUTO_INCREMENT,
  `clinic_num` int(11) NOT NULL,
  `worker_num` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `app_date` varchar(40) NOT NULL,
  `call_date` varchar(10) NOT NULL,
  `results` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`appointment_num`),
  KEY `clinic_num_in_clinics_to_apps_idx` (`clinic_num`),
  KEY `clinic_num_in_Medical_Worker_to_apps_idx` (`worker_num`),
  CONSTRAINT `clinic_num_in_Medical_Worker_to_apps` FOREIGN KEY (`worker_num`) REFERENCES `medical_worker` (`worker_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `clinic_num_in_clinics_to_apps` FOREIGN KEY (`clinic_num`) REFERENCES `clinics` (`clinic_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,11,420,2,'01/06/16-12:00','13/01/16',''),(2,11,425,2,'01/06/16-17:00','20/04/16',NULL),(3,22,424,1,'01/06/16-13:00','03/11/15',NULL),(4,55,421,2,'02/06/16-11:00','03/04/16',NULL),(5,22,420,0,'01/06/16-09:00','29/03/16',NULL),(6,33,421,0,'01/06/16-12:00','10/03/16',NULL),(7,44,422,1,'02/06/16-18:00','18/02/16',NULL);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointments_file`
--

DROP TABLE IF EXISTS `appointments_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments_file` (
  `appointment_file_num` int(11) NOT NULL,
  `appointment_num` int(11) NOT NULL,
  PRIMARY KEY (`appointment_file_num`,`appointment_num`),
  UNIQUE KEY `appointment_num_UNIQUE` (`appointment_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments_file`
--

LOCK TABLES `appointments_file` WRITE;
/*!40000 ALTER TABLE `appointments_file` DISABLE KEYS */;
INSERT INTO `appointments_file` VALUES (1,1),(2,2),(3,3),(1,4),(5,5),(7,6),(7,7);
/*!40000 ALTER TABLE `appointments_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinics`
--

DROP TABLE IF EXISTS `clinics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clinics` (
  `clinic_num` int(11) NOT NULL,
  `clinic_name` varchar(45) DEFAULT NULL,
  `clinic_address` varchar(45) DEFAULT NULL,
  `clinic_phone` varchar(11) DEFAULT NULL,
  `erea_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`clinic_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinics`
--

LOCK TABLES `clinics` WRITE;
/*!40000 ALTER TABLE `clinics` DISABLE KEYS */;
INSERT INTO `clinics` VALUES (11,'Clalit Kiryat Binyamin','Kiryat Ata - Shprinzak 5','04-8478645','4'),(22,'Maccabi','Haifa - Haazmaut 52','04-8453677','4'),(33,'Clalit','Ashkelon - Vardinun 6','03-7167886','3'),(44,'Briut','Jerusalem - Borekasim 3','02-8677654','2'),(55,'Leumit','Sderot - Hasusim 30','03-6118787','3');
/*!40000 ALTER TABLE `clinics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_report`
--

DROP TABLE IF EXISTS `daily_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily_report` (
  `Date` varchar(45) NOT NULL,
  `Day_name` varchar(45) DEFAULT NULL,
  `Patient_amount` int(11) DEFAULT NULL,
  `Waiting_time_avg` float DEFAULT NULL,
  `Waiting_time_max` int(11) DEFAULT NULL,
  `Waiting_time_min` int(11) DEFAULT NULL,
  `Waiting_time_deviation` int(11) DEFAULT NULL,
  `Clinic_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`Date`),
  KEY `Report_for_clinic_idx` (`Clinic_num`),
  CONSTRAINT `Report_for_clinic` FOREIGN KEY (`Clinic_num`) REFERENCES `clinics` (`clinic_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_report`
--

LOCK TABLES `daily_report` WRITE;
/*!40000 ALTER TABLE `daily_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `daily_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `day_ in_week`
--

DROP TABLE IF EXISTS `day_ in_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `day_ in_week` (
  `Start_week_date` varchar(45) NOT NULL,
  `Date` varchar(45) NOT NULL,
  PRIMARY KEY (`Start_week_date`,`Date`),
  KEY `weak->day_idx` (`Date`),
  CONSTRAINT `day_to_week` FOREIGN KEY (`Start_week_date`) REFERENCES `weekly_report` (`Start_week_date`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `week_to_day` FOREIGN KEY (`Date`) REFERENCES `daily_report` (`Date`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `day_ in_week`
--

LOCK TABLES `day_ in_week` WRITE;
/*!40000 ALTER TABLE `day_ in_week` DISABLE KEYS */;
/*!40000 ALTER TABLE `day_ in_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expertise`
--

DROP TABLE IF EXISTS `expertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expertise` (
  `expertise_code` int(11) NOT NULL,
  `expertise_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`expertise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expertise`
--

LOCK TABLES `expertise` WRITE;
/*!40000 ALTER TABLE `expertise` DISABLE KEYS */;
INSERT INTO `expertise` VALUES (1,'Heart'),(2,'Orthopedist'),(3,'Gynecologist'),(4,'ENT'),(5,'Digestive tract');
/*!40000 ALTER TABLE `expertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experts`
--

DROP TABLE IF EXISTS `experts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experts` (
  `worker_num` int(11) NOT NULL,
  `expertise_code` int(11) NOT NULL,
  PRIMARY KEY (`worker_num`),
  KEY `expertise_code_idx` (`expertise_code`),
  CONSTRAINT `expertise_code_in_expertise_to_experts` FOREIGN KEY (`expertise_code`) REFERENCES `expertise` (`expertise_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `worker_num_in_medical_worker_to_experts` FOREIGN KEY (`worker_num`) REFERENCES `medical_worker` (`worker_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experts`
--

LOCK TABLES `experts` WRITE;
/*!40000 ALTER TABLE `experts` DISABLE KEYS */;
INSERT INTO `experts` VALUES (420,1),(423,1),(424,2),(426,2),(422,3),(421,4),(427,4),(425,5);
/*!40000 ALTER TABLE `experts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance_by_expertise`
--

DROP TABLE IF EXISTS `insurance_by_expertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insurance_by_expertise` (
  `expertise_code` int(11) NOT NULL,
  `insurance_level` int(11) NOT NULL,
  PRIMARY KEY (`expertise_code`,`insurance_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_by_expertise`
--

LOCK TABLES `insurance_by_expertise` WRITE;
/*!40000 ALTER TABLE `insurance_by_expertise` DISABLE KEYS */;
INSERT INTO `insurance_by_expertise` VALUES (1,5),(2,2),(3,1),(4,2),(5,4);
/*!40000 ALTER TABLE `insurance_by_expertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurances`
--

DROP TABLE IF EXISTS `insurances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insurances` (
  `insurance_level` int(11) NOT NULL AUTO_INCREMENT,
  `insurance_name` varchar(50) NOT NULL,
  PRIMARY KEY (`insurance_level`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurances`
--

LOCK TABLES `insurances` WRITE;
/*!40000 ALTER TABLE `insurances` DISABLE KEYS */;
INSERT INTO `insurances` VALUES (1,'Makif'),(2,'Silver'),(3,'Gold'),(4,'Primium'),(5,'Platinum');
/*!40000 ALTER TABLE `insurances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab_of_clinic`
--

DROP TABLE IF EXISTS `lab_of_clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab_of_clinic` (
  `lab_num` int(11) NOT NULL,
  `clinic_num` int(11) NOT NULL,
  PRIMARY KEY (`lab_num`,`clinic_num`),
  KEY `clinic_num_in_labs_to_clinics_idx` (`clinic_num`),
  CONSTRAINT `clinic_num_in_labs_to_clinics` FOREIGN KEY (`clinic_num`) REFERENCES `clinics` (`clinic_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lab_num_in_labs_to_lab_of_clinic` FOREIGN KEY (`lab_num`) REFERENCES `labs` (`lab_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_of_clinic`
--

LOCK TABLES `lab_of_clinic` WRITE;
/*!40000 ALTER TABLE `lab_of_clinic` DISABLE KEYS */;
INSERT INTO `lab_of_clinic` VALUES (2,11),(2,22),(1,33),(3,33),(4,44),(1,55),(3,55);
/*!40000 ALTER TABLE `lab_of_clinic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab_test`
--

DROP TABLE IF EXISTS `lab_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab_test` (
  `test_num` int(11) NOT NULL,
  `lab_num` int(11) NOT NULL,
  PRIMARY KEY (`test_num`,`lab_num`),
  KEY `lab_num_idx` (`lab_num`),
  CONSTRAINT `lab_num` FOREIGN KEY (`lab_num`) REFERENCES `labs` (`lab_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `test_num` FOREIGN KEY (`test_num`) REFERENCES `tests_type` (`test_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_test`
--

LOCK TABLES `lab_test` WRITE;
/*!40000 ALTER TABLE `lab_test` DISABLE KEYS */;
INSERT INTO `lab_test` VALUES (1,1),(2,1),(4,1),(1,2),(3,2),(5,2),(5,3),(4,4);
/*!40000 ALTER TABLE `lab_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab_tests`
--

DROP TABLE IF EXISTS `lab_tests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab_tests` (
  `lab_test_num` int(11) NOT NULL,
  `test_num` int(11) DEFAULT NULL,
  `notes` varchar(120) DEFAULT NULL,
  `appointment_num` int(11) DEFAULT NULL,
  `results` varchar(120) DEFAULT NULL,
  `pictures` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`lab_test_num`),
  KEY `test_num_idx` (`test_num`),
  KEY `appointment_num_in_apps_idx` (`appointment_num`),
  CONSTRAINT `appointment_num_in_apps` FOREIGN KEY (`appointment_num`) REFERENCES `appointments` (`appointment_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `test_num_in_types` FOREIGN KEY (`test_num`) REFERENCES `tests_type` (`test_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_tests`
--

LOCK TABLES `lab_tests` WRITE;
/*!40000 ALTER TABLE `lab_tests` DISABLE KEYS */;
INSERT INTO `lab_tests` VALUES (123,1,'May have fiver',1,NULL,NULL),(124,5,'Patient is weak',5,NULL,NULL),(125,5,'Need it fast',3,NULL,NULL),(126,2,'Fear of infection',2,NULL,NULL);
/*!40000 ALTER TABLE `lab_tests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab_tests_file`
--

DROP TABLE IF EXISTS `lab_tests_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab_tests_file` (
  `lab_test_file_num` int(11) NOT NULL,
  `lab_test_num` int(11) NOT NULL,
  PRIMARY KEY (`lab_test_file_num`,`lab_test_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_tests_file`
--

LOCK TABLES `lab_tests_file` WRITE;
/*!40000 ALTER TABLE `lab_tests_file` DISABLE KEYS */;
INSERT INTO `lab_tests_file` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `lab_tests_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `labs`
--

DROP TABLE IF EXISTS `labs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labs` (
  `lab_num` int(11) NOT NULL,
  `lab_name` varchar(45) DEFAULT NULL,
  `lab_address` varchar(45) DEFAULT NULL,
  `lab_phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`lab_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labs`
--

LOCK TABLES `labs` WRITE;
/*!40000 ALTER TABLE `labs` DISABLE KEYS */;
INSERT INTO `labs` VALUES (1,'Milenium','Beer Sheva - Yosef haburskai 1','072-3211644'),(2,'American Medical','Nahariya - Susim 6','04-8778534'),(3,'EMC Medical Center','Rishon Letzion - Hanachol 30','072-3222584'),(4,'Ofek Labs','Jerusalem - Diskin 9','072-3224079');
/*!40000 ALTER TABLE `labs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_files`
--

DROP TABLE IF EXISTS `medical_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_files` (
  `medical_file_num` int(11) NOT NULL AUTO_INCREMENT,
  `appointment_file_num` int(11) DEFAULT NULL,
  `references_file_num` int(11) DEFAULT NULL,
  `lab_test_file_num` int(11) DEFAULT NULL,
  `report_file_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`medical_file_num`),
  UNIQUE KEY `appointment_file_num_UNIQUE` (`appointment_file_num`),
  UNIQUE KEY `references_file_num_UNIQUE` (`references_file_num`),
  UNIQUE KEY `lab_test_file_num_UNIQUE` (`lab_test_file_num`),
  UNIQUE KEY `report_file_num_UNIQUE` (`report_file_num`),
  CONSTRAINT `app_file_num_in_apps_file_to_med_files` FOREIGN KEY (`appointment_file_num`) REFERENCES `appointments_file` (`appointment_file_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lbtstf_num_in_lbtst_file_to_med_files` FOREIGN KEY (`lab_test_file_num`) REFERENCES `lab_tests_file` (`lab_test_file_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ref_file_num_in_ref_files_to_medical_files` FOREIGN KEY (`references_file_num`) REFERENCES `references_files` (`references_file_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rep_file_num_in_rep_file_to_med_files` FOREIGN KEY (`report_file_num`) REFERENCES `report_file` (`report_file_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_files`
--

LOCK TABLES `medical_files` WRITE;
/*!40000 ALTER TABLE `medical_files` DISABLE KEYS */;
INSERT INTO `medical_files` VALUES (1,1,1,1,1),(2,2,2,2,2),(3,3,3,3,3),(4,7,4,4,4),(5,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `medical_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_worker`
--

DROP TABLE IF EXISTS `medical_worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_worker` (
  `worker_num` int(11) NOT NULL,
  `password` varchar(15) NOT NULL,
  `person_id` varchar(10) NOT NULL,
  `is_connected` tinyint(1) DEFAULT '0',
  `worker_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`worker_num`),
  UNIQUE KEY `person_id_UNIQUE` (`person_id`),
  KEY `person_id_idx` (`person_id`),
  CONSTRAINT `pid_in_med_work_to_people` FOREIGN KEY (`person_id`) REFERENCES `people` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_worker`
--

LOCK TABLES `medical_worker` WRITE;
/*!40000 ALTER TABLE `medical_worker` DISABLE KEYS */;
INSERT INTO `medical_worker` VALUES (120,'cm0','111555999',0,'1'),(121,'cm1','112233445',0,'1'),(122,'cm2','123123123',0,'1'),(123,'cm3','100100100',0,'1'),(124,'cm4','123451234',0,'1'),(220,'lw0','222444666',0,'2'),(221,'lw1','321321321',0,'2'),(222,'lw2','333555777',0,'2'),(223,'lw3','444555666',0,'2'),(224,'lw4','975975975',0,'2'),(320,'ds0','987987987',0,'3'),(321,'ds1','998877665',0,'3'),(420,'exp0','999888777',0,'4'),(421,'exp1','987654321',0,'4'),(422,'exp2','135135135',0,'4'),(423,'exp3','111555000',0,'4'),(424,'exp4','908908908',0,'4'),(425,'exp5','777888666',0,'4'),(426,'exp6','999666111',0,'4'),(427,'exp7','890890890',0,'4'),(999,'manager','111222333',0,'0');
/*!40000 ALTER TABLE `medical_worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_worker_in_clinic`
--

DROP TABLE IF EXISTS `medical_worker_in_clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_worker_in_clinic` (
  `clinic_num` int(11) NOT NULL,
  `worker_num` int(11) NOT NULL,
  `date` varchar(10) NOT NULL,
  `h8` int(11) NOT NULL DEFAULT '0',
  `h9` int(11) NOT NULL DEFAULT '0',
  `h10` int(11) NOT NULL DEFAULT '0',
  `h11` int(11) NOT NULL DEFAULT '0',
  `h12` int(11) NOT NULL DEFAULT '0',
  `h13` int(11) NOT NULL DEFAULT '0',
  `h14` int(11) NOT NULL DEFAULT '0',
  `h15` int(11) NOT NULL DEFAULT '0',
  `h16` int(11) NOT NULL DEFAULT '0',
  `h17` int(11) NOT NULL DEFAULT '0',
  `h18` int(11) NOT NULL DEFAULT '0',
  `h19` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`clinic_num`,`date`,`worker_num`),
  KEY `worker_num_idx` (`worker_num`),
  CONSTRAINT `clinic_num_in_clinics_to_mwic` FOREIGN KEY (`clinic_num`) REFERENCES `clinics` (`clinic_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `worker_num_in_med_worker_to_mwic` FOREIGN KEY (`worker_num`) REFERENCES `medical_worker` (`worker_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_worker_in_clinic`
--

LOCK TABLES `medical_worker_in_clinic` WRITE;
/*!40000 ALTER TABLE `medical_worker_in_clinic` DISABLE KEYS */;
INSERT INTO `medical_worker_in_clinic` VALUES (11,420,'01/06/16',0,1,0,1,1,0,1,1,0,1,0,0),(11,423,'01/06/16',0,0,1,1,1,0,0,0,1,1,0,0),(11,425,'01/06/16',1,0,0,1,0,1,1,0,1,0,0,1),(11,420,'02/06/16',1,0,1,1,1,0,1,0,0,1,0,0),(11,425,'02/06/16',0,1,1,1,0,0,1,0,0,0,1,1),(22,420,'01/06/16',1,0,1,0,1,1,0,1,1,1,0,1),(22,424,'01/06/16',1,1,0,1,0,0,1,0,0,0,0,1),(22,424,'02/06/16',0,0,0,1,0,0,1,0,0,0,1,0),(33,421,'01/06/16',0,1,0,0,1,1,0,1,1,0,0,0),(33,421,'02/06/16',1,0,1,0,1,1,1,0,0,1,1,1),(44,423,'01/06/16',0,1,1,1,1,0,1,1,1,1,0,0),(44,422,'02/06/16',0,1,1,1,0,0,0,1,1,0,1,0),(55,421,'01/06/16',1,0,0,1,1,0,1,0,1,1,1,0),(55,426,'01/06/16',0,1,1,0,0,0,0,1,0,0,0,1),(55,427,'01/06/16',0,0,0,0,1,0,1,0,0,0,1,0),(55,426,'02/06/16',0,0,1,1,1,1,1,0,1,0,0,0);
/*!40000 ALTER TABLE `medical_worker_in_clinic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_report`
--

DROP TABLE IF EXISTS `monthly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_report` (
  `Month_date` varchar(45) NOT NULL,
  `etc` varchar(45) DEFAULT NULL,
  `clinic_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`Month_date`),
  KEY `monthy_r_to_clinc_idx` (`clinic_num`),
  CONSTRAINT `monthy_r_to_clinc` FOREIGN KEY (`clinic_num`) REFERENCES `clinics` (`clinic_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_report`
--

LOCK TABLES `monthly_report` WRITE;
/*!40000 ALTER TABLE `monthly_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `monthly_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patients` (
  `patient_num` int(11) NOT NULL,
  `person_id` varchar(10) DEFAULT NULL,
  `insurance_level` int(11) DEFAULT NULL,
  `insurance_validity` int(1) DEFAULT '0',
  `medical_file_num` int(11) DEFAULT NULL,
  `extensive_clinic_email` varchar(45) DEFAULT NULL,
  `leave_date` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`patient_num`),
  UNIQUE KEY `medical_file_num_UNIQUE` (`medical_file_num`),
  KEY `person_id_idx` (`person_id`),
  KEY `insurance_level_idx` (`insurance_level`),
  CONSTRAINT `insurance_level` FOREIGN KEY (`insurance_level`) REFERENCES `insurances` (`insurance_level`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `person_id` FOREIGN KEY (`person_id`) REFERENCES `people` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1100,'200200200',1,1,1,'ramzclinic@gmail.com',NULL),(1110,'300300300',5,1,2,'medart@gmail.com',NULL),(1120,'400400400',2,1,3,'nozha@gmail.com',NULL),(1130,'456456456',4,0,4,'garaclinic@gmail.com',NULL),(1140,'500500500',2,1,5,'haktgid@gmail.com','03/05/16'),(1150,'600600600',3,1,6,'laron@gmail.com',NULL),(1160,'666333111',4,0,7,'citydent@gmail.com',NULL),(1170,'678678678',2,1,8,'reshefnao@gmail.com',NULL),(1180,'876876876',4,1,9,'hadasa@gmail.com','16/03/16'),(1190,'111555000',3,0,10,'isa@gmail.com',NULL);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `person_id` varchar(10) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `birth_date` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES ('100100100','Sara','Shemesh','Jerusalem - Yad Harutzim 15','sara@gmail.com','053-9378661','02/10/43'),('111222333','Shon','Gal','Netivot - Shmona 8','shon@gmail.com','050-8810450','24/03/60'),('111555000','Ofer','Hadad','Jerusalem - Ein Karm 74','ofer@gmail.com','054-3249663','05/12/85'),('111555999','Lauri','Ylonen','Carmiel - nofit 5','lauri@gmail.com','050-7116099','23/04/79'),('112233445','Rona','Warner','Netania - Swechter 11','rona@gmail.com','050-5477111','16/05/91'),('123123123','Leon','Adams','Rahat - Sigalim 8','leon@gmail.com','052-3902215','01/01/89'),('123451234','Shira','White','Lod - Rabin 6','shira@gmail.com','052-3794934','01/05/72'),('135135135','Gil','Dror','Ashdod - malachi 3','gil@gmail.com','050-7756433','19/09/63'),('200200200','Beny','Miler','Abu Gosh - Shalom street 27','beny@gmail.com','053-7655648','08/03/91'),('222444666','Eero','Heinonen','Haifa - Hadar 3','eero@walla.com','0536261176','27/11/83'),('300300300','Gal','Reuvner','Netaniam - Oved ben ami 10','gal@gmail.com','052-8777865','11/11/71'),('321321321','Pauli','Rantasalmi','Naharia - Vradim 9/5','pauli@gmail.com','052-8477345','01/05/88'),('333555777','Moris','Osto','Beit Shemesh - Menahem 5','moris@gmail.com','054-8778654','29/03/83'),('400400400','Dani','Feldman','Petach Tikva - Haim ozer 4','dani@gmail.com','054-9876678','15/12/69'),('444555666','Aki','Hakala','Carmiel - Shushan 8','aki@nana.co.il','050-8136264','28/10/95'),('456456456','Ohad','Shaul','Eliad - Haimnb 6','ohad@nana.co.il','054-7785681','07/07/38'),('500500500','Rahel','Rebinovich','Haifa - Kibutz Galuyot 23','rahel@gmail.com','054-6556439','21/12/45'),('600600600','Ora','Zanani','Hertzelia-  Ben guryon 22','ora@gmail.com','053-1990019','23/04/79'),('666333111','Michael','Laderman','Tel Aviv - Yehazkael 6','michael@gmail.com','052-7710180','28/04/67'),('678678678','Urit','Amsalem','Misgav - Golda 15','urit@nana.co.il','050-8877300','19/08/90'),('777888666','Zipy','Menahem','Amiad - Menahem 2','zipy@nana.co.il','050-6663328','15/05/87'),('876876876','Nir','Tzafary','Tzfat - Snesh','nir@nana.co.il','050-7499038','26/08/77'),('890890890','Dorit','Rahom','Amiad - choko 9','dorit@walla.com','054-7306077','14/05/72'),('908908908','Dudu','Grinberg','Binyamina - Mofaz 10','dudu@nana.co.il','054-9788204','20/10/80'),('975975975','Dana','Alul','Netaniya - Korman 15','dana@gmail.com','050-4554654','23/11/85'),('987654321','Ema','Shapi','Lod-  Dar 6','ema@gmail.com','052-8776789','26/02/55'),('987987987','Siri','Shitrit','Rahat - Nurit 7','siri@gmail.com','054-4566765','26/02/57'),('998877665','Sivan','Molko','Lod - Prahim 1','sivan@gmail.com','054-4747821','30/05/51'),('999666111','Sapir','Hakam','Raanana - Ahuza 117','sapirh@gmail.com','053-8618862','05/05/66'),('999888777','Dan','Haim','Dimona - shushan 6','dan@walla.com','053-6231765','07/12/49');
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `references_files`
--

DROP TABLE IF EXISTS `references_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `references_files` (
  `references_file_num` int(11) NOT NULL,
  `referral_num` int(11) NOT NULL,
  PRIMARY KEY (`references_file_num`,`referral_num`),
  UNIQUE KEY `referral_num_UNIQUE` (`referral_num`),
  CONSTRAINT `referral_num_in_referrals` FOREIGN KEY (`referral_num`) REFERENCES `referrals` (`referral_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `references_files`
--

LOCK TABLES `references_files` WRITE;
/*!40000 ALTER TABLE `references_files` DISABLE KEYS */;
INSERT INTO `references_files` VALUES (1,1),(1,2),(1,3),(2,4),(3,5),(4,6),(4,7),(5,8),(4,9);
/*!40000 ALTER TABLE `references_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referrals`
--

DROP TABLE IF EXISTS `referrals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referrals` (
  `referral_num` int(11) NOT NULL AUTO_INCREMENT,
  `clinic_num` int(11) DEFAULT NULL,
  `expertise_code` int(11) DEFAULT NULL,
  `referring_doctor_name` varchar(45) DEFAULT NULL,
  `referring_doctor_email` varchar(45) DEFAULT NULL,
  `urgency` int(11) DEFAULT NULL,
  `reason` varchar(120) NOT NULL,
  `appointment_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`referral_num`),
  KEY `expertise_code_in_expertise_to_referrals_idx` (`expertise_code`),
  KEY `appointments_ref_to_referrals_idx` (`appointment_num`),
  CONSTRAINT `appointments_ref_to_referrals` FOREIGN KEY (`appointment_num`) REFERENCES `appointments` (`appointment_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `expertise_code_in_expertise_to_referrals` FOREIGN KEY (`expertise_code`) REFERENCES `expertise` (`expertise_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referrals`
--

LOCK TABLES `referrals` WRITE;
/*!40000 ALTER TABLE `referrals` DISABLE KEYS */;
INSERT INTO `referrals` VALUES (1,11,1,'Leibovich Moisy','moisy@gmail.com',5,'Chest pain - Fainting',NULL),(2,11,1,'Rahamim Saadia','saadiram@gmail.com',5,'Peripheral edema - tiredness - Shortness of breath',NULL),(3,11,5,'Libin Leor','leorlib@gmail.com',2,'Lower abdominal pain - Burning urination',NULL),(4,22,1,'Dekel Elias','elias@gmail.com',5,'Fainting, tiredness -  Shortness of breath',NULL),(5,22,2,'Repoport Eran','eranrep@gmail.com',3,'back pain',NULL),(6,33,4,'Gragora Adi','gragora@gmail.com',4,'A discharge and ear',NULL),(7,44,3,'Faadel Hany','hanyfad@gmail.com',1,'Irregular menstrual cycles',NULL),(8,44,1,'Kera Tamra','kerat@gmail.com',5,'Heart palpitations - chest pain',NULL),(9,55,4,'Viar Galina','galiav@gmail.com',5,'A hearing problem - redness of the eardrum',NULL);
/*!40000 ALTER TABLE `referrals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_file`
--

DROP TABLE IF EXISTS `report_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_file` (
  `report_file_num` int(11) NOT NULL,
  `report_num` int(11) NOT NULL,
  `expertise_code` int(11) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `internal\extrnal` int(11) DEFAULT NULL,
  PRIMARY KEY (`report_file_num`,`report_num`),
  UNIQUE KEY `report_num_UNIQUE` (`report_num`),
  KEY `expertise_code_in_expertise_idx` (`expertise_code`),
  CONSTRAINT `expertise_code_in_expertise_to_repf` FOREIGN KEY (`expertise_code`) REFERENCES `expertise` (`expertise_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_file`
--

LOCK TABLES `report_file` WRITE;
/*!40000 ALTER TABLE `report_file` DISABLE KEYS */;
INSERT INTO `report_file` VALUES (1,1,1,'Heart description',1),(1,2,2,'Ortoped description',1),(2,3,2,'Ortoped description',0),(3,4,4,'ENT description',1),(4,5,5,'Urine description',1),(4,6,3,'Gani description',0);
/*!40000 ALTER TABLE `report_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tests_type`
--

DROP TABLE IF EXISTS `tests_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tests_type` (
  `test_num` int(11) NOT NULL,
  `test_name` varchar(45) DEFAULT NULL,
  `test_description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`test_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tests_type`
--

LOCK TABLES `tests_type` WRITE;
/*!40000 ALTER TABLE `tests_type` DISABLE KEYS */;
INSERT INTO `tests_type` VALUES (1,'blood','blood test'),(2,'urine','urine test'),(3,'feces','feces test'),(4,'Haartests','Haartests test'),(5,'general','general tests= sugar - cholesterol - blood pressure');
/*!40000 ALTER TABLE `tests_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `week_in_month`
--

DROP TABLE IF EXISTS `week_in_month`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `week_in_month` (
  `Start_week_date` varchar(45) NOT NULL,
  `Month_date` varchar(45) NOT NULL,
  PRIMARY KEY (`Start_week_date`,`Month_date`),
  KEY `week_to_month_idx` (`Month_date`),
  CONSTRAINT `month_to_week` FOREIGN KEY (`Start_week_date`) REFERENCES `weekly_report` (`Start_week_date`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `week_to_month` FOREIGN KEY (`Month_date`) REFERENCES `monthly_report` (`Month_date`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `week_in_month`
--

LOCK TABLES `week_in_month` WRITE;
/*!40000 ALTER TABLE `week_in_month` DISABLE KEYS */;
/*!40000 ALTER TABLE `week_in_month` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weekly_report`
--

DROP TABLE IF EXISTS `weekly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weekly_report` (
  `Start_week_date` varchar(45) NOT NULL,
  `Week_num` int(11) DEFAULT NULL,
  `Weekly_patient_amount_avg` float DEFAULT NULL,
  `Weekly_patient_amount_max` int(11) DEFAULT NULL,
  `Weekly_patient_amount_min` int(11) DEFAULT NULL,
  `Weekly_patient_amount_deviation` int(11) DEFAULT NULL,
  `Weekly_waiting_time_avg` float DEFAULT NULL,
  `Weekly_waiting_time_max` int(11) DEFAULT NULL,
  `Weekly_waiting_time_min` int(11) DEFAULT NULL,
  `Weekly_waiting_time_deviation` int(11) DEFAULT NULL,
  `clinic_num` int(11) DEFAULT NULL,
  `Whole_Partial` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Start_week_date`),
  KEY `weakly_r_for_clinc_idx` (`clinic_num`),
  CONSTRAINT `weakly_r_for_clinc` FOREIGN KEY (`clinic_num`) REFERENCES `clinics` (`clinic_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weekly_report`
--

LOCK TABLES `weekly_report` WRITE;
/*!40000 ALTER TABLE `weekly_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `weekly_report` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-03 13:27:06
