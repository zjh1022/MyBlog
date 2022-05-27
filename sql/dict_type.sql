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

 Date: 27/05/2022 12:48:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `delete_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '刪除者',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '用户性别列表');
INSERT INTO `dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '菜单状态列表');
INSERT INTO `dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '系统开关列表');
INSERT INTO `dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '任务状态列表');
INSERT INTO `dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '任务分组列表');
INSERT INTO `dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '系统是否列表');
INSERT INTO `dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '通知类型列表');
INSERT INTO `dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '通知状态列表');
INSERT INTO `dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '操作类型列表');
INSERT INTO `dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2022-02-16 11:33:00', 'ry', '2022-02-16 11:33:00', '', NULL, '登录状态列表');
INSERT INTO `dict_type` VALUES (11, '博客状态', 'bg_blog_status', '0', 'admin', '2022-02-16 11:33:00', '', NULL, '', NULL, '博客状态列表');
INSERT INTO `dict_type` VALUES (12, '博客推荐', 'bg_blog_support', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-01-19 10:28:38', '', NULL, '博客推荐列表');
INSERT INTO `dict_type` VALUES (13, '博客评论', 'bg_blog_comment', '0', 'admin', '2022-02-16 11:33:00', '', NULL, '', NULL, '博客评论列表');

SET FOREIGN_KEY_CHECKS = 1;
