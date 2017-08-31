/*
Navicat MySQL Data Transfer

Source Server         : lishch
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : temp_db

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-03-23 10:12:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '6912b2d167c8e11a2fdd0105902f6904',
  `realname` varchar(100) DEFAULT NULL COMMENT 'test',
  `userArea` varchar(255) DEFAULT NULL,
  `userType` int(11) DEFAULT '0' COMMENT '1  管理员\r\n2  用户（报纸、期刊、等）\r\n3、初审\r\n4、复审\r\n5、终审',
  `userState` int(11) DEFAULT '0' COMMENT '0 存在,1 删除',
  `roleIds` varchar(100) DEFAULT NULL COMMENT '角色ID',
  `updateTime` datetime DEFAULT NULL COMMENT '用户密码修改时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'test', '6912b2d167c8e11a2fdd0105902f6904', '管理者', null, '1', '0', '1,2,6,10', '2017-03-13 17:02:29', null);
