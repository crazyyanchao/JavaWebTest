/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : onlinetest

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-04-25 00:22:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for academydepartment
-- ----------------------------
DROP TABLE IF EXISTS `academydepartment`;
CREATE TABLE `academydepartment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstCode` varchar(20) DEFAULT NULL,
  `firstCodeValue` varchar(40) DEFAULT NULL,
  `secondCode` varchar(20) DEFAULT NULL,
  `secondCodeValue` varchar(40) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of academydepartment
-- ----------------------------
INSERT INTO `academydepartment` VALUES ('1', '001', '机电学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('2', '002', '光电学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('3', '003', '自动化学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('4', '004', '通信学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('5', '005', '计算机学院', '005JK', '计算机科学与技术', '0');
INSERT INTO `academydepartment` VALUES ('6', '006', '经济管理学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('7', '007', '信息管理学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('8', '008', '政教学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('9', '009', '公共管理与传媒学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('10', '010', '外国语学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('11', '011', '理学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('12', '012', '国际交流学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('13', '010', '外国语学院', '010EN', '英语', '0');
INSERT INTO `academydepartment` VALUES ('14', '005', '计算机学院', '005RG', '软件工程', '0');
INSERT INTO `academydepartment` VALUES ('15', '005', '计算机学院', '005WG', '网络工程', '0');
INSERT INTO `academydepartment` VALUES ('16', '010', '外国语学院', '010FR', '法语', '0');
INSERT INTO `academydepartment` VALUES ('17', '013', '天文学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('18', '014', '量子学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('19', '015', '大学', null, null, '1');
INSERT INTO `academydepartment` VALUES ('20', '010', '外国语学院', '010GR', '德语', '0');
INSERT INTO `academydepartment` VALUES ('21', '010', '外国语学院', '010JP', '日语', '0');
INSERT INTO `academydepartment` VALUES ('22', '010', '外国语学院', '010KR', '韩语', '0');
INSERT INTO `academydepartment` VALUES ('23', '015', '人文社科学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('24', '016', 'dhksadhkdhka', null, null, '1');
INSERT INTO `academydepartment` VALUES ('25', '016', 'ewqeqeqweqweq', null, null, '1');
INSERT INTO `academydepartment` VALUES ('26', '005', '计算机学院', '005DSADS', 'dadsada', '1');
INSERT INTO `academydepartment` VALUES ('27', '016', '大大大是大大大', null, null, '1');
INSERT INTO `academydepartment` VALUES ('28', '001', '机电学院', '001JD', '大家爱大吉', '1');
INSERT INTO `academydepartment` VALUES ('29', '002', '光电学院', '002GD', 'fsakfdnsal', '1');
INSERT INTO `academydepartment` VALUES ('30', '003', '自动化学院', '003GDF', 'fafdsfada', '1');
INSERT INTO `academydepartment` VALUES ('31', '016', 'eqeqwewqewqeqwew', null, null, '1');

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `courseCode` varchar(20) DEFAULT NULL,
  `chapterCode` varchar(20) DEFAULT NULL,
  `chapterCodeValue` varchar(60) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0' COMMENT '0表示有效，1为已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('1', '00500001', '01', '程序设计和C语言', '0');
INSERT INTO `chapter` VALUES ('2', '00500001', '02', '算法—程序的灵魂', '0');
INSERT INTO `chapter` VALUES ('3', '00500001', '03', '最简单的C程序设计—顺序程序设计', '0');
INSERT INTO `chapter` VALUES ('4', '00500001', '04', '选择结构', '0');
INSERT INTO `chapter` VALUES ('5', '00500001', '05', '循环结构', '0');
INSERT INTO `chapter` VALUES ('6', '00500001', '06', '利用数组处理批量数据', '0');
INSERT INTO `chapter` VALUES ('7', '00500002', '01', '基本知识', '0');
INSERT INTO `chapter` VALUES ('8', '00500002', '02', 'C++的初步知识', '0');
INSERT INTO `chapter` VALUES ('9', '00500002', '03', '数据的存储、表示形式和基本运算', '0');
INSERT INTO `chapter` VALUES ('10', '00500002', '04', '基于过程的程序设计', '0');
INSERT INTO `chapter` VALUES ('11', '00500002', '05', '程序设计初步', '0');
INSERT INTO `chapter` VALUES ('12', '00500003', '01', 'java第一章', '0');
INSERT INTO `chapter` VALUES ('13', '00500003', '02', 'java第二章', '0');
INSERT INTO `chapter` VALUES ('14', '00500003', '03', 'java第三章', '0');
INSERT INTO `chapter` VALUES ('15', '00500003', '04', 'java第四章', '0');
INSERT INTO `chapter` VALUES ('16', '00500003', '05', 'java第五章', '0');
INSERT INTO `chapter` VALUES ('17', '00500003', '06', 'java第六章', '0');
INSERT INTO `chapter` VALUES ('18', '00500003', '07', 'java第七章', '0');
INSERT INTO `chapter` VALUES ('19', '00500003', '08', 'java第八章', '0');
INSERT INTO `chapter` VALUES ('20', '00500003', '09', 'java第九章', '0');
INSERT INTO `chapter` VALUES ('21', '00500003', '10', 'java第十章', '0');
INSERT INTO `chapter` VALUES ('22', '00500003', '11', 'java第十一章', '0');
INSERT INTO `chapter` VALUES ('23', '1100003', '01', '哆啦A梦1', '0');
INSERT INTO `chapter` VALUES ('24', '1100003', '02', '哆啦A梦2', '0');
INSERT INTO `chapter` VALUES ('25', '1100003', '03', '哆啦A梦3', '0');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `profession` varchar(20) DEFAULT NULL,
  `startYear` int(4) DEFAULT NULL,
  `classCode` varchar(20) DEFAULT NULL,
  `classValue` varchar(20) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '005JK', '2012', '005JK201201', '计科1201', '0');
INSERT INTO `class` VALUES ('2', '005JK', '2012', '005JK201202', '计科1202', '0');
INSERT INTO `class` VALUES ('3', '005JK', '2012', '005JK201203', '计科1203', '0');
INSERT INTO `class` VALUES ('4', '005Jk', '2012', '005JK201204', '计科1204', '0');
INSERT INTO `class` VALUES ('5', '005JK', '2012', '005JK201205', '计科1205', '0');
INSERT INTO `class` VALUES ('6', '005JK', '2012', '005JK201206', '计科1206', '0');
INSERT INTO `class` VALUES ('7', '005RG', '2012', '005RG201201', '软工1201', '0');
INSERT INTO `class` VALUES ('8', '005RG', '2012', '005RG201202', '软工1202', '0');
INSERT INTO `class` VALUES ('9', '005RG', '2012', '005RG201203', '软工1203', '0');
INSERT INTO `class` VALUES ('10', '005JK', '2010', '005JK201001', '计科2010', '0');
INSERT INTO `class` VALUES ('11', '005RG', '2010', '005RG201001', '软工2010', '0');
INSERT INTO `class` VALUES ('12', '005WG', '2015', '005WG201501', '网工1502', '1');
INSERT INTO `class` VALUES ('13', '005JK', '2014', '005JK201401', '计科1204', '0');
INSERT INTO `class` VALUES ('14', '005RG', '2015', '005RG201501', '软工1501', '0');
INSERT INTO `class` VALUES ('15', '005RG', '2105', '005RG210501', '软工0501', '0');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `courseCode` varchar(20) DEFAULT '',
  `courseCodeValue` varchar(60) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0' COMMENT '0表示有效，1为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '00500001', 'C语言程序设计', '0');
INSERT INTO `course` VALUES ('2', '00500002', 'C++程序设计', '0');
INSERT INTO `course` VALUES ('3', '00500003', 'Java程序设计', '0');
INSERT INTO `course` VALUES ('4', '01100001', '高等数学', '0');
INSERT INTO `course` VALUES ('5', '00500004', '操作系统', '0');
INSERT INTO `course` VALUES ('6', '00500005', '微机原理', '0');
INSERT INTO `course` VALUES ('7', '01100002', '离散数学', '0');
INSERT INTO `course` VALUES ('8', '00300001', 'asdasdsadas', '1');
INSERT INTO `course` VALUES ('9', '1100003', '哆啦A梦', '0');

-- ----------------------------
-- Table structure for datadictionary
-- ----------------------------
DROP TABLE IF EXISTS `datadictionary`;
CREATE TABLE `datadictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstCode` varchar(20) DEFAULT NULL,
  `firstCodeValue` varchar(20) DEFAULT NULL,
  `secondCode` varchar(20) DEFAULT NULL,
  `secondCodeValue` varchar(20) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0' COMMENT '0表示有效，1为已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of datadictionary
-- ----------------------------
INSERT INTO `datadictionary` VALUES ('1', 'SYS', '系统角色', 'SYS01', '教师', '0');
INSERT INTO `datadictionary` VALUES ('2', 'SYS', '系统角色', 'SYS02', '学生', '0');
INSERT INTO `datadictionary` VALUES ('3', 'SYS', '系统角色', 'SYS03', '管理员', '0');
INSERT INTO `datadictionary` VALUES ('4', 'S', '学生', 'S01', '博士', '1');
INSERT INTO `datadictionary` VALUES ('5', 'S', '学生', 'S02', '硕士', '1');
INSERT INTO `datadictionary` VALUES ('6', 'S', '学生', 'S03', '本科', '1');
INSERT INTO `datadictionary` VALUES ('7', '001', '机电学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('8', '002', '光电学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('9', '003', '自动化学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('10', '004', '通信学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('11', '005', '计算机学院', '005JK', '计算机科学与技术', '1');
INSERT INTO `datadictionary` VALUES ('12', '006', '经济管理学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('13', '007', '信息管理学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('14', '008', '政教学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('15', '009', '公共管理与传媒学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('16', '010', '外国语学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('17', '011', '理学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('18', '012', '国际交流学院', null, null, '1');
INSERT INTO `datadictionary` VALUES ('19', '010', '外国语学院', '010EN', '英语', '1');
INSERT INTO `datadictionary` VALUES ('20', '005', '计算机学院', '005RG', '软件工程', '1');
INSERT INTO `datadictionary` VALUES ('21', '005', '计算机学院', '005WG', '网络工程', '1');
INSERT INTO `datadictionary` VALUES ('22', '010', '外国语学院', '010FR', '法语', '1');
INSERT INTO `datadictionary` VALUES ('23', 'sys', '管理员', null, null, '1');
INSERT INTO `datadictionary` VALUES ('24', '100', '性别', '1001', '男', '0');
INSERT INTO `datadictionary` VALUES ('25', '100', '性别', '1002', '女', '0');
INSERT INTO `datadictionary` VALUES ('26', '200', '难度等级', '2001', '简单', '0');
INSERT INTO `datadictionary` VALUES ('27', '200', '难度等级', '2002', '一般', '0');
INSERT INTO `datadictionary` VALUES ('28', '200', '难度等级', '2003', '难', '0');
INSERT INTO `datadictionary` VALUES ('29', '300', '填空判断', '3000', '填空题', '0');
INSERT INTO `datadictionary` VALUES ('30', '300', '填空判断', '3001', '判断题', '0');
INSERT INTO `datadictionary` VALUES ('31', '400', '试卷类型', '4001', '试题固定', '0');
INSERT INTO `datadictionary` VALUES ('32', '400', '试卷类型', '4002', '试题不固定', '0');
INSERT INTO `datadictionary` VALUES ('33', '500', '是否参加考试', '5001', '参加', '1');
INSERT INTO `datadictionary` VALUES ('34', '500', '是否参加考试', '5002', '旷考', '1');
INSERT INTO `datadictionary` VALUES ('35', 'SYS', '系统角色', 'SYS04', '超级管理员', '0');
INSERT INTO `datadictionary` VALUES ('36', '600', '判断题正误', '6000', '正确', '0');
INSERT INTO `datadictionary` VALUES ('37', '600', '判断题正误', '6001', '错误', '0');
INSERT INTO `datadictionary` VALUES ('38', '100', '性别', '1003', '保密', '0');

-- ----------------------------
-- Table structure for essayquestion
-- ----------------------------
DROP TABLE IF EXISTS `essayquestion`;
CREATE TABLE `essayquestion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course` varchar(20) DEFAULT NULL,
  `chapter` varchar(20) DEFAULT NULL,
  `level` varchar(4) DEFAULT NULL,
  `title` text,
  `answer` text,
  `creator` varchar(20) DEFAULT NULL,
  `createTime` varchar(40) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of essayquestion
-- ----------------------------
INSERT INTO `essayquestion` VALUES ('1', '00500002', '05', '2001', '范德萨发的飒飒', '大大', null, null, '0');
INSERT INTO `essayquestion` VALUES ('8', '00500001', '02', '2001', '天天热工', '换个电话', null, null, '0');
INSERT INTO `essayquestion` VALUES ('9', '00500002', '05', '2002', '热武器热情为人', '4325324', null, null, '0');
INSERT INTO `essayquestion` VALUES ('10', '00500002', '05', '2001', '范德萨范德萨', '未完全', null, null, '0');
INSERT INTO `essayquestion` VALUES ('11', '00500001', '03', '2001', '飞洒的范德萨发顺丰', '刚发给回复回复', null, null, '0');
INSERT INTO `essayquestion` VALUES ('12', '00500001', '05', '2001', '发生的萨芬', '地方撒飞洒', null, null, '0');
INSERT INTO `essayquestion` VALUES ('13', '00500001', '06', '2001', '的法案是', '范德萨范德萨', null, null, '0');
INSERT INTO `essayquestion` VALUES ('14', '00500002', '04', '2001', '的撒发生', '法人维尔体育', null, null, '0');
INSERT INTO `essayquestion` VALUES ('15', '00500003', '04', '2002', '为企鹅企鹅全文', '二恶趣味', '19950103', '1460107613509', '0');
INSERT INTO `essayquestion` VALUES ('16', '00500003', '06', '2003', '地方还不能失败', '水电费', '19950103', '1460107628849', '0');

-- ----------------------------
-- Table structure for fillblank
-- ----------------------------
DROP TABLE IF EXISTS `fillblank`;
CREATE TABLE `fillblank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course` varchar(20) DEFAULT NULL,
  `chapter` varchar(20) DEFAULT NULL,
  `level` varchar(4) DEFAULT '1' COMMENT '难度等级,1为简单，2为难',
  `title` text,
  `isAnswerFixed` varchar(4) DEFAULT '0' COMMENT '答案是否固定，设定0为不固定，1为固定，默认为0',
  `answer` varchar(255) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `createTime` varchar(40) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0' COMMENT '1表示已经被删除，0表示未被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fillblank
-- ----------------------------
INSERT INTO `fillblank` VALUES ('1', '00500001', '02', '2001', '3+5=8?', '3001', '6000', null, null, '0');
INSERT INTO `fillblank` VALUES ('2', '00500002', '03', '2001', '你是好人吗()', '3000', '我不是好人', null, null, '0');
INSERT INTO `fillblank` VALUES ('3', '00500002', '02', '2001', '函数是C++独有的吗', '3000', '不是，C语言和Java也有函数', null, null, '0');
INSERT INTO `fillblank` VALUES ('4', '00500002', '04', '2001', 'fsaddfghjkgfdsafghjgfds', '3000', 'fdasrew231321', null, null, '0');
INSERT INTO `fillblank` VALUES ('5', '00500002', '02', '2001', 'fdsafdsafdsafsd', '3000', 'fdsfdsfsadf', null, null, '0');
INSERT INTO `fillblank` VALUES ('6', '00500002', '05', '2002', '函数是C++独有的一种结构？', '3001', '6001', null, null, '0');
INSERT INTO `fillblank` VALUES ('7', '00500001', '05', '2002', 'String不是对象', '3001', '6000', null, null, '0');
INSERT INTO `fillblank` VALUES ('8', '00500003', '08', '2002', '去玩儿童分享', '3000', '期望通过从', '19950103', '1460099465299', '0');
INSERT INTO `fillblank` VALUES ('9', '00500003', '04', '2003', '言恶发挥大', '3001', '6001', '19950103', '1460099702973', '0');

-- ----------------------------
-- Table structure for multichoose
-- ----------------------------
DROP TABLE IF EXISTS `multichoose`;
CREATE TABLE `multichoose` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course` varchar(20) DEFAULT NULL COMMENT '科目',
  `chapter` varchar(20) DEFAULT NULL COMMENT '所在章节',
  `level` varchar(4) DEFAULT '1' COMMENT '难度等级,1为简单，2为难',
  `title` text COMMENT '题目内容',
  `answer1` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `answer3` varchar(255) DEFAULT NULL,
  `answer4` varchar(255) DEFAULT NULL,
  `answer5` varchar(255) DEFAULT NULL,
  `answer6` varchar(255) DEFAULT NULL,
  `trueanswer1` varchar(2) DEFAULT NULL,
  `trueanswer2` varchar(2) DEFAULT NULL,
  `trueanswer3` varchar(2) DEFAULT NULL,
  `trueanswer4` varchar(2) DEFAULT NULL,
  `trueanswer5` varchar(2) DEFAULT NULL,
  `trueanswer6` varchar(2) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `createTime` varchar(40) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0' COMMENT '1表示已经被删除，0表示未被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of multichoose
-- ----------------------------
INSERT INTO `multichoose` VALUES ('1', '00500001', '01', '2001', '在下列选项中,不正确的赋值语句是', '++t', 'n1=(n2=(n3=0))', 'k=i=j', 'a=b+c=1', 'EEE', 'FFFF', 'D', 'A', 'B', 'C', 'E', 'F', null, null, '0');
INSERT INTO `multichoose` VALUES ('2', '00500001', '01', '2001', 'C语言提供的合法的数据类型关键字是', 'Double', 'short', 'integer', 'Char', 'EEE', 'FFF', 'B', 'D', 'C', '', '', '', null, null, '0');
INSERT INTO `multichoose` VALUES ('3', '00500001', '01', '2001', '设 int a=12,则执行完语句a+=a-=a*a后,a的值是', '552', '264', '144', '-264', 'EEEFD', 'FFDS', 'C', 'A', 'B', '', '', '', null, null, '0');
INSERT INTO `multichoose` VALUES ('4', '00500001', '04', '2001', '多选题题目', '答案A', '答案B', '答案C', '答案D', '答案E', '答案F', 'A', 'B', 'E', '', '', '', null, null, '0');
INSERT INTO `multichoose` VALUES ('5', '00500001', '05', '2001', 'fdsafsa', 'a', 'b', 'c', 'd', 'e', 'f', 'A', 'D', 'C', '', '', '', null, null, '0');
INSERT INTO `multichoose` VALUES ('6', '00500001', '05', '2002', '打发大水', 'A', 'B', 'C', 'D', 'E', 'F', 'A', 'C', 'F', '', '', '', null, null, '0');
INSERT INTO `multichoose` VALUES ('7', '00500002', '03', '2001', ' 数据的存储、表示形式和基本运算', 'a', 'b', 'c', 'd', 'E', 'F', 'A', 'D', 'F', '', '', '', null, null, '0');
INSERT INTO `multichoose` VALUES ('8', '00500001', '01', '2001', 'fsfdsadsadsadsadas', 'qweqw', 'ewrewre', 'trytr', 'ewqeqe', 'ewqeqwe', 'ewqeqwe', 'A', 'C', '', '', '', '', null, null, '0');
INSERT INTO `multichoose` VALUES ('9', '00500003', '11', '2002', '去维尔体育范德萨', '请问', '而我却', ' 热热', '一天', '放到', '阿萨', 'A', 'D', 'F', '', '', '', '19950103', '1460097140022', '0');

-- ----------------------------
-- Table structure for singlechoose
-- ----------------------------
DROP TABLE IF EXISTS `singlechoose`;
CREATE TABLE `singlechoose` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course` varchar(20) DEFAULT NULL COMMENT '科目',
  `chapter` varchar(20) DEFAULT NULL,
  `level` varchar(4) DEFAULT '1' COMMENT '难度等级，1为简单，2为难',
  `title` text COMMENT '题目内容',
  `answer1` varchar(255) DEFAULT NULL,
  `answer2` varchar(255) DEFAULT NULL,
  `answer3` varchar(255) DEFAULT NULL,
  `answer4` varchar(255) DEFAULT NULL,
  `trueanswer` varchar(2) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `createTime` varchar(40) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0' COMMENT '1表示已经被删除，0表示未被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of singlechoose
-- ----------------------------
INSERT INTO `singlechoose` VALUES ('1', '00500001', '01', '2001', '在下列选项中,不正确的赋值语句是', '++t', 'n1=(n2=(n3=0))', 'k=i=j', 'a=b+c=1', 'D', null, null, '0');
INSERT INTO `singlechoose` VALUES ('2', '00500001', '01', '2001', 'C语言提供的合法的数据类型关键字是', 'Double', 'short', 'integer', 'Char', 'B', null, null, '0');
INSERT INTO `singlechoose` VALUES ('3', '00500001', '01', '2001', '设 int a=12,则执行完语句a+=a-=a*a后,a的值是', '552', '264', '144', '-264', 'C', null, null, '0');
INSERT INTO `singlechoose` VALUES ('4', '00500001', '01', '2001', 'ti', 'ad', 'bsd', 'dgf', 'hg', 'B', null, null, '0');
INSERT INTO `singlechoose` VALUES ('5', '00500001', '04', '2002', 'rte', 'fgds', 'fdsa', 'gfdgf', 'fgf', 'C', null, null, '0');
INSERT INTO `singlechoose` VALUES ('6', '00500001', '01', '2001', '程序设计和C语言', 'ad', 'bsd', 'dgf', 'hg', 'B', null, null, '0');
INSERT INTO `singlechoose` VALUES ('7', '00500001', '04', '2002', '选择结构', 'fgds', 'fdsa', 'gfdgf', 'fgf', 'C', null, null, '0');
INSERT INTO `singlechoose` VALUES ('8', '00500001', '01', '2001', 'e', 'sa', 'dsa', 'fds', 'ere', 'A', null, null, '0');
INSERT INTO `singlechoose` VALUES ('9', '00500002', '05', '2001', '程序设计初步', 'dsada', 'bbcb', 'kjhkk', 'ddvxv', 'B', null, null, '0');
INSERT INTO `singlechoose` VALUES ('10', '00500003', '02', '2001', 'java的发生范德萨', '发生的发生', 'gdfg', '个梵蒂冈', '额外', 'B', '19950103', '1458986288051', '0');

-- ----------------------------
-- Table structure for teachercourse
-- ----------------------------
DROP TABLE IF EXISTS `teachercourse`;
CREATE TABLE `teachercourse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` varchar(20) DEFAULT NULL,
  `courses` varchar(255) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teachercourse
-- ----------------------------
INSERT INTO `teachercourse` VALUES ('1', '19950103', '00500001-00500002-00500003-1100003-', '0');
INSERT INTO `teachercourse` VALUES ('2', '19950103', '00500001-00500002-00500003-00500004-', '1');

-- ----------------------------
-- Table structure for testpaper
-- ----------------------------
DROP TABLE IF EXISTS `testpaper`;
CREATE TABLE `testpaper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course` varchar(20) NOT NULL,
  `chapter` varchar(20) DEFAULT NULL,
  `date` varchar(40) NOT NULL,
  `startTime` varchar(40) NOT NULL,
  `endTime` varchar(40) NOT NULL,
  `joinClass` varchar(255) NOT NULL,
  `creater` varchar(20) NOT NULL,
  `creatTime` varchar(40) NOT NULL,
  `totalScore` varchar(3) DEFAULT NULL,
  `marker` varchar(255) DEFAULT NULL,
  `sc` varchar(255) DEFAULT NULL,
  `sScore` varchar(2) DEFAULT NULL,
  `mc` varchar(255) DEFAULT NULL,
  `mScore` varchar(2) DEFAULT NULL,
  `tf` varchar(255) DEFAULT NULL,
  `tfScore` varchar(255) DEFAULT NULL,
  `fb` varchar(255) DEFAULT NULL,
  `fScore` varchar(2) DEFAULT NULL,
  `eq` varchar(255) DEFAULT NULL,
  `eScore` varchar(2) DEFAULT NULL,
  `fixed` varchar(4) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of testpaper
-- ----------------------------
INSERT INTO `testpaper` VALUES ('47', '00500001', '', '03/13/2016', '17:00', '19:15', '005JK201204-005JK201205-005JK201206', '19950103', '1457860060421', '100', '19950103', '6-1-2-3-2', '10', '4-1', '10', '', '', '', '', '11-8', '15', '4001', '0');
INSERT INTO `testpaper` VALUES ('48', '00500001', '', '03/22/2016', '15:00', '17:00', '005JK201204-005JK201205-005JK201206', '19950103', '1458630929203', '100', '19950103-19960101', '3-2-4-6-5', '10', '8-5-1', '10', '', '', '', '', '13-8', '10', '4001', '0');
INSERT INTO `testpaper` VALUES ('49', '00500001', '', '03/29/2016', '21:15', '23:15', '005JK201204-005JK201205-005JK201206', '19950103', '1459257456542', '100', '19950103-19960101', '1-6-2-3-7', '5', '8-2-3-4-5', '5', '1', '10', '', '', '13-12-11-8', '10', '4001', '0');
INSERT INTO `testpaper` VALUES ('50', '00500001', '', '04/16/2016', '14:30', '16:30', 'ForAllStudent', '19950103', '1460789328756', '100', '19950103-19960101', '2-6-1-3-4', '10', '3-1-5', '10', '', '', '', '', '11-12', '10', '4001', '0');
INSERT INTO `testpaper` VALUES ('51', '00500001', '', '04/16/2016', '17:15', '18:15', 'ForAllStudent', '19950103', '1460798920338', '50', '19950103-19960101', '2-6-1-4-8', '10', '', '', '', '', '', '', '', '', '4001', '0');
INSERT INTO `testpaper` VALUES ('52', '00500001', '', '04/16/2016', '17:15', '18:15', 'ForAllStudent', '19950103', '1460798997049', '50', '19950103-19960101', '5-2001', '10', '', '', '', '', '', '', '', '', '4002', '0');
INSERT INTO `testpaper` VALUES ('53', '00500001', '', '04/16/2016', '18:30', '19:30', 'ForAllStudent', '19950103', '1460803533111', '50', '19950103-19960101', '3-2-1-8', '10', '', '', '1', '10', '', '', '', '', '4001', '0');
INSERT INTO `testpaper` VALUES ('54', '00500001', '', '04/16/2016', '18:30', '19:30', 'ForAllStudent', '19950103', '1460803547739', '50', '19950103-19960101', '4-2001', '10', '', '', '1-2001', '10', '', '', '', '', '4002', '0');

-- ----------------------------
-- Table structure for tests
-- ----------------------------
DROP TABLE IF EXISTS `tests`;
CREATE TABLE `tests` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` varchar(20) DEFAULT NULL,
  `classCode` varchar(20) DEFAULT NULL,
  `testPaperId` varchar(20) DEFAULT NULL,
  `score` varchar(3) DEFAULT NULL,
  `sc` varchar(255) DEFAULT NULL,
  `mc` varchar(255) DEFAULT NULL,
  `tf` varchar(255) DEFAULT NULL,
  `fb` varchar(255) DEFAULT NULL,
  `eq` text,
  `isDelete` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tests
-- ----------------------------
INSERT INTO `tests` VALUES ('27', '2012011222', '005JK201205', '47', '19', 'A`B`C`D`B', 'B-D-E`A-B-E-F', '', '', 'dbasifhdjfndaskjflhasjflasndfasdjhj`发的哈所犯的那是繁琐覅就发了多少发哪的十来年的了考试环境来看', '0');
INSERT INTO `tests` VALUES ('28', '2012011222', '005JK201205', '48', '58', 'A`B`C`D`B', 'A-B-C`B-C-D`C-D-E', '', '', '分公司的好的撒的分工会尽快`而法国就恢复的的股份合计', '0');
INSERT INTO `tests` VALUES ('29', '2012011222', '005JK201205', '50', '70', 'A`B`C`D`B', 'A-B-C`B-C-D`C-D-E', '', '', '法国大开发是否打开时能否卡萨丁疾风剑豪撒打开了发号施令`水电费好久沙拉发电恢复拉升的福利卡速度覅偶撒旦加福禄寿', '0');
INSERT INTO `tests` VALUES ('30', '2012011222', '005JK201205', '51', '10', '10', '', '', '', '', '1');
INSERT INTO `tests` VALUES ('31', '2012011222', '005JK201205', '52', '10', '10', '', '', '', '', '1');
INSERT INTO `tests` VALUES ('32', '2012011222', '005JK201205', '53', '20', 'A`B`C`D', '', '6000', '', '', '0');
INSERT INTO `tests` VALUES ('33', '2012011222', '005JK201205', '54', '10', 'A`6`B`8`C`3`D`2`', '', '6001`1', '', '', '1');
INSERT INTO `tests` VALUES ('34', '2012011222', '005JK201205', '54', '30', 'A`8`B`3`C`6`D`1`', '', '6000`1', '', '', '1');
INSERT INTO `tests` VALUES ('35', '2012011222', '005JK201205', '54', '0', 'A`6`B`3`C`1`D`8`', '', '6001`1', '', '', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` varchar(20) DEFAULT NULL COMMENT '用户编号，包括学号和教职工号',
  `name` varchar(20) DEFAULT NULL COMMENT '用户的真实姓名',
  `password` varchar(40) DEFAULT NULL,
  `gender` int(4) DEFAULT NULL COMMENT '1为男，0为女',
  `phone` bigint(11) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL COMMENT '以邮箱作为用户区分的判定标准，一个邮箱表示一个用户',
  `qq` int(10) DEFAULT NULL COMMENT 'QQ',
  `role` varchar(20) NOT NULL COMMENT '用户类型，教师(职称),学生(学历)',
  `belongNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '所属,学生填写班级，教师填写学院',
  `isDelete` varchar(2) DEFAULT '0' COMMENT '0表示有效，1为已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2012011222', '丁鹏', '202CB962AC59075B964B07152D234B70', '1001', '123456789099', 'dingpeng@163.com', '1334728153', 'SYS02', '005JK201205', '0');
INSERT INTO `user` VALUES ('2', '19950103', 'DingPeng', '202CB962AC59075B964B07152D234B70', '1001', '15652797338', '1767685713@qq.com', '1767', 'SYS01', '005', '0');
INSERT INTO `user` VALUES ('3', '19911102', 'Admin', '202CB962AC59075B964B07152D234B70', '1002', '15652797338', null, null, 'SYS03', '', '0');
INSERT INTO `user` VALUES ('4', '88889999', 'SuperAdmin', 'C20AD4D76FE97759AA27A0C99BFF6710', '1002', null, null, null, 'SYS04', '', '0');
INSERT INTO `user` VALUES ('5', '19910101', 'fang', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS01', '002', '0');
INSERT INTO `user` VALUES ('6', '19940101', 'li', 'C20AD4D76FE97759AA27A0C99BFF6710', '1002', null, null, null, 'SYS01', '004', '0');
INSERT INTO `user` VALUES ('7', '19930101', 'wang', '202CB962AC59075B964B07152D234B70', '1002', null, null, null, 'SYS01', '010', '0');
INSERT INTO `user` VALUES ('8', '19920101', 'deng', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS01', '007', '0');
INSERT INTO `user` VALUES ('9', '2012011223', 'fangsads', 'F7E0EF389AC6133C88AEDBD66B44A4E1', '1001', null, null, null, 'SYS02', '005JK201202', '0');
INSERT INTO `user` VALUES ('10', '2010011224', 'fewfvfhs', 'DB9EEB7E678863649BCE209842E0D164', '1002', null, null, null, 'SYS02', '005RG201203', '0');
INSERT INTO `user` VALUES ('11', '2011011111', '1121212', '3AC42954E48115575CCD4DD286EAEB94', '1002', null, null, null, 'SYS02', '005JK201203', '0');
INSERT INTO `user` VALUES ('12', '19950101', 'qwetyui', 'E86FDC2283AFF4717103F2D44D0610F7', '1002', null, null, null, 'SYS01', '015', '0');
INSERT INTO `user` VALUES ('13', '00001111', 'dfadfsa', '202CB962AC59075B964B07152D234B70', '1002', null, null, null, 'SYS03', '', '0');
INSERT INTO `user` VALUES ('14', '11112222', 'qwer', 'B8C37E33DEFDE51CF91E1E03E51657DA', '1002', null, null, null, 'SYS03', '', '0');
INSERT INTO `user` VALUES ('15', '22223333', '2323', '149815EB972B3C370DEE3B89D645AE14', '1001', null, null, null, 'SYS03', null, '0');
INSERT INTO `user` VALUES ('16', '33334444', '3434', '14C879F3F5D8ED93A09F6090D77C2CC3', '1002', null, null, null, 'SYS03', null, '0');
INSERT INTO `user` VALUES ('17', '19960101', 'facker', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS01', '005', '0');
INSERT INTO `user` VALUES ('18', null, '123', 'C20AD4D76FE97759AA27A0C99BFF6710', '1003', null, null, null, 'SYS02', '005JK201205', '1');
INSERT INTO `user` VALUES ('19', '123456', '123', 'C20AD4D76FE97759AA27A0C99BFF6710', '1003', null, null, null, 'SYS02', '005JK201205', '0');
INSERT INTO `user` VALUES ('20', '3123132131', '321313', '202CB962AC59075B964B07152D234B70', '1002', null, null, null, 'SYS01', '015', '1');
INSERT INTO `user` VALUES ('21', '31231231', '32131312', 'C4DF13CAD905BBFF4CFD811606745CD8', '1001', null, null, null, 'SYS01', '005', '1');
INSERT INTO `user` VALUES ('22', '2010004011', '21212121', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS02', '005RG201001', '1');
INSERT INTO `user` VALUES ('23', '32131231', '是否为', '4D1EA1367ACF0560C6716DD076A84D7F', '1003', null, null, null, 'SYS03', null, '1');
INSERT INTO `user` VALUES ('24', '13213131', '32131', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS02', '005RG201201', '0');
INSERT INTO `user` VALUES ('25', '3123131312', '的方式的发生', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS02', null, '0');
INSERT INTO `user` VALUES ('26', '7319217932', '31231233', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS01', null, '0');
INSERT INTO `user` VALUES ('27', '331231312', '3213131', '202CB962AC59075B964B07152D234B70', '1002', null, null, null, 'SYS01', null, '0');
INSERT INTO `user` VALUES ('28', '312131', '31231', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS02', null, '0');
INSERT INTO `user` VALUES ('29', '2121121', '12121', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS02', null, '0');
INSERT INTO `user` VALUES ('30', '1234567', '123', '202CB962AC59075B964B07152D234B70', '1002', null, null, null, 'SYS02', ' ', '0');
INSERT INTO `user` VALUES ('31', '12345678', '1234', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS02', ' ', '0');
INSERT INTO `user` VALUES ('32', '123456789', '12345', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS02', ' ', '0');
INSERT INTO `user` VALUES ('33', '1234567899', '123', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS02', ' ', '1');
INSERT INTO `user` VALUES ('34', '31343242', '123', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS01', null, '1');
INSERT INTO `user` VALUES ('35', '55556666', '5656', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS03', null, '1');
INSERT INTO `user` VALUES ('36', '212321241421', '32132', '202CB962AC59075B964B07152D234B70', '1002', null, null, null, 'SYS01', null, '0');
INSERT INTO `user` VALUES ('37', '12345654', '12234', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS01', null, '0');
INSERT INTO `user` VALUES ('38', '12345676', '123444', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS02', ' ', '0');
INSERT INTO `user` VALUES ('39', '1231132111', '1231321', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS02', '005RG210501', '0');
INSERT INTO `user` VALUES ('40', '121212121', '123', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS03', null, '1');
