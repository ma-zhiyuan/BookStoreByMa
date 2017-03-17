/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : BookStore

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 03/17/2017 22:28:56 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `addresses`
-- ----------------------------
DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `user_id` int(6) NOT NULL,
  `addr` varchar(2000) NOT NULL,
  `consignee` varchar(50) NOT NULL,
  `tel` varchar(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `addr_userid_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `addresses`
-- ----------------------------
BEGIN;
INSERT INTO `addresses` VALUES ('1', '2', '北大街', 'tom', '110'), ('2', '1', '西安工业大学', '马志远', '18302992058'), ('3', '1', '西安工业大学9-524', '刘江', '18302991061'), ('4', '4', '西安', '你看', '11111'), ('5', '6', '西工院', '刘江', '18302992061');
COMMIT;

-- ----------------------------
--  Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `admins`
-- ----------------------------
BEGIN;
INSERT INTO `admins` VALUES ('1', 'ma', 'BBB8AAE57C104CDA40C93843AD5E6DB8'), ('2', 'mzy', 'BBB8AAE57C104CDA40C93843AD5E6DB8');
COMMIT;

-- ----------------------------
--  Table structure for `books`
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` double(5,2) DEFAULT '0.00',
  `publishcorp` varchar(50) DEFAULT NULL,
  `stock` int(5) DEFAULT '0',
  `sold` int(7) DEFAULT '0',
  `isonsale` int(1) DEFAULT '0',
  `details` varchar(4000) DEFAULT NULL,
  `surface` varchar(255) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `onsaletime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `books`
-- ----------------------------
BEGIN;
INSERT INTO `books` VALUES ('1', '从你的全世界路过', '60.00', '工业出版社', '20', '1', '1', '爱情故事', 'bookpic/1.png', '言情', '2016-06-02'), ('2', 'java核心技术(卷一)', '50.30', '机械出版社', '20', '1', '1', '核心技术哦', 'bookpic/1.png', '编程', '2013-03-04'), ('3', 'java核心技术(卷二)', '60.30', '机械出版社', '20', '1', '1', '核心技术', 'bookpic/1.png', '编程', '2014-02-09'), ('4', 'java编程思想', '99.90', '教育出版社', '19', '2', '1', 'java入门级宝典', 'bookpic/1.png', '编程', '2010-05-23'), ('5', '平凡的世界', '78.34', '人民出版社', '29', '2', '1', '名著级小说', 'bookpic/1.png', '小说', '2001-02-03'), ('7', '数据结构', '56.50', '清华大学出版社', '74', '6', '1', '不会啊', 'bookpic/1.png', '编程', '2016-12-25'), ('8', '教材全解', '5.00', '人民教育出版社', '3', '3', '1', '课本教辅', 'bookpic/1.png', '教辅', '2011-01-02'), ('9', '爱你', '22.00', '你的出版社', '2', '0', '1', '无详情', 'bookpic/1.png', '言情', '2017-03-12'), ('10', 'Sring', '999.00', '你的出版社', '111', '0', '1', '看不懂', 'bookpic/1489745102701', '编程', '2017-03-17'), ('11', 'Spring', '12.00', '你的出版社', '122', '0', '1', '啊哈哈', 'bookpic/1.png', '编程', '2017-03-17'), ('12', 'struts', '57.00', '你的出版社', '2', '0', '1', '你看', 'bookpic/1.png', '编程', '2017-03-17');
COMMIT;

-- ----------------------------
--  Table structure for `cards`
-- ----------------------------
DROP TABLE IF EXISTS `cards`;
CREATE TABLE `cards` (
  `id` varchar(32) NOT NULL,
  `balance` double(10,2) DEFAULT '0.00',
  `time` date DEFAULT NULL,
  `isused` int(1) DEFAULT '0',
  `user_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `card_userid_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cards`
-- ----------------------------
BEGIN;
INSERT INTO `cards` VALUES ('1A654BCAD1FFB4AF83A92D8184F8B894', '0.00', '2017-03-16', '1', '1'), ('64C6E1788A5547D130E44B393A9734F0', '0.00', '2017-03-17', '1', '4'), ('71FB2AE6AA13C31E71AF22FCEEB6F298', '100.00', '2017-03-17', '0', null), ('A4042CE82C7ADD2A7F28236CF12318F9', '0.00', '2017-03-17', '1', '6');
COMMIT;

-- ----------------------------
--  Table structure for `carts`
-- ----------------------------
DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `user_id` int(6) NOT NULL,
  `total` double(8,0) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `cart_userid_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `carts`
-- ----------------------------
BEGIN;
INSERT INTO `carts` VALUES ('1', '1', '5'), ('2', '2', '0'), ('3', '3', '0'), ('4', '4', '0'), ('5', '5', '0'), ('6', '6', '0');
COMMIT;

-- ----------------------------
--  Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `time` date NOT NULL,
  `content` varchar(4000) NOT NULL,
  `book_id` int(4) NOT NULL,
  `stars` int(1) DEFAULT NULL,
  `user_id` int(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `comm_bookid_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `comm_userid_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `comments`
-- ----------------------------
BEGIN;
INSERT INTO `comments` VALUES ('2', '2017-03-16', '好难啊', '7', '5', '1'), ('3', '2017-03-17', '看不懂  真难', '8', '5', '4'), ('4', '2017-03-17', '很好', '8', '5', '6');
COMMIT;

-- ----------------------------
--  Table structure for `consults`
-- ----------------------------
DROP TABLE IF EXISTS `consults`;
CREATE TABLE `consults` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `time` date NOT NULL,
  `content` varchar(4000) NOT NULL,
  `book_id` int(6) NOT NULL,
  `isreplied` int(1) DEFAULT '0',
  `user_id` int(6) NOT NULL,
  `reply_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  KEY `reply_admin_id` (`reply_id`),
  CONSTRAINT `con_bookid_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `con_replyanminid_fk` FOREIGN KEY (`reply_id`) REFERENCES `replys` (`id`),
  CONSTRAINT `con_userid_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `consults`
-- ----------------------------
BEGIN;
INSERT INTO `consults` VALUES ('1', '2017-03-16', 'o ', '1', '1', '1', '2'), ('2', '2017-03-16', 'nihao', '8', '1', '1', '3'), ('3', '2017-03-16', '好看啊', '8', '1', '1', '4'), ('4', '2017-03-16', '我来了', '8', '1', '1', '5'), ('5', '2017-03-17', '你看吧', '8', '1', '4', '6'), ('6', '2017-03-17', '我来了 啊啊啊啊啊啊啊啊啊啊', '8', '1', '6', '7');
COMMIT;

-- ----------------------------
--  Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `user_id` int(6) NOT NULL,
  `time` date NOT NULL,
  `total` double(10,2) DEFAULT NULL,
  `issend` int(1) DEFAULT '0',
  `address_id` int(6) NOT NULL,
  `iscommented` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `address_id` (`address_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_addressid_fk` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `order_userid_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `orders`
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES ('1', '2', '2016-04-04', '0.00', '1', '1', '0'), ('2', '2', '2017-03-12', '156.00', '1', '1', '0'), ('3', '2', '2017-03-12', '134.84', '1', '1', '0'), ('4', '1', '2017-03-16', '1000.00', '1', '2', '1'), ('5', '1', '2017-03-16', '56.50', '0', '3', '0'), ('6', '4', '2017-03-17', '5.00', '1', '4', '1'), ('7', '6', '2017-03-17', '5.00', '1', '5', '1');
COMMIT;

-- ----------------------------
--  Table structure for `replys`
-- ----------------------------
DROP TABLE IF EXISTS `replys`;
CREATE TABLE `replys` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `time` date NOT NULL,
  `content` varchar(4000) NOT NULL,
  `admin_id` int(6) NOT NULL,
  `consult_id` int(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_id` (`admin_id`),
  KEY `consult_id` (`consult_id`),
  CONSTRAINT `reply_consultid_fk` FOREIGN KEY (`consult_id`) REFERENCES `consults` (`id`),
  CONSTRAINT `reply_replyadminid_fk` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `replys`
-- ----------------------------
BEGIN;
INSERT INTO `replys` VALUES ('1', '2017-03-16', 'o', '1', '1'), ('2', '2017-03-16', 'oo', '1', '1'), ('3', '2017-03-16', 'nihao', '1', '2'), ('4', '2017-03-16', '好看', '1', '3'), ('5', '2017-03-16', '你咋来了', '1', '4'), ('6', '2017-03-17', '不看', '1', '5'), ('7', '2017-03-17', '好啊', '1', '6');
COMMIT;

-- ----------------------------
--  Table structure for `shoppingitems`
-- ----------------------------
DROP TABLE IF EXISTS `shoppingitems`;
CREATE TABLE `shoppingitems` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `book_id` int(6) NOT NULL,
  `count` int(3) DEFAULT '1',
  `itemprice` double(10,2) NOT NULL,
  `order_id` int(6) DEFAULT NULL,
  `cart_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_id` (`cart_id`),
  KEY `order_id` (`order_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `shopitem_bookid_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `shopitem_cartid_fk` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`),
  CONSTRAINT `shopitem_orderid_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `shoppingitems`
-- ----------------------------
BEGIN;
INSERT INTO `shoppingitems` VALUES ('1', '7', '2', '113.00', '4', null), ('2', '4', '1', '99.90', '2', null), ('3', '7', '1', '56.50', '2', null), ('4', '5', '1', '78.34', '3', null), ('5', '7', '1', '56.50', '3', null), ('6', '7', '1', '56.50', '5', null), ('7', '8', '1', '5.00', null, '1'), ('8', '8', '1', '5.00', '6', null), ('9', '8', '1', '5.00', '7', null);
COMMIT;

-- ----------------------------
--  Table structure for `useropenid`
-- ----------------------------
DROP TABLE IF EXISTS `useropenid`;
CREATE TABLE `useropenid` (
  `user_id` int(6) DEFAULT NULL,
  `openid` varchar(100) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `useropenid_userid_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `useropenid`
-- ----------------------------
BEGIN;
INSERT INTO `useropenid` VALUES ('1', 'oG9BMwzQtjbLl_GywGs2QnwZz4Y4');
COMMIT;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `balance` double(10,2) DEFAULT '0.00',
  `profile` varchar(255) DEFAULT NULL,
  `gender` int(1) DEFAULT '1',
  `age` int(3) DEFAULT '20',
  `cart_id` int(6) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_uk` (`name`),
  UNIQUE KEY `email_uk` (`email`) USING BTREE,
  KEY `cart_id` (`cart_id`),
  CONSTRAINT `user_cartid_fk` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('1', 'ma', 'BBB8AAE57C104CDA40C93843AD5E6DB8', '943.50', 'bookpic/14897392281281.png', '1', '20', '1', null), ('2', 'tom', 'BBB8AAE57C104CDA40C93843AD5E6DB8', '709.16', 'bookpic/1489310581700kebiao.png', '1', '0', '2', null), ('3', 'mazhiyuan', 'BBB8AAE57C104CDA40C93843AD5E6DB8', '0.00', null, '1', '0', '3', null), ('4', 'maazhiyuan@163.com', 'BBB8AAE57C104CDA40C93843AD5E6DB8', '995.00', 'bookpic/14897231113521.png', '1', '22', '4', null), ('5', 'liujiang', 'BBB8AAE57C104CDA40C93843AD5E6DB8', '0.00', 'bookpic/14897447453711.png', '1', '22', '5', null), ('6', '526674005@qq.com', 'BBB8AAE57C104CDA40C93843AD5E6DB8', '995.00', null, '1', '0', '6', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
