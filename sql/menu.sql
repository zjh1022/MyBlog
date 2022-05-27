/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : blog2

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 27/05/2022 12:50:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(11) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（0目录 1菜单 2按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `delete_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '刪除者',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1098 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '系统管理', 0, 50, 'system', NULL, 1, 'M', '0', '', 'system', 'admin', '2018-03-16 11:33:00', 'admin', '2022-01-19 10:29:48', '', NULL, '系统管理目录');
INSERT INTO `menu` VALUES (2, '系统监控', 0, 40, 'monitor', NULL, 1, 'M', '0', '', 'monitor', 'admin', '2018-03-16 11:33:00', 'admin', '2022-01-19 10:30:19', '', NULL, '系统监控目录');
INSERT INTO `menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 'C', '1', 'system:user:list', 'user', 'admin', '2018-03-16 11:33:00', 'admin', '2022-02-19 17:40:53', '', NULL, '用户管理菜单');
INSERT INTO `menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 'C', '1', 'system:role:list', 'peoples', 'admin', '2018-03-16 11:33:00', 'admin', '2022-02-19 17:41:00', '', NULL, '角色管理菜单');
INSERT INTO `menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 'C', '0', 'system:menu:list', 'tree-table', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '菜单管理菜单');
INSERT INTO `menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 'C', '0', 'system:dict:list', 'dict', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '字典管理菜单');
INSERT INTO `menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 'C', '1', 'system:config:list', 'edit', 'admin', '2018-03-16 11:33:00', 'admin', '2022-02-16 13:31:12', '', NULL, '参数设置菜单');
INSERT INTO `menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 'C', '0', 'system:notice:list', 'message', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '通知公告菜单');
INSERT INTO `menu` VALUES (108, '日志管理', 0, 20, 'log', '', 1, 'M', '1', '', 'log', 'admin', '2018-03-16 11:33:00', 'admin', '2022-03-02 16:53:14', '', NULL, '日志管理菜单');
INSERT INTO `menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 'C', '1', 'monitor:online:list', 'online', 'admin', '2018-03-16 11:33:00', 'admin', '2022-02-19 17:48:45', '', NULL, '在线用户菜单');
INSERT INTO `menu` VALUES (110, '定时任务', 1095, 2, 'quartz', 'tool/quartz/index', 1, 'C', '1', 'monitor:job:list', 'job', 'admin', '2018-03-16 11:33:00', 'admin', '2022-02-22 16:17:32', '', NULL, '定时任务菜单');
INSERT INTO `menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 'C', '0', 'monitor:druid:list', 'druid', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '数据监控菜单');
INSERT INTO `menu` VALUES (115, '系统接口', 2, 3, 'swagger', 'monitor/swagger/index', 1, 'C', '0', 'tool:swagger:list', 'swagger', 'admin', '2018-03-16 11:33:00', 'admin', '2019-10-24 10:21:02', '', NULL, '系统接口菜单');
INSERT INTO `menu` VALUES (500, '操作日志', 108, 1, 'operateLog', 'log/operateLog/index', 1, 'C', '0', 'monitor:operlog:list', 'form', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '操作日志菜单');
INSERT INTO `menu` VALUES (501, '登录日志', 108, 2, 'loginLog', 'log/visitLog/index', 1, 'C', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2018-03-16 11:33:00', 'admin', '2022-03-02 16:45:16', '', NULL, '登录日志菜单');
INSERT INTO `menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 'F', '0', 'system:user:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 'F', '0', 'system:role:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 'F', '0', 'system:menu:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1026, '字典查询', 105, 1, '#', '', 1, 'F', '0', 'system:dict:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1027, '字典新增', 105, 2, '#', '', 1, 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1028, '字典修改', 105, 3, '#', '', 1, 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1029, '字典删除', 105, 4, '#', '', 1, 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1030, '字典导出', 105, 5, '#', '', 1, 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1031, '参数查询', 106, 1, '#', '', 1, 'F', '0', 'system:config:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1032, '参数新增', 106, 2, '#', '', 1, 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1033, '参数修改', 106, 3, '#', '', 1, 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1034, '参数删除', 106, 4, '#', '', 1, 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1035, '参数导出', 106, 5, '#', '', 1, 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1036, '公告查询', 107, 1, '#', '', 1, 'F', '0', 'system:notice:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1037, '公告新增', 107, 2, '#', '', 1, 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1038, '公告修改', 107, 3, '#', '', 1, 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1039, '公告删除', 107, 4, '#', '', 1, 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1040, '操作查询', 500, 1, '#', '', 1, 'F', '0', 'monitor:operlog:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1041, '操作删除', 500, 2, '#', '', 1, 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1042, '日志导出', 500, 4, '#', '', 1, 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1043, '登录查询', 501, 1, '#', '', 1, 'F', '0', 'monitor:logininfor:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1044, '登录删除', 501, 2, '#', '', 1, 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1045, '日志导出', 501, 3, '#', '', 1, 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1046, '在线查询', 109, 1, '#', '', 1, 'F', '0', 'monitor:online:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1047, '批量强退', 109, 2, '#', '', 1, 'F', '0', 'monitor:online:batchLogout', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1048, '单条强退', 109, 3, '#', '', 1, 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1049, '任务查询', 110, 1, '#', '', 1, 'F', '0', 'monitor:job:query', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1050, '任务新增', 110, 2, '#', '', 1, 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1051, '任务修改', 110, 3, '#', '', 1, 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1052, '任务删除', 110, 4, '#', '', 1, 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1053, '状态修改', 110, 5, '#', '', 1, 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1054, '任务导出', 110, 7, '#', '', 1, 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', '', NULL, '');
INSERT INTO `menu` VALUES (1066, '博客管理', 0, 10, 'blogManager', NULL, 1, 'M', '0', '', 'nested', 'admin', '2019-10-24 10:25:45', 'admin', '2020-06-03 17:13:01', '', NULL, '');
INSERT INTO `menu` VALUES (1067, '文章管理', 1066, 1, 'blog', 'blog/blog/index', 1, 'C', '0', 'blog:blog:list', 'dict', 'admin', '2019-10-24 10:26:41', 'admin', '2022-01-19 10:36:44', '', NULL, '');
INSERT INTO `menu` VALUES (1068, '评论管理', 1066, 4, 'comment', 'blog/comment/index', 1, 'C', '0', 'blog:comment:list', 'component', 'admin', '2019-10-24 10:31:20', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1069, '类别管理', 1066, 2, 'category', 'blog/category/index', 1, 'C', '0', 'blog:category:list', 'download', 'admin', '2019-10-24 10:32:17', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1072, '友链管理', 1, 6, 'link', 'system/link/index', 1, 'C', '0', 'system:link:list', '404', 'admin', '2019-10-29 11:27:42', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1073, '博文查询', 1067, 1, '', NULL, 1, 'F', '0', 'blog:blog:query', '#', 'admin', '2019-11-01 17:25:38', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1074, '博文修改', 1067, 2, '', NULL, 1, 'F', '0', 'blog:blog:edit', '#', 'admin', '2019-11-01 17:25:56', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1075, '博文新增', 1067, 3, '', '', 1, 'F', '0', 'blog:blog:add', '#', 'admin', '2019-11-01 17:26:14', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1076, '博文删除', 1067, 4, '', NULL, 1, 'F', '0', 'blog:blog:remove', '#', 'admin', '2019-11-01 17:26:39', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1077, '类别新增', 1069, 1, '', NULL, 1, 'F', '0', 'blog:category:add', '#', 'admin', '2019-11-01 17:27:09', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1078, '类别删除', 1069, 2, '', NULL, 1, 'F', '0', 'blog:category:remove', '#', 'admin', '2019-11-01 17:27:27', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1079, '分类查询', 1069, 3, '', NULL, 1, 'F', '0', 'blog:category:query', '#', 'admin', '2019-11-01 17:27:49', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1080, '分类修改', 1069, 4, '', NULL, 1, 'F', '0', 'blog:category:edit', '#', 'admin', '2019-11-01 17:28:09', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1081, '评论新增', 1068, 1, '', NULL, 1, 'F', '0', 'blog:comment:add', '#', 'admin', '2019-11-01 17:28:28', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1082, '评论删除', 1068, 2, '', NULL, 1, 'F', '0', 'blog:comment:remove', '#', 'admin', '2019-11-01 17:28:50', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1083, '评论修改', 1068, 3, '', NULL, 1, 'F', '0', 'blog:comment:edit', '#', 'admin', '2019-11-01 17:29:32', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1084, '评论查询', 1068, 4, '', NULL, 1, 'F', '0', 'blog:comment:query', '#', 'admin', '2019-11-01 17:29:51', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1085, '友链新增', 1072, 1, '', NULL, 1, 'F', '0', 'system:link:add', '#', 'admin', '2019-11-01 17:30:17', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1086, '友链删除', 1072, 2, '', NULL, 1, 'F', '0', 'system:link:remove', '#', 'admin', '2019-11-01 17:30:34', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1087, '友链查询', 1072, 3, '', NULL, 1, 'F', '0', 'system:link:query', '#', 'admin', '2019-11-01 17:30:52', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1088, '友链修改', 1072, 4, '', NULL, 1, 'F', '0', 'system:link:edit', '#', 'admin', '2019-11-01 17:31:08', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1093, '轮播图管理', 1, 7, 'carousel', 'system/carousel/index', 1, 'C', '0', 'system:carousel:list', 'dict', 'admin', '2019-11-04 11:11:02', '', NULL, '', NULL, '');
INSERT INTO `menu` VALUES (1094, '访问日志', 2, 6, 'log', 'log/visitLog/index', 1, 'C', '0', '', 'education', 'admin', '2019-11-05 18:16:20', 'admin', '2022-03-02 16:53:44', '', NULL, '');
INSERT INTO `menu` VALUES (1095, '系统工具', 0, 30, 'tool', NULL, 1, 'M', '0', '', 'education', 'admin', '2019-11-06 10:53:11', 'admin', '2020-06-03 17:13:20', '', NULL, '');
INSERT INTO `menu` VALUES (1097, '存储管理', 1095, 2, 'storage', 'tool/storage/index', 1, 'C', '0', 'tool:storage:list', 'eye-open', 'admin', '2019-11-06 10:54:28', 'admin', '2019-11-07 12:07:52', '', NULL, '');

SET FOREIGN_KEY_CHECKS = 1;
