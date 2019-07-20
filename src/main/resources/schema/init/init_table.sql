/*
Navicat MySQL Data Transfer

Source Server         : aigo1-collect-engine
Source Server Version : 50643
Source Host           : aigo1:30011
Source Database       : collect-engine

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-05-16 15:35:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(30) NOT NULL COMMENT '模块',
  `module_name` varchar(50) NOT NULL COMMENT '模块名称',
  `action` varchar(20) NOT NULL COMMENT '操作',
  `description` varchar(128) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限表';


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '约定ID为1的角色为管理员',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(128) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', null);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限关联表';



-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '用户名称(显示名称),如果账户类型为1,则该字段不能为空',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `account_type` int(11) NOT NULL COMMENT '账户类型,1:引擎供应商,2:平台用户',
  `company_name` varchar(64) NOT NULL COMMENT '公司名,如果账户类型为1,则该字段不能为空',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '盐值: 在密码中混入一段"随机"的字符串再进行哈希加密, 这个字符串被称作盐值',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话,如果账户类型为1,则该字段不能为空',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态(1:正常 2:删除)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `login_error_count` int(11) DEFAULT '0' COMMENT '账号登录错误次数, 达到指定次数账号将被锁定',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '管理员', 'admin', '1', '测试引擎供应商1', 'd5004059bb7e4b79267b88dc167980a7', 'admin', null, null, '1', '2019-05-16 05:04:16', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
