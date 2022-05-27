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

 Date: 27/05/2022 12:47:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_data
-- ----------------------------
DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data`  (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `delete_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '刪除者',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_data
-- ----------------------------
INSERT INTO `dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '性别男');
INSERT INTO `dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '性别女');
INSERT INTO `dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '性别未知');
INSERT INTO `dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '显示菜单');
INSERT INTO `dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '隐藏菜单');
INSERT INTO `dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '正常状态');
INSERT INTO `dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '停用状态');
INSERT INTO `dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '正常状态');
INSERT INTO `dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '停用状态');
INSERT INTO `dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '默认分组');
INSERT INTO `dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '系统分组');
INSERT INTO `dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '系统默认是');
INSERT INTO `dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '系统默认否');
INSERT INTO `dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '通知');
INSERT INTO `dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '公告');
INSERT INTO `dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '正常状态');
INSERT INTO `dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '关闭状态');
INSERT INTO `dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '新增操作');
INSERT INTO `dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '修改操作');
INSERT INTO `dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '删除操作');
INSERT INTO `dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '授权操作');
INSERT INTO `dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '导出操作');
INSERT INTO `dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '导入操作');
INSERT INTO `dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '强退操作');
INSERT INTO `dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '生成操作');
INSERT INTO `dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '清空操作');
INSERT INTO `dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '正常状态');
INSERT INTO `dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '停用状态');
INSERT INTO `dict_data` VALUES (29, 1, '草稿', 'false', 'bg_blog_status', NULL, NULL, 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '草稿箱');
INSERT INTO `dict_data` VALUES (30, 2, '发布', 'true', 'bg_blog_status', NULL, NULL, 'N', '0', 'admin', '2022-02-16 11:33:00', 'admin', '2022-02-16 11:33:00', '', NULL, '已发布');
INSERT INTO `dict_data` VALUES (31, 1, '是', 'true', 'bg_blog_support', NULL, NULL, 'N', '0', 'admin', '2022-02-16 11:33:00', '', NULL, '', NULL, '推荐');
INSERT INTO `dict_data` VALUES (32, 2, '否', 'false', 'bg_blog_support', NULL, NULL, 'N', '0', 'admin', '2022-02-16 11:33:00', '', NULL, '', NULL, '不推荐');
INSERT INTO `dict_data` VALUES (33, 1, '开启', 'true', 'bg_blog_comment', NULL, NULL, 'N', '0', 'admin', '2022-02-16 11:33:00', '', NULL, '', NULL, '开启');
INSERT INTO `dict_data` VALUES (34, 2, '关闭', 'false', 'bg_blog_comment', NULL, NULL, 'N', '0', 'admin', '2022-02-16 11:33:00', '', NULL, '', NULL, '关闭');

SET FOREIGN_KEY_CHECKS = 1;
