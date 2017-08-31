/*
Navicat MySQL Data Transfer

Source Server         : lishch
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : new_medium_txxxb

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-08-31 19:49:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `localUrl` varchar(200) DEFAULT NULL COMMENT '针对单纯的图片，语音，视频稿件，内部系统URL',
  `externalUrl` varchar(200) DEFAULT NULL COMMENT '针对单纯的图片，语音，视频稿件，稿件推送到外部系统后，外部URL',
  `size` int(10) DEFAULT NULL COMMENT '纯语音，视频，图片稿件的size',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `classification` varchar(300) DEFAULT NULL COMMENT '分类-针对推送的第三方系统',
  `mediumTag` varchar(1000) DEFAULT NULL COMMENT '内部系统分类标签',
  `brief` varchar(300) DEFAULT NULL COMMENT '摘要',
  `type` tinyint(4) DEFAULT NULL COMMENT '稿件类型：0纯图片，1纯音频，2纯视频，3纯文字，4文图混合，5文图音视频混合....',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `pending` tinyint(1) DEFAULT NULL COMMENT '判断标志：审核过/审核中/未审核',
  `updateTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', 'E:/加好.png', null, null, 'ZB0002F06 事業所情報／取引先の所属のエラーチェックについて', null, '体育,足球,中超,篮球,NBA,时政,国内要闻,国际要闻,军事要闻,文艺,影视,明星,国际电影节', '阿斯蒂芬', null, null, null, '2017-04-26 17:51:51', '2017-04-26 17:51:51');
INSERT INTO `tb_article` VALUES ('3', 'C:\\Users\\lishch\\workspace2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mediumbuildplat\\wxEditor\\upload\\article\\20170426-181145.txt', null, '0', '标题1', null, '体育,足球,中超,篮球,NBA,时政,国内要闻,国际要闻,军事要闻,文艺,影视,明星,国际电影节', '啊撒旦解放；lkj\nasdf\n阿斯利康的飞机；离开家', '0', null, '0', '2017-04-26 18:11:45', '2017-04-26 18:11:45');
INSERT INTO `tb_article` VALUES ('4', 'C:\\Users\\lishch\\workspace2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mediumbuildplat\\wxEditor\\upload\\article\\20170426-181703.txt', null, '0', '标题1', null, '体育,足球,中超,篮球,NBA', '这次来电\n特殊的\n吧', '0', null, '0', '2017-04-27 21:05:21', '2017-04-26 18:17:03');
INSERT INTO `tb_article` VALUES ('5', 'C:\\Users\\lishch\\workspace2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mediumbuildplat\\wxEditor\\upload\\article\\20170427-163712.txt', null, '0', '标题1', null, '时政,国内要闻,国际要闻,军事要闻', '啊撒旦解放；lkj\nasdf\n阿斯利康的飞机；离开家', '0', null, '0', '2017-04-27 21:05:40', '2017-04-27 16:37:12');
INSERT INTO `tb_article` VALUES ('6', 'C:\\Users\\lishch\\workspace2\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mediumbuildplat\\wxEditor\\upload\\article\\20170427-210645.txt', null, '0', '标题1', null, '体育,足球,中超,篮球,NBA', '啥地方\n来着\n呢', '0', null, '0', '2017-04-27 21:06:45', '2017-04-27 21:06:45');
INSERT INTO `tb_article` VALUES ('7', 'C:\\Users\\lishch\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mediumbuildplat\\static\\upload_txt\\无标题.txt', null, null, '拉萨扩大飞机离开', null, '体育,足球,中超,篮球,NBA,时政,国内要闻,国际要闻,军事要闻,文艺,影视,明星,国际电影节', '儿童岁的法国\r\nasdf\r\n\r\nasdf\r\n\r\n\r\n\r\nasdf\r\n\r\n\r\n\r\nsdf\r\n\r\n\r\n\r\n\r\nasdf\r\n\r\n\r\n\r\n\r\nasdf', '3', null, null, '2017-04-28 17:19:05', '2017-04-28 17:19:05');
INSERT INTO `tb_article` VALUES ('8', 'C:\\Users\\lishch\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mediumbuildplat\\static\\upload_txt\\图文.txt', null, null, '拉萨扩大飞机离开', null, '体育,足球,中超,篮球,NBA,时政,国内要闻,国际要闻,军事要闻,文艺,影视,明星,国际电影节', '儿童岁的法国\r\nasdf\r\n\r\nasdf\r\n\r\n\r\n\r\nasdf\r\n\r\n\r\n\r\nsdf\r\n\r\n\r\n\r\n\r\nasdf\r\n\r\n\r\n\r\n\r\nasdf', '3', null, null, '2017-04-28 17:19:30', '2017-04-28 17:19:30');
INSERT INTO `tb_article` VALUES ('9', 'C:\\Users\\lishch\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mediumbuildplat\\static\\upload_txt\\无标题.txt', null, null, '拉萨扩大飞机离开', null, '体育,足球,中超,篮球,NBA,时政,国内要闻,国际要闻,军事要闻,文艺,影视,明星,国际电影节', '儿童岁的法国\r\nasdf\r\n\r\nasdf\r\n\r\n\r\n\r\nasdf\r\n\r\n\r\n\r\nsdf\r\n\r\n\r\n\r\n\r\nasdf\r\n\r\n\r\n\r\n\r\nasdf', '3', null, null, '2017-04-28 17:20:13', '2017-04-28 17:20:13');

-- ----------------------------
-- Table structure for tb_article_sending_rs
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_sending_rs`;
CREATE TABLE `tb_article_sending_rs` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `matchingArticleId` int(20) DEFAULT NULL COMMENT '配稿ID',
  `msgId` int(20) DEFAULT NULL COMMENT '媒体识别的消息ID',
  `callbackStatus` int(10) DEFAULT NULL COMMENT 'callback错误码',
  `checkState` tinyint(4) DEFAULT NULL COMMENT '整体校验结果',
  `checkContent` varchar(2000) DEFAULT NULL COMMENT 'callback信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article_sending_rs
-- ----------------------------

-- ----------------------------
-- Table structure for tb_autograph
-- ----------------------------
DROP TABLE IF EXISTS `tb_autograph`;
CREATE TABLE `tb_autograph` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) NOT NULL COMMENT '用户ID',
  `autographName` varchar(64) DEFAULT NULL COMMENT '签名名称',
  `content` varchar(500) DEFAULT NULL COMMENT '签名内容',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_autograph
-- ----------------------------

-- ----------------------------
-- Table structure for tb_matching_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_matching_article`;
CREATE TABLE `tb_matching_article` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `articleId` int(20) DEFAULT NULL COMMENT '稿件ID',
  `mediumId` int(20) DEFAULT NULL COMMENT '推送的媒体ID',
  `aoutoMatching` tinyint(1) DEFAULT NULL COMMENT '是否自动推送，0 手动 1自动， 默认手动',
  `matchingTime` datetime DEFAULT NULL COMMENT '配稿时间',
  `aoutoSendingTime` datetime DEFAULT NULL COMMENT '计划推送时间，自动推送才会有',
  `realSendingTime` datetime DEFAULT NULL COMMENT '实际推送时间，不管自动还是人工都会有实际推送时间',
  `matchingUserId` varchar(100) DEFAULT NULL COMMENT '配稿人',
  `groupId` int(20) DEFAULT NULL COMMENT '消息分组，一样的ID表示一组的：比如自媒体号一样，\n 计划推送时间（天）一样，\n 就用groupId来区分不同的发送，当然可以用具体时间区分',
  `createTime` datetime DEFAULT NULL COMMENT '记录创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_matching_article
-- ----------------------------

-- ----------------------------
-- Table structure for tb_medium
-- ----------------------------
DROP TABLE IF EXISTS `tb_medium`;
CREATE TABLE `tb_medium` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `access_Token` varchar(200) DEFAULT NULL COMMENT 'token',
  `apiType` tinyint(4) DEFAULT NULL COMMENT 'API类型：1http：0',
  `apiUrl` varchar(200) DEFAULT NULL COMMENT 'API地址',
  `method` varchar(50) DEFAULT NULL COMMENT 'http请求方式',
  `callbackUrl` varchar(200) DEFAULT NULL COMMENT 'callback URL，操作成功后回调的url',
  `mediumTag` varchar(1000) DEFAULT NULL COMMENT '内部标签',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型：微信、微博、自媒体',
  `articleType` tinyint(4) DEFAULT NULL COMMENT '可以推送的稿件类型：纯文本，纯图片，纯文字，纯视频，语音，混合类型1，混合类型2...',
  `sendingTimes` tinyint(4) DEFAULT NULL COMMENT '允许发送的次数',
  `countPertime` tinyint(4) DEFAULT NULL COMMENT '单次允许发送的条数',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `createUserId` int(10) DEFAULT NULL COMMENT '修改者',
  `updateUserId` int(10) DEFAULT NULL COMMENT '创建者',
  `updateTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_medium
-- ----------------------------

-- ----------------------------
-- Table structure for tb_mediumtag
-- ----------------------------
DROP TABLE IF EXISTS `tb_mediumtag`;
CREATE TABLE `tb_mediumtag` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `fatherId` int(20) DEFAULT NULL COMMENT '父节点ID',
  `content` varchar(200) DEFAULT NULL,
  `createUserId` int(10) DEFAULT NULL COMMENT '修改者',
  `updateUserId` int(10) DEFAULT NULL COMMENT '创建者',
  `updateTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_mediumtag
-- ----------------------------
INSERT INTO `tb_mediumtag` VALUES ('1', '0', '体育', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('2', '1', '足球', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('3', '2', '中超', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('4', '1', '篮球', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('5', '4', 'NBA', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('6', '0', '时政', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('7', '6', '国内要闻', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('8', '6', '国际要闻', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('9', '6', '军事要闻', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('10', '0', '文艺', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('11', '10', '影视', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('12', '10', '明星', null, null, null, null);
INSERT INTO `tb_mediumtag` VALUES ('13', '10', '国际电影节', null, null, null, null);

-- ----------------------------
-- Table structure for tb_medium_msg_cond_perday
-- ----------------------------
DROP TABLE IF EXISTS `tb_medium_msg_cond_perday`;
CREATE TABLE `tb_medium_msg_cond_perday` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id：每天同步这个表，把所有的媒体都同步过来，主要用于检验次数',
  `mediumId` int(20) DEFAULT NULL COMMENT '关联媒体ID',
  `sendingTime` varchar(8) DEFAULT NULL COMMENT '发送的时间例如：20170101',
  `sendingTimes` tinyint(4) DEFAULT NULL COMMENT '当日发送次数，发送结果没有callback之前当做发送成功，如果发送检验失败，则更新字段',
  `validFlag` tinyint(1) DEFAULT NULL COMMENT '是否还让发送，发送结果没有callback之前当做发送成功，如果发送检验失败，则更新字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_medium_msg_cond_perday
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0 存在,1 废弃',
  `systemType` tinyint(4) NOT NULL COMMENT '系统类型：0-通用平台，1生产平台， 2分发平台',
  `updateTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', null, null, null, null, null, '1', '0', '2017-04-20 17:19:29', '2017-04-20 17:19:29');
INSERT INTO `tb_menu` VALUES ('2', '标签维护', '1', '#', 'glyphicon glyphicon-list', '0', '1', '0', '2017-04-20 17:20:13', '2017-04-20 17:20:13');
INSERT INTO `tb_menu` VALUES ('3', '用户权限管理', '1', '/user/management', 'glyphicon glyphicon-lock', '1', '1', '0', '2017-04-20 17:21:58', '2017-04-20 17:21:58');
INSERT INTO `tb_menu` VALUES ('4', '微信公众号', '2', '/tagmt/wechat', 'glyphicon glyphicon-share-alt', '0', '1', '0', '2017-04-20 17:29:59', '2017-04-20 17:29:59');
INSERT INTO `tb_menu` VALUES ('5', '微博', '2', '/tagmt/webo', 'glyphicon glyphicon-share-alt', '1', '1', '0', '2017-04-20 17:30:45', '2017-04-20 17:30:45');
INSERT INTO `tb_menu` VALUES ('6', '其它', '2', '/tagmt/othermedium', 'glyphicon glyphicon-share-alt', '2', '1', '0', '2017-04-20 17:30:45', '2017-04-20 17:30:45');

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `receiverId` int(10) unsigned NOT NULL COMMENT '接收者ID',
  `readFlag` tinyint(1) NOT NULL COMMENT '已阅标志',
  `sourceMessageId` int(10) NOT NULL COMMENT '如果为-1则为原始群发消息，其他则为原始消息id',
  `messageTextId` int(10) NOT NULL COMMENT '消息体id',
  `sendTime` datetime DEFAULT NULL COMMENT '发送时间',
  `readTime` datetime DEFAULT NULL COMMENT '阅读时间',
  `type` tinyint(4) DEFAULT NULL COMMENT '消息：0系统群发，1个人<=>个人，2群聊',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_message
-- ----------------------------

-- ----------------------------
-- Table structure for tb_message_text
-- ----------------------------
DROP TABLE IF EXISTS `tb_message_text`;
CREATE TABLE `tb_message_text` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `senderId` int(10) unsigned NOT NULL COMMENT '发送者',
  `title` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '标题',
  `content` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_message_text
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(64) NOT NULL COMMENT '角色名',
  `flag` int(11) DEFAULT '0' COMMENT '0 存在，1 删除',
  `menus` varchar(500) DEFAULT NULL COMMENT '菜单ID',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='后台角色权限表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '系统管理员', '0', '1,2,3,4,5,6', '2017-04-20 20:50:01', '2017-04-20 20:50:01');
INSERT INTO `tb_role` VALUES ('2', '标签管理员', '0', '1,2,4,5,6', '2017-04-20 20:50:01', '2017-04-20 20:50:01');
INSERT INTO `tb_role` VALUES ('3', '用户管理员', '0', '1,3', '2017-04-20 20:50:01', '2017-04-20 20:50:01');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '6912b2d167c8e11a2fdd0105902f6904',
  `realName` varchar(100) DEFAULT NULL COMMENT 'test',
  `userType` tinyint(4) DEFAULT '0' COMMENT '1  管理员\r\n2  后台系统用户',
  `userState` int(11) DEFAULT '0' COMMENT '0 存在,1 删除',
  `roles` varchar(100) DEFAULT NULL COMMENT '角色ID，中间逗号隔开',
  `updateTime` datetime DEFAULT NULL COMMENT '用户密码修改时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `sex` tinyint(4) DEFAULT NULL COMMENT '男0 女1',
  `phoneNum` int(11) DEFAULT NULL,
  `fixedPhoneNum` int(12) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'test', '6912b2d167c8e11a2fdd0105902f6904', '管理者', '1', '1', '1,2,6,10', '2017-03-13 17:02:29', null, null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('15', 'zhangsan', '6912b2d167c8e11a2fdd0105902f6904', '张三', '0', '1', '1', '2017-04-24 11:24:50', '2017-04-24 11:24:50', '0', '13705965', '59128608', 'zhangsan@txxxb.com', '西洪路祭酒岭');

-- ----------------------------
-- Table structure for wf_hist_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_order`;
CREATE TABLE `wf_hist_order` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `process_Id` varchar(32) NOT NULL COMMENT '流程定义ID',
  `order_State` tinyint(1) NOT NULL COMMENT '状态',
  `creator` varchar(100) DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) NOT NULL COMMENT '发起时间',
  `end_Time` varchar(50) DEFAULT NULL COMMENT '完成时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '期望完成时间',
  `priority` tinyint(1) DEFAULT NULL COMMENT '优先级',
  `parent_Id` varchar(32) DEFAULT NULL COMMENT '父流程ID',
  `order_No` varchar(100) DEFAULT NULL COMMENT '流程实例编号',
  `variable` longtext COMMENT '附属变量json存储',
  PRIMARY KEY (`id`),
  KEY `IDX_HIST_ORDER_PROCESSID` (`process_Id`) USING BTREE,
  KEY `IDX_HIST_ORDER_NO` (`order_No`) USING BTREE,
  KEY `FK_HIST_ORDER_PARENTID` (`parent_Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史流程实例表';

-- ----------------------------
-- Records of wf_hist_order
-- ----------------------------

-- ----------------------------
-- Table structure for wf_hist_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task`;
CREATE TABLE `wf_hist_task` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `order_Id` varchar(32) NOT NULL COMMENT '流程实例ID',
  `task_Name` varchar(100) NOT NULL COMMENT '任务名称',
  `display_Name` varchar(200) NOT NULL COMMENT '任务显示名称',
  `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
  `perform_Type` tinyint(1) DEFAULT NULL COMMENT '参与类型',
  `task_State` tinyint(1) NOT NULL COMMENT '任务状态',
  `operator` varchar(100) DEFAULT NULL COMMENT '任务处理人',
  `create_Time` varchar(50) NOT NULL COMMENT '任务创建时间',
  `finish_Time` varchar(50) DEFAULT NULL COMMENT '任务完成时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '任务期望完成时间',
  `action_Url` varchar(200) DEFAULT NULL COMMENT '任务处理url',
  `parent_Task_Id` varchar(32) DEFAULT NULL COMMENT '父任务ID',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  PRIMARY KEY (`id`),
  KEY `IDX_HIST_TASK_ORDER` (`order_Id`) USING BTREE,
  KEY `IDX_HIST_TASK_TASKNAME` (`task_Name`) USING BTREE,
  KEY `IDX_HIST_TASK_PARENTTASK` (`parent_Task_Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史任务表';

-- ----------------------------
-- Records of wf_hist_task
-- ----------------------------

-- ----------------------------
-- Table structure for wf_hist_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task_actor`;
CREATE TABLE `wf_hist_task_actor` (
  `task_Id` varchar(32) NOT NULL COMMENT '任务ID',
  `actor_Id` varchar(100) NOT NULL COMMENT '参与者ID',
  KEY `IDX_HIST_TASKACTOR_TASK` (`task_Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史任务参与者表';

-- ----------------------------
-- Records of wf_hist_task_actor
-- ----------------------------

-- ----------------------------
-- Table structure for wf_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_order`;
CREATE TABLE `wf_order` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `parent_Id` varchar(32) DEFAULT NULL COMMENT '父流程ID',
  `process_Id` varchar(32) NOT NULL COMMENT '流程定义ID',
  `creator` varchar(100) DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) NOT NULL COMMENT '发起时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '期望完成时间',
  `last_Update_Time` varchar(50) DEFAULT NULL COMMENT '上次更新时间',
  `last_Updator` varchar(100) DEFAULT NULL COMMENT '上次更新人',
  `priority` tinyint(1) DEFAULT NULL COMMENT '优先级',
  `parent_Node_Name` varchar(100) DEFAULT NULL COMMENT '父流程依赖的节点名称',
  `order_No` varchar(100) DEFAULT NULL COMMENT '流程实例编号',
  `variable` longtext COMMENT '附属变量json存储',
  `version` int(3) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `IDX_ORDER_PROCESSID` (`process_Id`) USING BTREE,
  KEY `IDX_ORDER_NO` (`order_No`) USING BTREE,
  KEY `FK_ORDER_PARENTID` (`parent_Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程实例表';

-- ----------------------------
-- Records of wf_order
-- ----------------------------

-- ----------------------------
-- Table structure for wf_process
-- ----------------------------
DROP TABLE IF EXISTS `wf_process`;
CREATE TABLE `wf_process` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(100) DEFAULT NULL COMMENT '流程名称',
  `display_Name` varchar(200) DEFAULT NULL COMMENT '流程显示名称',
  `type` varchar(100) DEFAULT NULL COMMENT '流程类型',
  `instance_Url` varchar(200) DEFAULT NULL COMMENT '实例url',
  `state` tinyint(1) DEFAULT NULL COMMENT '流程是否可用',
  `content` longblob COMMENT '流程模型定义',
  `version` int(2) DEFAULT NULL COMMENT '版本',
  `create_Time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  KEY `IDX_PROCESS_NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程定义表';

-- ----------------------------
-- Records of wf_process
-- ----------------------------

-- ----------------------------
-- Table structure for wf_surrogate
-- ----------------------------
DROP TABLE IF EXISTS `wf_surrogate`;
CREATE TABLE `wf_surrogate` (
  `id` varchar(100) NOT NULL COMMENT '主键ID',
  `process_Name` varchar(100) DEFAULT NULL COMMENT '流程名称',
  `operator` varchar(100) DEFAULT NULL COMMENT '授权人',
  `surrogate` varchar(100) DEFAULT NULL COMMENT '代理人',
  `odate` varchar(64) DEFAULT NULL COMMENT '操作时间',
  `sdate` varchar(64) DEFAULT NULL COMMENT '开始时间',
  `edate` varchar(64) DEFAULT NULL COMMENT '结束时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `IDX_SURROGATE_OPERATOR` (`operator`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='委托代理表';

-- ----------------------------
-- Records of wf_surrogate
-- ----------------------------

-- ----------------------------
-- Table structure for wf_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_task`;
CREATE TABLE `wf_task` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `order_Id` varchar(32) NOT NULL COMMENT '流程实例ID',
  `task_Name` varchar(100) NOT NULL COMMENT '任务名称',
  `display_Name` varchar(200) NOT NULL COMMENT '任务显示名称',
  `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
  `perform_Type` tinyint(1) DEFAULT NULL COMMENT '参与类型',
  `operator` varchar(100) DEFAULT NULL COMMENT '任务处理人',
  `create_Time` varchar(50) DEFAULT NULL COMMENT '任务创建时间',
  `finish_Time` varchar(50) DEFAULT NULL COMMENT '任务完成时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '任务期望完成时间',
  `action_Url` varchar(200) DEFAULT NULL COMMENT '任务处理的url',
  `parent_Task_Id` varchar(100) DEFAULT NULL COMMENT '父任务ID',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  `version` tinyint(1) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `IDX_TASK_ORDER` (`order_Id`) USING BTREE,
  KEY `IDX_TASK_TASKNAME` (`task_Name`) USING BTREE,
  KEY `IDX_TASK_PARENTTASK` (`parent_Task_Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
-- Records of wf_task
-- ----------------------------

-- ----------------------------
-- Table structure for wf_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_task_actor`;
CREATE TABLE `wf_task_actor` (
  `task_Id` varchar(32) NOT NULL COMMENT '任务ID',
  `actor_Id` varchar(100) NOT NULL COMMENT '参与者ID',
  KEY `IDX_TASKACTOR_TASK` (`task_Id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务参与者表';

-- ----------------------------
-- Records of wf_task_actor
-- ----------------------------
