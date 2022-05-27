/*
 Navicat MySQL Data Transfer

 Source Server         : 172.20.182.164
 Source Server Type    : MySQL
 Source Server Version : 100505
 Source Host           : 172.20.182.164:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 100505
 File Encoding         : 65001

 Date: 27/05/2022 12:49:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);


SET FOREIGN_KEY_CHECKS = 1;
