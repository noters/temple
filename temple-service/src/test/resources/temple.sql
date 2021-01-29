/*
Navicat MariaDB Data Transfer

Source Server         : vmware_mysql
Source Server Version : 100308
Source Host           : mysql:3306
Source Database       : temple

Target Server Type    : MariaDB
Target Server Version : 100308
File Encoding         : 65001

Date: 2021-01-27 18:19:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `image` varchar(4000) DEFAULT NULL COMMENT '图片',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) DEFAULT current_timestamp(3) COMMENT '创建日期',
  `update_time` datetime(3) DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3) COMMENT '更新日期',
  `status` varchar(2) DEFAULT '1' COMMENT '状态，0禁用，1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '轮播图';

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `temple`.`banner` (`image`) VALUES ('/images/banner1.png');
INSERT INTO `temple`.`banner` (`image`) VALUES ('/images/banner2.png');
INSERT INTO `temple`.`banner` (`image`) VALUES ('/images/banner3.png');
INSERT INTO `temple`.`banner` (`image`) VALUES ('/images/banner4.png');

-- ----------------------------
-- Table structure for goods_class
-- ----------------------------
DROP TABLE IF EXISTS `goods_class`;
CREATE TABLE `goods_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `image` varchar(4000) DEFAULT NULL COMMENT '图片',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) DEFAULT current_timestamp(3) COMMENT '创建日期',
  `update_time` datetime(3) DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3) COMMENT '更新日期',
  `status` varchar(2) DEFAULT '1' COMMENT '状态，0禁用，1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品类别';

-- ----------------------------
-- Records of goods_class
-- ----------------------------
INSERT INTO `temple`.`goods_class` (`name`, `image`) VALUES ('商品类别', '/images/class1.png');
INSERT INTO `temple`.`goods_class` (`name`, `image`) VALUES ('最多四字', '/images/class2.png');
INSERT INTO `temple`.`goods_class` (`name`, `image`) VALUES ('类别3', '/images/class3.png');
INSERT INTO `temple`.`goods_class` (`name`, `image`) VALUES ('类别4', '/images/class4.png');
INSERT INTO `temple`.`goods_class` (`name`, `image`) VALUES ('类别5', '/images/class5.png');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_class` int(11) DEFAULT NULL COMMENT '商品类别',
  `name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `title` varchar(200) DEFAULT NULL COMMENT '副标题',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) DEFAULT current_timestamp(3) COMMENT '创建日期',
  `update_time` datetime(3) DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3) COMMENT '更新日期',
  `status` varchar(2) DEFAULT '1' COMMENT '状态，0禁用，1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `temple`.`goods` (`goods_class`, `name`, `title`) VALUES ('1', '商品名称', '商品名称 特价100');
INSERT INTO `temple`.`goods` (`goods_class`, `name`, `title`) VALUES ('1', '最多四字', '商品名称2 特价200');
INSERT INTO `temple`.`goods` (`goods_class`, `name`, `title`) VALUES ('1', '商品3', '商品名称3 特价200');

-- ----------------------------
-- Table structure for goods_item
-- ----------------------------
DROP TABLE IF EXISTS `goods_item`;
CREATE TABLE `goods_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods` int(11) DEFAULT NULL COMMENT '商品',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `price` varchar(20) DEFAULT NULL COMMENT '价格',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) DEFAULT current_timestamp(3) COMMENT '创建日期',
  `update_time` datetime(3) DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3) COMMENT '更新日期',
  `status` varchar(2) DEFAULT '1' COMMENT '状态，0禁用，1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品套餐';

-- ----------------------------
-- Records of goods_item
-- ----------------------------
INSERT INTO `temple`.`goods_item` (`goods`, `name`, `price`) VALUES ('1', '套餐一', '100');
INSERT INTO `temple`.`goods_item` (`goods`, `name`, `price`) VALUES ('1', '套餐二', '110');

-- ----------------------------
-- Table structure for goods_detail
-- ----------------------------
DROP TABLE IF EXISTS `goods_detail`;
CREATE TABLE `goods_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods` int(11) DEFAULT NULL COMMENT '商品',
  `image` varchar(4000) DEFAULT NULL COMMENT '图片',
  `type` varchar(4) DEFAULT NULL COMMENT '类型，0缩略图，1主图，2详情图，3描述文字',
  `note` varchar(4000) DEFAULT NULL COMMENT '描述',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) DEFAULT current_timestamp(3) COMMENT '创建日期',
  `update_time` datetime(3) DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3) COMMENT '更新日期',
  `status` varchar(2) DEFAULT '1' COMMENT '状态，0禁用，1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品详情';

-- ----------------------------
-- Records of goods_detail
-- ----------------------------
INSERT INTO `temple`.`goods_detail` (`goods`, `image`, `type`, `note`) VALUES ('1', '/images/detail1.png', '1', null);
INSERT INTO `temple`.`goods_detail` (`goods`, `image`, `type`, `note`) VALUES ('1', '/images/detail2.png', '1', null);
INSERT INTO `temple`.`goods_detail` (`goods`, `image`, `type`, `note`) VALUES ('1', '/images/detail3.png', '2', null);
INSERT INTO `temple`.`goods_detail` (`goods`, `image`, `type`, `note`) VALUES ('1', '/images/detail4.png', '2', null);
INSERT INTO `temple`.`goods_detail` (`goods`, `image`, `type`, `note`) VALUES ('1', null, '3', '描述文字');
INSERT INTO `temple`.`goods_detail` (`goods`, `image`, `type`, `note`) VALUES ('2', '/images/detail5.png', '1', null);
INSERT INTO `temple`.`goods_detail` (`goods`, `image`, `type`, `note`) VALUES ('3', '/images/detail6.png', '1', null);

SET FOREIGN_KEY_CHECKS=1;
