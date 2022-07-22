/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:32306
 Source Schema         : memory

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 20/07/2022 18:58:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情',
  `type_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户号',
  `revision` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for momento
-- ----------------------------
DROP TABLE IF EXISTS `momento`;
CREATE TABLE `momento`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` blob NULL COMMENT '内容',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `tag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户号',
  `revision` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'momento' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户号',
  `revision` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for timing
-- ----------------------------
DROP TABLE IF EXISTS `timing`;
CREATE TABLE `timing`  (
  `TENANT_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户号',
  `REVISION` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一码',
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `POINT` decimal(24, 6) NULL DEFAULT NULL COMMENT '积分',
  `USER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户',
  `TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型，0:努力 1:奖励',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '时机' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of timing
-- ----------------------------
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:11:42', NULL, '2022-07-19 18:11:42', 19, '9JPGwNKDwyo', '左神', 3.000000, NULL, '0');
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:22:16', NULL, '2022-07-19 18:22:16', 20, 'vdqN5GdBAgl', '编码', 3.000000, NULL, '0');
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:22:32', NULL, '2022-07-19 18:22:32', 21, 'Wdn91vNLRkm', 'book', 1.000000, NULL, '0');
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:22:53', NULL, '2022-07-19 18:22:53', 22, 'Dnokoobk53B', '整洁', 1.000000, NULL, '0');
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:23:13', NULL, '2022-07-19 18:23:13', 23, 'pgxo7BAjYBK', '沟通', 2.000000, NULL, '0');
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:24:05', NULL, '2022-07-19 18:24:05', 24, 'dMqx2p0DK97', '早起', 2.000000, NULL, '0');
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:26:02', NULL, '2022-07-19 18:26:02', 25, 'Mmrp1QLpovj', 'lol', -8.000000, NULL, '1');
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:26:23', NULL, '2022-07-19 18:26:23', 26, '0PR4d3K6ELb', 'xx', -10.000000, NULL, '1');
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:38:33', NULL, '2022-07-19 18:38:33', 27, 'DJWd1DApqVE', 'TV', -5.000000, NULL, '1');
INSERT INTO `timing` VALUES (NULL, NULL, 'wqy', '2022-07-20 09:58:17', NULL, '2022-07-20 09:58:17', 28, 'Z5VG7azEbz7', 'mess', 0.500000, NULL, '0');

-- ----------------------------
-- Table structure for timing_record
-- ----------------------------
DROP TABLE IF EXISTS `timing_record`;
CREATE TABLE `timing_record`  (
  `TENANT_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户号',
  `REVISION` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TIMING_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时机编码',
  `POINT` int(0) NULL DEFAULT NULL COMMENT '积分',
  `DEGREE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '程度',
  `FINISH_TIME` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型',
  `POINT_CHANGE` decimal(24, 6) NULL DEFAULT NULL COMMENT '积分变动',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '时间点记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of timing_record
-- ----------------------------
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:17:20', NULL, '2022-07-19 18:17:20', 17, '9JPGwNKDwyo', 3, '0', '2022-07-18 08:30:01', '0', 3.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:27:03', NULL, '2022-07-19 18:27:03', 18, 'vdqN5GdBAgl', 3, '1', '2022-07-18 14:26:46', '0', 6.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:27:25', NULL, '2022-07-19 18:27:25', 19, 'vdqN5GdBAgl', 3, '1', '2022-07-18 15:27:14', '0', 9.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:27:42', NULL, '2022-07-19 18:27:42', 20, 'vdqN5GdBAgl', 3, '1', '2022-07-18 16:27:32', '0', 12.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:28:02', NULL, '2022-07-19 18:28:02', 21, 'vdqN5GdBAgl', 3, '1', '2022-07-18 17:27:51', '0', 15.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:29:38', NULL, '2022-07-19 18:29:38', 22, 'dMqx2p0DK97', 2, '1', '2022-07-18 06:46:18', '0', 17.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:30:24', NULL, '2022-07-19 18:30:24', 23, '0PR4d3K6ELb', -10, '0', '2022-07-19 00:16:08', '1', 7.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:31:27', NULL, '2022-07-19 18:31:27', 24, 'Wdn91vNLRkm', 1, '1', '2022-07-18 07:30:53', '0', 8.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:31:52', NULL, '2022-07-19 18:31:52', 25, 'Wdn91vNLRkm', 1, '0', '2022-07-19 07:31:38', '0', 9.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:32:51', NULL, '2022-07-19 18:32:51', 26, '9JPGwNKDwyo', 3, '0', '2022-07-19 08:36:36', '0', 12.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:33:14', NULL, '2022-07-19 18:33:14', 27, 'vdqN5GdBAgl', 3, '1', '2022-07-19 09:42:56', '0', 15.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:33:30', NULL, '2022-07-19 18:33:30', 28, 'vdqN5GdBAgl', 3, '1', '2022-07-19 14:28:19', '0', 18.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:34:21', NULL, '2022-07-19 18:34:21', 29, 'vdqN5GdBAgl', 3, '1', '2022-07-19 15:34:14', '0', 21.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:34:41', NULL, '2022-07-19 18:34:41', 30, 'vdqN5GdBAgl', 3, '1', '2022-07-19 16:35:27', '0', 24.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:34:53', NULL, '2022-07-19 18:34:53', 31, 'vdqN5GdBAgl', 3, '1', '2022-07-19 17:34:46', '0', 27.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-19 18:35:09', NULL, '2022-07-19 18:35:09', 32, 'vdqN5GdBAgl', 3, '1', '2022-07-19 18:35:02', '0', 30.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-20 09:54:21', NULL, '2022-07-20 09:54:21', 33, '9JPGwNKDwyo', 3, '0', '2022-07-20 09:34:07', '0', 33.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-20 09:55:04', NULL, '2022-07-20 09:55:04', 34, 'Wdn91vNLRkm', 1, '1', '2022-07-20 07:54:54', '0', 34.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-20 09:55:34', NULL, '2022-07-20 09:55:34', 35, 'dMqx2p0DK97', 2, '0', '2022-07-20 06:50:22', '0', 36.000000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-20 09:58:59', NULL, '2022-07-20 09:58:59', 36, 'Z5VG7azEbz7', 1, '1', '2022-07-19 22:40:38', '0', 36.500000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-20 10:12:03', NULL, '2022-07-20 10:12:03', 37, 'pgxo7BAjYBK', 2, '0', '2022-07-19 21:30:46', '0', 38.500000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-20 15:35:11', NULL, '2022-07-20 15:35:11', 38, 'vdqN5GdBAgl', 3, '0', '2022-07-20 15:35:05', '0', 41.500000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-20 16:34:45', NULL, '2022-07-20 16:34:45', 39, 'vdqN5GdBAgl', 3, '0', '2022-07-20 16:34:42', '0', 44.500000);
INSERT INTO `timing_record` VALUES (NULL, NULL, 'wqy', '2022-07-20 17:56:45', NULL, '2022-07-20 17:56:45', 40, 'vdqN5GdBAgl', 3, '0', '2022-07-20 17:56:41', '0', 47.500000);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(0) NOT NULL COMMENT 'id',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户号',
  `revision` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_point
-- ----------------------------
DROP TABLE IF EXISTS `user_point`;
CREATE TABLE `user_point`  (
  `TENANT_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户号',
  `REVISION` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `ID` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户编码',
  `POINT` decimal(24, 6) NULL DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户积分' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_point
-- ----------------------------
INSERT INTO `user_point` VALUES (NULL, NULL, NULL, '2022-07-19 18:17:20', NULL, '2022-07-19 18:17:20', 3, 'wqy', 47.500000);

SET FOREIGN_KEY_CHECKS = 1;
