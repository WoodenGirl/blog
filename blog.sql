/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 08/12/2024 15:14:24
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
  `createdTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `articleTags` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `categoryId` int(0) NOT NULL,
  `articleContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `articleCover` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`articleId`) USING BTREE,
  INDEX `categoryForeign`(`categoryId`) USING BTREE,
  INDEX `article_user_foreign`(`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('article_d774f37033dd4faab79db2525315e416', '租房事宜', 'user_1111111111111111', '2024-12-04 10:34:39', '2024-12-04 16:55:50', '租房,安全', 1, '<p>租房：</p><p> &nbsp; &nbsp;地点：工作地点附近</p><p> &nbsp; &nbsp;人物：中介，拿一个月房租的一半， 房东，希望他人好</p><p> &nbsp; &nbsp;关键物品：合同（一定要每条都看，来自第一次租房每条都看啥事没有，第二次大胆签名回家天塌了的感悟）（分享一下感觉天塌了的租房合同😀）</p><p><img src=\"http://obs.aprilsxz.fun/articleInsert/m49nkrl4pv71d2km2.jpg\" alt=\"微信图片_20241204103820.jpg\" data-href=\"http://obs.aprilsxz.fun/articleInsert/m49nkrl4pv71d2km2.jpg\" style=\"width: 50%;\"/></p><p>租房后：</p><p> &nbsp; &nbsp;网络：手机流量，随身wifi，租房wifi</p><p> &nbsp; &nbsp;安全：换锁，装摄像头</p><p><br></p>', 'articleCover/1733279496288.jpg');
INSERT INTO `tb_article` VALUES ('b39d098c7ac7574e', '开发进行中', 'user_1111111111111111', '2024-12-05 10:45:44', '2024-12-06 15:48:12', '开发,本网站', 1, '<p style=\"text-align: center;\">开发ing</p><table style=\"width: 100%;table-layout: fixed;height:357\"><colgroup contentEditable=\"false\"><col width=30></col><col width=30></col><col width=87></col><col width=60></col></colgroup><tbody><tr><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">模块</th><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">分工</th><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">实现</th><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">待实现</th></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">目录</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">树展示，重命名，添加父子节点，删除节点</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删改查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">评论</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">动态中样式需修改；展开收起回复</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">文章</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">文章封面上传、文章内容图片从服务器上传删除；文章列表显示，文章查看修改删除</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">表单校验；图片格式大小限制；跳转不更新；</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删改查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">动态</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">动态添加，图片上传，展示图片</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">点赞评论数量显示</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">添加数据库表，点赞表</td></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">用户</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">个人中心，权限</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删改查</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">整体</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">分页？；点赞功能</td></tr></tbody></table><p><br></p>', 'articleCover/1733365098697.jpg');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `categoryId` int(0) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `categoryName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名称',
  `categoryParent` int(0) NOT NULL COMMENT '父目录',
  PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (1, 'Aprisxlz', 0);
INSERT INTO `tb_category` VALUES (2, 'acg', 0);
INSERT INTO `tb_category` VALUES (3, 'interest', 0);
INSERT INTO `tb_category` VALUES (4, 'technique', 0);
INSERT INTO `tb_category` VALUES (5, 'life', 0);
INSERT INTO `tb_category` VALUES (50, '小说', 2);
INSERT INTO `tb_category` VALUES (51, '漫画', 2);
INSERT INTO `tb_category` VALUES (52, '动漫', 2);
INSERT INTO `tb_category` VALUES (53, '电视剧', 2);
INSERT INTO `tb_category` VALUES (54, '电影', 2);
INSERT INTO `tb_category` VALUES (55, '综艺', 2);
INSERT INTO `tb_category` VALUES (56, '游戏', 2);
INSERT INTO `tb_category` VALUES (57, '音乐', 3);
INSERT INTO `tb_category` VALUES (58, '棋类', 3);
INSERT INTO `tb_category` VALUES (59, '书法', 3);
INSERT INTO `tb_category` VALUES (60, '绘画', 3);
INSERT INTO `tb_category` VALUES (61, '运动', 3);
INSERT INTO `tb_category` VALUES (62, '养动物', 3);
INSERT INTO `tb_category` VALUES (63, '计算机', 4);
INSERT INTO `tb_category` VALUES (64, '动画', 4);
INSERT INTO `tb_category` VALUES (65, '考大学', 5);
INSERT INTO `tb_category` VALUES (66, '大学', 5);
INSERT INTO `tb_category` VALUES (67, '找工作', 5);
INSERT INTO `tb_category` VALUES (68, '工作', 5);
INSERT INTO `tb_category` VALUES (69, '遇见', 5);
INSERT INTO `tb_category` VALUES (70, '生病', 5);
INSERT INTO `tb_category` VALUES (71, '生活', 5);

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `commentId` int(0) NOT NULL AUTO_INCREMENT,
  `commentParent` int(0) NOT NULL DEFAULT 0,
  `userId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `commentContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createdTime` datetime(0) NOT NULL,
  `commentReply` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复的用户id',
  `linkedId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`commentId`) USING BTREE,
  INDEX `userForeign`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `tb_dynamic`;
CREATE TABLE `tb_dynamic`  (
  `dynamicId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userId` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dynamicContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createdTime` datetime(0) NOT NULL,
  `categoryId` int(0) NOT NULL,
  `dynamicImages` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dynamicId`) USING BTREE,
  INDEX `category-foreign`(`categoryId`) USING BTREE,
  INDEX `user-foreign`(`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_dynamic
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
  INDEX `nickname`(`nickname`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('user_1111111111111111', 'Aprilsxz', NULL, '333', NULL);

-- ----------------------------
-- View structure for view_article
-- ----------------------------
DROP VIEW IF EXISTS `view_article`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_article` AS select `tb_article`.`articleId` AS `articleId`,`tb_article`.`categoryId` AS `categoryId`,`tb_article`.`userId` AS `userId`,`tb_article`.`articleTitle` AS `articleTitle`,`tb_article`.`articleTags` AS `articleTags`,`tb_article`.`articleCover` AS `articleCover`,`tb_article`.`articleContent` AS `articleContent`,`tb_user`.`nickname` AS `nickname`,`tb_category`.`categoryName` AS `categoryName`,`tb_article`.`createdTime` AS `createdTime`,`tb_article`.`updateTime` AS `updateTime` from ((`tb_article` join `tb_user`) join `tb_category`) where ((`tb_article`.`userId` = `tb_user`.`userId`) and (`tb_article`.`categoryId` = `tb_category`.`categoryId`));

-- ----------------------------
-- View structure for view_comment
-- ----------------------------
DROP VIEW IF EXISTS `view_comment`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_comment` AS select `tb_comment`.`commentId` AS `commentId`,`tb_comment`.`commentParent` AS `commentParent`,`tb_comment`.`linkedId` AS `linkedId`,`tb_comment`.`commentContent` AS `commentContent`,`tb_comment`.`commentReply` AS `commentReply`,`tb_user`.`userId` AS `userId`,`tb_user`.`nickname` AS `nickname`,`tb_user`.`avatar` AS `avatar`,`tb_comment`.`createdTime` AS `createdTime`,(select `tb_user`.`nickname` from `tb_user` where (`tb_user`.`userId` = `tb_comment`.`commentReply`)) AS `replyName` from (`tb_comment` join `tb_user`) where (`tb_comment`.`userId` = `tb_user`.`userId`);

-- ----------------------------
-- View structure for view_dynamic
-- ----------------------------
DROP VIEW IF EXISTS `view_dynamic`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `view_dynamic` AS select `tb_dynamic`.`dynamicId` AS `dynamicId`,`tb_dynamic`.`dynamicContent` AS `dynamicContent`,`tb_dynamic`.`dynamicImages` AS `dynamicImages`,`tb_dynamic`.`userId` AS `userId`,`tb_user`.`nickname` AS `nickname`,`tb_user`.`avatar` AS `avatar`,`tb_dynamic`.`createdTime` AS `createdTime`,`tb_dynamic`.`categoryId` AS `categoryId`,`tb_category`.`categoryName` AS `categoryName` from ((`tb_dynamic` join `tb_user`) join `tb_category`) where ((`tb_user`.`userId` = `tb_dynamic`.`userId`) and (`tb_dynamic`.`categoryId` = `tb_category`.`categoryId`));

SET FOREIGN_KEY_CHECKS = 1;
