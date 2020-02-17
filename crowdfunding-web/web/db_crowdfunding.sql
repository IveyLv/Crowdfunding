/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : db_crowdfunding

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2020-02-17 19:21:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('1', '系统菜单', '0', null, 'glyphicon glyphicon-dashboard');
INSERT INTO `tb_permission` VALUES ('2', '控制面板', '1', null, 'glyphicon glyphicon-dashboard');
INSERT INTO `tb_permission` VALUES ('3', '权限管理', '1', null, 'glyphicon glyphicon glyphicon-tasks');
INSERT INTO `tb_permission` VALUES ('4', '用户维护', '3', '/user/index', 'glyphicon glyphicon-user');
INSERT INTO `tb_permission` VALUES ('5', '角色维护', '3', '/role/index', 'glyphicon glyphicon-king');
INSERT INTO `tb_permission` VALUES ('6', '许可维护', '3', '/permission/index', 'glyphicon glyphicon-lock');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', 'PM-项目经理');
INSERT INTO `tb_role` VALUES ('2', 'SE-软件工程师');
INSERT INTO `tb_role` VALUES ('3', 'PG-程序员');
INSERT INTO `tb_role` VALUES ('4', 'TL-组长');
INSERT INTO `tb_role` VALUES ('5', 'GL-组长');
INSERT INTO `tb_role` VALUES ('6', 'QC-品质控制');
INSERT INTO `tb_role` VALUES ('7', 'SA-软件架构师');
INSERT INTO `tb_role` VALUES ('8', 'CMO/CMS-配置管理');
INSERT INTO `tb_role` VALUES ('9', 'SYSTEM-系统管理');

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES ('4', '1', '1');
INSERT INTO `tb_role_permission` VALUES ('5', '1', '3');
INSERT INTO `tb_role_permission` VALUES ('6', '1', '4');
INSERT INTO `tb_role_permission` VALUES ('7', '1', '5');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'Tom', '123456', 'Tom', 'Tom@163.com', '2019-07-03 08:51:50');
INSERT INTO `tb_user` VALUES ('2', 'Jack', '468648', 'Jack', 'Jack@163.com', '2019-07-05 16:54:34');
INSERT INTO `tb_user` VALUES ('3', 'Linda', '452527', 'Linda', 'Linda@163.com', '2019-07-10 10:04:41');
INSERT INTO `tb_user` VALUES ('4', 'Mark', '452778', 'Mark', 'Mark@163.com', '2019-07-12 15:53:27');
INSERT INTO `tb_user` VALUES ('5', 'Jerry', '528692', 'Jerry', 'Jerry@163.com', '2019-07-17 11:51:58');
INSERT INTO `tb_user` VALUES ('6', 'Rich', '782937', 'Rich', 'Rich@163.com', '2019-07-18 13:18:24');
INSERT INTO `tb_user` VALUES ('7', 'Ivey', '785287', 'Ivey', 'Ivey@163.com', '2019-07-24 11:51:00');
INSERT INTO `tb_user` VALUES ('8', 'James', '824658', 'James', 'James@163.com', '2019-07-23 16:51:12');
INSERT INTO `tb_user` VALUES ('9', '张三', '358697', '张三', 'zhangsan@163.com', '2019-07-24 11:55:41');
INSERT INTO `tb_user` VALUES ('10', 'William', '278278', 'William', 'William@163.com', '2019-07-24 11:55:41');
INSERT INTO `tb_user` VALUES ('11', '王五', '288795', '李四', 'lisi@163.com', '2020-01-23 12:03:21');
INSERT INTO `tb_user` VALUES ('12', 'Kernel', '123456', 'Kernel', 'Kernel@163.com', '2020-01-24 11:03:22');
INSERT INTO `tb_user` VALUES ('13', 'Peter', '123456', 'Peter', 'Peter@163.com', '2020-01-24 12:03:21');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '1');
INSERT INTO `tb_user_role` VALUES ('2', '12', '2');
