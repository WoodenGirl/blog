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

 Date: 25/11/2024 17:01:28
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
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('article_b0b244ced819431a9d6116985281c64b', '关于网站', 'user_1111111111111111', '2024-11-22 10:10:27', '2024-11-22 10:10:27', 'tag 1,tag 2', 38, '<p><strong>网站技术： </strong></p><p>前端：vue + elmentui</p><p>后端：asp.net.core + furion + sqlsugar</p><p><br></p><p>网站部署：</p><p>服务器：华为云服务器 &nbsp; &nbsp; &nbsp; &nbsp;一年</p><p>域名： &nbsp; aprisxz.fun &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;一年 &nbsp; &nbsp; &nbsp;备案中</p><p><br></p><p>功能：</p><table style=\"width: auto;\"><tbody><tr><th colspan=\"1\" rowspan=\"1\" width=\"142.09\" style=\"text-align: center;\">功能</th><th colSpan=\"1\" rowSpan=\"1\" width=\"118\">实现时间</th><th colSpan=\"1\" rowSpan=\"1\" width=\"164.7\">状态</th></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">目录</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">√</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">文章</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">√</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">文章评论</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">√</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">动态</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\"></td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">动态评论</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\"></td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">用户</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">微信登录</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td></tr></tbody></table><p><br></p>', 'fabd1ad9ef612e9f619257190eed33f4.jpg');
INSERT INTO `tb_article` VALUES ('article_f026f6d898ba45bc8ec4c9b7ae2c42bb', 'test', 'user_1111111111111111', '2024-11-22 10:14:14', '2024-11-22 10:14:14', 'tag 1,tag 2', 38, '<p>test</p>', 'fabd1ad9ef612e9f619257190eed33f4.jpg');

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
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (15, '女生', 14, '小说-女生');
INSERT INTO `tb_category` VALUES (20, '名著', 14, '小说-名著');
INSERT INTO `tb_category` VALUES (21, '穿书', 15, '小说-女生-穿书');
INSERT INTO `tb_category` VALUES (22, '穿越', 15, '小说-女生-穿越');
INSERT INTO `tb_category` VALUES (23, '系统', 15, '小说-女生-系统');
INSERT INTO `tb_category` VALUES (24, '现代', 15, '小说-女生-现代');
INSERT INTO `tb_category` VALUES (25, '古代', 15, '小说-女生-古代');
INSERT INTO `tb_category` VALUES (27, '休闲娱乐', 0, '休闲时间');
INSERT INTO `tb_category` VALUES (28, '小说漫画', 27, '休闲时间-小说漫画');
INSERT INTO `tb_category` VALUES (29, '影视番剧', 27, '休闲时间-影视番剧');
INSERT INTO `tb_category` VALUES (30, '手游端游', 27, '休闲时间-手游端游');
INSERT INTO `tb_category` VALUES (31, '兴趣爱好', 0, '兴趣爱好');
INSERT INTO `tb_category` VALUES (32, '音乐', 31, '兴趣爱好-音乐');
INSERT INTO `tb_category` VALUES (33, '绘画', 31, '兴趣爱好-绘画');
INSERT INTO `tb_category` VALUES (34, '书法', 31, '兴趣爱好-书法');
INSERT INTO `tb_category` VALUES (35, '专业技术', 0, '专业技术');
INSERT INTO `tb_category` VALUES (36, '计算机类', 35, '专业技术-计算机类');
INSERT INTO `tb_category` VALUES (37, '日常生活', 0, '日常生活');
INSERT INTO `tb_category` VALUES (38, '网站相关', 0, '网站相关');
INSERT INTO `tb_category` VALUES (39, '电视剧', 29, '休闲娱乐-影视番剧-电视剧');
INSERT INTO `tb_category` VALUES (40, '电影', 29, '休闲娱乐-影视番剧-电影');
INSERT INTO `tb_category` VALUES (41, '番剧', 29, '休闲娱乐-影视番剧-番剧');
INSERT INTO `tb_category` VALUES (42, '手游', 30, '休闲娱乐-手游端游-手游');
INSERT INTO `tb_category` VALUES (43, '端游', 30, '休闲娱乐-手游端游-端游');
INSERT INTO `tb_category` VALUES (44, '小说', 28, '休闲娱乐-小说漫画-小说');
INSERT INTO `tb_category` VALUES (45, '漫画', 28, '休闲娱乐-小说漫画-漫画');
INSERT INTO `tb_category` VALUES (46, '女生', 44, '休闲娱乐-小说漫画-小说-女生');
INSERT INTO `tb_category` VALUES (47, '穿越', 46, '休闲娱乐-小说漫画-小说-女生-穿越');
INSERT INTO `tb_category` VALUES (48, '尤克里里', 32, '兴趣爱好-音乐-尤克里里');
INSERT INTO `tb_category` VALUES (49, '出版', 44, '休闲娱乐-小说漫画-小说-出版');

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
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES (15, 0, 'user_1111111111111111', 'test', '2024-11-22 10:06:35', NULL, 'article_ae633d9dcc4d4f548c4b0fd84ef3c339');
INSERT INTO `tb_comment` VALUES (18, 0, 'user_1111111111111111', 'test', '2024-11-22 10:14:38', NULL, 'article_f026f6d898ba45bc8ec4c9b7ae2c42bb');
INSERT INTO `tb_comment` VALUES (19, 18, 'user_1111111111111111', 'success happy', '2024-11-22 10:14:45', NULL, 'article_f026f6d898ba45bc8ec4c9b7ae2c42bb');
INSERT INTO `tb_comment` VALUES (20, 0, 'user_1111111111111111', '13', '2024-11-22 17:02:55', NULL, 'dynamic_ea37dd6baac14e488c227d250ed26e2f');
INSERT INTO `tb_comment` VALUES (21, 0, 'user_1111111111111111', 'test', '2024-11-22 17:05:45', NULL, 'dynamic_ea37dd6baac14e488c227d250ed26e2f');
INSERT INTO `tb_comment` VALUES (22, 0, 'user_1111111111111111', 'test dynamic comment', '2024-11-22 17:07:19', NULL, 'dynamic_b72c912fcb714699b164f04af8403ec4');
INSERT INTO `tb_comment` VALUES (23, 0, 'user_1111111111111111', '123', '2024-11-25 10:55:25', NULL, 'dynamic_3d88e704a7b34c82b62d99509b528568');
INSERT INTO `tb_comment` VALUES (24, 0, 'user_1111111111111111', '456', '2024-11-25 10:55:30', NULL, 'dynamic_59e2f803d6aa48dfbc1edee7e93e1b1d');
INSERT INTO `tb_comment` VALUES (26, 23, 'user_1111111111111111', '123', '2024-11-25 11:00:29', NULL, 'dynamic_3d88e704a7b34c82b62d99509b528568');
INSERT INTO `tb_comment` VALUES (30, 18, 'user_1111111111111111', 'test again', '2024-11-25 11:56:21', 'user_1111111111111111', 'article_f026f6d898ba45bc8ec4c9b7ae2c42bb');
INSERT INTO `tb_comment` VALUES (31, 18, 'user_1111111111111111', 'success fully', '2024-11-25 14:14:21', 'user_1111111111111111', 'article_f026f6d898ba45bc8ec4c9b7ae2c42bb');
INSERT INTO `tb_comment` VALUES (32, 23, 'user_1111111111111111', 'test success', '2024-11-25 14:25:15', 'user_1111111111111111', 'dynamic_3d88e704a7b34c82b62d99509b528568');

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
-- Records of tb_dynamic
-- ----------------------------
INSERT INTO `tb_dynamic` VALUES ('dynamic_3d88e704a7b34c82b62d99509b528568', 'user_1111111111111111', 'test again', '2024-11-25 10:55:11', 38, '');
INSERT INTO `tb_dynamic` VALUES ('dynamic_59e2f803d6aa48dfbc1edee7e93e1b1d', 'user_1111111111111111', 'test 38', '2024-11-25 10:48:43', 38, '');
INSERT INTO `tb_dynamic` VALUES ('dynamic_b72c912fcb714699b164f04af8403ec4', 'user_1111111111111111', 'test dynamic😆', '2024-11-22 17:06:52', 32, '');
INSERT INTO `tb_dynamic` VALUES ('dynamic_e840f833e6cc4a8e8337f19d7f83761b', 'user_1111111111111111', 'testtrteststrtre🤔', '2024-11-22 17:10:48', 32, '');
INSERT INTO `tb_dynamic` VALUES ('dynamic_f600288bac714c41a7bcc01bdb3ebbe9', 'user_1111111111111111', 'test', '2024-11-25 10:43:04', 0, '');

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
-- Records of tb_recreation
-- ----------------------------

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
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('user_1111111111111111', 'Aprilsxz', NULL, '333', NULL);

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
