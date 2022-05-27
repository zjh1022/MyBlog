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

 Date: 27/05/2022 12:49:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 108);
INSERT INTO `role_menu` VALUES (1, 500);
INSERT INTO `role_menu` VALUES (1, 501);
INSERT INTO `role_menu` VALUES (1, 1040);
INSERT INTO `role_menu` VALUES (1, 1041);
INSERT INTO `role_menu` VALUES (1, 1042);
INSERT INTO `role_menu` VALUES (1, 1043);
INSERT INTO `role_menu` VALUES (1, 1044);
INSERT INTO `role_menu` VALUES (1, 1045);
INSERT INTO `role_menu` VALUES (1, 1066);
INSERT INTO `role_menu` VALUES (1, 1067);
INSERT INTO `role_menu` VALUES (1, 1068);
INSERT INTO `role_menu` VALUES (1, 1069);
INSERT INTO `role_menu` VALUES (1, 1073);
INSERT INTO `role_menu` VALUES (1, 1074);
INSERT INTO `role_menu` VALUES (1, 1075);
INSERT INTO `role_menu` VALUES (1, 1076);
INSERT INTO `role_menu` VALUES (1, 1077);
INSERT INTO `role_menu` VALUES (1, 1078);
INSERT INTO `role_menu` VALUES (1, 1079);
INSERT INTO `role_menu` VALUES (1, 1080);
INSERT INTO `role_menu` VALUES (1, 1081);
INSERT INTO `role_menu` VALUES (1, 1082);
INSERT INTO `role_menu` VALUES (1, 1083);
INSERT INTO `role_menu` VALUES (1, 1084);

SET FOREIGN_KEY_CHECKS = 1;
