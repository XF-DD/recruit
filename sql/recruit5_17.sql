/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50087
Source Host           : localhost:3306
Source Database       : recruit

Target Server Type    : MYSQL
Target Server Version : 50087
File Encoding         : 65001

Date: 2020-05-17 17:32:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `userid` bigint(20) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) default NULL,
  `email` varchar(50) default NULL,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员信息';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('19980208', '17812345678', '9136f8f230e20ca9afc410d14c826586', '管理员', 'babycoder@163.com');

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `applicationId` int(11) NOT NULL auto_increment,
  `state` int(11) default NULL,
  `recentTime` datetime default NULL,
  `resumeId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  `hrId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY  (`applicationId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES ('1', '-3', null, '1', '16', '6', '1');
INSERT INTO `application` VALUES ('2', '-2', '2020-05-09 15:52:32', '1', '16', '6', '1');
INSERT INTO `application` VALUES ('3', '-2', '2020-05-09 16:00:21', '1', '16', '6', '1');
INSERT INTO `application` VALUES ('4', '0', '2020-05-15 14:12:43', '1', '10', '1', '1');
INSERT INTO `application` VALUES ('5', '0', '2020-05-17 16:55:27', '2', '17', '6', '2');
INSERT INTO `application` VALUES ('6', '0', '2020-05-17 16:55:59', '2', '16', '6', '2');
INSERT INTO `application` VALUES ('7', '0', '2020-05-17 16:56:21', '2', '15', '6', '2');
INSERT INTO `application` VALUES ('8', '0', '2020-05-17 16:57:08', '3', '17', '6', '3');
INSERT INTO `application` VALUES ('9', '0', '2020-05-17 16:57:20', '3', '16', '6', '3');
INSERT INTO `application` VALUES ('10', '0', '2020-05-17 16:57:30', '3', '14', '1', '3');
INSERT INTO `application` VALUES ('11', '0', '2020-05-17 16:57:41', '3', '15', '6', '3');
INSERT INTO `application` VALUES ('12', '0', '2020-05-17 17:30:24', '4', '1', '6', '4');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL auto_increment,
  `categoryName` varchar(50) NOT NULL,
  `description` longtext,
  PRIMARY KEY  (`categoryId`),
  UNIQUE KEY `categoryName_UNIQUE` (`categoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Java', 'Java开发');
INSERT INTO `category` VALUES ('2', 'C++', 'C++开发');
INSERT INTO `category` VALUES ('3', 'PHP', 'PHP开发');
INSERT INTO `category` VALUES ('4', '.NET', '.NET开发');
INSERT INTO `category` VALUES ('5', 'Python', 'Python开发');
INSERT INTO `category` VALUES ('6', '数据挖掘', '数据挖掘');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL auto_increment,
  `type` int(11) default NULL,
  `content` longtext,
  `releaseTime` datetime default NULL,
  `userId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  PRIMARY KEY  (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '3', 'C++是C语言的继承，它既可以进行C语言的过程化程序设计，又可以进行以抽象数据类型为特点的基于对象的程序设计，还可以进行以继承和多态为特点的面向对象的程序设计', '2017-11-14 22:47:42', '1', '5');
INSERT INTO `comment` VALUES ('2', '2', 'Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念', '2017-11-14 22:47:42', '1', '17');
INSERT INTO `comment` VALUES ('3', '1', 'Python是纯粹的自由软件， 源代码和解释器CPython遵循 GPL(GNU General Public License)协议', '2017-11-14 22:47:42', '2', '11');
INSERT INTO `comment` VALUES ('4', '3', 'C++不仅拥有计算机高效运行的实用性特征，同时还致力于提高大规模程序的编程质量与程序设计语言的问题描述能力', '2017-11-14 22:47:42', '3', '18');
INSERT INTO `comment` VALUES ('5', '3', '这是一些关于Java工程师的评论，这个职位需要丰富的阅历的和工作经验', '2017-11-14 22:47:42', '5', '2');
INSERT INTO `comment` VALUES ('6', '2', '又要写测试评论，写点啥呢？布吉岛', '2017-11-14 22:47:42', '6', '8');
INSERT INTO `comment` VALUES ('7', '3', '数据挖掘一般是指从大量的数据中通过算法搜索隐藏于其中信息的过程', '2017-11-14 22:47:42', '6', '14');
INSERT INTO `comment` VALUES ('8', '3', 'Java具有简单性、面向对象、分布式、健壮性、安全性、平台独立与可移植性、多线程、动态性等特点', '2017-11-14 22:47:42', '6', '17');
INSERT INTO `comment` VALUES ('9', '2', 'Python 已经成为最受欢迎的程序设计语言之一', '2017-11-14 22:47:42', '1', '10');
INSERT INTO `comment` VALUES ('10', '3', '<p>.NET是 Microsoft XML Web services 平台</p>', '2017-11-14 22:47:42', '2', '9');
INSERT INTO `comment` VALUES ('19', '3', 'IndexRecruit大透明弱弱地评论一句~', '2017-11-16 22:37:26', '7', '13');
INSERT INTO `comment` VALUES ('21', '3', '学习数据挖掘小白应该从哪个方面入手？萌新小白求教~', '2017-11-17 11:32:32', '24', '14');
INSERT INTO `comment` VALUES ('23', '3', '<p><span><u><b>XML Web services 允许应用程序通过 Internet 进行通讯和共享数据，而不管所采用的是哪种操作系统、设备或编程语言</b></u></span></p>', '2017-11-17 21:45:42', '25', '9');
INSERT INTO `comment` VALUES ('24', '3', '<i>阿里今年双十一赚大发了。。。</i>', '2017-11-17 21:56:58', '25', '1');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `companyId` int(11) NOT NULL auto_increment,
  `companyName` varchar(100) default NULL,
  `companyLogo` int(11) default NULL,
  `description` longtext,
  `state` int(11) default NULL,
  `companyCode` varchar(50) default NULL,
  PRIMARY KEY  (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '阿里巴巴', '1', '阿里巴巴网络技术有限公司（简称：阿里巴巴集团）是以曾担任英语教师的马云为首的18人于1999年在浙江杭州创立', '1', 'AL85685');
INSERT INTO `company` VALUES ('2', '滴滴出行', '2', '滴滴出行是涵盖出租车、专车、 快车、顺风车、代驾及 大巴等多项业务在内的一站式出行平台，2015年9月9日由“滴滴打车”更名而来', '1', 'DD36526');
INSERT INTO `company` VALUES ('3', '搜狐媒体', '3', '搜狐公司是中国领先的互联网品牌，是中国最主要的新闻提供商，搜狐的网络资产给众多用户在信息、娱乐以及交流方面提供了广泛的选择', '1', 'SH74524');
INSERT INTO `company` VALUES ('4', '京东', '4', '京东致力于成为一家为社会创造最大价值的公司。经过13年砥砺前行，京东在商业领域一次又一次突破创新，取得了跨越式发展', '1', 'JD86635');
INSERT INTO `company` VALUES ('5', '网易', '5', '网易公司（NASDAQ: NTES）是中国的互联网公司，利用互联网技术，加强人与人之间信息的交流和共享，实现“网聚人的力量”', '1', 'WY53265');
INSERT INTO `company` VALUES ('6', '爱奇艺', '6', '自成立伊始，爱奇艺坚持“悦享品质”的公司理念，以“用户体验”为生命，专注为用户提供清晰、流畅、界面友好的观映体验', '1', 'AQ86532');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `departmentId` int(11) NOT NULL auto_increment,
  `departmentName` varchar(50) default NULL,
  `description` longtext,
  `companyId` int(11) NOT NULL,
  PRIMARY KEY  (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '技术部', '负责对新产品的设计和开发的控制，编制各类技术文件', '1');
INSERT INTO `department` VALUES ('2', '行政部', '负责行政事务和办公事务', '1');
INSERT INTO `department` VALUES ('3', '市场部', '对销售预测，提出未来市场的分析、发展方向和规划', '1');
INSERT INTO `department` VALUES ('4', '技术部', '负责对新产品的设计和开发的控制，编制各类技术文件', '2');
INSERT INTO `department` VALUES ('5', '行政部', '负责行政事务和办公事务', '2');
INSERT INTO `department` VALUES ('6', '市场部', '对销售预测，提出未来市场的分析、发展方向和规划', '2');
INSERT INTO `department` VALUES ('7', '技术部', '负责对新产品的设计和开发的控制，编制各类技术文件', '3');
INSERT INTO `department` VALUES ('8', '行政部', '负责行政事务和办公事务', '3');
INSERT INTO `department` VALUES ('9', '市场部', '对销售预测，提出未来市场的分析、发展方向和规划', '3');
INSERT INTO `department` VALUES ('10', '技术部', '负责对新产品的设计和开发的控制，编制各类技术文件', '4');
INSERT INTO `department` VALUES ('11', '行政部', '负责行政事务和办公事务', '4');
INSERT INTO `department` VALUES ('12', '市场部', '对销售预测，提出未来市场的分析、发展方向和规划', '4');
INSERT INTO `department` VALUES ('13', '技术部', '负责对新产品的设计和开发的控制，编制各类技术文件', '5');
INSERT INTO `department` VALUES ('14', '行政部', '负责行政事务和办公事务', '5');
INSERT INTO `department` VALUES ('15', '市场部', '对销售预测，提出未来市场的分析、发展方向和规划', '5');
INSERT INTO `department` VALUES ('16', '技术部', '负责对新产品的设计和开发的控制，编制各类技术文件', '6');
INSERT INTO `department` VALUES ('17', '行政部', '负责行政事务和办公事务', '6');
INSERT INTO `department` VALUES ('18', '市场部', '对销售预测，提出未来市场的分析、发展方向和规划', '6');

-- ----------------------------
-- Table structure for favor
-- ----------------------------
DROP TABLE IF EXISTS `favor`;
CREATE TABLE `favor` (
  `favorId` int(11) NOT NULL auto_increment,
  `userId` int(11) NOT NULL,
  `positionId` int(11) NOT NULL,
  PRIMARY KEY  (`favorId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favor
-- ----------------------------
INSERT INTO `favor` VALUES ('1', '1', '16');
INSERT INTO `favor` VALUES ('2', '1', '2');
INSERT INTO `favor` VALUES ('3', '1', '8');
INSERT INTO `favor` VALUES ('4', '2', '2');
INSERT INTO `favor` VALUES ('5', '2', '15');
INSERT INTO `favor` VALUES ('6', '2', '3');
INSERT INTO `favor` VALUES ('7', '2', '5');
INSERT INTO `favor` VALUES ('8', '3', '1');
INSERT INTO `favor` VALUES ('9', '4', '6');
INSERT INTO `favor` VALUES ('10', '4', '8');
INSERT INTO `favor` VALUES ('11', '4', '8');
INSERT INTO `favor` VALUES ('12', '6', '10');
INSERT INTO `favor` VALUES ('13', '6', '11');
INSERT INTO `favor` VALUES ('14', '6', '18');
INSERT INTO `favor` VALUES ('17', '24', '2');

-- ----------------------------
-- Table structure for hr
-- ----------------------------
DROP TABLE IF EXISTS `hr`;
CREATE TABLE `hr` (
  `hrId` int(11) NOT NULL auto_increment,
  `hrMobile` varchar(11) NOT NULL,
  `hrPassword` varchar(500) NOT NULL,
  `hrName` varchar(50) default NULL,
  `hrEmail` varchar(50) default NULL,
  `description` longtext,
  `departmentId` int(11) NOT NULL,
  PRIMARY KEY  (`hrId`),
  UNIQUE KEY `mobile_UNIQUE` (`hrMobile`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hr
-- ----------------------------
INSERT INTO `hr` VALUES ('1', '1', '4QrcOUm6Wau+VuBX8g+IPg==', '董一鸣', 'dongyiming@163.com', '行政部HR', '2');
INSERT INTO `hr` VALUES ('2', '2', '4QrcOUm6Wau+VuBX8g+IPg==', '张帆', 'zhangfan@163.com', '行政部HR', '5');
INSERT INTO `hr` VALUES ('3', '3', '4QrcOUm6Wau+VuBX8g+IPg==', '李斌', 'libin@163.com', '行政部HR', '8');
INSERT INTO `hr` VALUES ('4', '4', '4QrcOUm6Wau+VuBX8g+IPg==', '王语意', 'wangyuyi@163.com', '行政部HR', '11');
INSERT INTO `hr` VALUES ('5', '5', '4QrcOUm6Wau+VuBX8g+IPg==', '李星泽', 'lixingze@163.com', '行政部HR', '14');
INSERT INTO `hr` VALUES ('6', '6', '4QrcOUm6Wau+VuBX8g+IPg==', '嘻嘻', '54456@qq.com', '牛皮', '17');
INSERT INTO `hr` VALUES ('8', '8', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, null, '16');
INSERT INTO `hr` VALUES ('9', '9', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, null, '16');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageId` int(11) NOT NULL auto_increment,
  `news` varchar(255) default NULL,
  `applicationId` int(11) default NULL,
  `userId` int(255) default NULL,
  `hrId` int(255) default NULL,
  `state` int(255) default NULL,
  PRIMARY KEY  (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '恭喜你通过面试！请晚上来我家', '1', '1', '6', '-3');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `positionId` int(11) NOT NULL auto_increment,
  `title` varchar(50) default NULL,
  `requirement` longtext,
  `quantity` int(11) default NULL,
  `workCity` varchar(50) default NULL,
  `salaryUp` int(11) default NULL,
  `salaryDown` int(11) default NULL,
  `releaseDate` date default NULL,
  `validDate` date default NULL,
  `statePub` int(11) default NULL,
  `hits` int(11) default '0',
  `categoryId` int(11) NOT NULL,
  `departmentId` int(11) NOT NULL,
  `hrIdPub` int(11) NOT NULL,
  PRIMARY KEY  (`positionId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', 'Java工程师', '熟练使用RPC框架，具备相关的分布式开发经验', '3', '北京市', '12000', '7000', '2017-10-27', null, '1', '146', '1', '17', '6');
INSERT INTO `position` VALUES ('2', 'Java工程师', '接收应届实习生，实习期满应聘上岗接收应届实习生，实习期满应聘上岗收应届实习生，实习期满应聘上岗', '5', '上海市', '16000', '11000', '2017-11-07', null, '1', '121', '1', '4', '2');
INSERT INTO `position` VALUES ('3', 'Java工程师', '有扎实的java基础，熟悉分布式、缓存、异步消息等原理和应用', '15', '天津市', '13000', '10000', '2017-09-30', null, '1', '3', '1', '7', '3');
INSERT INTO `position` VALUES ('4', 'Java工程师', 'JAVA WEB方向2年+经验', '2', '南京市', '16000', '12000', '2017-09-25', null, '1', '8', '1', '10', '4');
INSERT INTO `position` VALUES ('5', 'C++工程师', '可接收计算机相关专业应届生，表现优秀者加薪转正', '30', '南京市', '8000', '5000', '2017-10-16', null, '1', '13', '2', '10', '4');
INSERT INTO `position` VALUES ('6', 'C++工程师', '3-5年工作经验，计算机相关专业毕业', '1', '上海市', '16000', '8000', '2017-11-03', null, '1', '4', '2', '4', '2');
INSERT INTO `position` VALUES ('7', 'PHP工程师', '一年以上PHP开发经验 （项目经验丰富的，也可以升级为高级开发工程师）', '10', '上海市', '11000', '8000', '2017-11-10', null, '1', '82', '3', '4', '2');
INSERT INTO `position` VALUES ('8', 'PHP工程师', '熟悉LNMP/WNMP开发环境 , 熟练使用Yaf, Yii, ThinkPHP等一种或多种框架', '5', '上海市', '12000', '7000', '2017-11-01', null, '1', '1', '3', '4', '2');
INSERT INTO `position` VALUES ('9', '.NET工程师', '熟悉WinForm/WPF窗体开发并有实际项目经验', '2', '北京市', '13000', '11000', '2017-11-02', null, '1', '11', '4', '1', '1');
INSERT INTO `position` VALUES ('10', 'Python数据分析', '熟练使用Linux，可以快速上手编写shell、powershell、cmd等操作系统脚本', '2', '北京市', '23000', '18000', '2017-10-09', null, '1', '50', '5', '1', '1');
INSERT INTO `position` VALUES ('11', 'Python开发', '4年以上互联网产品后台研发经验，2年以上后台构架经验', '2', '北京市', '22000', '18000', '2017-09-23', null, '1', '0', '5', '1', '1');
INSERT INTO `position` VALUES ('12', 'Python开发', '精通Python，2年或以上Python项目经验', '3', '天津市', '16000', '14000', '2017-07-27', null, '1', '35', '5', '7', '3');
INSERT INTO `position` VALUES ('13', '数据挖掘工程师', '熟悉 Linux平台上的编程环境，精通Java开发，精通 Python/Shell等脚本语言', '12', '天津市', '22000', '15000', '2017-11-05', null, '1', '4', '6', '7', '3');
INSERT INTO `position` VALUES ('14', '数据挖掘工程师', '熟悉Hadoop、Hive、Spark、流式计算、实时计算等大数据相关技术者优先，熟悉时序挖掘、文本挖掘、网络挖掘等优先', '2', '北京市', '32000', '28000', '2017-11-06', null, '1', '8', '6', '1', '1');
INSERT INTO `position` VALUES ('15', '数据挖掘工程师', '精通Python，熟悉PHP/GO/Java/C++/C等语言中的一种或几种', '2', '杭州市', '26000', '14000', '2017-11-08', null, '1', '22', '1', '17', '6');
INSERT INTO `position` VALUES ('16', 'Java工程师', '熟悉Spring、Freemark、Struts、Hibernate 等开源框架', '13', '杭州市', '18000', '15000', '2017-11-11', null, '1', '17', '1', '17', '6');
INSERT INTO `position` VALUES ('17', 'Java后端开发', '熟练使用Mybatis，SpringMVC，SpringCloud等框架', '5', '杭州市', '21000', '18000', '2017-10-23', null, '1', '5', '1', '17', '6');
INSERT INTO `position` VALUES ('18', 'C++后端开发', '熟练linux系统操作，熟练gcc,gdb,vim, eclipse等开发工具', '5', '北京市', '12000', '9000', '2017-10-28', null, '1', '0', '2', '1', '1');

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume` (
  `resumeId` int(11) NOT NULL auto_increment,
  `ability` longtext,
  `internship` longtext,
  `workExperience` longtext,
  `certificate` longtext,
  `jobDesire` longtext,
  `userId` int(11) NOT NULL,
  PRIMARY KEY  (`resumeId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resume
-- ----------------------------
INSERT INTO `resume` VALUES ('1', 'a', 'a', 'a', 'a', 'a', '1');
INSERT INTO `resume` VALUES ('2', null, null, null, null, null, '2');
INSERT INTO `resume` VALUES ('3', 'a', 'a', 'a', 'a', 'a', '3');
INSERT INTO `resume` VALUES ('4', null, null, null, null, null, '4');
INSERT INTO `resume` VALUES ('5', null, null, null, null, null, '5');
INSERT INTO `resume` VALUES ('6', null, null, null, null, null, '6');
INSERT INTO `resume` VALUES ('7', null, null, null, null, null, '7');
INSERT INTO `resume` VALUES ('8', null, null, null, null, null, '8');
INSERT INTO `resume` VALUES ('9', null, null, null, null, null, '9');
INSERT INTO `resume` VALUES ('10', null, null, null, null, null, '10');
INSERT INTO `resume` VALUES ('11', null, null, null, null, null, '11');
INSERT INTO `resume` VALUES ('12', null, null, null, null, null, '12');
INSERT INTO `resume` VALUES ('13', null, null, null, null, null, '13');
INSERT INTO `resume` VALUES ('14', null, null, null, null, null, '14');
INSERT INTO `resume` VALUES ('17', null, null, null, null, null, '17');
INSERT INTO `resume` VALUES ('21', null, null, null, null, null, '21');
INSERT INTO `resume` VALUES ('24', '写点什么。。。', '写点什么。。。', '写点什么。。。', '写点什么。。。', '写点什么。。。', '24');
INSERT INTO `resume` VALUES ('25', '专业能力么。。。也就能悄悄代码', '还没毕业，也没啥实习经历~', '无实际工作经历~', '拿过几次奖学金吧', '有一份别太累稳定的工作就好。。。', '25');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL auto_increment,
  `mobile` varchar(11) NOT NULL,
  `password` varchar(500) NOT NULL,
  `name` varchar(50) default NULL,
  `gender` int(11) default NULL,
  `birthYear` int(11) default NULL,
  `nickname` varchar(100) default NULL,
  `email` varchar(50) default NULL,
  `province` varchar(50) default NULL,
  `city` varchar(50) default NULL,
  `eduDegree` varchar(50) default NULL,
  `graduation` varchar(100) default NULL,
  `graYear` int(11) default NULL,
  `major` varchar(50) default NULL,
  `dirDesire` int(11) default NULL,
  PRIMARY KEY  (`userId`),
  UNIQUE KEY `user_id_UNIQUE` (`userId`),
  UNIQUE KEY `user_mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '4QrcOUm6Wau+VuBX8g+IPg==', '夏高兴', '0', null, '云淡天高', 'xiagaoxin@163.com', '北京市', '北京市', '硕士', '中南财经政法大学', null, null, '3');
INSERT INTO `user` VALUES ('2', '2', '4QrcOUm6Wau+VuBX8g+IPg==', '沈茂德', '0', null, '冷酷的云', 'shenmaode@163.com', '北京市', '北京市', '本科', '湖南师范大学', null, null, '3');
INSERT INTO `user` VALUES ('3', '3', '4QrcOUm6Wau+VuBX8g+IPg==', '杜文瑞', '0', null, '我心寂寞', 'duwenrui@163.com', '上海市', '上海市', '本科', '东北财经大学', null, null, '1');
INSERT INTO `user` VALUES ('4', '4', '4QrcOUm6Wau+VuBX8g+IPg==', '彭友卉', '0', null, '尘封记忆', 'pengyouhui@163.com', '上海市', '上海市', '本科', '西南大学', null, null, '2');
INSERT INTO `user` VALUES ('5', '5', '4QrcOUm6Wau+VuBX8g+IPg==', '崔谷槐', '0', null, '飘雪无垠', 'cuiguhuai@163.com', '上海市', '上海市', '本科', '苏州大学', null, null, '2');
INSERT INTO `user` VALUES ('6', '6', '4QrcOUm6Wau+VuBX8g+IPg==', '魏茂材', '0', null, '风过无痕', 'weimaocai@163.com', '广东省', '广州市', '本科', '西北大学', null, null, '6');
INSERT INTO `user` VALUES ('7', '7', '4QrcOUm6Wau+VuBX8g+IPg==', '侯成文', '0', '1997', '星月相随', 'huochengwen', '湖北省', '武汉市', '硕士', '上海财经大学', '2019', '国际金融', '3');
INSERT INTO `user` VALUES ('8', '8', '4QrcOUm6Wau+VuBX8g+IPg==', '邵夜云', '1', null, '低调沉默者', 'shaoyeyun@163.com', '北京市', '北京市', '本科', '江苏大学', null, null, '2');
INSERT INTO `user` VALUES ('9', '9', '4QrcOUm6Wau+VuBX8g+IPg==', '方彭湃', '1', null, '梦醒童话', 'fangpengpai@163.com', '天津市', '天津市', '硕士', '西南政法大学', null, null, '4');
INSERT INTO `user` VALUES ('10', '10', '4QrcOUm6Wau+VuBX8g+IPg==', '熊新觉', '1', null, '咖啡的味道', 'xiongxinjue@163.com', '广东省', '广州市', '本科', '重庆医科大学', null, null, '3');
INSERT INTO `user` VALUES ('11', '11', '4QrcOUm6Wau+VuBX8g+IPg==', '肖又香', '1', null, '悬世尘埃', 'xiaoyouxiang@163.com', '浙江省', '杭州市', '大专', '福建师范大学', null, null, '2');
INSERT INTO `user` VALUES ('12', '12', '4QrcOUm6Wau+VuBX8g+IPg==', '严经纶', '0', null, '冰封夕阳', 'yanjinlun@163.com', '浙江省', '杭州市', '本科', '广州中医药大学', null, null, '6');
INSERT INTO `user` VALUES ('13', '13', '4QrcOUm6Wau+VuBX8g+IPg==', '邓和豫', '1', null, '隐水酣龙', 'dengheyu@163.com', '浙江省', '杭州市', '本科', '哈尔滨工程大学', null, null, '1');
INSERT INTO `user` VALUES ('14', '14', '4QrcOUm6Wau+VuBX8g+IPg==', '邓雪风', '0', null, '一顿小皮锤', 'dengxuefeng@163.com', '江苏省', '南京市', '本科', '暨南大学', null, null, '4');
INSERT INTO `user` VALUES ('17', '17', '4QrcOUm6Wau+VuBX8g+IPg==', '龟龟', '0', null, '龟龟', 'guigui@163.com', '广东省', '中山市', '本科', '华东理工大学', null, null, '0');
INSERT INTO `user` VALUES ('21', '21', '4QrcOUm6Wau+VuBX8g+IPg==', '木木', '0', null, '木木', 'mumu@163.com', '湖北省', '武汉市', '本科', '天津科技大学', null, null, '0');
INSERT INTO `user` VALUES ('24', '24', '4QrcOUm6Wau+VuBX8g+IPg==', '轩', '0', '1997', '轩', 'xuan@163.com', '山东省', '青岛市', '本科', '青岛科技大学', '2019', '软件工程', '2');
INSERT INTO `user` VALUES ('25', '25', '4QrcOUm6Wau+VuBX8g+IPg==', '青柠', '0', '1997', '青柠', 'babycoder@foxmail.com', '浙江省', '杭州市', '本科', '青岛科技大学', '2019', '软件', '1');
