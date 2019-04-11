/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : kb

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 12/04/2019 01:29:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for couandstu
-- ----------------------------
DROP TABLE IF EXISTS `couandstu`;
CREATE TABLE `couandstu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classId` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classNum` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `s_id`(`s_id`) USING BTREE,
  INDEX `classNum`(`classNum`) USING BTREE,
  INDEX `s_name`(`s_name`) USING BTREE,
  INDEX `classId`(`classId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
