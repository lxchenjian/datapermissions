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

 Date: 03/05/2023 16:14:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_data_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_data_permission`;
CREATE TABLE `user_data_permission` (
  `id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `permission_id` bigint DEFAULT NULL,
  `values` varchar(1000) NOT NULL COMMENT '不能null，不能 为空字符串'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_data_permission
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
