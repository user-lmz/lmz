-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: order
-- ------------------------------------------------------
-- Server version	8.0.22-0ubuntu0.20.04.3

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
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `f_id` int NOT NULL,
  `f_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `f_materials` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `f_price` double(15,2) DEFAULT NULL,
  `f_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `f_img` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'米饭','泰国香米',1.20,'主食','米饭.jpg'),(2,'小笼包','面粉，猪肉，白菜',10.00,'主食','小笼包.jpg'),(3,'花卷','面粉，葱，盐',2.00,'主食','花卷.jpg'),(4,'玉米馒头','玉米，面粉',3.00,'主食','玉米馒头.jpg'),(5,'麻辣汤粉','米粉，辣椒油，原骨汤',12.00,'主食','麻辣汤粉.jpg'),(6,'枸杞米酒汤','枸杞，米酒',8.00,'汤品','枸杞米酒汤.jpg'),(7,'莲耳汤','莲子，白木耳，红枣',18.00,'汤品','莲耳汤.jpg'),(8,'玉米排骨汤','玉米，排骨，纯净水',14.00,'汤品','玉米排骨汤.jpg'),(9,'豆角烧茄子','豆角，茄子，大蒜',8.00,'炒菜','豆角烧茄子.jpg'),(10,'青椒啤酒鸭','青椒，鸭，啤酒，酱油',18.00,'炒菜','青椒啤酒鸭.jpg'),(11,'酸辣土豆丝','土豆，小辣椒，陈醋',8.00,'炒菜','酸辣土豆丝.jpg'),(12,'蒜苔腊肉','大蒜苔，腊肉，花生油',15.00,'炒菜','蒜苔腊肉.jpg'),(13,'红烧鱼块盖浇饭','草鱼，大蒜，辣椒，白米饭',12.00,'盖浇饭','红烧鱼块盖浇饭.jpg'),(14,'麻婆豆腐盖浇饭','豆腐，花椒，剁椒，原油，米饭',11.00,'盖浇饭','麻婆豆腐盖浇饭.jpg'),(15,'鲜虾盖浇饭','基围虾，酱油，醋，米饭',16.00,'盖浇饭','鲜虾盖浇饭.jpg'),(16,'橙汁','鲜橙粉，糖，水',3.00,'饮料','橙汁.jpg'),(17,'可乐','香草、肉桂、柠檬香味',2.50,'饮料','可乐.jpg'),(18,'脉动','纯净水、白砂糖、苹果汁、食用香精、食品添加剂',4.00,'饮料','脉动.jpg'),(19,'咖啡','咖啡因，纯净水，白砂糖',8.00,'饮料','咖啡.jpg'),(20,'雪碧','水，果葡萄浆，白砂糖，食品添加剂',2.50,'饮料','雪碧.jpg'),(21,'苏打水','水，苏打',3.50,'饮料','苏打水.jpg');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` int NOT NULL,
  `f_id` int DEFAULT NULL,
  `num` int DEFAULT NULL,
  `subtotal` double(11,0) DEFAULT NULL,
  `o_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `o_id` (`o_id`),
  KEY `f_id` (`f_id`),
  CONSTRAINT `f_id` FOREIGN KEY (`f_id`) REFERENCES `food` (`f_id`),
  CONSTRAINT `o_id` FOREIGN KEY (`o_id`) REFERENCES `order` (`o_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,5,1,12,1),(2,6,1,8,1),(3,18,1,4,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchant` (
  `m_id` int NOT NULL,
  `m_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_password` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_address` varchar(35) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
INSERT INTO `merchant` VALUES (1,'admin','123','黄石','2452452452'),(2,'admin1','123','hue','161651561');
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `o_id` int NOT NULL,
  `u_id` int DEFAULT NULL,
  `o_totalprice` double(10,2) DEFAULT NULL,
  `message` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`o_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,24.00,'味道不错','2020-5-6 12:10:17');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `u_id` int NOT NULL,
  `u_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `u_password` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `u_address` varchar(35) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `u_tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'lmz','1234','wh','16165165156'),(2,'hq','123','wh','22542542452');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-24 22:05:30
