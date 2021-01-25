SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `article_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '评论所在文章的ID',
  `comment_parent` int DEFAULT '0' COMMENT '父评论ID（如果不为0，说明是子评论，此属性才有效）',
  `comment_client` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '评论的客户端ID',
  `comment_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `comment_praise` int DEFAULT '0' COMMENT '点赞数',
  `create_time` bigint DEFAULT '0' COMMENT '创建时间',
  `grade_star` int DEFAULT '5' COMMENT '文章评分',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='这是评论信息表！';

-- ----------------------------
-- Records of article_comment
-- ----------------------------
INSERT INTO `article_comment` VALUES ('22', '1606045755481', '0', '1606131169233', 'AAA', '1', '1606140150693', '5');
INSERT INTO `article_comment` VALUES ('23', '1606045755481', '0', '1606131169233', 'BBB', '1', '1606140154482', '5');
INSERT INTO `article_comment` VALUES ('24', '1606045755481', '0', '1606131343528', '👍嘿嘿，太棒了！', '0', '1606140428684', '4');

-- ----------------------------
-- Table structure for article_info
-- ----------------------------
DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info` (
  `article_id` char(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章ID',
  `article_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '文章的URL',
  `article_comment_count` int DEFAULT '0' COMMENT '文章的评论数量统计',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of article_info
-- ----------------------------
INSERT INTO `article_info` VALUES ('1606045755481', 'http://localhost:4000/2020/11/19/58995.html', '0');
INSERT INTO `article_info` VALUES ('1606045789632', 'http://localhost:4000/2020/10/18/20683.html', '0');
INSERT INTO `article_info` VALUES ('1606045799333', 'http://localhost:4000/2020/10/15/50852.html', '0');
INSERT INTO `article_info` VALUES ('1606112252902', 'http://localhost:4000/categories/', '0');
INSERT INTO `article_info` VALUES ('1606112774395', 'http://localhost:4000/tags/', '0');
INSERT INTO `article_info` VALUES ('1606113208033', 'http://localhost:4000/2020/10/14/42452.html', '0');
INSERT INTO `article_info` VALUES ('1606113212675', 'http://localhost:4000/casual/', '0');
INSERT INTO `article_info` VALUES ('1606113220747', 'http://localhost:4000/2020/02/28/293026705.html', '0');
INSERT INTO `article_info` VALUES ('1606113259896', 'http://localhost:4000/2020/02/27/3815841153.html', '0');
INSERT INTO `article_info` VALUES ('1606113270712', 'http://localhost:4000/2020/07/11/3887185772.html', '0');
INSERT INTO `article_info` VALUES ('1606119811227', 'http://localhost:4000/about/', '0');
INSERT INTO `article_info` VALUES ('1606125910047', 'http://localhost:4000/2020/08/19/628369017.html', '0');
INSERT INTO `article_info` VALUES ('1606125931984', 'http://localhost:4000/2020/07/03/1716304322.html', '0');
INSERT INTO `article_info` VALUES ('1606131650386', 'http://localhost:4000/2020/09/29/11218.html', '0');
INSERT INTO `article_info` VALUES ('1606131687965', 'http://localhost:4000/2020/09/28/54440.html', '0');
INSERT INTO `article_info` VALUES ('1606134024170', 'http://localhost:4000/2019/02/09/2595086645.html', '0');

-- ----------------------------
-- Table structure for child_comment
-- ----------------------------
DROP TABLE IF EXISTS `child_comment`;
CREATE TABLE `child_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `comment_parent` int DEFAULT '0' COMMENT '父评论ID（如果不为0，说明是子评论，此属性才有效）',
  `comment_client` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '评论的客户端ID',
  `comment_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `comment_praise` int DEFAULT '0' COMMENT '点赞数',
  `reply_client` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='这是评论信息表！';

-- ----------------------------
-- Records of child_comment
-- ----------------------------
INSERT INTO `child_comment` VALUES ('37', '22', '1606131169233', 'AAA', '1', '1606131169233', '1606140377597');
INSERT INTO `child_comment` VALUES ('38', '22', '1606131169233', 'CCC', '1', '1606131169233', '1606140381723');
INSERT INTO `child_comment` VALUES ('39', '22', '1606131169233', 'DDD', '1', '1606131169233', '1606140384324');
INSERT INTO `child_comment` VALUES ('40', '22', '1606131343528', 'EEE', '0', '1606131169233', '1606140408508');
INSERT INTO `child_comment` VALUES ('41', '24', '1606131343528', 'o((>ω< ))o', '0', '1606131343528', '1606140435722');

-- ----------------------------
-- Table structure for client_info
-- ----------------------------
DROP TABLE IF EXISTS `client_info`;
CREATE TABLE `client_info` (
  `client_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `client_email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `client_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL COMMENT '用户创建时间',
  `client_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像URL',
  `client_os` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of client_info
-- ----------------------------
INSERT INTO `client_info` VALUES ('0010', 'zchanglin@163.com', '反杀闰土的猹', '1606045755481', 'https://img.zouchanglin.cn///20200413/kCxj1XROmHTt.png', 'Windows Windows 8.1 Chrome Windows Windows 10 Chrome 86.0.4240.198\n');
INSERT INTO `client_info` VALUES ('0011', 'tmp_3781973891', 'AAAA', '1606045755482', 'https://portrait.gitee.com/uploads/avatars/user/688/2065554_xuhongv_1578965442.png', 'Windows Windows 8.1 Chrome 35.0.1916.138');
INSERT INTO `client_info` VALUES ('0012', 'tmp_3781273891', 'BBBBB', '1606045765482', 'https://portrait.gitee.com/uploads/avatars/user/718/2154539_ifaswind_1588824550.jpeg', 'Windows Windows 8.1 Chrome Windows Windows 10 Chrome 86.0.4240.198\n');
INSERT INTO `client_info` VALUES ('1606098707578', '1877391@qq.com', '张三', '1606098707578', 'https://portrait.gitee.com/uploads/avatars/user/688/2065554_xuhongv_1578965442.png', 'Windows Windows 8.1 Chrome 35.0.1916.138');
INSERT INTO `client_info` VALUES ('1606131169233', '', '巫包艄', '1606131169233', 'https://www.thiswaifudoesnotexist.net/example-7848.jpg', 'Windows Windows 10 Chrome 86.0.4240.198');
INSERT INTO `client_info` VALUES ('1606131343528', '127897391@qq.com', '杞谈', '1606131343528', 'https://www.thiswaifudoesnotexist.net/example-8076.jpg', 'Windows Windows 10 Firefox 83.0');
INSERT INTO `client_info` VALUES ('1606133482632', '', '第五泞', '1606133482632', 'https://www.thiswaifudoesnotexist.net/example-2264.jpg', 'Windows Windows 10 Firefox 83.0');
