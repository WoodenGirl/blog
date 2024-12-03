/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 03/12/2024 16:50:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
  `articleId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `articleTitle` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createdTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `articleTags` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `categoryId` int NOT NULL,
  `articleContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `articleCover` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`articleId`) USING BTREE,
  INDEX `categoryForeign`(`categoryId` ASC) USING BTREE,
  INDEX `article_user_foreign`(`userId` ASC) USING BTREE,
  CONSTRAINT `categoryForeign` FOREIGN KEY (`categoryId`) REFERENCES `tb_category` (`categoryId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `categoryId` int NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `categoryName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名称',
  `categoryParent` int NOT NULL COMMENT '父目录',
  `categoryDetail` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `commentId` int NOT NULL AUTO_INCREMENT,
  `commentParent` int NOT NULL DEFAULT 0,
  `userId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `commentContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createdTime` datetime NOT NULL,
  `commentReply` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复的用户id',
  `linkedId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`commentId`) USING BTREE,
  INDEX `userForeign`(`userId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `tb_dynamic`;
CREATE TABLE `tb_dynamic`  (
  `dynamicId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dynamicContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createdTime` datetime NOT NULL,
  `categoryId` int NOT NULL,
  `dynamicImages` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dynamicId`) USING BTREE,
  INDEX `category-foreign`(`categoryId` ASC) USING BTREE,
  INDEX `user-foreign`(`userId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_recreation
-- ----------------------------
DROP TABLE IF EXISTS `tb_recreation`;
CREATE TABLE `tb_recreation`  (
  `recreationId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `recreationName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `recreationCover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `recreationIntro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `recreationDirector` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `recreationRate` float NOT NULL,
  `recreationState` int NOT NULL,
  `recreationTags` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `recreationRemark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `recreationActor` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `recreationChapter` int NULL DEFAULT NULL,
  `categoryId` int NOT NULL,
  PRIMARY KEY (`recreationId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `userId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`, `nickname`) USING BTREE,
  INDEX `nickname`(`nickname` ASC) USING BTREE,
  INDEX `userId`(`userId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for view_article
-- ----------------------------
DROP VIEW IF EXISTS `view_article`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_article` AS select `tb_article`.`articleId` AS `articleId`,`tb_article`.`categoryId` AS `categoryId`,`tb_article`.`userId` AS `userId`,`tb_article`.`articleTitle` AS `articleTitle`,`tb_article`.`articleTags` AS `articleTags`,`tb_article`.`articleCover` AS `articleCover`,`tb_article`.`articleContent` AS `articleContent`,`tb_user`.`nickname` AS `nickname`,`tb_category`.`categoryDetail` AS `categoryDetail`,`tb_article`.`createdTime` AS `createdTime`,`tb_article`.`updateTime` AS `updateTime` from ((`tb_article` join `tb_user`) join `tb_category`) where ((`tb_article`.`userId` = `tb_user`.`userId`) and (`tb_article`.`categoryId` = `tb_category`.`categoryId`));

-- ----------------------------
-- View structure for view_comment
-- ----------------------------
DROP VIEW IF EXISTS `view_comment`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_comment` AS select `tb_comment`.`commentId` AS `commentId`,`tb_comment`.`commentParent` AS `commentParent`,`tb_comment`.`linkedId` AS `linkedId`,`tb_comment`.`commentContent` AS `commentContent`,`tb_comment`.`commentReply` AS `commentReply`,`tb_user`.`userId` AS `userId`,`tb_user`.`nickname` AS `nickname`,`tb_user`.`avatar` AS `avatar`,`tb_comment`.`createdTime` AS `createdTime`,(select `tb_user`.`nickname` from `tb_user` where (`tb_user`.`userId` = `tb_comment`.`commentReply`)) AS `replyName` from (`tb_comment` join `tb_user`) where (`tb_comment`.`userId` = `tb_user`.`userId`);

-- ----------------------------
-- View structure for view_dynamic
-- ----------------------------
DROP VIEW IF EXISTS `view_dynamic`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_dynamic` AS select `tb_dynamic`.`dynamicId` AS `dynamicId`,`tb_dynamic`.`dynamicContent` AS `dynamicContent`,`tb_dynamic`.`dynamicImages` AS `dynamicImages`,`tb_dynamic`.`userId` AS `userId`,`tb_user`.`nickname` AS `nickname`,`tb_user`.`avatar` AS `avatar`,`tb_dynamic`.`createdTime` AS `createdTime`,`tb_dynamic`.`categoryId` AS `categoryId`,`tb_category`.`categoryDetail` AS `categoryDetail` from ((`tb_dynamic` join `tb_user`) join `tb_category`) where ((`tb_user`.`userId` = `tb_dynamic`.`userId`) and (`tb_dynamic`.`categoryId` = `tb_category`.`categoryId`));

SET FOREIGN_KEY_CHECKS = 1;
