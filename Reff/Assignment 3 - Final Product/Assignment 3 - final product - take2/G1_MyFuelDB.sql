-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: myfuel
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `amount_data_per_fuel`
--

DROP TABLE IF EXISTS `amount_data_per_fuel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amount_data_per_fuel` (
  `amountDataPerFuelID` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `fuelTypeID` int(11) DEFAULT NULL,
  `ReportID` int(11) DEFAULT NULL,
  PRIMARY KEY (`amountDataPerFuelID`),
  UNIQUE KEY `amountDataPerFuelID_UNIQUE` (`amountDataPerFuelID`),
  KEY `fk_AmoutDataPerFuelReport_idx` (`ReportID`),
  CONSTRAINT `fk_AmoutDataPerFuelReport` FOREIGN KEY (`ReportID`) REFERENCES `report` (`reportID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amount_data_per_fuel`
--

LOCK TABLES `amount_data_per_fuel` WRITE;
/*!40000 ALTER TABLE `amount_data_per_fuel` DISABLE KEYS */;
INSERT INTO `amount_data_per_fuel` VALUES (0,10,0,0),(1,20,1,0),(2,30,2,0),(3,10,0,1),(4,20,1,1),(5,30,2,1);
/*!40000 ALTER TABLE `amount_data_per_fuel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `carID` int(11) NOT NULL,
  `subscriptionID` int(11) DEFAULT NULL,
  `fuelTypeID` int(11) DEFAULT NULL,
  `carCustomerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`carID`),
  UNIQUE KEY `carID_UNIQUE` (`carID`),
  KEY `fk_CarSubscription_idx` (`subscriptionID`),
  KEY `fk_CarFuelType_idx` (`fuelTypeID`),
  KEY `fk_CarCarCustomer_idx` (`carCustomerID`),
  CONSTRAINT `fk_CarCarCustomer` FOREIGN KEY (`carCustomerID`) REFERENCES `car_customer` (`carCustomerID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CarFuelType` FOREIGN KEY (`fuelTypeID`) REFERENCES `fuel_type` (`fuelTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CarSubscription` FOREIGN KEY (`subscriptionID`) REFERENCES `subscription` (`subscriptionID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (0,1,0,3),(1,2,2,3),(2,3,1,3),(3,4,0,3),(4,5,0,9);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_customer`
--

DROP TABLE IF EXISTS `car_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car_customer` (
  `carCustomerID` int(11) NOT NULL,
  `purchasePlanID` int(11) DEFAULT NULL,
  PRIMARY KEY (`carCustomerID`),
  UNIQUE KEY `carCustomerID_UNIQUE` (`carCustomerID`),
  KEY `fk_CarCustomerPurchasePlan_idx` (`purchasePlanID`),
  CONSTRAINT `fk_CarCustomerPurchasePlan` FOREIGN KEY (`purchasePlanID`) REFERENCES `purchase_plan` (`purchasePlanID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_customer`
--

LOCK TABLES `car_customer` WRITE;
/*!40000 ALTER TABLE `car_customer` DISABLE KEYS */;
INSERT INTO `car_customer` VALUES (3,0),(9,0),(1,1);
/*!40000 ALTER TABLE `car_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_fuel_order`
--

DROP TABLE IF EXISTS `car_fuel_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car_fuel_order` (
  `carFuelOrderID` int(11) NOT NULL,
  `driverName` varchar(45) DEFAULT NULL,
  `carID` int(11) DEFAULT NULL,
  `stationID` int(11) DEFAULT NULL,
  PRIMARY KEY (`carFuelOrderID`),
  UNIQUE KEY `carFuelOrderID_UNIQUE` (`carFuelOrderID`),
  KEY `fk_CarFuelOrderCar_idx` (`carID`),
  KEY `fk_CarFuelOrderStation_idx` (`stationID`),
  CONSTRAINT `fk_CarFuelOrderCar` FOREIGN KEY (`carID`) REFERENCES `car` (`carID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CarFuelOrderOrder` FOREIGN KEY (`carFuelOrderID`) REFERENCES `order` (`orderID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CarFuelOrderStation` FOREIGN KEY (`stationID`) REFERENCES `station` (`stationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_fuel_order`
--

LOCK TABLES `car_fuel_order` WRITE;
/*!40000 ALTER TABLE `car_fuel_order` DISABLE KEYS */;
INSERT INTO `car_fuel_order` VALUES (0,'testDriver1',2,0),(2,'Sergei',4,0),(3,'Sergei',4,0),(5,'TestDriver2',1,1),(6,'TestDriver2',2,1),(7,'Sergei In Disguise',2,0);
/*!40000 ALTER TABLE `car_fuel_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cash`
--

DROP TABLE IF EXISTS `cash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cash` (
  `cashID` int(11) NOT NULL,
  PRIMARY KEY (`cashID`),
  UNIQUE KEY `cashID_UNIQUE` (`cashID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cash`
--

LOCK TABLES `cash` WRITE;
/*!40000 ALTER TABLE `cash` DISABLE KEYS */;
INSERT INTO `cash` VALUES (1),(4);
/*!40000 ALTER TABLE `cash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card` (
  `creditCardID` int(11) NOT NULL,
  `cardNo` varchar(45) DEFAULT NULL,
  `validDate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`creditCardID`),
  UNIQUE KEY `creditCardID_UNIQUE` (`creditCardID`),
  CONSTRAINT `fk_paymentInfoCreditCard` FOREIGN KEY (`creditCardID`) REFERENCES `payment_info` (`paymentInfoID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (0,'0000111122223333','2050-01-00'),(3,'1234123412341234','2050-01-00');
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerID` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `discriminator` int(11) DEFAULT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `customerID_UNIQUE` (`customerID`),
  CONSTRAINT `fk_CustomerUser` FOREIGN KEY (`customerID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'testAddr1',1),(3,'testAddr2',1),(4,'testAddr3',2),(9,'testAddr4',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_per_customer`
--

DROP TABLE IF EXISTS `data_per_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_per_customer` (
  `dataPerCustomerID` int(11) NOT NULL,
  `orderCount` int(11) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  `periodicCustomerReportID` int(11) DEFAULT NULL,
  PRIMARY KEY (`dataPerCustomerID`),
  UNIQUE KEY `dataPerCustomerID_UNIQUE` (`dataPerCustomerID`),
  KEY `fk_DataPerCustomerPeriodicCustomerReport_idx` (`periodicCustomerReportID`),
  KEY `fk_DataPerCustomerCustomer_idx` (`customerID`),
  CONSTRAINT `fk_DataPerCustomerCustomer` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DataPerCustomerPeriodicCustomerReport` FOREIGN KEY (`periodicCustomerReportID`) REFERENCES `periodic_customer_report` (`periodicCustomerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_per_customer`
--

LOCK TABLES `data_per_customer` WRITE;
/*!40000 ALTER TABLE `data_per_customer` DISABLE KEYS */;
INSERT INTO `data_per_customer` VALUES (0,15,1,2),(1,5,3,2),(2,12,4,2),(3,42,9,2);
/*!40000 ALTER TABLE `data_per_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_per_customer_type`
--

DROP TABLE IF EXISTS `data_per_customer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_per_customer_type` (
  `dataPerCustomerTypeID` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `hourScores` varchar(96) DEFAULT NULL,
  `generatedScoreReportID` int(11) DEFAULT NULL,
  `userTypeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`dataPerCustomerTypeID`),
  UNIQUE KEY `dataPerCustomerTypeID_UNIQUE` (`dataPerCustomerTypeID`),
  KEY `fk_DataPerCustomerTypeGeneratedScoreReport_idx` (`generatedScoreReportID`),
  KEY `fk_DataPerCustomerTypeUserType_idx` (`userTypeID`),
  CONSTRAINT `fk_DataPerCustomerTypeGeneratedScoreReport` FOREIGN KEY (`generatedScoreReportID`) REFERENCES `generated_score_report` (`generatedScoreReportID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DataPerCustomerTypeUserType` FOREIGN KEY (`userTypeID`) REFERENCES `user_type` (`userTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_per_customer_type`
--

LOCK TABLES `data_per_customer_type` WRITE;
/*!40000 ALTER TABLE `data_per_customer_type` DISABLE KEYS */;
INSERT INTO `data_per_customer_type` VALUES (0,6,'01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|',4,2),(1,8,'01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|01|',4,3);
/*!40000 ALTER TABLE `data_per_customer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount_feedback_report`
--

DROP TABLE IF EXISTS `discount_feedback_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount_feedback_report` (
  `discountFeedbackReportID` int(11) NOT NULL,
  `overallOrders` int(11) DEFAULT NULL,
  `periodicDiscountID` int(11) DEFAULT NULL,
  PRIMARY KEY (`discountFeedbackReportID`),
  UNIQUE KEY `discountFeedbackReportID_UNIQUE` (`discountFeedbackReportID`),
  KEY `fk_DiscountFeedbackReportPeriodicDiscount_idx` (`periodicDiscountID`),
  CONSTRAINT `fk_DiscountFeedbackReportPeriodicDiscount` FOREIGN KEY (`periodicDiscountID`) REFERENCES `periodic_discount` (`periodicDiscountID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DiscountFeedbackReportReport` FOREIGN KEY (`discountFeedbackReportID`) REFERENCES `report` (`reportID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_feedback_report`
--

LOCK TABLES `discount_feedback_report` WRITE;
/*!40000 ALTER TABLE `discount_feedback_report` DISABLE KEYS */;
INSERT INTO `discount_feedback_report` VALUES (5,50,0);
/*!40000 ALTER TABLE `discount_feedback_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount_table`
--

DROP TABLE IF EXISTS `discount_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount_table` (
  `discountTableID` int(11) NOT NULL,
  `discountTable` varchar(96) DEFAULT NULL,
  `discountTableChanges` varchar(96) DEFAULT NULL,
  PRIMARY KEY (`discountTableID`),
  UNIQUE KEY `discountTableID_UNIQUE` (`discountTableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_table`
--

LOCK TABLES `discount_table` WRITE;
/*!40000 ALTER TABLE `discount_table` DISABLE KEYS */;
INSERT INTO `discount_table` VALUES (0,'00|04|10|03|02|03|04|','00|04|10|03|02|03|04|');
/*!40000 ALTER TABLE `discount_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel_replanish`
--

DROP TABLE IF EXISTS `fuel_replanish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fuel_replanish` (
  `FuelReplanishID` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `deliveryDate` varchar(45) DEFAULT NULL,
  `fuelTypeID` int(11) DEFAULT NULL,
  `stationID` int(11) DEFAULT NULL,
  PRIMARY KEY (`FuelReplanishID`),
  UNIQUE KEY `FuelReplanishID_UNIQUE` (`FuelReplanishID`),
  KEY `fk_FuelReplanishFuelStock_idx` (`stationID`,`fuelTypeID`),
  CONSTRAINT `fk_FuelReplanishFuelStock` FOREIGN KEY (`stationID`, `fuelTypeID`) REFERENCES `fuel_stock` (`stationID`, `FuelTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_replanish`
--

LOCK TABLES `fuel_replanish` WRITE;
/*!40000 ALTER TABLE `fuel_replanish` DISABLE KEYS */;
INSERT INTO `fuel_replanish` VALUES (0,200,2,'2014-12-27',0,0),(1,310,0,'null',0,0);
/*!40000 ALTER TABLE `fuel_replanish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel_stock`
--

DROP TABLE IF EXISTS `fuel_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fuel_stock` (
  `stationID` int(11) NOT NULL,
  `FuelTypeID` int(11) NOT NULL,
  `stockAmount` int(11) DEFAULT NULL,
  `minStockLevel` int(11) DEFAULT NULL,
  `maxStockLevel` int(11) DEFAULT NULL,
  `lastFuelReplanishID` int(11) DEFAULT NULL,
  PRIMARY KEY (`stationID`,`FuelTypeID`),
  KEY `fk_FuelStockFuelType_idx` (`FuelTypeID`),
  KEY `fk_FuelStockLastFUelReplanish_idx` (`lastFuelReplanishID`),
  CONSTRAINT `fk_FuelStockFuelType` FOREIGN KEY (`FuelTypeID`) REFERENCES `fuel_type` (`fuelTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_FuelStockLastFUelReplanish` FOREIGN KEY (`lastFuelReplanishID`) REFERENCES `fuel_replanish` (`FuelReplanishID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_FuelStockStation` FOREIGN KEY (`stationID`) REFERENCES `station` (`stationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_stock`
--

LOCK TABLES `fuel_stock` WRITE;
/*!40000 ALTER TABLE `fuel_stock` DISABLE KEYS */;
INSERT INTO `fuel_stock` VALUES (0,0,190,200,500,0),(0,1,500,200,500,0),(0,2,200,200,500,0),(0,3,500,200,500,0),(1,0,400,300,600,0),(1,1,243,200,500,0),(1,2,190,100,300,0),(1,3,450,400,800,0);
/*!40000 ALTER TABLE `fuel_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fuel_type`
--

DROP TABLE IF EXISTS `fuel_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fuel_type` (
  `fuelTypeID` int(11) NOT NULL,
  `fuelName` varchar(255) DEFAULT NULL,
  `basePrice` double DEFAULT NULL,
  PRIMARY KEY (`fuelTypeID`),
  UNIQUE KEY `fuelTypeID_UNIQUE` (`fuelTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_type`
--

LOCK TABLES `fuel_type` WRITE;
/*!40000 ALTER TABLE `fuel_type` DISABLE KEYS */;
INSERT INTO `fuel_type` VALUES (0,'Gasoline',10),(1,'Disel',12),(2,'Scooter Fuel',8),(3,'House Fuel',10);
/*!40000 ALTER TABLE `fuel_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generated_score_report`
--

DROP TABLE IF EXISTS `generated_score_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generated_score_report` (
  `generatedScoreReportID` int(11) NOT NULL,
  PRIMARY KEY (`generatedScoreReportID`),
  UNIQUE KEY `generatedScoreReportID_UNIQUE` (`generatedScoreReportID`),
  CONSTRAINT `fk_GeneratedScoreReportReport` FOREIGN KEY (`generatedScoreReportID`) REFERENCES `report` (`reportID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generated_score_report`
--

LOCK TABLES `generated_score_report` WRITE;
/*!40000 ALTER TABLE `generated_score_report` DISABLE KEYS */;
INSERT INTO `generated_score_report` VALUES (4);
/*!40000 ALTER TABLE `generated_score_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house_fuel_info`
--

DROP TABLE IF EXISTS `house_fuel_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house_fuel_info` (
  `houseFuelInfoID` int(11) NOT NULL,
  `deliveryStatus` int(11) DEFAULT NULL,
  `estimatedTimeLeft` int(11) DEFAULT NULL,
  `houseFuelOrderID` int(11) DEFAULT NULL,
  PRIMARY KEY (`houseFuelInfoID`),
  UNIQUE KEY `houseFuelInfoID_UNIQUE` (`houseFuelInfoID`),
  KEY `fk_HouseFuelInfoHouseFuelOrder_idx` (`houseFuelOrderID`),
  CONSTRAINT `fk_HouseFuelInfoHouseFuelOrder` FOREIGN KEY (`houseFuelOrderID`) REFERENCES `house_fuel_order` (`houseFuelOrderID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_fuel_info`
--

LOCK TABLES `house_fuel_info` WRITE;
/*!40000 ALTER TABLE `house_fuel_info` DISABLE KEYS */;
INSERT INTO `house_fuel_info` VALUES (0,1,0,1),(1,2,0,4);
/*!40000 ALTER TABLE `house_fuel_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house_fuel_order`
--

DROP TABLE IF EXISTS `house_fuel_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house_fuel_order` (
  `houseFuelOrderID` int(11) NOT NULL,
  `urgent` bit(1) DEFAULT NULL,
  `deliveryTime` varchar(45) DEFAULT NULL,
  `houseFuelInfoID` int(11) DEFAULT NULL,
  `houseOwnerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`houseFuelOrderID`),
  UNIQUE KEY `houseFuelOrderID_UNIQUE` (`houseFuelOrderID`),
  KEY `fk_HouseFuelOrderHouseOwner_idx` (`houseOwnerID`),
  CONSTRAINT `fk_HouseFuelOrderHouseOwner` FOREIGN KEY (`houseOwnerID`) REFERENCES `house_owner` (`houseOwnerID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_HouseFuelOrderOrder` FOREIGN KEY (`houseFuelOrderID`) REFERENCES `order` (`orderID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_fuel_order`
--

LOCK TABLES `house_fuel_order` WRITE;
/*!40000 ALTER TABLE `house_fuel_order` DISABLE KEYS */;
INSERT INTO `house_fuel_order` VALUES (1,'','2016-01-15 17:05:50',0,4),(4,'\0','2016-01-18 12:00:00',1,4);
/*!40000 ALTER TABLE `house_fuel_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house_owner`
--

DROP TABLE IF EXISTS `house_owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house_owner` (
  `houseOwnerID` int(11) NOT NULL,
  PRIMARY KEY (`houseOwnerID`),
  UNIQUE KEY `houseOwnerID_UNIQUE` (`houseOwnerID`),
  CONSTRAINT `fk_HouseOwnerCustomer` FOREIGN KEY (`houseOwnerID`) REFERENCES `customer` (`customerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_owner`
--

LOCK TABLES `house_owner` WRITE;
/*!40000 ALTER TABLE `house_owner` DISABLE KEYS */;
INSERT INTO `house_owner` VALUES (4);
/*!40000 ALTER TABLE `house_owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income_report`
--

DROP TABLE IF EXISTS `income_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income_report` (
  `incomeReportID` int(11) NOT NULL,
  `totalIncome` double DEFAULT NULL,
  `stationID` int(11) DEFAULT NULL,
  PRIMARY KEY (`incomeReportID`),
  UNIQUE KEY `incomeReportID_UNIQUE` (`incomeReportID`),
  KEY `fk_StationID_idx` (`stationID`),
  CONSTRAINT `fk_IncomeReportReport` FOREIGN KEY (`incomeReportID`) REFERENCES `report` (`reportID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_StationID` FOREIGN KEY (`stationID`) REFERENCES `station` (`stationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income_report`
--

LOCK TABLES `income_report` WRITE;
/*!40000 ALTER TABLE `income_report` DISABLE KEYS */;
INSERT INTO `income_report` VALUES (3,12,0);
/*!40000 ALTER TABLE `income_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `invoiceID` int(11) NOT NULL,
  `totalPrice` double DEFAULT NULL,
  `dueDate` varchar(45) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  `purchasePlanID` int(11) DEFAULT NULL,
  `subscriptionID` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`invoiceID`),
  UNIQUE KEY `invoiceID_UNIQUE` (`invoiceID`),
  KEY `fk_InvoicePurchasePlan_idx` (`purchasePlanID`),
  KEY `fk_InvoiceSubscription_idx` (`subscriptionID`),
  KEY `fk_InvoiceCustomer_idx` (`customerID`),
  CONSTRAINT `fk_InvoiceCustomer` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_InvoicePurchasePlan` FOREIGN KEY (`purchasePlanID`) REFERENCES `purchase_plan` (`purchasePlanID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_InvoiceSubscription` FOREIGN KEY (`subscriptionID`) REFERENCES `subscription` (`subscriptionID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (0,64.80000305175781,'2015-01-02',3,0,2,3),(1,100,'2015-01-02',4,0,0,1),(2,0,'2016-02-01',9,0,3,1),(3,100,'2016-02-01',4,0,0,3),(4,270,'2016-02-01',3,0,3,3),(5,120,'2016-02-01',4,0,3,1);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `locationID` int(11) NOT NULL,
  `locAddr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`locationID`),
  UNIQUE KEY `locationID_UNIQUE` (`locationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (0,'testStationAddr'),(1,'station2Addr');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `UserName` varchar(45) NOT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('Admin','Admin'),('CarCustomer','1234'),('HouseOwner','1234'),('NetworkManager','1234'),('SalesManager','1234'),('SalesWorker','1234'),('Sergei','1234'),('StationManager','1234'),('StationManager2','1234');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly`
--

DROP TABLE IF EXISTS `monthly`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly` (
  `monthlyID` int(11) NOT NULL,
  PRIMARY KEY (`monthlyID`),
  UNIQUE KEY `monthlyID_UNIQUE` (`monthlyID`),
  CONSTRAINT `fk_PaymentInfoMonthly` FOREIGN KEY (`monthlyID`) REFERENCES `payment_info` (`paymentInfoID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly`
--

LOCK TABLES `monthly` WRITE;
/*!40000 ALTER TABLE `monthly` DISABLE KEYS */;
INSERT INTO `monthly` VALUES (2),(5);
/*!40000 ALTER TABLE `monthly` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_full`
--

DROP TABLE IF EXISTS `monthly_full`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_full` (
  `monthlyFullID` int(11) NOT NULL,
  PRIMARY KEY (`monthlyFullID`),
  UNIQUE KEY `monthlyFullID_UNIQUE` (`monthlyFullID`),
  CONSTRAINT `fk_MonthlyFull` FOREIGN KEY (`monthlyFullID`) REFERENCES `subscription` (`subscriptionID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_full`
--

LOCK TABLES `monthly_full` WRITE;
/*!40000 ALTER TABLE `monthly_full` DISABLE KEYS */;
INSERT INTO `monthly_full` VALUES (4),(5);
/*!40000 ALTER TABLE `monthly_full` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_multiple`
--

DROP TABLE IF EXISTS `monthly_multiple`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_multiple` (
  `monthlyMultipleID` int(11) NOT NULL,
  `numOfCars` int(11) DEFAULT NULL,
  PRIMARY KEY (`monthlyMultipleID`),
  UNIQUE KEY `monthlyMultipleID_UNIQUE` (`monthlyMultipleID`),
  CONSTRAINT `fk_MonthlyMultipleSubscription` FOREIGN KEY (`monthlyMultipleID`) REFERENCES `subscription` (`subscriptionID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_multiple`
--

LOCK TABLES `monthly_multiple` WRITE;
/*!40000 ALTER TABLE `monthly_multiple` DISABLE KEYS */;
INSERT INTO `monthly_multiple` VALUES (3,1);
/*!40000 ALTER TABLE `monthly_multiple` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthly_simple`
--

DROP TABLE IF EXISTS `monthly_simple`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_simple` (
  `monthlySimpleID` int(11) NOT NULL,
  PRIMARY KEY (`monthlySimpleID`),
  UNIQUE KEY `monthlySimpleID_UNIQUE` (`monthlySimpleID`),
  CONSTRAINT `fk_MonthlySimpleSubscription` FOREIGN KEY (`monthlySimpleID`) REFERENCES `subscription` (`subscriptionID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_simple`
--

LOCK TABLES `monthly_simple` WRITE;
/*!40000 ALTER TABLE `monthly_simple` DISABLE KEYS */;
INSERT INTO `monthly_simple` VALUES (1),(2);
/*!40000 ALTER TABLE `monthly_simple` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `orderID` int(11) NOT NULL,
  `quantity` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `orderTime` varchar(45) DEFAULT NULL,
  `invoiceID` int(11) DEFAULT NULL,
  `periodicDiscountID` int(11) DEFAULT NULL,
  `fuelTypeID` int(11) DEFAULT NULL,
  `discriminator` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  UNIQUE KEY `orderID_UNIQUE` (`orderID`),
  KEY `fk_OrderInvoice_idx` (`invoiceID`),
  KEY `fk_orederDiscount_idx` (`periodicDiscountID`),
  KEY `fk_OrderFuelType_idx` (`fuelTypeID`),
  CONSTRAINT `fk_OrderDiscount` FOREIGN KEY (`periodicDiscountID`) REFERENCES `periodic_discount` (`periodicDiscountID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderFuelType` FOREIGN KEY (`fuelTypeID`) REFERENCES `fuel_type` (`fuelTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderInvoice` FOREIGN KEY (`invoiceID`) REFERENCES `invoice` (`invoiceID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (0,10,64.8,1,'2015-01-02 12:00:00',0,0,1,1),(1,10,100,1,'2015-01-02 00:00:00',1,0,3,2),(2,5,0,1,'2016-01-15 07:00:00',2,0,0,1),(3,5,0,1,'2016-01-15 14:00:00',2,0,0,1),(4,10,100,1,'2016-01-16 14:00:00',3,0,3,2),(5,15,180,1,'2016-01-17 18:00:00',4,1,1,1),(6,15,90,1,'2016-01-17 18:00:00',4,1,2,1),(7,10,120,1,'2016-01-18 12:00:00',5,1,1,1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_info`
--

DROP TABLE IF EXISTS `payment_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_info` (
  `paymentInfoID` int(11) NOT NULL,
  `customerID` int(11) DEFAULT NULL,
  `preferredPaymentMethod` int(11) DEFAULT NULL,
  `discriminator` int(11) DEFAULT NULL,
  PRIMARY KEY (`paymentInfoID`),
  UNIQUE KEY `paymentInfoID_UNIQUE` (`paymentInfoID`),
  KEY `fk_customerPaymentInfo_idx` (`customerID`),
  CONSTRAINT `fk_customerPaymentInfo` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_info`
--

LOCK TABLES `payment_info` WRITE;
/*!40000 ALTER TABLE `payment_info` DISABLE KEYS */;
INSERT INTO `payment_info` VALUES (0,3,0,1),(1,4,1,2),(2,3,1,3),(3,9,1,1),(4,9,0,2),(5,9,100,3);
/*!40000 ALTER TABLE `payment_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodic_customer_report`
--

DROP TABLE IF EXISTS `periodic_customer_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodic_customer_report` (
  `periodicCustomerID` int(11) NOT NULL,
  PRIMARY KEY (`periodicCustomerID`),
  UNIQUE KEY `periodicCustomerID_UNIQUE` (`periodicCustomerID`),
  CONSTRAINT `fk_PeriodicCustomerReportReport` FOREIGN KEY (`periodicCustomerID`) REFERENCES `report` (`reportID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodic_customer_report`
--

LOCK TABLES `periodic_customer_report` WRITE;
/*!40000 ALTER TABLE `periodic_customer_report` DISABLE KEYS */;
INSERT INTO `periodic_customer_report` VALUES (2);
/*!40000 ALTER TABLE `periodic_customer_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodic_discount`
--

DROP TABLE IF EXISTS `periodic_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodic_discount` (
  `periodicDiscountID` int(11) NOT NULL,
  `startDate` varchar(45) DEFAULT NULL,
  `endDate` varchar(45) DEFAULT NULL,
  `periodicDiscountTemplateID` int(11) DEFAULT NULL,
  PRIMARY KEY (`periodicDiscountID`),
  UNIQUE KEY `periodicDiscountID_UNIQUE` (`periodicDiscountID`),
  KEY `fk_PeriodicDiscountPeriodicDiscountTemplate_idx` (`periodicDiscountTemplateID`),
  CONSTRAINT `fk_PeriodicDiscountPeriodicDiscountTemplate` FOREIGN KEY (`periodicDiscountTemplateID`) REFERENCES `periodic_discount_template` (`periodicDiscountTemplateID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodic_discount`
--

LOCK TABLES `periodic_discount` WRITE;
/*!40000 ALTER TABLE `periodic_discount` DISABLE KEYS */;
INSERT INTO `periodic_discount` VALUES (0,'0001-01-01','0001-01-01',0),(1,'2016-01-01','2016-03-01',1);
/*!40000 ALTER TABLE `periodic_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodic_discount_template`
--

DROP TABLE IF EXISTS `periodic_discount_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodic_discount_template` (
  `periodicDiscountTemplateID` int(11) NOT NULL,
  `discount` double DEFAULT NULL,
  `startTime` varchar(45) DEFAULT NULL,
  `endTime` varchar(45) DEFAULT NULL,
  `minFuelAmount` double DEFAULT NULL,
  PRIMARY KEY (`periodicDiscountTemplateID`),
  UNIQUE KEY `periodicDiscountTemplateID_UNIQUE` (`periodicDiscountTemplateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodic_discount_template`
--

LOCK TABLES `periodic_discount_template` WRITE;
/*!40000 ALTER TABLE `periodic_discount_template` DISABLE KEYS */;
INSERT INTO `periodic_discount_template` VALUES (0,0,'12:00:00','12:00:00',0),(1,0.1,'06:00:00','23:00:00',20);
/*!40000 ALTER TABLE `periodic_discount_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_plan`
--

DROP TABLE IF EXISTS `purchase_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_plan` (
  `purchasePlanID` int(11) NOT NULL,
  `planName` varchar(45) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `agreementDetails` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`purchasePlanID`),
  UNIQUE KEY `purchasePlanID_UNIQUE` (`purchasePlanID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_plan`
--

LOCK TABLES `purchase_plan` WRITE;
/*!40000 ALTER TABLE `purchase_plan` DISABLE KEYS */;
INSERT INTO `purchase_plan` VALUES (0,'exclusive',0.2,'The plan owner may only use refueling stations that belong to this company.'),(1,'Next Level',0.1,'The plan owner may use the stations of two more companies other than this.');
/*!40000 ALTER TABLE `purchase_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases_report`
--

DROP TABLE IF EXISTS `purchases_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchases_report` (
  `purchasesReportID` int(11) NOT NULL,
  `stationID` int(11) DEFAULT NULL,
  PRIMARY KEY (`purchasesReportID`),
  UNIQUE KEY `purchasesReportID_UNIQUE` (`purchasesReportID`),
  KEY `fk_StationID_idx` (`stationID`),
  CONSTRAINT `fk_PurchasesReportReport` FOREIGN KEY (`purchasesReportID`) REFERENCES `report` (`reportID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PurchasesReportStation` FOREIGN KEY (`stationID`) REFERENCES `station` (`stationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases_report`
--

LOCK TABLES `purchases_report` WRITE;
/*!40000 ALTER TABLE `purchases_report` DISABLE KEYS */;
INSERT INTO `purchases_report` VALUES (1,0);
/*!40000 ALTER TABLE `purchases_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `receiptID` int(11) NOT NULL,
  `paymentDate` varchar(45) DEFAULT NULL,
  `invoiceID` int(11) DEFAULT NULL,
  PRIMARY KEY (`receiptID`),
  UNIQUE KEY `receiptID_UNIQUE` (`receiptID`),
  KEY `fk_ReceiptInvoice_idx` (`invoiceID`),
  CONSTRAINT `fk_ReceiptInvoice` FOREIGN KEY (`invoiceID`) REFERENCES `invoice` (`invoiceID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (0,'2015-01-02',0),(1,'2016-01-17',4),(2,'2016-01-17',3);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `reportID` int(11) NOT NULL,
  `reportTitle` varchar(255) DEFAULT NULL,
  `reportDate` varchar(45) DEFAULT NULL,
  `reportDesc` varchar(1024) DEFAULT NULL,
  `discriminator` int(11) DEFAULT NULL,
  PRIMARY KEY (`reportID`),
  UNIQUE KEY `reportID_UNIQUE` (`reportID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (0,'Test Stock Report','2015-01-01','This is a test report for evaluation purposes.',6),(1,'Test Purchases Report','2015-01-01','This is a test report for evaluation purposes.',5),(2,'Test Periodic Customer','2015-01-01','This is a test report for evaluation purposes.',4),(3,'Test Income Report','2015-01-01','This is a test report for evaluation purposes.',3),(4,'Test Generated Report','2015-01-01','This is a test report for evaluation purposes.',2),(5,'Test Discount Feddback','2015-01-01','This is a test report for evaluation purposes.',1);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_per_fuel_type`
--

DROP TABLE IF EXISTS `score_per_fuel_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score_per_fuel_type` (
  `scorePerFuelTypeID` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `fuelTypeID` int(11) DEFAULT NULL,
  `generatedScoreReportID` int(11) DEFAULT NULL,
  PRIMARY KEY (`scorePerFuelTypeID`),
  UNIQUE KEY `scorePerFuelTypeID_UNIQUE` (`scorePerFuelTypeID`),
  KEY `fk_ScorePerFuelTypeGeneratedScoreReport_idx` (`generatedScoreReportID`),
  KEY `fk_ScorePerFuelTypeFuelType_idx` (`fuelTypeID`),
  CONSTRAINT `fk_ScorePerFuelTypeFuelType` FOREIGN KEY (`fuelTypeID`) REFERENCES `fuel_type` (`fuelTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ScorePerFuelTypeGeneratedScoreReport` FOREIGN KEY (`generatedScoreReportID`) REFERENCES `generated_score_report` (`generatedScoreReportID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_per_fuel_type`
--

LOCK TABLES `score_per_fuel_type` WRITE;
/*!40000 ALTER TABLE `score_per_fuel_type` DISABLE KEYS */;
INSERT INTO `score_per_fuel_type` VALUES (0,6,0,4),(1,8,1,4),(2,7,2,4);
/*!40000 ALTER TABLE `score_per_fuel_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `stationID` int(11) NOT NULL,
  PRIMARY KEY (`stationID`),
  UNIQUE KEY `stationID_UNIQUE` (`stationID`),
  CONSTRAINT `fk_LocationStation` FOREIGN KEY (`stationID`) REFERENCES `location` (`locationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (0),(1);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_report`
--

DROP TABLE IF EXISTS `stock_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_report` (
  `stockReportID` int(11) NOT NULL,
  `stockStationID` int(11) DEFAULT NULL,
  PRIMARY KEY (`stockReportID`),
  UNIQUE KEY `stockReportID_UNIQUE` (`stockReportID`),
  KEY `fk_StockReportStation_idx` (`stockStationID`),
  CONSTRAINT `fk_StockReportReport` FOREIGN KEY (`stockReportID`) REFERENCES `report` (`reportID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_StockReportStation` FOREIGN KEY (`stockStationID`) REFERENCES `station` (`stationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_report`
--

LOCK TABLES `stock_report` WRITE;
/*!40000 ALTER TABLE `stock_report` DISABLE KEYS */;
INSERT INTO `stock_report` VALUES (0,0);
/*!40000 ALTER TABLE `stock_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `subscriptionID` int(11) NOT NULL,
  `discountTableID` int(11) DEFAULT NULL,
  `discriminator` int(11) DEFAULT NULL,
  PRIMARY KEY (`subscriptionID`),
  UNIQUE KEY `subscriptionID_UNIQUE` (`subscriptionID`),
  KEY `fk_SubscriptionDiscountTable_idx` (`discountTableID`),
  CONSTRAINT `fk_SubscriptionDiscountTable` FOREIGN KEY (`discountTableID`) REFERENCES `discount_table` (`discountTableID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (0,0,0),(1,0,3),(2,0,3),(3,0,2),(4,0,1),(5,0,1);
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `eMail` varchar(255) DEFAULT NULL,
  `userTypeID` int(11) DEFAULT NULL,
  `discriminator` int(11) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `UserID_UNIQUE` (`userID`),
  KEY `fk_UserUserType_idx` (`userTypeID`),
  CONSTRAINT `fk_UserUserType` FOREIGN KEY (`userTypeID`) REFERENCES `user_type` (`userTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'Admin',NULL,'Admin','Admin',NULL,0,2),(1,'TestUser','0001234567','Test','User','test@test.com',1,1),(2,'TestWorker','0007654321','Test','Worker','worker@test.com',4,2),(3,'CarCustomer','0001112222','Car','Customer','carcus@test.com',2,1),(4,'HouseOwner','0002221111','House','Owner','huso@test.com',3,1),(5,'StationManager','1110002222','Station','Manager','sergeikov95@gmail.com',5,2),(6,'SalesWorker','9998887777','SalesDep','Worker','slsdep@test.com',6,2),(7,'SalesManager','6665554444','SalesDep','Manager','slsdp@test.com',7,2),(8,'NetworkManager','1231231234','Network','Manager','sergeikov95@gmail.com',8,2),(9,'Sergei','something','Sergei','Kov','sergeikov95@gmail.com',2,1),(10,'StationManager2','somePhone','Station','Manager2','stmng@test.com',5,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `userTypeID` int(11) NOT NULL,
  `posName` varchar(255) DEFAULT NULL,
  `privilegeLevels` varchar(45) DEFAULT NULL,
  `isCustomer` bit(1) DEFAULT NULL,
  `isCarCustomer` bit(1) DEFAULT NULL,
  PRIMARY KEY (`userTypeID`),
  UNIQUE KEY `UserTypeID_UNIQUE` (`userTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (0,'Admin','111111011100','\0','\0'),(1,'Customer','000000010000','','\0'),(2,'Car Customer','000000010000','',''),(3,'House Owner','000000010100','','\0'),(4,'Worker','000011011100','\0','\0'),(5,'Station Manager','100011011000','\0','\0'),(6,'Sales Department Worker','011111011100','\0','\0'),(7,'Sales Department Manager','011111011100','\0','\0'),(8,'Network Manager','011111011100','\0','\0'),(9,'Car Fleet','000000010000','','');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worker` (
  `workerID` int(11) NOT NULL,
  `locationID` int(11) DEFAULT NULL,
  PRIMARY KEY (`workerID`),
  UNIQUE KEY `workerID_UNIQUE` (`workerID`),
  KEY `fk_WorkerLocation_idx` (`locationID`),
  CONSTRAINT `fk_UserWorker` FOREIGN KEY (`workerID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_WorkerLocation` FOREIGN KEY (`locationID`) REFERENCES `location` (`locationID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (0,0),(2,0),(5,0),(6,0),(7,0),(8,0),(10,1);
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-17 22:38:27
