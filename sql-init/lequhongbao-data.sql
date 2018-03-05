/*
Navicat MySQL Data Transfer

Source Server         : lequ
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : lequhongbao

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-03-05 09:52:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for packet_content
-- ----------------------------
DROP TABLE IF EXISTS `packet_content`;
CREATE TABLE `packet_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL,
  `createTime` datetime(6) DEFAULT NULL,
  `number` int(6) DEFAULT NULL,
  `numberWords` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of packet_content
-- ----------------------------
INSERT INTO `packet_content` VALUES ('1', '我爱你', '2018-01-26 16:35:07.000000', null, null);
INSERT INTO `packet_content` VALUES ('2', '新年快乐', null, null, null);

-- ----------------------------
-- Table structure for packet_split
-- ----------------------------
DROP TABLE IF EXISTS `packet_split`;
CREATE TABLE `packet_split` (
  `packetId` varchar(100) DEFAULT NULL,
  `splitId` varchar(10) DEFAULT NULL,
  `amount` double(20,2) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10977 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of packet_split
-- ----------------------------
INSERT INTO `packet_split` VALUES ('0cac5067f58440a3b2749d48d1ccf43f', '4', '46.78', '10976');

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openId` varchar(100) DEFAULT NULL,
  `total_fee` int(100) DEFAULT NULL,
  `out_trade_no` varchar(32) DEFAULT NULL,
  `body` varchar(128) DEFAULT NULL,
  `time_start` varchar(14) DEFAULT NULL,
  `time_expire` varchar(14) DEFAULT NULL,
  `pay_status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pay_order
-- ----------------------------

-- ----------------------------
-- Table structure for rob_packet
-- ----------------------------
DROP TABLE IF EXISTS `rob_packet`;
CREATE TABLE `rob_packet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) DEFAULT NULL,
  `amount` double(20,2) DEFAULT NULL,
  `createTime` datetime(6) DEFAULT NULL,
  `packetId` varchar(100) DEFAULT NULL,
  `recorderFilePath` varchar(500) DEFAULT NULL,
  `nickName` varchar(200) DEFAULT NULL,
  `avatarUrl` varchar(500) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `updateTime` datetime(6) DEFAULT NULL,
  `senderOpenId` varchar(100) DEFAULT NULL COMMENT '发包人',
  `splitId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rob_packet
-- ----------------------------
INSERT INTO `rob_packet` VALUES ('20', '11111111', '59.51', '2018-01-28 15:28:44.000000', '95cd0da73b314d53b2bd4a6e18fc9d34', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, null);
INSERT INTO `rob_packet` VALUES ('21', '11111111', '102.73', '2018-01-28 15:29:51.000000', 'd12085d0259549bfa447139deabc4471', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, null);
INSERT INTO `rob_packet` VALUES ('22', '11111111', '102.73', '2018-01-29 00:08:18.000000', 'd12085d0259549bfa447139deabc4471', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, null);
INSERT INTO `rob_packet` VALUES ('23', '11111111', '67.62', '2018-02-01 20:37:36.000000', 'd12085d0259549bfa447139deabc4471', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '1');
INSERT INTO `rob_packet` VALUES ('24', '11111111', '16.91', '2018-02-01 20:38:47.000000', 'd12085d0259549bfa447139deabc4471', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '3');
INSERT INTO `rob_packet` VALUES ('25', '11111111', '102.73', '2018-02-01 20:39:14.000000', 'd12085d0259549bfa447139deabc4471', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '5');
INSERT INTO `rob_packet` VALUES ('26', '11111111', '90.79', '2018-02-01 20:39:16.000000', 'd12085d0259549bfa447139deabc4471', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '4');
INSERT INTO `rob_packet` VALUES ('27', '11111111', '21.95', '2018-02-01 20:40:03.000000', 'd12085d0259549bfa447139deabc4471', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '2');
INSERT INTO `rob_packet` VALUES ('28', '11111111', '13.33', '2018-02-01 20:53:07.000000', '0cac5067f58440a3b2749d48d1ccf43f', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '1');
INSERT INTO `rob_packet` VALUES ('29', '11111111', '111.62', '2018-02-01 20:54:26.000000', '0cac5067f58440a3b2749d48d1ccf43f', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '3');
INSERT INTO `rob_packet` VALUES ('30', '11111111', '45.88', '2018-02-01 20:55:53.000000', '95cd0da73b314d53b2bd4a6e18fc9d34', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '1');
INSERT INTO `rob_packet` VALUES ('31', '11111111', '59.51', '2018-02-01 20:56:02.000000', '95cd0da73b314d53b2bd4a6e18fc9d34', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '3');
INSERT INTO `rob_packet` VALUES ('32', '11111111', '4.99', '2018-02-01 20:56:10.000000', '95cd0da73b314d53b2bd4a6e18fc9d34', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '5');
INSERT INTO `rob_packet` VALUES ('33', '11111111', '125.35', '2018-02-01 20:56:19.000000', '95cd0da73b314d53b2bd4a6e18fc9d34', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '4');
INSERT INTO `rob_packet` VALUES ('34', '11111111', '64.27', '2018-02-01 20:57:24.000000', '95cd0da73b314d53b2bd4a6e18fc9d34', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '2');
INSERT INTO `rob_packet` VALUES ('35', '11111111', '46.06', '2018-02-01 21:44:17.000000', '0cac5067f58440a3b2749d48d1ccf43f', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '2');
INSERT INTO `rob_packet` VALUES ('36', '11111111', '82.21', '2018-02-01 21:45:39.000000', '0cac5067f58440a3b2749d48d1ccf43f', '/aa/aaa/aaaaa.pcm', 'gaoxu', '/aa/aaa/aaaaa.pcm', '1', 'ä¸­å?½', 'æ²³å??', null, null, '5');

-- ----------------------------
-- Table structure for rob_packet_stat
-- ----------------------------
DROP TABLE IF EXISTS `rob_packet_stat`;
CREATE TABLE `rob_packet_stat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) DEFAULT NULL,
  `totalAmount` double(20,2) NOT NULL COMMENT '发包总金额',
  `totalNumber` int(20) DEFAULT NULL COMMENT '发包总个数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rob_packet_stat
-- ----------------------------
INSERT INTO `rob_packet_stat` VALUES ('3', '11111111', '1118.19', '17');

-- ----------------------------
-- Table structure for send_packet
-- ----------------------------
DROP TABLE IF EXISTS `send_packet`;
CREATE TABLE `send_packet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) DEFAULT NULL COMMENT '用户唯一标识',
  `amount` double(100,2) DEFAULT NULL COMMENT '金额,赏金',
  `createTime` datetime(6) DEFAULT NULL COMMENT '发包日期',
  `number` int(11) DEFAULT NULL COMMENT '数量(个)',
  `content` varchar(500) DEFAULT NULL COMMENT '口令内容',
  `robbedState` char(1) DEFAULT NULL COMMENT '0结束,1进行中,2未被领取准备退钱',
  `robbedNumber` int(11) DEFAULT NULL COMMENT '已抢数量,剩余数量是number-robbedNumber',
  `packetId` varchar(100) DEFAULT NULL COMMENT '红包唯一识别Ｉｄ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of send_packet
-- ----------------------------
INSERT INTO `send_packet` VALUES ('91', '33333333', '300.00', '2018-01-28 14:24:47.000000', '5', 'å??é??', '0', '5', '95cd0da73b314d53b2bd4a6e18fc9d34');
INSERT INTO `send_packet` VALUES ('92', '33333333', '300.00', '2018-01-28 14:25:12.000000', '5', 'å??é??', '0', '5', 'd12085d0259549bfa447139deabc4471');
INSERT INTO `send_packet` VALUES ('93', '33333333', '300.00', '2018-01-28 14:25:49.000000', '5', 'å??é??', '1', '4', '0cac5067f58440a3b2749d48d1ccf43f');

-- ----------------------------
-- Table structure for send_packet_stat
-- ----------------------------
DROP TABLE IF EXISTS `send_packet_stat`;
CREATE TABLE `send_packet_stat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) DEFAULT NULL,
  `totalAmount` double(20,2) DEFAULT NULL COMMENT '发包总金额',
  `totalNumber` int(20) DEFAULT NULL COMMENT '发包总个数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of send_packet_stat
-- ----------------------------
INSERT INTO `send_packet_stat` VALUES ('6', '33333333', '900.00', '3');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) DEFAULT NULL,
  `nickName` varchar(200) DEFAULT NULL COMMENT '昵称',
  `avatarUrl` varchar(500) DEFAULT NULL COMMENT '用户头像',
  `gender` char(1) DEFAULT NULL COMMENT '值为1时是男性，值为2时是女性，值为0时是未知',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `country` varchar(100) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL COMMENT '语言',
  `createDate` datetime(6) DEFAULT NULL,
  `updateTime` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '9999999', 'gaoxu', '', null, null, null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('2', '9999999', 'gaoxu', '', null, null, null, null, null, '2018-01-21 22:22:28.000000', null);

-- ----------------------------
-- Table structure for user_balance
-- ----------------------------
DROP TABLE IF EXISTS `user_balance`;
CREATE TABLE `user_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) DEFAULT NULL,
  `totalAmount` double(50,2) DEFAULT NULL COMMENT '余额包括,抢到的红包和退款,余额应该是异步加载的,余额可以提现',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_balance
-- ----------------------------
INSERT INTO `user_balance` VALUES ('3', '11111111', '88.27');

-- ----------------------------
-- Table structure for user_withdrawal
-- ----------------------------
DROP TABLE IF EXISTS `user_withdrawal`;
CREATE TABLE `user_withdrawal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) DEFAULT NULL,
  `totalAmount` double(50,2) DEFAULT NULL COMMENT '余额包括,抢到的红包和退款,余额应该是异步加载的,余额可以提现',
  `withdrawalState` char(1) DEFAULT NULL COMMENT '提现状态,0未提现,1提现中,2已提现',
  `withdrawalStartDate` datetime(6) DEFAULT NULL,
  `withdrawalEndDate` datetime(6) DEFAULT NULL,
  `amount` double(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_withdrawal
-- ----------------------------
INSERT INTO `user_withdrawal` VALUES ('1', '11111111', '128.27', '1', '2018-02-05 12:47:46.000000', null, '20.00');
INSERT INTO `user_withdrawal` VALUES ('2', '11111111', '108.27', '1', '2018-02-05 12:49:08.000000', null, '20.00');
