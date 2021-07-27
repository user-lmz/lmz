/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : order

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-05-11 11:13:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `f_id` int(11) NOT NULL,
  `f_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `f_materials` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `f_price` double(15,2) DEFAULT NULL,
  `f_type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `f_img` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('1', '米饭', '泰国香米', '1.20', '主食', '米饭.jpg');
INSERT INTO `food` VALUES ('2', '小笼包', '面粉，猪肉，白菜', '10.00', '主食', '小笼包.jpg');
INSERT INTO `food` VALUES ('3', '花卷', '面粉，葱，盐', '2.00', '主食', '花卷.jpg');
INSERT INTO `food` VALUES ('4', '玉米馒头', '玉米，面粉', '3.00', '主食', '玉米馒头.jpg');
INSERT INTO `food` VALUES ('5', '麻辣汤粉', '米粉，辣椒油，原骨汤', '12.00', '主食', '麻辣汤粉.jpg');
INSERT INTO `food` VALUES ('6', '枸杞米酒汤', '枸杞，米酒', '8.00', '汤品', '枸杞米酒汤.jpg');
INSERT INTO `food` VALUES ('7', '莲耳汤', '莲子，白木耳，红枣', '18.00', '汤品', '莲耳汤.jpg');
INSERT INTO `food` VALUES ('8', '玉米排骨汤', '玉米，排骨，纯净水', '14.00', '汤品', '玉米排骨汤.jpg');
INSERT INTO `food` VALUES ('9', '豆角烧茄子', '豆角，茄子，大蒜', '8.00', '炒菜', '豆角烧茄子.jpg');
INSERT INTO `food` VALUES ('10', '青椒啤酒鸭', '青椒，鸭，啤酒，酱油', '18.00', '炒菜', '青椒啤酒鸭.jpg');
INSERT INTO `food` VALUES ('11', '酸辣土豆丝', '土豆，小辣椒，陈醋', '8.00', '炒菜', '酸辣土豆丝.jpg');
INSERT INTO `food` VALUES ('12', '蒜苔腊肉', '大蒜苔，腊肉，花生油', '15.00', '炒菜', '蒜苔腊肉.jpg');
INSERT INTO `food` VALUES ('13', '红烧鱼块盖浇饭', '草鱼，大蒜，辣椒，白米饭', '12.00', '盖浇饭', '红烧鱼块盖浇饭.jpg');
INSERT INTO `food` VALUES ('14', '麻婆豆腐盖浇饭', '豆腐，花椒，剁椒，原油，米饭', '11.00', '盖浇饭', '麻婆豆腐盖浇饭.jpg');
INSERT INTO `food` VALUES ('15', '鲜虾盖浇饭', '基围虾，酱油，醋，米饭', '16.00', '盖浇饭', '鲜虾盖浇饭.jpg');
INSERT INTO `food` VALUES ('16', '橙汁', '鲜橙粉，糖，水', '3.00', '饮料', '橙汁.jpg');
INSERT INTO `food` VALUES ('17', '可乐', '香草、肉桂、柠檬香味', '2.50', '饮料', '可乐.jpg');
INSERT INTO `food` VALUES ('18', '脉动', '纯净水、白砂糖、苹果汁、食用香精、食品添加剂', '4.00', '饮料', '脉动.jpg');
INSERT INTO `food` VALUES ('19', '咖啡', '咖啡因，纯净水，白砂糖', '8.00', '饮料', '咖啡.jpg');
INSERT INTO `food` VALUES ('20', '雪碧', '水，果葡萄浆，白砂糖，食品添加剂', '2.50', '饮料', '雪碧.jpg');
INSERT INTO `food` VALUES ('21', '苏打水', '水，苏打', '3.50', '饮料', '苏打水.jpg');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(10) NOT NULL,
  `f_id` int(10) DEFAULT NULL,
  `num` int(5) DEFAULT NULL,
  `subtotal` double(11,0) DEFAULT NULL,
  `o_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `o_id` (`o_id`),
  KEY `f_id` (`f_id`),
  CONSTRAINT `f_id` FOREIGN KEY (`f_id`) REFERENCES `food` (`f_id`),
  CONSTRAINT `o_id` FOREIGN KEY (`o_id`) REFERENCES `order` (`o_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '5', '1', '12', '1');
INSERT INTO `item` VALUES ('2', '6', '1', '8', '1');
INSERT INTO `item` VALUES ('3', '18', '1', '4', '1');

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `m_id` int(11) NOT NULL,
  `m_name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_password` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_address` varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_tel` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES ('1', 'admin', '123', '黄石', '2452452452');
INSERT INTO `merchant` VALUES ('2', 'admin1', '123', 'hue', '161651561');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `o_id` int(11) NOT NULL,
  `u_id` int(10) DEFAULT NULL,
  `o_totalprice` double(10,2) DEFAULT NULL,
  `message` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`o_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '1', '24.00', '味道不错', '2020-5-6 12:10:17');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(10) NOT NULL,
  `u_name` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `u_password` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `u_address` varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL,
  `u_tel` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lmz', '1234', 'wh', '16165165156');
INSERT INTO `user` VALUES ('2', 'hq', '123', 'wh', '22542542452');
