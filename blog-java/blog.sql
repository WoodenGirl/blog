/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 11/12/2024 17:13:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `article_title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章标题',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
  `article_tags` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文字标签',
  `category_id` int NOT NULL COMMENT '分类id',
  `article_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文字内容',
  `article_cover` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '封面图片',
  `status` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '发布状态：0表示草稿，1表示已发布',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `categoryForeign`(`category_id` ASC) USING BTREE,
  INDEX `article_user_foreign`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('article_d774f37033dd4faab79db2525315e416', '租房事宜', 'user_1111111111111111', '租房,安全', 1, '<p>租房：</p><p> &nbsp; &nbsp;地点：工作地点附近</p><p> &nbsp; &nbsp;人物：中介，拿一个月房租的一半， 房东，希望他人好</p><p> &nbsp; &nbsp;关键物品：合同（一定要每条都看，来自第一次租房每条都看啥事没有，第二次大胆签名回家天塌了的感悟）（分享一下感觉天塌了的租房合同😀）</p><p><img src=\"http://obs.aprilsxz.fun/articleInsert/m49nkrl4pv71d2km2.jpg\" alt=\"微信图片_20241204103820.jpg\" data-href=\"http://obs.aprilsxz.fun/articleInsert/m49nkrl4pv71d2km2.jpg\" style=\"width: 50%;\"/></p><p>租房后：</p><p> &nbsp; &nbsp;网络：手机流量，随身wifi，租房wifi</p><p> &nbsp; &nbsp;安全：换锁，装摄像头</p><p><br></p>', 'articleCover/1733279496288.jpg', 0, '2024-12-04 10:34:39', '2024-12-04 16:55:50');
INSERT INTO `article` VALUES ('b39d098c7ac7574e', '开发进行中', 'user_1111111111111111', '开发,本网站', 1, '<p style=\"text-align: center;\">开发ing</p><table style=\"width: 100%;table-layout: fixed;height:357\"><colgroup contentEditable=\"false\"><col width=30></col><col width=30></col><col width=87></col><col width=60></col></colgroup><tbody><tr><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">模块</th><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">分工</th><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">实现</th><th colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center;\">待实现</th></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">目录</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">树展示，重命名，添加父子节点，删除节点</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删改查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">评论</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">动态中样式需修改；展开收起回复</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">文章</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">文章封面上传、文章内容图片从服务器上传删除；文章列表显示，文章查看修改删除</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">表单校验；图片格式大小限制；跳转不更新；</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删改查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">动态</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">动态添加，图片上传，展示图片</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">点赞评论数量显示</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删查</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">添加数据库表，点赞表</td></tr><tr><td colspan=\"1\" rowspan=\"2\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">用户</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">个人中心，权限</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"display: none; text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">后端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">增删改查</td></tr><tr><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">整体</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">前端</td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\"></td><td colspan=\"1\" rowspan=\"1\" width=\"auto\" style=\"text-align: center; border-width: 1px; border-style: solid; border-color: rgb(204, 204, 204);\">分页？；点赞功能</td></tr></tbody></table><p><br></p>', 'articleCover/1733365098697.jpg', 0, '2024-12-05 10:45:44', '2024-12-06 15:48:12');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `category_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名称',
  `category_parent` int NOT NULL COMMENT '父目录',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Aprisxlz', 0);
INSERT INTO `category` VALUES (2, 'acg', 0);
INSERT INTO `category` VALUES (3, 'interest', 0);
INSERT INTO `category` VALUES (4, 'technique', 0);
INSERT INTO `category` VALUES (5, 'life', 0);
INSERT INTO `category` VALUES (50, '小说', 2);
INSERT INTO `category` VALUES (51, '漫画', 2);
INSERT INTO `category` VALUES (52, '动漫', 2);
INSERT INTO `category` VALUES (53, '电视剧', 2);
INSERT INTO `category` VALUES (54, '电影', 2);
INSERT INTO `category` VALUES (55, '综艺', 2);
INSERT INTO `category` VALUES (56, '游戏', 2);
INSERT INTO `category` VALUES (57, '音乐', 3);
INSERT INTO `category` VALUES (58, '棋类', 3);
INSERT INTO `category` VALUES (59, '书法', 3);
INSERT INTO `category` VALUES (60, '绘画', 3);
INSERT INTO `category` VALUES (61, '运动', 3);
INSERT INTO `category` VALUES (62, '养动物', 3);
INSERT INTO `category` VALUES (63, '计算机', 4);
INSERT INTO `category` VALUES (64, '动画', 4);
INSERT INTO `category` VALUES (65, '考大学', 5);
INSERT INTO `category` VALUES (66, '大学', 5);
INSERT INTO `category` VALUES (67, '找工作', 5);
INSERT INTO `category` VALUES (68, '工作', 5);
INSERT INTO `category` VALUES (69, '遇见', 5);
INSERT INTO `category` VALUES (70, '生病', 5);
INSERT INTO `category` VALUES (71, '生活', 5);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `comment_parent` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '父评论id',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `comment_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `comment_reply` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回复的用户id',
  `linked_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `userForeign`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic`  (
  `dynamic_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
  `category_id` int UNSIGNED NOT NULL COMMENT '分类id',
  `dynamic_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '动态内容',
  `dynamic_images` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '动态图片',
  `status` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '发布状态：0表示草稿，1表示已发布',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`dynamic_id`) USING BTREE,
  INDEX `category-foreign`(`category_id` ASC) USING BTREE,
  INDEX `user-foreign`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dynamic
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
  `nickname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `salt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '随机盐值加密',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个性签名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `nickname`(`nickname` ASC) USING BTREE,
  INDEX `userId`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '哈哈哈哈', '12', '', NULL, NULL);
INSERT INTO `user` VALUES ('user_1111111111111111', 'Aprilsxz', '333', '', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
