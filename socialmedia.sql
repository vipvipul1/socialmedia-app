-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: social_media
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `comment_mapping`
--

DROP TABLE IF EXISTS `comment_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_mapping` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_comment_id` int DEFAULT NULL,
  `child_comment_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cmnt_map_prnt_cmnt_id_idx` (`parent_comment_id`),
  KEY `fk_cmnt_map_chld_cmnt_id_idx` (`child_comment_id`),
  CONSTRAINT `fk_cmnt_map_chld_cmnt_id` FOREIGN KEY (`child_comment_id`) REFERENCES `comments` (`id`),
  CONSTRAINT `fk_cmnt_map_prnt_cmnt_id` FOREIGN KEY (`parent_comment_id`) REFERENCES `comments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_mapping`
--

LOCK TABLES `comment_mapping` WRITE;
/*!40000 ALTER TABLE `comment_mapping` DISABLE KEYS */;
INSERT INTO `comment_mapping` VALUES (2,4,7),(3,7,8),(4,5,9);
/*!40000 ALTER TABLE `comment_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_votes`
--

DROP TABLE IF EXISTS `comment_votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_votes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `is_upvote` tinyint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cmnt_vots_cmntId_idx` (`comment_id`),
  KEY `fk_cmnt_vots_userId_idx` (`user_id`),
  CONSTRAINT `fk_cmnt_vots_cmntId` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`),
  CONSTRAINT `fk_cmnt_vots_userId` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_votes`
--

LOCK TABLES `comment_votes` WRITE;
/*!40000 ALTER TABLE `comment_votes` DISABLE KEYS */;
INSERT INTO `comment_votes` VALUES (4,3,26,1,'2023-05-04 17:45:32'),(5,10,26,1,'2023-05-04 17:51:43'),(6,3,24,0,'2023-05-04 18:02:00'),(7,5,24,0,'2023-05-04 18:02:14');
/*!40000 ALTER TABLE `comment_votes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `body` varchar(100) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `feed_id` int DEFAULT NULL,
  `level` int DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comments_userId_idx` (`user_id`),
  KEY `fk_comments_feedId_idx` (`feed_id`),
  CONSTRAINT `fk_comments_feedId` FOREIGN KEY (`feed_id`) REFERENCES `feeds` (`id`),
  CONSTRAINT `fk_comments_userId` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (3,'I am awesome! What about you?',23,4,0,'2023-05-03 21:26:27'),(4,'Please come home soon!',23,4,0,'2023-05-03 21:27:54'),(5,'Ghar ka bank h. Jab mn kre aa k le ja.',23,3,0,'2023-05-03 21:29:17'),(6,'Aate h kal milne haveli p.',24,3,1,'2023-05-03 21:32:01'),(7,'I am in ur heart baby <3',24,4,1,'2023-05-03 21:36:35'),(8,'Wo to pta h pr jaldi aa jana.',23,4,2,'2023-05-03 21:40:28'),(9,'what r u talking about?',26,3,1,'2023-05-04 17:13:15'),(10,'And u will rock the interview!',26,2,0,'2023-05-04 17:15:20'),(11,'sab badhiya! tum btao?',24,4,0,'2023-05-04 17:15:20');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_votes`
--

DROP TABLE IF EXISTS `feed_votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed_votes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `feed_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `is_upvote` tinyint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fed_vots_feedId_idx` (`feed_id`),
  KEY `fk_fed_vots_userId_idx` (`user_id`),
  CONSTRAINT `fk_fed_vots_feedId` FOREIGN KEY (`feed_id`) REFERENCES `feeds` (`id`),
  CONSTRAINT `fk_fed_vots_userId` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_votes`
--

LOCK TABLES `feed_votes` WRITE;
/*!40000 ALTER TABLE `feed_votes` DISABLE KEYS */;
INSERT INTO `feed_votes` VALUES (5,6,24,1,'2023-05-04 17:37:15'),(6,1,23,0,'2023-05-04 17:37:53'),(8,6,23,0,'2023-05-04 17:38:57'),(9,6,26,0,'2023-05-04 17:39:48'),(10,1,26,1,'2023-05-04 17:40:01'),(11,2,23,0,'2023-05-04 17:40:01'),(12,1,24,1,'2023-05-04 17:40:01'),(13,5,23,0,'2023-05-04 17:40:01'),(14,5,24,0,'2023-05-04 17:40:01'),(15,5,26,1,'2023-05-04 17:40:01');
/*!40000 ALTER TABLE `feed_votes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feeds`
--

DROP TABLE IF EXISTS `feeds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feeds` (
  `id` int NOT NULL AUTO_INCREMENT,
  `body` varchar(500) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feed_userId_idx` (`user_id`),
  CONSTRAINT `fk_feed_userId` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feeds`
--

LOCK TABLES `feeds` WRITE;
/*!40000 ALTER TABLE `feeds` DISABLE KEYS */;
INSERT INTO `feeds` VALUES (1,'Hey Vipul! You are doing great progress. Keep up the good work!',23,'2023-05-03 17:27:01'),(2,'Vipul, remember you need to complete Gameberry Project.',23,'2023-05-03 17:28:09'),(3,'Rahul, please arrange a cash of Rs 2000 urgently!',24,'2023-05-03 17:29:22'),(4,'hello darling! how are you?',24,'2023-05-03 19:55:51'),(5,'Good Morning Friends!',23,'2023-05-03 21:18:54'),(6,'Today is going to be a wonderful day!',26,'2023-05-04 17:11:03');
/*!40000 ALTER TABLE `feeds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follows`
--

DROP TABLE IF EXISTS `follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follows` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_user_id` int DEFAULT NULL,
  `to_user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_follow_to_userId_idx` (`to_user_id`),
  KEY `fk_follow_from_userId_idx` (`from_user_id`),
  CONSTRAINT `fk_follows_from_userId` FOREIGN KEY (`from_user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_follows_to_userId` FOREIGN KEY (`to_user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follows`
--

LOCK TABLES `follows` WRITE;
/*!40000 ALTER TABLE `follows` DISABLE KEYS */;
INSERT INTO `follows` VALUES (4,23,24),(5,24,23),(11,26,23),(12,26,24);
/*!40000 ALTER TABLE `follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (23,'vipul','7033341667','v@g.c','pass','2023-05-03 17:23:40'),(24,'vipul1','7033341661','v@g.c1','pass1','2023-05-03 17:24:01'),(26,'vipul2','7033341662','v@g.c2','pass2','2023-05-04 17:07:37');
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

-- Dump completed on 2023-05-05 21:04:10
