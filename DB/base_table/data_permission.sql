/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.170
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : 192.168.1.170:3306
 Source Schema         : datapermissions

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 03/05/2023 16:14:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for data_permission
-- ----------------------------
DROP TABLE IF EXISTS `data_permission`;
CREATE TABLE `data_permission` (
  `id` int DEFAULT NULL COMMENT 'id',
  `fieldName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '字段名',
  `permissionType` int DEFAULT NULL COMMENT '拦截方式：1 in、2 between'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of data_permission
-- ----------------------------
BEGIN;
INSERT INTO `data_permission` (`id`, `fieldName`, `permissionType`) VALUES (1, 'acc_date', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
