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

 Date: 12/04/2019 01:29:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `hashDay` int(10) NOT NULL,
  `hashLesson` int(10) NOT NULL,
  `beginClass` int(10) NOT NULL,
  `day` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classId` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lesson` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classroom` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classNum` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rawWeek` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `week` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `weekBegin` int(11) NOT NULL,
  `weekEnd` int(10) NOT NULL,
  `weekModel` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `classNum`(`classNum`) USING BTREE,
  INDEX `classId`(`classId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
