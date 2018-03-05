/*
Navicat MySQL Data Transfer

Source Server         : lequ
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : lequhongbao

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-03-05 09:53:10
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
