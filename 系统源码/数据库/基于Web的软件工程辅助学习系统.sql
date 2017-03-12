-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.36 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 ass 的数据库结构
CREATE DATABASE IF NOT EXISTS `ass` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ass`;


-- 导出  表 ass.t_answerinfo 结构
CREATE TABLE IF NOT EXISTS `t_answerinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `info` int(11) NOT NULL,
  `judgeAnswers` varchar(255) DEFAULT NULL,
  `singleAnswers` varchar(255) DEFAULT NULL,
  `subjectAnswers` longtext,
  `paperId` int(11) DEFAULT NULL,
  `studentId` varchar(32) DEFAULT NULL,
  `unitId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6o8ihwdiqgdla8s3pf1049vdl` (`paperId`),
  KEY `FK_pybotai0xrggg1qyvkr50dop9` (`studentId`),
  KEY `FK_46qj0scyo01eqgypmi33r591y` (`unitId`),
  CONSTRAINT `FK_46qj0scyo01eqgypmi33r591y` FOREIGN KEY (`unitId`) REFERENCES `t_unit` (`id`),
  CONSTRAINT `FK_6o8ihwdiqgdla8s3pf1049vdl` FOREIGN KEY (`paperId`) REFERENCES `t_practicepaper` (`id`),
  CONSTRAINT `FK_pybotai0xrggg1qyvkr50dop9` FOREIGN KEY (`studentId`) REFERENCES `t_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_answerinfo 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_answerinfo` DISABLE KEYS */;
INSERT INTO `t_answerinfo` (`id`, `createDate`, `info`, `judgeAnswers`, `singleAnswers`, `subjectAnswers`, `paperId`, `studentId`, `unitId`) VALUES
	(1, '2015-06-03 02:46:24', 40, '0,1', 'C,C,A', '腾立我们', 1, '201200814102', 1),
	(2, '2015-06-03 12:44:56', 50, '0,1', 'C,C', 'Xcb////Vhk', 2, '201100834429', 1);
/*!40000 ALTER TABLE `t_answerinfo` ENABLE KEYS */;


-- 导出  表 ass.t_chapter 结构
CREATE TABLE IF NOT EXISTS `t_chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chaName` varchar(32) NOT NULL,
  `unitId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3dp9w5n8s0dmsg7n61rb2n3ye` (`unitId`),
  CONSTRAINT `FK_3dp9w5n8s0dmsg7n61rb2n3ye` FOREIGN KEY (`unitId`) REFERENCES `t_unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_chapter 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `t_chapter` DISABLE KEYS */;
INSERT INTO `t_chapter` (`id`, `chaName`, `unitId`) VALUES
	(1, '项目估计', 1),
	(2, '项目计划管理', 1),
	(3, '案例分析', 1),
	(4, '需求分析', 2),
	(5, '需求定义', 2),
	(6, '需求管理', 2),
	(7, '体系结构设计', 3),
	(8, '数据库设计', 3),
	(9, '界面设计', 3),
	(10, '程序设计语言', 4),
	(11, '代码审查', 4),
	(12, '案例分析', 4);
/*!40000 ALTER TABLE `t_chapter` ENABLE KEYS */;


-- 导出  表 ass.t_class 结构
CREATE TABLE IF NOT EXISTS `t_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_p7jnmk5xk7t7n8t7dyu4342ew` (`teacher_id`),
  CONSTRAINT `FK_p7jnmk5xk7t7n8t7dyu4342ew` FOREIGN KEY (`teacher_id`) REFERENCES `t_teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_class 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `t_class` DISABLE KEYS */;
INSERT INTO `t_class` (`id`, `name`, `teacher_id`) VALUES
	(1, '计123', 'df4f88ef73ae41989f1b6a44b43db095'),
	(2, '软112', 'df4f88ef73ae41989f1b6a44b43db095'),
	(3, '软件113', 'df4f88ef73ae41989f1b6a44b43db095');
/*!40000 ALTER TABLE `t_class` ENABLE KEYS */;


-- 导出  表 ass.t_file 结构
CREATE TABLE IF NOT EXISTS `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addTimes` datetime DEFAULT NULL,
  `fkey` varchar(64) DEFAULT NULL,
  `ftype` int(11) NOT NULL,
  `isCheck` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `realName` varchar(32) NOT NULL,
  `url` varchar(128) NOT NULL,
  `teacherId` varchar(255) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ejbcxcg35blwoklmuuddt1usy` (`teacherId`),
  KEY `FK_gx978n0wl945vq0mwk334ce3g` (`unit_id`),
  CONSTRAINT `FK_ejbcxcg35blwoklmuuddt1usy` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`),
  CONSTRAINT `FK_gx978n0wl945vq0mwk334ce3g` FOREIGN KEY (`unit_id`) REFERENCES `t_unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_file 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `t_file` DISABLE KEYS */;
INSERT INTO `t_file` (`id`, `addTimes`, `fkey`, `ftype`, `isCheck`, `name`, `realName`, `url`, `teacherId`, `unit_id`) VALUES
	(1, '2015-06-02 18:35:51', NULL, 1, 1, '20150602183551_2236.swf', '第一章 软件工程学概述.swf', '/WEB-INF/upload/ppt/201506/20150602183551_2236.swf', '2', 1),
	(2, '2015-06-02 18:36:31', NULL, 1, 1, '20150602183631_9400.swf', '第二章 可行性研究.swf', '/WEB-INF/upload/ppt/201506/20150602183631_9400.swf', '2', 2),
	(3, '2015-06-03 10:18:43', NULL, 3, 1, '20150603101843_1050.pdf', '软件实现.pdf', '/WEB-INF/upload/document/201506/20150603101843_1050.pdf', '1', 1),
	(4, '2015-06-03 14:52:14', NULL, 1, 1, '20150603145214_7966.swf', '第三章 需求分析.swf', '/WEB-INF/upload/ppt/201506/20150603145214_7966.swf', '1', 3),
	(5, '2015-06-03 14:52:36', NULL, 2, 1, '20150603145236_8346.mp4', 'video.mp4', '/WEB-INF/upload/video/201506/20150603145236_8346.mp4', '1', 1);
/*!40000 ALTER TABLE `t_file` ENABLE KEYS */;


-- 导出  表 ass.t_fill 结构
CREATE TABLE IF NOT EXISTS `t_fill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` int(11) DEFAULT NULL,
  `fkey` varchar(255) DEFAULT NULL,
  `quesDifficult` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `useNum` int(11) DEFAULT NULL,
  `sectorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_btwo3ao890c7rvnfr71jcvu54` (`sectorId`),
  CONSTRAINT `FK_btwo3ao890c7rvnfr71jcvu54` FOREIGN KEY (`sectorId`) REFERENCES `t_sector` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_fill 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_fill` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_fill` ENABLE KEYS */;


-- 导出  表 ass.t_fill_temp 结构
CREATE TABLE IF NOT EXISTS `t_fill_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` int(11) DEFAULT NULL,
  `fkey` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `teacherId` varchar(255) DEFAULT NULL,
  `sectorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iv5wiyij2d7uac5o5erg5wk28` (`teacherId`),
  KEY `FK_mnqimwtu9tu5oe2i42dnhwoe0` (`sectorId`),
  CONSTRAINT `FK_iv5wiyij2d7uac5o5erg5wk28` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`),
  CONSTRAINT `FK_mnqimwtu9tu5oe2i42dnhwoe0` FOREIGN KEY (`sectorId`) REFERENCES `t_sector` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_fill_temp 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_fill_temp` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_fill_temp` ENABLE KEYS */;


-- 导出  表 ass.t_judge 结构
CREATE TABLE IF NOT EXISTS `t_judge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` int(11) DEFAULT NULL,
  `jkey1` varchar(255) DEFAULT NULL,
  `quesDifficult` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `useNum` int(11) DEFAULT NULL,
  `sectorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b5ym73h4s61o03gl8joggfx6o` (`sectorId`),
  CONSTRAINT `FK_b5ym73h4s61o03gl8joggfx6o` FOREIGN KEY (`sectorId`) REFERENCES `t_sector` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_judge 的数据：~118 rows (大约)
/*!40000 ALTER TABLE `t_judge` DISABLE KEYS */;
INSERT INTO `t_judge` (`id`, `answer`, `jkey1`, `quesDifficult`, `question`, `score`, `useNum`, `sectorId`) VALUES
	(1, 0, '1', '0', '软件是计算机程序、数据结构和文档的有机组合。', 5, 1, 2),
	(2, 0, '1', '1', '数字1     软件是计算机程序、数据结构和文档的有机组合。', 5, 0, 2),
	(3, 0, '1', '0', '数字1    软件是计算机程序、数据结构和文档的有机组合。', 5, 1, 2),
	(4, 0, '软件', '1', '中文关键字  软件是计算机程序、数据结构和文档的有机组合。', 5, 0, 2),
	(5, 0, '2', '1', '数字 2   软件是计算机程序、数据结构和文档的有机组合。', 5, 0, 2),
	(6, 0, '软件1', '1', '中文加数字  软件一个', 5, 1, 3),
	(7, 0, '软件开发', '0', '软件是由开发或工程化而形成的，而不是由传统意义上的制造产生的！', 5, 1, 3),
	(8, 0, '软件花费', '1', '软件生命周期中所花费用最多的阶段是软件编码 。', 5, 0, 3),
	(9, 0, '软件需求', '0', '用户对软件需求的描述不精确，往往是产生软件危机的原因之一。', 5, 2, 1),
	(10, 0, '项目进度', '0', '目前，软件项目的进度安排的两种比较常用的方法是程序评估与审查技术（PERT）和关键路径法（CPM）。', 5, 2, 1),
	(11, 1, '原型技术', '1', '目前的绝大多数软件都不适合于快速原型技术。', 5, 2, 1),
	(12, 0, '开发能力', '1', '一个好的开发人员应具备的素质和能力包括善于与周围人员团结协作，建立良好的人际关系，善于听取别人的意见。', 5, 1, 1),
	(13, 1, '软件', '2', '软件也会磨损和老化。', 5, 0, 1),
	(14, 0, '维护', '2', '完善性维护是提高或完善软件的性能。', 5, 0, 1),
	(15, 1, '测试', '2', '测试计划、测试用例、出错统计和有关的分析报告一般不用长期保存。', 5, 0, 2),
	(16, 0, '文档', '1', '文档是一种数据媒体和其上所记录的数据。', 5, 0, 3),
	(17, 1, '测试', '2', '用穷举测试是较现实的测试方法。', 5, 0, 3),
	(18, 1, '开发能力', '2', '一个好的开发人员应具备的素质和能力不包括具有良好的书面和口头表达能力。', 5, 0, 3),
	(19, 0, '数据层次', '0', '层次方框图描绘数据的层次结构, 结构图描绘的是软件结构。', 5, 0, 1),
	(20, 0, '软件', '1', '软件生命周期中所花费用最多的阶段是软件编码 。', 5, 0, 29),
	(21, 0, '软件工程', '1', '软件工程和网络工程都比较好', 5, 1, 1),
	(22, 1, '软件定义', '2', '软件是计算机程序、数据结构和文档的有机组合。', 5, 1, 1),
	(23, 0, '数据库', '0', '数据库是项目开发中必不可少的一部分。', 5, 0, 22),
	(24, 0, '需求', '1', '需求管理主要是对需求变化的管理，及如何有效控制和适应需求的变化。', 5, 0, 14),
	(25, 1, '软件', '0', '软件就是程序，编写软件的关键是编写程序。', 5, 1, 4),
	(26, 0, '可行性', '0', '可行性研究阶段要进行一次大大压缩简化了的系统分析和设计的过程。', 5, 0, 4),
	(27, 0, '需求管理', '0', '需求管理主要是对需求变化的管理，及如何有效控制和适应需求的变化。', 5, 0, 4),
	(28, 1, '数据流图', '0', '数据流图表示了软件系统对数据的算法处理过程，即系统的物理模型。', 5, 0, 4),
	(29, 1, '需求分析', '0', '需求分析的主要方法有 SD 法、OOA 法及 HIPO 法等。', 5, 0, 4),
	(30, 0, '结构化', '1', '没有 Do-case、Do-until 形结构，就不能实现某些结构化程序，从而降低了程序的运', 5, 0, 4),
	(31, 1, '模型', '1', '用面向对象方法分析、设计、实现软件，仍属线性的瀑布开发模型。', 5, 0, 4),
	(32, 0, '文档', '1', '文档是影响软件可维护性的决定因素。', 5, 0, 4),
	(33, 1, '软件开发', '2', '软件是指用程序设计语言（如 PASCAL ,C,VISUAL BASIC 等）编写的程序，软件开发', 5, 0, 4),
	(34, 0, '模块', '2', '软件模块之间的耦合性越弱越好。', 5, 0, 4),
	(35, 0, '软件开发', '0', '软件开发小组的组成人员的素质应该好，而人数则不宜过多。', 5, 0, 5),
	(36, 0, '总体设计', '0', '总体设计的基本目的就是回答："概括地说，系统应该如何实现？"这个问题。', 5, 0, 5),
	(37, 1, '文档', '0', '文档只起备忘录的作用，可以在软件开发完成后再整理生成。', 5, 0, 5),
	(38, 1, '文档', '0', '结构化软件开发的方法的工作模型是螺旋模型。', 5, 0, 5),
	(39, 0, '总体设计', '0', '总体设计的基本目的就是回答："概括地说，系统应该如何实现？"这个问题。', 5, 1, 5),
	(40, 0, '瀑布模型', '1', '瀑布模型的最大优点是将软件开发的各个阶段划分得十分清晰。', 5, 0, 5),
	(41, 1, '面向对象', '1', '在面向对象的软件开发方法中，每个类都存在其相应的对象，类是对象的实例，对象是生', 5, 0, 5),
	(42, 1, '过程描述', '1', '过程描述语言可以用于描述软件的系统结构。', 5, 0, 5),
	(43, 1, '继承性', '2', '继承性是父类和子类之间共享数据结构和消息的机制，这是类之间的一种关系。', 5, 0, 5),
	(44, 0, '快速原型模型', '2', '快速原型模型可以有效地适应用户需求的动态变化。', 5, 1, 5),
	(45, 1, '需求分析', '0', '在面向对象的需求分析方法中，建立动态模型是最主要的任务。', 5, 0, 6),
	(46, 1, '集成测试', '0', '集成测试主要由用户来完成。', 5, 1, 6),
	(47, 1, '测试', '0', '确认测试计划应该在可行性研究阶段制定', 5, 1, 6),
	(48, 1, '白盒测试', '0', '白盒测试无需考虑模块内部的执行过程和程序结构，只要了解模块的功能即可。', 5, 0, 6),
	(49, 0, '概要设计', '0', '软件概要设计包括软件系统结构设计以及数据结构和数据库设计。', 5, 0, 6),
	(50, 0, '总体设计', '1', '总体设计的基本目的就是回答："概括地说，系统应该如何实现？"这个问题。', 5, 0, 6),
	(51, 0, '面向对象', '1', '面向对象的开发方法包括 OOA,OOD,OOP。', 5, 1, 6),
	(52, 0, '面向对象', '1', '面向对象的设计的主要目标是提高生产效率，提高质量和可维护性。', 5, 0, 6),
	(53, 1, '测试', '2', '如果通过软件测试没有发现错误，则说明软件是正确的。', 5, 0, 6),
	(54, 0, '概要设计', '2', '软件概要设计包括软件系统结构设计以及数据结构和数据库设计。', 5, 0, 6),
	(55, 0, '模块化', '0', '模块化，信息隐藏，抽象和逐步求精的软件设计原则有助于得到高内聚，低耦合度的软', 5, 0, 7),
	(56, 0, '程序', '0', '使用括号以改善表达式的清晰性。', 5, 0, 7),
	(57, 1, '设计', '0', '对递归定义的数据结构不要使用递归过程。', 5, 0, 7),
	(58, 1, '风格', '0', '尽可能对程序代码进行优化。', 5, 0, 7),
	(59, 0, '设计', '0', '不要修补不好的程序, 要重新写。', 5, 0, 7),
	(60, 0, '程序', '1', '不要进行浮点数的相等比较。', 5, 0, 7),
	(61, 1, '设计', '1', '应尽可能多地输出中间结果。', 5, 0, 7),
	(62, 0, '设计', '1', '利用数据类型对数据值进行防范。', 5, 0, 7),
	(63, 1, '程序', '2', '用计数方法而不是用文件结束符或输入序列结束符来判别输入的结束。', 5, 0, 7),
	(64, 1, '风格', '2', '程序中的注释是可有可无的。', 5, 0, 7),
	(65, 0, '程序', '0', '使用有意义的标识符。', 5, 0, 8),
	(66, 1, '程序', '0', '用户界面设计颜色选择以鲜艳、丰富多样的色彩搭配最好。', 5, 0, 8),
	(67, 0, '软件', '0', '为了使得软件容易测试, 应该使用高级的程序设计语言编制程序。', 5, 0, 8),
	(68, 0, '软件', '0', '程序测试是一个程序的执行过程, 目的是为了发现软件中隐藏的错误。', 5, 0, 8),
	(69, 0, '软件', '0', '如果程序中连锁式连接了 8 个判定(IF)结构, 则程序中总的路径数达 28 。', 5, 0, 8),
	(70, 0, '测试', '1', '白盒测试仅与程序的内部结构有关, 完全可以不考虑程序的功能要求。', 5, 0, 8),
	(71, 1, '测试', '1', '为了快速完成集成测试, 采用一次性集成方式是适宜的。', 5, 0, 8),
	(72, 1, '测试', '1', '对一批模块进行测试,发现错误多的模块中残留的错误将比其它的模块少。', 5, 0, 8),
	(73, 1, '测试', '2', '好的测试用例应能证明软件是正确的。', 5, 0, 8),
	(74, 0, '软件测试', '2', '边界值分析方法是取输入／输出等价类的边界值做为测试用例。', 5, 0, 8),
	(75, 1, '软件测试', '0', '等价类划分方法考虑了各等价类之间取值的组合情况下可能的结果。', 5, 0, 9),
	(76, 1, '软件测试', '0', '判定覆盖法可能查不出在判定中逻辑运算符使用有误时产生的错误。', 5, 1, 9),
	(77, 1, '软件测试', '0', '软件测试中关键在于测试用例的选择，因此不需要提供测试计划和测试分析报告', 5, 1, 9),
	(78, 1, '软件测试', '0', '由于第三方不了解软件的内部结构， 因此软件测试最好由软件编码人员进行测试。', 5, 0, 9),
	(79, 0, '软件测试', '0', '软件测试效果的取决如何选择高效的测试用例，以便用尽量少的测试用例覆盖尽', 5, 0, 9),
	(80, 1, '软件测试', '1', '如果通过软件测试没有发现错误，则说明软件是没有错误的。', 5, 0, 9),
	(81, 1, '软件', '1', '在进行需求分析时需同时考虑如何实现可维护性问题。', 5, 0, 9),
	(82, 1, '可维护', '1', '完成测试作业后，为了缩短源程序的长度应删去程序中的注解。', 5, 0, 9),
	(83, 0, '软件', '2', '尽可能在软件生产过程中保证各阶段文档的正确性。', 5, 1, 9),
	(84, 1, '软件', '2', '编程时应尽可能使用全局变量。', 5, 0, 9),
	(85, 0, '软件', '0', '选择时间效率和空间效率尽可能高的算法。', 5, 0, 10),
	(86, 1, '软件可维护性', '0', '尽可能利用硬件的特点。', 5, 0, 10),
	(87, 0, '软件可维护性', '0', '重视程序结构的设计，使程序具有较好的层次结构。', 5, 1, 10),
	(88, 0, '软件可维护性', '0', '使用维护工具或支撑环境。', 5, 0, 10),
	(89, 1, '维护', '0', '在进行概要设计时应加强模块间的联系。', 5, 0, 10),
	(90, 0, '维护', '1', '提高程序的可读性，尽可能使用高级语言编写程序。', 5, 0, 10),
	(91, 1, '维护', '1', '为了加快软件维护作业的进度，应尽可能增加维护人员的数目。', 5, 0, 10),
	(92, 0, '螺旋模型', '1', '螺旋模型是在瀑布模型和增量模型的基础上增加了风险分析活动。', 5, 0, 10),
	(93, 1, '数据字典', '2', '数据字典是对数据流图中的数据流， 加工、数据存储、数据的源和终点进行详细定义。', 5, 0, 10),
	(94, 0, '文档', '2', '文档是影响软件可维护性的决定因素。', 5, 0, 10),
	(95, 1, '软件', '0', '软件是指用程序设计语言（如 PASCAL ,C,VISUAL BASIC 等）编写的程序，软件开发实际', 5, 0, 11),
	(96, 1, '系统设计', '0', '系统设计时应该将模块划分得很细，模块数越多越好。', 5, 1, 11),
	(97, 0, '数据库', '0', '数据库设计说明书是一个软件配置项', 5, 0, 11),
	(98, 0, '对象模型', '0', '对象模型表示了静态的、结构化的系统数据性质，描述了系统的静态结构，它是从客观世', 5, 0, 11),
	(99, 1, '过程描述', '0', '过程描述语言可以用于描述软件的系统结构。', 5, 0, 11),
	(100, 1, '测试', '1', '如果通过软件测试没有发现错误，则说明软件是正确的。', 5, 1, 11),
	(101, 0, '快速原型', '1', '快速原型模型可以有效地适应用户需求的动态变化。', 5, 1, 11),
	(102, 0, '软件设计', '1', '模块化，信息隐藏，抽象和逐步求精的软件设计原则有助于得到高内聚，低耦合度的软', 5, 0, 11),
	(103, 1, '集成测试', '2', '集成测试主要由用户来完成。（错）用面向对象方法分析、设计、实现软件，仍属线性的', 5, 0, 11),
	(104, 1, '测试', '2', '确认测试计划应该在可行性研究阶段制定', 5, 0, 11),
	(105, 1, 'CMM ', '0', 'CMM 标准共分五个等级，其中第三级是已管理级。', 5, 0, 12),
	(106, 0, '概要设计', '0', '软件概要设计包括软件系统结构设计以及数据结构和数据库设计。', 5, 0, 12),
	(107, 0, '软件开发', '0', '在软件开发过程中，软件项目管理比软件编码技术显得更为重要。', 5, 0, 12),
	(108, 0, '可行性', '0', '可行性研究阶段要进行一次大大压缩简化了的系统分析和设计的过程。', 5, 0, 12),
	(109, 1, '文档', '0', '文档只起备忘录的作用，可以在软件开发完成后再整理生成。', 5, 0, 12),
	(110, 1, '数据流图', '1', '数据流图表示了软件系统对数据的算法处理过程，即系统的物理模型。', 5, 0, 12),
	(111, 0, '总体设计', '1', '总体设计的基本目的就是回答："概括地说，系统应该如何实现？"这个问题。', 5, 0, 12),
	(112, 0, '效率', '1', '没有 Do-case、Do-until 形结构，就不能实现某些结构化程序，从而降低了程序的运行', 5, 0, 12),
	(113, 1, '面向对象', '2', '用面向对象方法分析、设计、实现软件，仍属线性的瀑布开发模型。', 5, 0, 12),
	(114, 0, '编译器', '2', 'JAVA 语言编译器是一个 CASE 工具。', 5, 0, 12),
	(115, 1, '白盒测试', '0', '白盒测试无需考虑模块内部的执行过程和程序结构，只要了解模块的功能即可。', 5, 0, 13),
	(116, 0, '耦合', '0', '软件模块之间的耦合性越弱越好。', 5, 0, 13),
	(117, 0, '开发', '0', '软件开发小组的组成人员的素质应该好，而人数则不宜过多。', 5, 1, 13),
	(118, 1, '软件开发', '0', '在面向对象的软件开发方法中，每个类都存在其相应的对象，类是对象的实例，对象是', 5, 0, 13);
/*!40000 ALTER TABLE `t_judge` ENABLE KEYS */;


-- 导出  表 ass.t_judge_temp 结构
CREATE TABLE IF NOT EXISTS `t_judge_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` int(11) DEFAULT NULL,
  `jkey1` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `teacherId` varchar(255) DEFAULT NULL,
  `sectorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4yraa0yfqfchs3he486erib5j` (`teacherId`),
  KEY `FK_ay5jm6eayiq7vw1plg0tjkjo5` (`sectorId`),
  CONSTRAINT `FK_4yraa0yfqfchs3he486erib5j` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`),
  CONSTRAINT `FK_ay5jm6eayiq7vw1plg0tjkjo5` FOREIGN KEY (`sectorId`) REFERENCES `t_sector` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_judge_temp 的数据：~21 rows (大约)
/*!40000 ALTER TABLE `t_judge_temp` DISABLE KEYS */;
INSERT INTO `t_judge_temp` (`id`, `answer`, `jkey1`, `question`, `teacherId`, `sectorId`) VALUES
	(8, 0, '生命周期', '软件生命周期中所花费用最多的阶段是软件编码 。', 'df4f88ef73ae41989f1b6a44b43db095', 17),
	(9, 1, '语言', '软件是一门语言', 'df4f88ef73ae41989f1b6a44b43db095', 11),
	(10, 1, '软件', '软件是计算机程序、数据结构和文档的有机组合。', 'df4f88ef73ae41989f1b6a44b43db095', 32),
	(11, 1, '1', '软件是计算机程序、数据结构和文档的有机组合。', 'df4f88ef73ae41989f1b6a44b43db095', 30),
	(12, 0, '1', '软件是计算机程序、数据结构和文档的有机组合。', 'df4f88ef73ae41989f1b6a44b43db095', 17),
	(13, 1, '数据输入', '数据输入的一般准则中包括尽量增加用户输入的动作。', 'df4f88ef73ae41989f1b6a44b43db095', 2),
	(14, 1, '软件开发', '软件工程主要介绍的是项目开发所需要具备的知识', 'df4f88ef73ae41989f1b6a44b43db095', 1),
	(15, 0, '需求', '需求管理主要是对需求变化的管理，及如何有效控制和适应需求的变化。', 'df4f88ef73ae41989f1b6a44b43db095', 7),
	(16, 0, '软件开发', '软件是由开发或工程化而形成的，而不是由传统意义上的制造产生的！', '1', 13),
	(17, 0, '软件', '软件是计算机程序、数据结构和文档的邮寄组合。', '1', 13),
	(18, 0, '软件花费', '软件生命周期中所花费用最多的阶段是软件编码 。', '1', 13),
	(19, 0, '软件工程', '软件工程和网络工程都比较好', '1', 14),
	(20, 0, '软件定义', '软件是计算机程序、数据结构和文档的有机组合。', '1', 14),
	(21, 0, '数据库', '数据库是项目开发中必不可少的一部分。', '1', 22),
	(22, 0, '资源估算', '软件开发过程中要重视资源估算。', '1', 2),
	(23, 0, '生命周期', '软件生命周期中所花费用最多的阶段是软件编码 。', '5e19596c794048ddad43b36799a7f53f', 17),
	(24, 1, '语言', '软件是一门语言', '5e19596c794048ddad43b36799a7f53f', 11),
	(25, 1, '软件', '软件是计算机程序、数据结构和文档的有机组合。', '5e19596c794048ddad43b36799a7f53f', 32),
	(26, 1, '1', '软件是计算机程序、数据结构和文档的有机组合。', '5e19596c794048ddad43b36799a7f53f', 30),
	(27, 0, '1', '软件是计算机程序、数据结构和文档的有机组合。', 'df4f88ef73ae41989f1b6a44b43db095', 17),
	(28, 0, 'sad', '第三方', 'df4f88ef73ae41989f1b6a44b43db095', 1);
/*!40000 ALTER TABLE `t_judge_temp` ENABLE KEYS */;


-- 导出  表 ass.t_lexicon 结构
CREATE TABLE IF NOT EXISTS `t_lexicon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `en_mean` longtext,
  `word` varchar(64) DEFAULT NULL,
  `zh_mean` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mnem58mh01vlywx54d5x45qcc` (`word`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_lexicon 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `t_lexicon` DISABLE KEYS */;
INSERT INTO `t_lexicon` (`id`, `en_mean`, `word`, `zh_mean`) VALUES
	(84, 'USB (Universal Serial Bus) is a computer bus standard.', 'USB', 'abbr. 通用串行总线（Universal Serial Bus）'),
	(85, 'a developer language', 'java', 'Sun公司推出的一种应用程序开发语言'),
	(86, 'Spring helps development teams everywhere build simple, portable,', 'Spring', 'Spring是一个开源框架，Spring是于2003 年兴起的一个轻量级的Java 开发框架，由Rod Johnson 在其著作Expert One-On-One J2EE Development and Design中阐述的部分理念和原型衍生而来。'),
	(87, 'Hibernate ORM enables developers to more easily write applications whose data outlives the application process.', 'Hibernate ', 'Hibernate是一个开放源代码的对象关系映射框架，它对JDBC进行了非常轻量级的对象封装，使得Java程序员可以随心所欲的使用对象编程思维来操纵数据库。'),
	(88, 'The Spring Web model-view-controller (MVC) framework is designed around a DispatcherServlet that dispatches requests to handlers', 'Spring MVC', 'Spring MVC属于SpringFrameWork的后续产品，已经融合在Spring Web Flow里面。Spring 框架提供了构建 Web 应用程序的全功能 MVC 模块。'),
	(89, 'Android powers hundreds of millions of mobile devices in more than 190 countries around the world.', 'Android', 'Android是一种基于Linux的自由及开放源代码的操作系统，主要使用于移动设备，如智能手机和平板电脑，由Google公司和开放手机联盟领导及开发。'),
	(90, 'Java Persistence API.', 'JPA', 'Java 持久层 API 。JPA通过JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。'),
	(91, 'Java Message Service', 'JMS', 'Java消息服务.Java消息服务是一个与具体平台无关的API，绝大多数MOM提供商都对JMS提供支持。'),
	(92, 'The previous chapter described the Spring’s support for AOP using @AspectJ and schema-based aspect definitions. In this chapter we discuss the lower-level Spring AOP APIs and the AOP support used in Spring 1.2 applications', 'AOP', '面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。'),
	(93, 'Inversion of Control.IoC is also known as dependency injection (DI). It is a process whereby objects define their dependencies, that is, the other objects they work with, only through constructor argument', 'IOC', '控制反转是一个重要的面向对象编程的法则来削减计算机程序的耦合问题，也是轻量级的Spring框架的核心。 控制反转一般分为两种类型，依赖注入（Dependency Injection，简称DI）和依赖查找（Dependency Lookup）。依赖注入应用比较广泛。'),
	(94, 'JAVA Enterprises ', 'JAVA EE', 'JavaEE是 J2EE的一个新的名称，之所以改名，目的还是让大家清楚J2EE只是Java企业应用.随着WEB和EJB容器概念诞生');
/*!40000 ALTER TABLE `t_lexicon` ENABLE KEYS */;


-- 导出  表 ass.t_page_fill 结构
CREATE TABLE IF NOT EXISTS `t_page_fill` (
  `paperId` int(11) NOT NULL,
  `fillId` int(11) NOT NULL,
  KEY `FK_s0tqjhqyxofa69274fxcket6t` (`fillId`),
  KEY `FK_jdh7mvmjlu0s83qb74foln5gq` (`paperId`),
  CONSTRAINT `FK_jdh7mvmjlu0s83qb74foln5gq` FOREIGN KEY (`paperId`) REFERENCES `t_testpaper` (`id`),
  CONSTRAINT `FK_s0tqjhqyxofa69274fxcket6t` FOREIGN KEY (`fillId`) REFERENCES `t_fill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_page_fill 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_page_fill` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_page_fill` ENABLE KEYS */;


-- 导出  表 ass.t_page_judge 结构
CREATE TABLE IF NOT EXISTS `t_page_judge` (
  `paperId` int(11) NOT NULL,
  `judgeId` int(11) NOT NULL,
  KEY `FK_duivtigtnig6w7byok5nxo83a` (`judgeId`),
  KEY `FK_1gn21wjna6gnopbvy3y8cc64x` (`paperId`),
  CONSTRAINT `FK_1gn21wjna6gnopbvy3y8cc64x` FOREIGN KEY (`paperId`) REFERENCES `t_testpaper` (`id`),
  CONSTRAINT `FK_duivtigtnig6w7byok5nxo83a` FOREIGN KEY (`judgeId`) REFERENCES `t_judge` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_page_judge 的数据：~88 rows (大约)
/*!40000 ALTER TABLE `t_page_judge` DISABLE KEYS */;
INSERT INTO `t_page_judge` (`paperId`, `judgeId`) VALUES
	(1, 11),
	(1, 12),
	(1, 9),
	(1, 10),
	(2, 9),
	(2, 10),
	(2, 19),
	(2, 11),
	(2, 12),
	(4, 7),
	(4, 9),
	(4, 1),
	(4, 8),
	(4, 6),
	(4, 16),
	(4, 17),
	(4, 18),
	(4, 15),
	(5, 10),
	(5, 19),
	(5, 9),
	(5, 2),
	(5, 16),
	(5, 21),
	(5, 18),
	(5, 14),
	(5, 13),
	(6, 19),
	(6, 1),
	(6, 9),
	(6, 5),
	(6, 16),
	(6, 14),
	(7, 9),
	(7, 1),
	(7, 7),
	(7, 5),
	(7, 2),
	(7, 22),
	(8, 10),
	(8, 1),
	(8, 9),
	(8, 7),
	(8, 12),
	(8, 16),
	(8, 13),
	(9, 10),
	(9, 7),
	(9, 9),
	(9, 6),
	(9, 5),
	(9, 18),
	(10, 3),
	(10, 7),
	(10, 19),
	(10, 11),
	(10, 4),
	(10, 13),
	(11, 7),
	(11, 19),
	(11, 9),
	(11, 4),
	(11, 21),
	(11, 18),
	(15, 7),
	(15, 9),
	(15, 1),
	(15, 8),
	(15, 6),
	(15, 13),
	(21, 7),
	(21, 10),
	(21, 47),
	(21, 51),
	(21, 11),
	(21, 22),
	(22, 9),
	(22, 25),
	(22, 3),
	(22, 46),
	(22, 39),
	(22, 6),
	(22, 12),
	(22, 44),
	(23, 9),
	(23, 10),
	(23, 11),
	(23, 21);
/*!40000 ALTER TABLE `t_page_judge` ENABLE KEYS */;


-- 导出  表 ass.t_page_single 结构
CREATE TABLE IF NOT EXISTS `t_page_single` (
  `paperId` int(11) NOT NULL,
  `singleselId` int(11) NOT NULL,
  KEY `FK_elqsfj4tg3ox3malm0mnorjbo` (`singleselId`),
  KEY `FK_pf9o704tm2wvh2x5bchf2xuq5` (`paperId`),
  CONSTRAINT `FK_elqsfj4tg3ox3malm0mnorjbo` FOREIGN KEY (`singleselId`) REFERENCES `t_singlesel` (`id`),
  CONSTRAINT `FK_pf9o704tm2wvh2x5bchf2xuq5` FOREIGN KEY (`paperId`) REFERENCES `t_testpaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_page_single 的数据：~79 rows (大约)
/*!40000 ALTER TABLE `t_page_single` DISABLE KEYS */;
INSERT INTO `t_page_single` (`paperId`, `singleselId`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(2, 3),
	(2, 4),
	(4, 2),
	(4, 1),
	(4, 13),
	(4, 16),
	(4, 3),
	(4, 10),
	(4, 12),
	(4, 6),
	(4, 11),
	(5, 7),
	(5, 8),
	(5, 13),
	(5, 15),
	(5, 16),
	(5, 9),
	(5, 17),
	(5, 5),
	(5, 11),
	(6, 13),
	(6, 7),
	(6, 1),
	(6, 3),
	(6, 10),
	(6, 6),
	(7, 14),
	(7, 2),
	(7, 8),
	(7, 16),
	(7, 3),
	(7, 11),
	(8, 7),
	(8, 2),
	(8, 8),
	(8, 16),
	(8, 10),
	(8, 12),
	(9, 22),
	(9, 14),
	(9, 1),
	(9, 10),
	(9, 15),
	(9, 5),
	(10, 7),
	(10, 1),
	(10, 13),
	(10, 4),
	(10, 9),
	(10, 5),
	(11, 2),
	(11, 1),
	(11, 7),
	(11, 3),
	(11, 15),
	(11, 5),
	(15, 8),
	(15, 14),
	(15, 2),
	(15, 4),
	(15, 16),
	(15, 17),
	(21, 14),
	(21, 26),
	(21, 35),
	(21, 41),
	(21, 16),
	(21, 12),
	(22, 7),
	(22, 1),
	(22, 38),
	(22, 16),
	(22, 41),
	(22, 17),
	(23, 1),
	(23, 2);
/*!40000 ALTER TABLE `t_page_single` ENABLE KEYS */;


-- 导出  表 ass.t_page_subj 结构
CREATE TABLE IF NOT EXISTS `t_page_subj` (
  `paperId` int(11) NOT NULL,
  `subjectiveId` int(11) NOT NULL,
  KEY `FK_7ghbun114329tgg60j6aybrc4` (`subjectiveId`),
  KEY `FK_q6j062kufpm2vt4yxdnik9430` (`paperId`),
  CONSTRAINT `FK_7ghbun114329tgg60j6aybrc4` FOREIGN KEY (`subjectiveId`) REFERENCES `t_subjective` (`id`),
  CONSTRAINT `FK_q6j062kufpm2vt4yxdnik9430` FOREIGN KEY (`paperId`) REFERENCES `t_testpaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_page_subj 的数据：~31 rows (大约)
/*!40000 ALTER TABLE `t_page_subj` DISABLE KEYS */;
INSERT INTO `t_page_subj` (`paperId`, `subjectiveId`) VALUES
	(4, 7),
	(4, 14),
	(4, 8),
	(4, 10),
	(4, 16),
	(4, 4),
	(4, 12),
	(4, 11),
	(4, 6),
	(5, 2),
	(5, 13),
	(5, 14),
	(5, 9),
	(5, 16),
	(5, 4),
	(5, 11),
	(5, 6),
	(5, 5),
	(6, 2),
	(7, 1),
	(8, 1),
	(9, 7),
	(10, 7),
	(11, 7),
	(15, 7),
	(21, 27),
	(22, 25),
	(1, 13),
	(2, 13),
	(23, 3),
	(23, 4);
/*!40000 ALTER TABLE `t_page_subj` ENABLE KEYS */;


-- 导出  表 ass.t_practiceanswer 结构
CREATE TABLE IF NOT EXISTS `t_practiceanswer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `judgeAnswers` varchar(255) DEFAULT NULL,
  `singleAnswers` varchar(255) DEFAULT NULL,
  `subjectAnswers` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_practiceanswer 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `t_practiceanswer` DISABLE KEYS */;
INSERT INTO `t_practiceanswer` (`id`, `judgeAnswers`, `singleAnswers`, `subjectAnswers`) VALUES
	(1, '0,0', 'B,C,B', 'JSP方法是面向数据结构的设计方法，其定义了一组以数据结构为指导的映射过程，它根据输入，输出的数据结构，按一定的规则映射成软件的过程描述，即程序结构。'),
	(2, '0,1', 'A,B', '描述软件开发过程中各种活动如何执行的模型////指相同的操作或函数、过程可作用于多种类型的对象上并获得不同结果。或（不同的对象，收到同一消息可以产生不同的结果。'),
	(3, '0,0', 'A,B,B', '就程序设计语言的工程特性而言，对程序编码有如下要求：');
/*!40000 ALTER TABLE `t_practiceanswer` ENABLE KEYS */;


-- 导出  表 ass.t_practicepaper 结构
CREATE TABLE IF NOT EXISTS `t_practicepaper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answerDate` datetime DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `isRelease` int(11) NOT NULL,
  `pagerName` varchar(255) DEFAULT NULL,
  `answerId` int(11) DEFAULT NULL,
  `teacherId` varchar(255) DEFAULT NULL,
  `unitId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iro49883peml1fsb50nxrrsl3` (`answerId`),
  KEY `FK_lylylkk71wvl9i3a82anfm1jj` (`teacherId`),
  KEY `FK_i7af2udq4k7gu3u9kf92oxub2` (`unitId`),
  CONSTRAINT `FK_i7af2udq4k7gu3u9kf92oxub2` FOREIGN KEY (`unitId`) REFERENCES `t_unit` (`id`),
  CONSTRAINT `FK_iro49883peml1fsb50nxrrsl3` FOREIGN KEY (`answerId`) REFERENCES `t_practiceanswer` (`id`),
  CONSTRAINT `FK_lylylkk71wvl9i3a82anfm1jj` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_practicepaper 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `t_practicepaper` DISABLE KEYS */;
INSERT INTO `t_practicepaper` (`id`, `answerDate`, `createDate`, `isRelease`, `pagerName`, `answerId`, `teacherId`, `unitId`) VALUES
	(1, '2015-06-03 03:23:45', '2015-06-03 02:39:37', 1, '测试一', 1, 'df4f88ef73ae41989f1b6a44b43db095', 1),
	(2, '2015-06-03 14:39:33', '2015-06-03 12:43:21', 1, 'Erff', 2, 'df4f88ef73ae41989f1b6a44b43db095', 1),
	(3, '2015-06-03 15:56:49', '2015-06-03 15:40:36', 1, '测试', 3, 'df4f88ef73ae41989f1b6a44b43db095', 1);
/*!40000 ALTER TABLE `t_practicepaper` ENABLE KEYS */;


-- 导出  表 ass.t_pra_judge 结构
CREATE TABLE IF NOT EXISTS `t_pra_judge` (
  `paperId` int(11) NOT NULL,
  `judgeId` int(11) NOT NULL,
  KEY `FK_3dcafms2n8cphrqwfxxdkk6x` (`judgeId`),
  KEY `FK_dtga1nu1xhmv14krmudg5nd8s` (`paperId`),
  CONSTRAINT `FK_3dcafms2n8cphrqwfxxdkk6x` FOREIGN KEY (`judgeId`) REFERENCES `t_judge` (`id`),
  CONSTRAINT `FK_dtga1nu1xhmv14krmudg5nd8s` FOREIGN KEY (`paperId`) REFERENCES `t_practicepaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_pra_judge 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `t_pra_judge` DISABLE KEYS */;
INSERT INTO `t_pra_judge` (`paperId`, `judgeId`) VALUES
	(1, 2),
	(1, 7),
	(2, 21),
	(2, 15),
	(3, 1),
	(3, 9);
/*!40000 ALTER TABLE `t_pra_judge` ENABLE KEYS */;


-- 导出  表 ass.t_pra_single 结构
CREATE TABLE IF NOT EXISTS `t_pra_single` (
  `paperId` int(11) NOT NULL,
  `singleselId` int(11) NOT NULL,
  KEY `FK_dxero5pm8ehklhlxosxyi8pxg` (`singleselId`),
  KEY `FK_n6lcsfe9a71gq16bsvk0av00t` (`paperId`),
  CONSTRAINT `FK_dxero5pm8ehklhlxosxyi8pxg` FOREIGN KEY (`singleselId`) REFERENCES `t_singlesel` (`id`),
  CONSTRAINT `FK_n6lcsfe9a71gq16bsvk0av00t` FOREIGN KEY (`paperId`) REFERENCES `t_practicepaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_pra_single 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `t_pra_single` DISABLE KEYS */;
INSERT INTO `t_pra_single` (`paperId`, `singleselId`) VALUES
	(1, 12),
	(1, 14),
	(1, 7),
	(2, 15),
	(2, 5),
	(3, 15),
	(3, 11),
	(3, 8);
/*!40000 ALTER TABLE `t_pra_single` ENABLE KEYS */;


-- 导出  表 ass.t_pra_subj 结构
CREATE TABLE IF NOT EXISTS `t_pra_subj` (
  `paperId` int(11) NOT NULL,
  `subjectiveId` int(11) NOT NULL,
  KEY `FK_tlm40myn16qavao5gc80lxmut` (`subjectiveId`),
  KEY `FK_49pdw2uemtlv9boawgo2c96cd` (`paperId`),
  CONSTRAINT `FK_49pdw2uemtlv9boawgo2c96cd` FOREIGN KEY (`paperId`) REFERENCES `t_practicepaper` (`id`),
  CONSTRAINT `FK_tlm40myn16qavao5gc80lxmut` FOREIGN KEY (`subjectiveId`) REFERENCES `t_subjective` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_pra_subj 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `t_pra_subj` DISABLE KEYS */;
INSERT INTO `t_pra_subj` (`paperId`, `subjectiveId`) VALUES
	(1, 7),
	(2, 5),
	(2, 8),
	(3, 3);
/*!40000 ALTER TABLE `t_pra_subj` ENABLE KEYS */;


-- 导出  表 ass.t_sector 结构
CREATE TABLE IF NOT EXISTS `t_sector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `secName` varchar(32) NOT NULL,
  `chaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gb6hhno54a23pdhwpw9pss5kj` (`chaId`),
  CONSTRAINT `FK_gb6hhno54a23pdhwpw9pss5kj` FOREIGN KEY (`chaId`) REFERENCES `t_chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_sector 的数据：~32 rows (大约)
/*!40000 ALTER TABLE `t_sector` DISABLE KEYS */;
INSERT INTO `t_sector` (`id`, `secName`, `chaId`) VALUES
	(1, '项目范围', 1),
	(2, '资源估算', 1),
	(3, '成本估算', 1),
	(4, '项目质量', 2),
	(5, '项目配置', 2),
	(6, '项目测试', 2),
	(7, '项目估算', 3),
	(8, '计划管理', 3),
	(9, '需求计划', 4),
	(10, '软件需求', 4),
	(11, '原型分析', 5),
	(12, '用力分析', 5),
	(13, '系统建模', 5),
	(14, '需求报告', 6),
	(15, '版本控制', 6),
	(16, '需求跟踪', 6),
	(17, '体系机构建模', 7),
	(18, '构架文档', 7),
	(19, 'MVC架构', 7),
	(20, '软件体系风格', 7),
	(21, '数据库设计过程', 8),
	(22, '数据库设计技巧', 8),
	(23, '界面设计原则', 9),
	(24, '设计流程', 9),
	(25, '设计规范', 9),
	(26, '程序设计分类', 10),
	(27, '程序设计特性', 10),
	(28, '审查目的', 11),
	(29, '审查内容', 11),
	(30, '审查过程', 11),
	(31, '编码规约', 12),
	(32, '代码审查', 12);
/*!40000 ALTER TABLE `t_sector` ENABLE KEYS */;


-- 导出  表 ass.t_singlesel 结构
CREATE TABLE IF NOT EXISTS `t_singlesel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(16) DEFAULT NULL,
  `choiceA` varchar(64) DEFAULT NULL,
  `choiceB` varchar(64) DEFAULT NULL,
  `choiceC` varchar(64) DEFAULT NULL,
  `choiceD` varchar(64) DEFAULT NULL,
  `quesDifficult` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT '5',
  `skey1` varchar(255) DEFAULT NULL,
  `useNum` int(11) DEFAULT '0',
  `sectorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mysd1y45jccigo3r4t8vsp7cq` (`sectorId`),
  CONSTRAINT `FK_mysd1y45jccigo3r4t8vsp7cq` FOREIGN KEY (`sectorId`) REFERENCES `t_sector` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_singlesel 的数据：~77 rows (大约)
/*!40000 ALTER TABLE `t_singlesel` DISABLE KEYS */;
INSERT INTO `t_singlesel` (`id`, `answer`, `choiceA`, `choiceB`, `choiceC`, `choiceD`, `quesDifficult`, `question`, `score`, `skey1`, `useNum`, `sectorId`) VALUES
	(1, 'C', '基于信息工程CASE', '人工智能CASE', '结构的基于图形CASE ', '集成的CASE环境', '0', '在下列工具与环境中（   ）属于较早期的CASE', 5, 'CASE', 2, 1),
	(2, 'D', '静态单变量', '动态单变量', '静态多变量', '动态多变量', '0', 'Putnam成本估算模型是一个（   ）模型。', 5, '模型', 1, 1),
	(3, 'C', '可靠性', '可重用行', '适应性', '可移植性', '1', '在McCall软件质量度量模型中，（   ）属于面向软件产品修改', 5, '模型', 0, 1),
	(4, 'D', 'SQIC', 'SQMC', 'SQRC', 'SQDC', '1', 'ISO的软件质量评价模型由3层组成，其中用于评价设计质量的准则是（   ）', 5, '模型', 0, 1),
	(5, 'B', '效率', '规模', ' 完整性', '容错性', '2', '软件复杂性度量的参数包括（    ）', 5, '软件复杂性', 0, 1),
	(6, 'C', '结合', '隐藏', '封装', '抽象', '2', '对象实现了数据和操作的结合，使数据和操作（    ）于对象的统一体中。', 5, '对象', 0, 1),
	(7, 'B', '边界值分析', '演绎法', '循环覆盖', '集成测试', '0', '软件调试技术包括（   ）', 5, '调试技术', 1, 2),
	(8, 'B', '用户容易参与开发', '缺乏灵活性', '用户与开发者易沟通', '适用可变需求', '0', '瀑布模型的存在问题是（    ）', 5, '模型', 0, 2),
	(9, 'A', '计算机辅助静态分析', '黑盒法 ', '路径覆盖 ', '边界值分析', '1', '软件测试方法中的静态测试方法之一为（    ）', 5, '测试', 0, 2),
	(10, 'D', '详细设计', '软件编码', '软件测试', '软件维护', '1', '软件生命周期中所花费用最多的阶段是（    ）', 5, '生命周期', 0, 2),
	(11, 'B', 'FORTRAN语言 ', 'Pascal语言  ', '．C语言', 'PL/1语言', '2', '第一个体现结构化编程思想的程序设计语言是（    ）', 5, '设计语言', 0, 2),
	(12, 'B', '过程、子程序和分程序', '顺序、选择和重复', '递归、堆栈和队列', '调用、返回和转移', '2', '程序的三种基本控制结构是（    ）', 5, '控制结构', 1, 2),
	(13, 'A', 'PAD  ', 'SA', 'SC', 'DFD', '0', '在详细设计阶段，经常采用的工具有（    ）', 5, '详细设计', 0, 3),
	(14, 'C', '代码的规模 ', '运行速度', '质量', '可维护性', '0', '详细设计的结果基本决定了最终程序的（    ）', 5, '详细设计', 1, 3),
	(15, 'A', '软件做什么', '用户使用界面', '输入的信息', '软件规模', '1', '需求分析中开发人员要从用户那里了解（    ）', 5, '需求分析', 0, 3),
	(16, 'D', '程序的规模', '程序的效率', '程序设计语言的先进性', '程序易读性', '1', '结构化程序设计主要强调的是（    ）', 5, '结构化', 2, 3),
	(17, 'D', '怎么做', '对谁做   ', '何时做', '做什么', '2', 'IDEF。图反映系统（    ）', 5, '图', 1, 3),
	(18, 'C', '资源有效性', '管理制度 ', '效益分析', '开发风险', '2', '经济可行性研究的范围包括（    ）', 5, '经济可行性', 0, 3),
	(19, 'D', '复制', '再生产', '拷贝', '研制', '0', '软件产品的开发主要是(      )。', 5, '软件开发', 0, 4),
	(20, 'C', '程序设计', '软件生产自动化', '程序系统', '软件工程', '0', '作坊式小团体合作生产方式的时代是(    )时代', 5, '合作', 0, 4),
	(21, 'C', '理论研究', '原理探讨', '建造软件系统', '原理的理论', '0', '软件工程与计算机科学性质不同，软件工程着重于(   )。', 5, '性质', 0, 5),
	(22, 'A', '编码', '需求分析', '详细设计', '测试', '0', '将每个模块的控制结构转换成计算机可接受的程序代码是(  A )阶段的任务。', 5, '编码', 0, 5),
	(23, 'B', '开发向木', '项目值得开发否', '规划项目', '维护项目', '1', '可行性研究的目的是决定（）', 5, '项目开发', 0, 5),
	(24, 'A', '概要设计', '详细设计', '需求分析', '可行性分析', '0', '在软件生命周期中，能准确确定软件系统的体系结构的功能阶段是（    ）', 5, '体系结构', 0, 5),
	(25, 'C', '过程', '方法', '环境', '工具', '0', '下面不是软件工程的3个要素的是（    ）', 5, '软件工程', 0, 5),
	(26, 'B', '程序', '记录', '文档', '数据', '0', '下面不属于软件的组成的是（    ）', 5, '软件', 1, 5),
	(27, 'C', '维护时期', '运行时期', '计划时期', '开发时期', '0', '在瀑布模型中，将软件分为若干个时期，软件项目的可行性研究一般归属于（    ）', 5, '瀑布模型', 0, 5),
	(28, 'C', '不适应平台的变动', '不适应算法的变动', '不适应用户需求的变动', '不适应程序语言的变动', '0', '在瀑布模型中，下面（    ）是其突出的缺点', 5, '瀑布模型', 0, 5),
	(29, 'C', '维护时期', '运行时期', '计划时期', '开发时期', '1', '软件工程过程通常包含4种基本活动，下面不属于4 种活动之一的是（    ）', 5, '软件工程', 0, 5),
	(30, 'D', '软件是一种软件产品', '软件产品不会用坏，不存在磨损、消耗问题', '软件产品的生产主要是研制', '软件产品非常便宜', '1', '下面不属于软件的特点的是（    ）', 5, '软件', 0, 5),
	(31, 'B', '记录', '提供源程序', '维护软件', '软件产品介绍', '1', '文档是描述程序、数据和系 统开发以及使用的各种图文资料。下面不是文档的作用的是（    ）', 5, '文档', 0, 5),
	(32, 'A  ', '维护工具', '编码工具', '测试工具', '需求分析工具', '2', '软件开发工具是协助开发人员进行软件开发活动所 使用的软件或环境。下面不是软件开发工具的是（    ）', 5, '软件开发工具', 0, 5),
	(33, 'D  ', '原型模型', '瀑布模型', '螺旋模型', '快速组装模型', '2', '下列叙述中不属于软件生命周期模型的是（    ）', 5, '生命周期', 0, 5),
	(34, 'B  ', '提高易读性', '降低复杂性', '增加内聚性', '降低耦合性', '0', '软件设计中，用抽象和分解的目的是（    ）', 5, '设计', 0, 6),
	(35, 'A  ', '程序、数据和文档', '程序、数据和界面', '数据、文档和界面', '程序、界面 和文档', '0', '软件由3部分组成，它们是（    ）', 5, '软件', 1, 6),
	(36, 'D     ', '详细设计', '软件编码', '概要设计', '软件测试和维护', '0', '软件生命周期中所花费用最多的阶段是', 5, '生命周期', 0, 6),
	(37, 'B  ', '操作系统的资源管理功能', '先进的软件开发工具和环境', '程序人员的数量', '计算机的并行处理能力', '0', '开发软件时对提高开发人员工作效率至关重要的是（    ）', 5, '工作效率', 0, 6),
	(38, 'B  ', '软件投机', '软件危机', '软件工程', '软件产生', '0', '开发软件所需高成本和产品的低质量之间有着尖锐的矛盾，这种现象称作（    ）', 5, '开发', 1, 6),
	(39, 'A  ', '定义、开发、运行维护', '设计阶段、编程 阶段、测试阶段', '总体设计、 详细设计、编程调试', '需求分析、 功能定义、 系统设计', '1', '软件开发的结构化生命周期方法将软件生命周期划分成（    ）', 5, '生命周期', 0, 6),
	(40, 'B  ', '消除软件危机', '软件工程管理', '程序设计自动化', '实现软件可重用', '1', '软件工程的理论和技术性研究的内容主要包括软件 开发技术和（    ）', 5, '内容', 0, 6),
	(41, 'C  ', '程序设计方法学的影响', '软件产业化的需要', '软件危机的出现', '计算机的发展', '1', '软件工程的出现是由于（    ）', 5, '软件工程', 2, 6),
	(42, 'C  ', '概要设计', '详细设计', '可行性分析', '需求分析', '2', '在软件生命周期中，能准确地确定软件系统必须做什 么和必须具备哪些功能的阶段是（    ）', 5, '生命周期', 0, 6),
	(43, 'A  ', '大系统的复杂性', '人员知识不足', '客观世界千变万化', '时间紧、任务重', '2', '开发大型软件时，产生困难的根本原因是（    ）', 5, '开发', 0, 6),
	(44, 'B  ', '接口', '桥梁', '科学', '继续', '0', '文档是软件开发人员、软件管理人员、维护人员、用户以及计算机之间的（    ）， 软件开发人员在各个阶段以文档作为前段工作成果的体现和后段工作的基础。', 5, '文档', 0, 7),
	(45, 'A  ', '较高', '较低', '可靠', '优秀', '0', '软件工程学的目的是以 较低的成本，研制（    ）质量的软件', 5, '质量', 0, 7),
	(46, 'D  ', '可用性', '兼容性', '可靠性', '可移植性', '0', '软件从一个计算机系统或环境转换到另一个计算机和环境的容易程度称为（    ）', 5, '软件', 0, 7),
	(47, 'B ', '可用性', '兼容性', '可靠性', '可移植性', '0', '软件使不同的系统约束条件和用户需求得到满足的容易程度称为（    ）', 5, '软件', 0, 7),
	(48, 'D ', '软件开始使用到用户要求修改为止', '软件开始使用到被淘汰为止', '从开始编写程序到不能再使用为止', '从立项制定计划，进行需求分析到不能再使用为止 ', '0', '软件生存周期是指（    ）阶段', 5, '生存周期', 0, 7),
	(49, 'B ', '引入新技术提高空间利用率', '用较少的投资获得高质量的软件', ' 缩短研制周期', '扩大软件功能 硬软件结合使系统面向应用', '1', '软件工程学是应用科学理 论和工程上的技术指导软件开发的学科，其目的是（    ）', 5, '软件工程', 0, 7),
	(50, 'B  ', '软件可靠性是指软件在给定 的时间间隔内，按用户要求成功运行的概率 ', '软件可靠性是指软件在给定的时间间隔内，按设计要求成功运行的概率', '软件可靠性 是指软件在正式投入运行后，按规格说明书的规定成功运 行的概率 ', '软件可靠性是指软件在给定时间间隔内，按规格说明书的规定成功运行的概率', '1', '下列四个软件可靠性定义中正确的是（    ）', 5, '软件可靠性', 0, 7),
	(51, 'C  ', '选好一种程序设计语言', '显式说明一切变量', '使用三种标准控制语句', '给程序加注释', '1', '提高程序可读性的有力手段是（    ）', 5, '可读性', 0, 7),
	(52, 'C  ', '数据流图', '数据字典', '程序流程图', '判定树', '2', '以下（    ）不是结构化分析方法常用的工具', 5, '结构化分析', 0, 7),
	(53, 'A  ', '经济可行性', '操作可行性', '技术可行性', '社会可行性', '2', '软件可行性分析是着重确 定系统的目标和规模。对成本-效益进行发现应属于下列选项中的（    ）', 5, '可行性分析', 0, 7),
	(54, 'B  ', '数据流', '加工', '数据存储', '数据源点或终点', '0', '在数据流图中，用圆或者椭圆来表示（    ）', 5, '数据流图', 0, 7),
	(55, 'A  ', '需求获取', '阶段性报告', '总结', '都不正确', '0', '软件需求分析阶段的工作，有4个方面，分别为需求评审，需求分析，编写需求规格说明书，以及（    ）', 5, '需求分析', 0, 7),
	(56, 'D  ', '正确性', '无歧义性', '完整性', '不可修改性', '0', '下面不是软件需求规格说 明书的特点的是（    ）', 5, '需求分析', 0, 7),
	(57, 'C  ', '软件开发方法', '软件开发费用', '软件系统功能', '软件开发工具', '0', '需求分析阶段的任务是确定（    ）', 5, '需求分析', 0, 7),
	(58, 'C  ', '可行性研究和需求分析', '问题定义和总体设计 ', '可行性研究和问题定义', '可行性研 究、需求分析和问题定 义', '0', '通常软件生命周期划分为 计划、开发和运行3个时期，下列选项中（    ）工作应属于软件计划期的内容', 5, '生命周期', 0, 7),
	(59, 'D ', '需求分析', '概要设计', '总体设计', '可行性研究', '1', '（    ）的目的就是用最小', 5, '可行性研究', 0, 7),
	(60, 'D  ', '便于用户、分析员和 软件设计人员进行理解及交流', '控制系统的实施过程', '作为软件测试和验收以及维护的依据', '便于软件的 维护', '1', '下列选项中（    ）不是软件需求规格目标', 5, '需求规格', 0, 7),
	(61, 'A  ', '需求规格说明书', '模块设计书', '合同文档', '详细设计说明书', '1', '需求分析是发现、求精、建模的过程，最终产生 （    ）', 5, '需求分析', 0, 7),
	(62, 'A ', '使用顺序、选择和重复（循环） 三种基本控制结构', '模块只有一个入口，可以有多个出口', '注重提高程序的执行效率', '不使用goto 语句', '2', '下面描述中，符合结构化程序设计风格的是（    ）', 5, '结构化程序', 0, 7),
	(63, 'A  ', '控制流', '加工', '数据存储', '源和潭', '2', '数据流图用于抽象描述一个软件的逻辑模型，数据流图由一些特定的图符构成。 下列图符名标识的图符不属于数据流图合法图符的是（    ）', 5, '数据流图', 0, 7),
	(64, 'B  ', '阶段性报告', '需求评审', '总结', '都不正确', '0', '软件需求分析阶段的工作，可以分为四个方面：需求获取、需求分析、编写需求规格说明书以及（    ）', 5, '需求分析', 0, 8),
	(65, 'D  ', 'PAD', 'PFD  ', 'N-S', 'DFD  ', '0', '下列工具中属于需求分析常用工具的是（    ）', 5, '工具', 0, 8),
	(66, 'C  ', '控制程序的执行顺序', '模块之间的调用关系', '数据的流向', '程序的组成 成分', '0', '在数据流图（DFD）中，带 有名字的箭头表示（    ）', 5, '数据流图', 0, 8),
	(67, 'D  ', '程序员', '项目管理者', '软件分析设计人员', '软件用户', '0', '在软件生产过程中，需求信息的给出是（    ）', 5, '生产', 0, 8),
	(68, 'D ', '面向数据流的结构化分析方法', '面向数据结构的Jackson方法', '面向数据结构的结构化数据系统开发方法', '面向对象的分析方法', '0', '下列叙述中，不属于结构化 分析方法的是（    ）', 5, '结构化', 0, 8),
	(69, 'D  ', '概要设计', '详细设计', '可行性分析', '需求分析', '1', '在软件生命周期中，能准确 地确定软件系统必须做什么和必须具备哪些功能的阶段是（    ）', 5, '生命周期', 0, 8),
	(70, 'B  ', '数据流', '数据流图', '数据库', '数据结构', '1', '结构设计是一种应用最广泛的系统设计方法，是以 （    ）为基础、自顶向下、逐步求精和模块化的过程', 5, '结构设计', 0, 8),
	(71, 'A  ', '模块说明书', '框图', '程序', '数据结构', '1', '概要设计的结果是提供一 份（    ）', 5, '概要设计', 0, 8),
	(72, 'B  ', '模块说明书', '软件规格说明书', '项目开发计划', '合同文档', '2', '需求分析是由分析员经了解用户的要求，认真细致地调研、分析，最终应建立目标系统的逻辑模型并写出（    ）', 5, '需求分析', 0, 8),
	(73, 'D  ', '标准化程序设计', '模块化程序设计', '多道程序设计', '结构化程序设计 ', '2', '1960年Dijkstra提倡的（    ）是一种有效的提高程序设计效率的方法，把程 序的基本控制结构限于顺 序、 选择和循环三种，同时避免使用GOTO语句， 这样使程序结构易于理解', 5, '效率', 0, 8),
	(74, 'D  ', '外部特性', '内部特性', '算法和使用数据', '功能和输入输出数据', '0', '概要设计的任务是决定系 统中各个模块的外部特性 ，即其（    ）', 5, '概要设计', 0, 9),
	(75, 'C  ', '外部特性', '内部特性', '算法和使用数据', '功能和输入输出数据', '0', '详细设计的任务是决定每 个模块的内部特性，即模块（    ）', 5, '详细设计', 1, 9),
	(76, 'D  ', 'DFD图', 'PAD图', 'IPO图', '数据字典', '0', '结构化分析方法以数据流图、（    ）和加工说明等描述工具，即用直观的图和简洁的语言来描述软件系统模型', 5, '结构化分析', 1, 9),
	(77, 'B  ', '经济', '技术', '法律', '操作', '0', '在可行性研究阶段，对系统所要求的功能、性能以及限制条件进行分析，确定是否能够构成一个满足要求的系统，这称为（    ）可行性', 5, '可行性研究', 1, 9);
/*!40000 ALTER TABLE `t_singlesel` ENABLE KEYS */;


-- 导出  表 ass.t_singlesel_temp 结构
CREATE TABLE IF NOT EXISTS `t_singlesel_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(16) DEFAULT NULL,
  `choiceA` varchar(64) DEFAULT NULL,
  `choiceB` varchar(64) DEFAULT NULL,
  `choiceC` varchar(64) DEFAULT NULL,
  `choiceD` varchar(64) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `skey1` varchar(16) DEFAULT NULL,
  `teacherId` varchar(255) DEFAULT NULL,
  `sectorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hq81anh7nbp7sukkvaqr9dmsm` (`teacherId`),
  KEY `FK_gjw6qgcbkot3431siqbcy9ij4` (`sectorId`),
  CONSTRAINT `FK_gjw6qgcbkot3431siqbcy9ij4` FOREIGN KEY (`sectorId`) REFERENCES `t_sector` (`id`),
  CONSTRAINT `FK_hq81anh7nbp7sukkvaqr9dmsm` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_singlesel_temp 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `t_singlesel_temp` DISABLE KEYS */;
INSERT INTO `t_singlesel_temp` (`id`, `answer`, `choiceA`, `choiceB`, `choiceC`, `choiceD`, `question`, `skey1`, `teacherId`, `sectorId`) VALUES
	(1, 'B', '对应', '映射', '反映', '反射', '面向对象（Object Oriented）方法是将现实世界的事物以对象的方式（  ）到计算机世界的方法。', '面向对象', 'df4f88ef73ae41989f1b6a44b43db095', 5),
	(2, 'C', '纠错性维护', '适应性维护', '改善性维护', '预防性维护', '根据用户在软件使用过程中提出的建设性意见而进行的维护活动称为（）。', '软件维护', '1', 2),
	(3, 'B', '提高易读性', '降低复杂性 ', '增加内聚性', '降低耦合性', '软件设计中，用抽象和分解的目的是（    ）。 ', '软件设计', '1', 1),
	(4, 'A', '程序、数据和文档', '程序、数据和界面', '数据、文档和界面', '程序、界面 和文档', '软件由3部分组成，它们是（    ）。', '软件组成', 'df4f88ef73ae41989f1b6a44b43db095', 3),
	(5, 'D', '详细设计', '软件编码', '概要设计', '软件测试和维护', '软件生命周期中所花费用最多的阶段是（    ）。', '生命周期', 'df4f88ef73ae41989f1b6a44b43db095', 3),
	(6, ' B', '操作系统的资源管 理功能', '先进的软件开发工具和环境', '程序人员的数量 ', '计算机的并行处理能力', '开发软件时对提高开发人员工作效率至关重要的是（    ）。  ', '软件开发', 'df4f88ef73ae41989f1b6a44b43db095', 2),
	(7, 'B', '软件投机 ', '软件危机 ', '软件工程', '软件产生', '开发软件所需高成本和产品的低质量之间有着尖锐的矛盾，这种现象称作（    ）。  ', '软件开发', 'df4f88ef73ae41989f1b6a44b43db095', 2),
	(8, 'B', '定义、开发、运行维护 ', '设计阶段、编程 阶段、测试阶段', '总体设计、 详细设计、编程调试', '需求分析、 功能定义、 系统设计', '软件开发的结构化生命周期方法将软件生命周期划分成（    ）', '软件开发', '1', 3);
/*!40000 ALTER TABLE `t_singlesel_temp` ENABLE KEYS */;


-- 导出  表 ass.t_student 结构
CREATE TABLE IF NOT EXISTS `t_student` (
  `id` varchar(32) NOT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `pwd` varchar(32) NOT NULL,
  `realName` varchar(32) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `classId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_frxpowmeqnmf3sflhwgq3x0ub` (`classId`),
  CONSTRAINT `FK_frxpowmeqnmf3sflhwgq3x0ub` FOREIGN KEY (`classId`) REFERENCES `t_class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_student 的数据：~38 rows (大约)
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` (`id`, `descs`, `email`, `imgurl`, `pwd`, `realName`, `remark`, `classId`) VALUES
	('201100834201', NULL, NULL, NULL, '201100834201', '伊泽楷', NULL, 3),
	('201100834429', '奋斗', '56666@qq.com', '/temp/img/201506/20150603153646_9253.png', '201100834429', '张威', '快捷键', 1),
	('201106324501', NULL, NULL, NULL, '201106324501', '易晨寰', NULL, 2),
	('201106324502', NULL, NULL, NULL, '201106324502', '魏庆婷', NULL, 2),
	('201106324503', NULL, NULL, NULL, '201106324503', '魏方文', NULL, 2),
	('201106324504', NULL, NULL, NULL, '201106324504', '胡志勇', NULL, 2),
	('201106324505', NULL, NULL, NULL, '201106324505', '朱路路', NULL, 2),
	('201106324506', NULL, NULL, NULL, '201106324506', '杨世君', NULL, 2),
	('201106324507', NULL, NULL, NULL, '201106324507', '许国锋', NULL, 2),
	('201106324508', NULL, NULL, NULL, '201106324508', '武思宇', NULL, 2),
	('201106324509', NULL, NULL, NULL, '201106324509', '汪永成', NULL, 2),
	('201106324510', NULL, NULL, NULL, '201106324510', '刘震', NULL, 2),
	('201106324511', NULL, NULL, NULL, '201106324511', '姜冠', NULL, 2),
	('201106324512', NULL, NULL, NULL, '201106324512', '黄伟阳', NULL, 2),
	('201106324513', NULL, NULL, NULL, '201106324513', '丁文治', NULL, 2),
	('201106324514', NULL, NULL, NULL, '201106324514', '郑磊', NULL, 2),
	('201106324515', NULL, NULL, NULL, '201106324515', '赵倩倩', NULL, 2),
	('201106324516', NULL, NULL, NULL, '201106324516', '张五', NULL, 2),
	('201106324517', NULL, NULL, NULL, '201106324517', '张海洋', NULL, 2),
	('201106324518', NULL, NULL, NULL, '201106324518', '张大东', NULL, 2),
	('201106324519', NULL, NULL, NULL, '201106324519', '游晓波', NULL, 2),
	('201200814101', NULL, NULL, NULL, '201200814101', '朱慧萍', NULL, 1),
	('201200814102', '奋斗', '2888899@qq.com', '/temp/img/201506/20150603025709_7756.png', '201200814102', '姜晓延', 'ABC', 1),
	('201200814103', NULL, NULL, NULL, '201200814103', '李晓亚', NULL, 1),
	('201200814104', NULL, NULL, NULL, '201200814104', '张赛男', NULL, 1),
	('201200814105', NULL, NULL, NULL, '201200814105', '孙文静', NULL, 1),
	('201200814107', NULL, NULL, NULL, '201200814107', '石方莉', NULL, 1),
	('201200814108', NULL, NULL, NULL, '201200814108', '李伟', NULL, 1),
	('201200814109', NULL, NULL, NULL, '201200814109', '王帅强', NULL, 1),
	('201200814111', NULL, NULL, NULL, '201200814111', '李旭航', NULL, 1),
	('201200814112', NULL, NULL, NULL, '201200814112', '谷君毅', NULL, 1),
	('201200814113', NULL, NULL, NULL, '201200814113', '赵海耀', NULL, 1),
	('201200814115', NULL, NULL, NULL, '201200814115', '王鹏宇', NULL, 1),
	('201200814116', NULL, NULL, NULL, '201200814116', '贾春光', NULL, 1),
	('201200814117', NULL, NULL, NULL, '201200814117', '王晓明', NULL, 1),
	('201200814119', NULL, NULL, NULL, '201200814119', '张喜波', NULL, 1),
	('201200814121', NULL, NULL, NULL, '201200814121', '代镇位', NULL, 1),
	('201200814122', NULL, NULL, NULL, '201200814122', '白杨', NULL, 1);
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;


-- 导出  表 ass.t_subjective 结构
CREATE TABLE IF NOT EXISTS `t_subjective` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` longtext,
  `quesDifficult` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT '10',
  `skey1` varchar(255) DEFAULT NULL,
  `useNum` int(11) DEFAULT '0',
  `sectorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_eafbkclhus306iyid3tamxbc5` (`sectorId`),
  CONSTRAINT `FK_eafbkclhus306iyid3tamxbc5` FOREIGN KEY (`sectorId`) REFERENCES `t_sector` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_subjective 的数据：~60 rows (大约)
/*!40000 ALTER TABLE `t_subjective` DISABLE KEYS */;
INSERT INTO `t_subjective` (`id`, `answer`, `quesDifficult`, `question`, `score`, `skey1`, `useNum`, `sectorId`) VALUES
	(1, '结构冗余：包括静态冗余、动态冗余和混合冗余。', '0', '简述容错技术的四种主要手段，并解释。', 10, '容错技术', 0, 1),
	(2, '软件测试是（1）为了发现错误而执行程序的过程；（2）一个好的用例能够发现至今尚未发现的错误的测试。（3）一个成功的测试是发现至今尚未发现的错误的测试。', '0', '以G.J.Myers的观点，简述对软件测试的目的', 10, '软件测试', 0, 1),
	(3, '就程序设计语言的工程特性而言，对程序编码有如下要求：', '1', '就程序设计语言的工程特性而言，对程序编码有哪些要求？', 10, '程序编码', 1, 1),
	(4, '模块的内聚性包括：', '1', '模块的内聚性包括哪些类型？', 10, '内聚性', 1, 1),
	(5, '描述软件开发过程中各种活动如何执行的模型', '2', '软件生存周期模型 ', 10, '生命周期', 0, 1),
	(6, '数据字典是用来定义数据流图中的各个成分的具体含义的。它以一种准确的、无二义性的说明方式为系统的分析、设计及维护提供了有关元素的一致的定义和详细的描述。', '2', '数据字典', 10, '数据字典', 0, 1),
	(7, 'JSP方法是面向数据结构的设计方法，其定义了一组以数据结构为指导的映射过程，它根据输入，输出的数据结构，按一定的规则映射成软件的过程描述，即程序结构。', '0', '简述JSP方法', 10, 'JSP', 0, 2),
	(8, '指相同的操作或函数、过程可作用于多种类型的对象上并获得不同结果。或（不同的对象，收到同一消息可以产生不同的结果。', '0', '简述多态性', 10, '多态性', 0, 2),
	(9, '需求分析的任务是确定待开发的软件系统“做什么”。具体任务包括确定软件系统的功能需求、性能需求和运行环境约束，编制软件需求规格说明书、软件系统的验收测试准则和初步的用户手册', '1', '需求分析的任务是什么？', 10, '需求分析', 0, 2),
	(10, '估算软件项目的成本，作为立项和签合同的依据之一，并在软件开发过程中按计划管理经费的使用', '1', '软件项目的成本管理', 10, '成本管理', 0, 2),
	(11, '在软件开发的早期，快速开发一个目标软件系统的原型，让用户对其进行评价并提出修改意见，然后开发人员根据用户的意见对原型进行改进。', '2', '快速原型技术的基本思想是什么？', 10, '原型设计', 0, 2),
	(12, '1）一致性2）二义性3）紧致性4）局部性', '2', '程序设计语言的有哪些特点？', 10, '程序设计语言', 0, 2),
	(13, '1）3个过程是：软件定义过程、软件开发过程、软件使用与维护过程。', '0', '可将软件生存周期划分为哪3个过程和哪9个阶段', 10, '生命周期', 0, 3),
	(14, '1．机器语言、汇编语言：依赖于机器，面向机器2．高级语言：独立于机器，面向过程或面向对象3．面向问题语言：独立于机器，非过程式语言', '0', '程序设计语言的三种类型和特点是什么？', 10, '程序设计语言', 0, 3),
	(15, '语句覆盖、判定覆盖、 条件覆盖、判定/条件覆盖、条件组合覆盖、 点覆盖、 边覆盖、路径覆盖', '1', '白盒测试主要有哪些覆盖？', 10, '测试', 0, 4),
	(16, '（1）数据流图（date flow diagram , DFD），是SA方法中用于表示系统逻辑模型的一种工具，它以图形的方式描绘数据在系统中流动和处理的过程，由于它只反映系统必须完成的逻辑功能，所以它是一种功能模型，是从数据的角度来描述一个系统的；而流程图则是从对数据加工的角度来描述系统的；\r\n（2）数据流图中的箭头是数据流，而流程图中的箭头则是控制流,它表达的是程序执行的次序；\r\n（3）数据流图适合于宏观地分析一个组织业务概况,而程序流程图只适合于描述系统中某个加工的执行细节。\r\n（4）数据流程图应该重点描述了数据加工的过程，主要是模块内部，数据流图则是描述模块之间的关系', '1', '流程图与数据流图有什么主要区别？', 10, '数据流图', 0, 3),
	(17, '（1）面向过程就是分析出解决问题所需要的步骤，然后用函数把这些步骤一步一步实现，使用的时候一个一个依次调用就可以了。面向对象是把构成问题事务分解成各个对象，建立对象的目的不是为了完成一个步骤，而是为了描叙某个事物在整个解决问题的步骤中的行为。\r\n（2）面向过程是把一件事一项工程分解成为一个个小的功能,用一个个函数来实现. \r\n面向对象是把事情看成是一个个小的对象组成的,或者说一个个小部分组成的,这些对象之间的相互关系,构成了整个项目. 在面向对象的思想中，万物皆对象。而"类"，就是对象的抽象或者说是概括。', '1', '面向对象和面向过程软件工程有哪些区别？', 10, '面向对象', 0, 7),
	(18, '（1）需求分析，建立系统初步的功能模型、业务模型和数据模型 （2）架构设计，建立系统完整的功能模型、业务模型和数据模型  （3）详细设计，将功能模型、业务模型和界面模型中的各个部件加以实现 （4）编程实现，将模型中的各个部件实现文档转化为相应代码', '1', '面向对象的设计步骤为？', 10, '面向对象', 0, 7),
	(19, '软件危机产生的原因有： (1)软件的规模越来越大，结构越来越复杂。 \n(2)软件开发的管理困难。由于软件规模大，结构复杂，又具有无形性，导致管理困难，进度控制困难，质量控制困难，可靠性无法保证。 ', '0', '简述软件危机产生的原因。', 10, '软件危机', 0, 4),
	(20, '需求分析是指开发人员要准确理解用户的要求，进行细致的调查分析，将用户非形式的需求陈述转化为完整的需求定义，再由需求定义转换到相应的形式功能规约(需求规格说明)的过程。需求分析的基本任务是要准确地定义新系统的目标，为了满足用户需要，回答系统必须“做什么”的问题。', '0', '简述需求分析的概念及需求分析的基本任务。', 10, '需求分析', 0, 4),
	(21, '数据流图有两类：变换型数据流图和事务型数据流图。变换型数据流图是由输入、处理和输出三部分组成，因此变化型数据流图是一个顺序结构。事务型数据流图特征：事务处理中心将它的输入流分离成许多发散的数据流，形成许多加工路径，并根据输入的值选择其中一个路径来执行。', '0', '简述数据流图的分类及每一类的特点。', 10, '数据流图', 0, 4),
	(22, '建立对象模型的步骤如下： (1)确定类：标出来自问题域的相关对象类。 \n(2)准备数据字典：为所有建模实体准备一个数据字典，准确描述各对象类的精确含义，描述当前问题中的类的范围，包括对类的成员、用法方面的假设或限制； (3)确定关联：确定二个或多个类之间的相互依赖； (4)确定属性：只考虑与具体应用直接相关的属性 \n(5)使用继承来细化类：使用继承来共享公共结构，以此来重新组织类', '1', '简述建立对象模型的过程。', 10, '对象模型', 0, 4),
	(23, '内容：①软件开发技术②软件开发管理面临的主要问题：①软件费用②软件可靠性', '1', '简述软件工程面临的问题。', 10, '软件工程', 0, 4),
	(24, '技术可行性：对要开发项目的功能、性能、限制条件进行分析，确定在现有的资源条件下，技术风险有多大，项目是否能实现。包括：开发的风险；资源的有效性；技术；开发人员在评估技术可行性时，一旦估计错误，将会出现灾难性后果。经济可行性：包括成本', '2', '简述可行性研究报告包含的主要内容。', 10, '可行性研究', 0, 4),
	(25, '：（1）模块独立性准则，即尽量高内聚，低耦合，保持模块相对的独立性，并以此原则优化软件结构；（2）控制范围与作用范围之间的准则，一个模块的作用范围应在其控制范围之内，且条件判断所在的模块应与受其影响的模块在层次上尽量靠近；（3）软件结构的形态特征准则，软件结构的深度、宽度。扇入、扇出应适当；（4）模块的大小准则，模块的大小最好控制在50~150条语句左右，以便于阅读研究；（5）模块的接口准则，模块的接口要简单。清晰及含义明确，以便于理解、实现、测试和维护。', '0', '简述软件结构设计的优化准则。', 10, '软件结构', 1, 5),
	(26, '快速原型开发步骤可划分下列阶段：', '0', '简述快速原型的开发步骤。', 10, '快速原型', 0, 5),
	(27, '(1)可行性研究和项目开发计划，提交项目开发计划和可行性分析报告； (2)需求分析，提交软件需求说明书； (3)概要设计，提交概要设计说明书； (4)详细设计，提交详细设计说明书； (5)编码，提交源程序清单； (6)测试，提交测试报告； (7)维护，提交维护报告。 ', '0', '软件生存周期可以分为几个阶段，每个阶段的提交物是什么？', 10, '生存周期', 1, 5),
	(28, '结构化分析的描述工具有：（1）数据流图，（2）数据字典，（3）描述加工逻辑的结构化语言、判定表、判定树。\n（1）了解当前系统的工作流程，获取当前系统的物理模型；（2）抽象出当前系统的逻辑模型（3）建立目标系统的逻辑模型；（4）作进一步补充和优化。', '1', '结构化分析的描述工具有哪些？简述结构化分析的步骤。', 10, '结构化分析', 0, 5),
	(29, '黑盒测试法把程序看成一个黑盒子，完全不考虑程序的内部结构和处理过程。黑盒测试是在程序接口进行的测试，它只检查程序功能是否能按照规格说明书的规定正常使用，程序是否能适当地接收输入数据产生正确的输出信息，并且保持外部信息的完整性。黑盒测试又称为功能测试。常用的黑盒测试方法有等价类划分、边界值分析、错误推测、因果图。', '1', '什么是黑盒测试法？常用的黑盒测试方法有哪些？', 10, '黑盒测试', 0, 5),
	(30, '需求分析阶段的基本任务是要准确地定义新系统的目标，为了满足用户需要，回答系统必须“做什么”的问题。本阶段要进行以下几方面的工作： \n(1)问题识别。双方确定对问题的综合需求，这些需求包括：功能需求、性能需求、环境需求、用户界面需求，另外还有可靠性、安全性、保密性、可移植性、可维护性等方面的需求。 (2)分析与综合，导出软件的逻辑模型。分析人员对获取的需求，进行一致性的分析检查，在分析、综合中逐步细化软件功能，划分成各个子功能。这里也包括对数据域进行分解，并分配到各个子功能上，以确定系统的构成及主要成份，并', '2', '需求分析阶段的基本任务是什么', 10, '需求分析', 0, 5),
	(31, '调试的目的是确定错误的原因和位置，并改正错误，因此调试也称为纠错。 调试技术主要有： \n(1)简单的调试方法。主要有在程序中插入打印语句、运行部分程序等。 \n(2)归纳法调试。它从测试结果发现的线索(错误迹象、征兆)入手，分析它们之间的联系，导出错误原因的假设，然后再证明或否定这个假设。 \n(3)演绎法调试。该方法列出所有可能的错误原因的假设，然后利用测试数据排除不适当的假设，最后再测试数据验证余下的假设确实是出错的原因。 ', '0', '调试的目的是什么?调试有哪些技术手段?', 10, '调试', 0, 6),
	(32, '数据流图：简称DFD，是SA方法中用于表示系统逻辑模型的一种工具，它以图形的方式描绘数据在系统中流动和处理的过程，由于它只反映系统必须完成的逻辑功能，所以它是一种功能模型。数据流图有四种基本图形符号：“→”箭头表示数据流；“○”圆或椭圆表示加工；“=”双杠表示数据存储；“□”方框表示数据的源点或终点。', '0', '什么是数据流图?其作用是什么?其中的基本符号各表示什么含义? ', 10, '数据流图', 0, 6),
	(33, '软件文档的作用是：提高软件开发过程的能见度；提高开发效率；作为开发人员阶段工作成果和结束标志；记录开发过程的有关信息便于使用与维护；提供软件运行、维护和培训有关资料；便于用户了解软件功能、性能。软件开发项目生存期各阶段应包括的文档以及与各类人员的关系如下：可行性研究报告、项目开发计划、软件需求说明书、数据要求说明书、测试计划、概要设计说明书、详细设计说明书、用户手册、操作手册、测试分析报告、开发进度月报、项目开发总结、程序维护手册(维护修改建议)。', '0', '请说明软件文档的作用?软件开发项目生存期各阶段都包含哪些文档? ', 10, '软件文档', 0, 6),
	(34, '(1)自顶向下估算方法。估算人员参照以前完成的项目所耗费的总成本(或总工作量)，来推算将要开发的软件的总成本(或总工作量)，然后把它们按阶段、步骤和工作单元进行分配，这种方法称为自顶向下的估算方法。 \n(2)自底向上估算方法。自底向上估算方法是将待开发的软件细分，分别估算每一个子任务所需要的开发工作量，然后将它们加起来，得到软件的总开发量。 \n(3)差别估算方法。差别估算是将开发项目与一个或多个已完成的类似项目进行比较，找出与某个相类似项目的若干不同之处，并估算每个不同之处对成本的影响，导出开发项目的总成', '1', '软件开发成本估算方法有哪几种?', 10, '开发成本', 0, 6),
	(35, '项目开发计划是一个管理性的文档，它的主要内容如下： \n\n\n\n\n\n\n\n\n\n(1)项目概述：说明项目的各项主要工作；说明软件的功能、性能；为完成项目应具备的条件；用户及合同承包者承担的工作、完成期限及其它条件限制；应交付的程序名称，所使用的语言及存储形式；应交付的文档。 \n(2)实施计划：说明任务的划分，各项任务的责任人；说明项目开发进度，按阶段应完成的任务，用图表说明每项任务的开始时间和完成时间；说明项目的预算，各阶段的费用支出预算。 ', '1', '项目开发计划有哪些内容?', 10, '开发计划', 0, 6),
	(36, '白盒测试法的覆盖标准有：语句覆盖、判定覆盖、条件覆盖、判定/条件覆盖、条件组合覆盖、路径覆盖。语句覆盖发现错误能力最弱。判定覆盖包含了语句覆盖，但它可能会使一些条件得不到测试。条件覆盖对每一条件进行单独检查，一般情况它的检错能力较判定覆盖强，但有时达不到判定覆盖的要求。判定/条件覆盖包含了判定覆盖和条件覆盖的要求，但由于计算机系统软件实现方式的限制，实际上不一定达到条件覆盖的标准。条件组合覆盖发现错误能力较强，凡满足其标准的测试用例，也必须满足前四种覆盖标准。 ', '2', '白盒测试法有哪些覆盖标准?试对它们的检错能力进行比较? ', 10, '白盒测试', 0, 6),
	(37, '(1)准备脚本。动态分析从寻找事件开始，然后确定各对象的可能事件的顺序。在分析阶段不考虑算法的执行，算法是实现模型的一部分。  (2)确定事件。确定所有外部事件。事件包括所有来自或发往用户的信息、外部设备的信号、输入、转换和动作。 \n(3)准备事件跟踪表。把脚本表示成一个事件跟踪表，即不同对象间的事件排序表，对象为表中的列，给每一个对象分配一个独立的列。 \n(4)构造状态图。对各对象类建立状态图，反映对象接收和发送的事件，每个事件跟踪都对应于状态图中一条路径。 ', '0', '说明动态建模的过程。', 10, '动态建模', 0, 7),
	(38, '一个CASE工作台是一组工具集，支持像设计、实现或测试等特定的软件开发阶段。工作台工具能通过共享文件、共享仓库或共享数据结构来集成。它能支持大多数的软件过程活动。工作台有： \n（1）程序设计工作台。（2）分析和设计工作台。（3）测试工作台。（4）交叉开发工作台。（5）配置管理(CM)工作台。（6）文档工作台。（7）项目管理工作台。 ', '0', 'CASE工作台有哪些分类?', 10, 'CASE工作台', 0, 7),
	(39, '(1)非结构化维护和结构化维护。软件的开发过程对软件的维护有很大的影响。若不采用软件工程的方法开发软件，则软件只有程序而无文档，维护工作非常困维，这是一种非结构化的维护。若采用软件工程的方法开发软件，则各阶段都有相应的文档，容易进行维护工作，这是一种结构化的维护。 \n(2)维护的困难性。软件维护的困难性是由于软件需求分析和开发方法的缺陷。软件生存周期中的开发阶段没有严格而又科学的管理和规划，就会引起软件运行时的维护困难。', '0', '软件维护的特点是什么? ', 10, '软件维护', 0, 7),
	(40, '(1)设计软件系统结构(简称软件结构)，具体为： \n①采用某种设计方法，将一个复杂的系统按功能划分成模块。 ②确定每个模块的功能。 ③确定模块之间的调用关系。 \n④确定模块之间的接口，即模块之间传递的信息。 ⑤评价模块结构的质量。 \n(2)数据结构及数据库设计，含数据结构的设计及数据库的设计。 \n(3)编写概要设计文档。主要有：①概要设计说明书；②数据库设计说明书；③用户手册；④修订测试计划。 (4)评审。 ', '1', '软件概要设计阶段的基本任务是什么? ', 10, '概要设计', 0, 7),
	(41, '数据字典(简称DD)是用来定义数据流图中的各个成分的具体含义的，它以一种准确的、无二义性的说明方式为系统的分析、设计及维护提供了有关元素的一致的定义和详细的描述。它和数据流图共同构成了系统的逻辑模型，是需求规格说明书的主要组成部分。 数据字典是为分析人员查找数据流图中有关名字的详细定义而服务的，因此也像普通字典一样，要把所有条目按一定的次序排列起来，以便查阅。 ', '1', '什么是数据字典?其作用是什么?它有哪些条目? ', 10, '数据字典', 0, 7),
	(42, '确认测试又称有效性测试。它的任务是检查软件的功能与性能是否与需求规格说明书中确定的指标相符合。 \n确认测试阶段有两项工作：进行确认测试与软件配置审查。 \n(1)确认测试一般是在模拟环境下运用黑盒测试方法，由专门测试人员和用户参加的测试。 (2)软件配置审查的任务是检查软件的所有文档资料的完整性、正确性。如果发现遗漏和错误，应补充和改正。同时要编排好目录，为以后的软件维护工作奠定基础。', '2', '什么是确认测试?该阶段有哪些工作?', 10, '确认测试', 0, 7),
	(43, '(1)按设计任务要求进行常规设计，尽量保证设计的正确。 (2)对可能出现的错误分类，确定实现容错的范围。 \n(3)按照“成本”——“效率”最优原则，选用某种冗余手段来实现对各类错误的屏蔽。 (4)分析或验证上述冗余结构的容错效果。如果效果没有达到预期的程度，则应重新进行冗余结构设计。如此重复，直到有一个满意的结果为止。 ', '0', '简述容错系统的设计过程', 10, '容错系统', 0, 8),
	(44, '系统开发是管理信息系统建设中最重要的一个阶段，从项目开发开始到结束的整个过程，称为系统开发的生命周期。系统开发的生命周期一般分为以下阶段： (1)系统分析 \n这一阶段的主要任务是明确用户的信息需求，提出新系统的逻辑方案。需要进行的工作有系统的初步调查，可行性研究，现行系统的详细调查及新系统逻辑模型的提出等。 \n\n\n\n\n\n\n\n\n\n(2)系统设计 \n这一阶段的主要任务是根据新系统的逻辑方案进行软、硬件系统的设计，其中包括总体结构设计、输出设计、输入设计、处理过程设计、数据存储设计和计算机系统方案的选择等。', '0', '试述系统开发的生命周期。', 10, '生命周期', 0, 8),
	(45, '软件质量保证工作是软件工程管理的重要内容，软件质量保证应做好以下几个方面的工作 ： \n(1)采用技术手段和工具。质量保证活动要贯彻开发过程始终，必须从采用技术手段和工具，尤其是使用软件开发环境来进行软件开发。 \n(2)组织正式技术评审。在软件开发的第一个阶段结束时，都要组织正式的技术评审。国家标准要求单位必须采用审查、文档评审、设计评审、审计和测试等具体手段来保证质量。 (3)加强软件测试。软件测试是质量保证的重要手段，因为测试可发现软件中大多数潜在错误。 \n(4)推选软件工程规范(标准)。用户可以自己制定软件工程规范(标准)，但标准一旦确认就应贯彻执行。 \n(5)对软件的变更进行控制。软件的修改和变更常常会引起潜伏的错误，因此必须严格控制软件的修改和变更。 \n(6)对软件质量进行度量。即对软件质量进行跟踪，及时记录和报告软件质量情况。 ', '0', '如何做好软件质量保证工作?', 10, '软件质量', 0, 8),
	(46, '为了控制因修改而引起的副作用，要做到：(1)按模块把修改分组；(2)自顶向下地安排被修改模块的顺序；(3)每次修改一个模块；(4)对每个修改了的模块，在安排修改下一个模块之前要确定这个修改的副作用，可使用交叉引用表、存储映像表、执行流程跟踪等。', '1', '如何控制因修改而引起的副作用? ', 10, '修改', 0, 8),
	(47, '渐增模型是瀑布模型的变种，它有两类： \n(1)增量构造模型。它在瀑布模型基础上，对一些阶段进行整体开发，对另一些阶段进行增量开发。也就是说在前面的开发阶段按瀑布模型进行整体开发，后面的开发阶段按增量方式开发。 \n(2)演化提交模型。它在瀑布模型基础上，所有阶段都进行增量开发，也就是说不仅是增量开发，也是增量提交。 ', '1', '渐增模型有几种?各有何特点? ', 10, '渐增模型', 0, 8),
	(48, '(1)软件开发环境应是高度集成的一体化的系统。 (2)软件开发环境应具有高度的通用性。 \n(3)软件开发环境应易于定制、裁剪或扩充以符合用户要求，即软件开发环境应具有高度的适应性和灵活性。 \n(4)软件开发环境不但可应用性要好，而且是易使用的、经济高效的系统。  (5)软件开发环境应有辅助开发向半自动开发和自动开发逐步过渡的系统。 ', '2', '对软件开发环境的基本要求有那些? ', 10, '软件开发', 0, 8),
	(49, '一个模块的作用范围(或称影响范围)指受该模块内一个判定影响的所有模块的集合。一个模块的控制范围指模块本身以及其所有下属模块(直接或间接从属于它的模块)的集合。一个模块的作用范围应在其控制范围之内，且判定所在的模块应在其影响的模块在层次上尽量靠近。如果在设计过程中，发现模块作用范围不在其控制范围之内，可以用“上移判点”或“下移受判断影响的模块，将它下移到判断所在模块的控制范围内”的方法加以改进。 ', '0', '什么是模块的影响范围?什么是模块的控制范围?它们之间应该建立什么关系?', 10, '模块', 0, 9),
	(50, '(1)采用方框和箭头等简单的图形符号描述系统的活动和数据流，描述活动所受到的约束条件及实现机制。从侧面清楚地反映了系统的功能。故IDEF0图宜全为正式文档。 (2)采用严格的自顶向下、逐层分解的方式建立系统功能模型。顶层确定系统范围，采用抽象原则，然后有控制地逐步展开有关活动的细节，符合SA方法的分析策略。同时，IDEF0规定每张图至少有3个、最多有6个方框，上界6保证采用层次性描述复杂问题的可理解性，下界3保证分解有意义。 ', '0', 'IDEF0方法有什么特点? ', 10, 'IDEF0方法', 0, 9),
	(51, '软件生存周期的各个阶段有不同的划分。软件规模、种类、开发方式、开发环境以及开发使用方法都影响软件生存周期的划分。在划分软件生存周期阶段时，应遵循的一条基本原则是各阶段的任务应尽可能相对独立，同一阶段各项目任务的性质尽可能相同，从而降低每个阶段任务的复杂程度，简化不同阶段之间的联系，有利于软件项目开发的组织管理。', '0', '在划分软件生存周期阶段时，应遵循的基本原则是什么? ', 10, '生存周期', 0, 9),
	(52, '软件工程是一种用科学知识和技术原理来定义、开发、维护软件的一门学科。软件工程是一门工程性学科，目的是成功地建造一个大型软件系统，所谓成功是要达到以下几个目标：付出较低的的开发成本；达到要求的软件功能；取得较好的软件性能；开发的软件易于移植；需要较低的维护费用；能按时完成开发任务，及时交付使用；开发的软件可靠性高。软件工程研究的主要内容是软件开发技术和软件开发管理两个方面。在软件开发技术中，主要研究软件开发方法、软件开发过程、软件开发工具和环境。', '1', '什么是软件工程?它的目标和内容是什么? ', 10, '软件工程', 0, 9),
	(53, '从实际应用来说，软件质量定义为： (1)与所确定的功能和性能需求的一致性。 (2)与所成文的开发标准一致性。 \n(3)与所有专业开发的软件所期望的隐含特性的一致性。 \n软件的质量保证就是向用户及社会提供满意的高质量的产品，确保软件产品从诞生到消亡为止的所有阶段的质量的活动，即确定、达到和维护需要的软件质量而进行的所有有计划、有系统的管理活动。 ', '1', '软件质量与软件质量保证的含义是什么?', 10, '软件质量', 0, 9),
	(54, '(1)等价类划分。等价类划分是将输入数据域按有效的或无效的(也称合理的或不合理的)划分成若干个等价类，测试每个等价类的代表值就等于对该类其它值的测试。 \n(2)边界值分析。该方法是将测试边界情况作为重点目标，选取正好等于、刚刚大于或刚刚小于边界值的测试数据。 \n(3)错误推测。错误推测法没有确定的步骤，凭经验进行。它的基本思想是列出程序中可能发生错误的情况，根据这些情况选择测试用例。 \n(4)因果图。因果图能有效地检测输入条件的各种组合可能会引起的错误。因果图的基本原理是通过画因果图，把用自然语言描述的功能说明转换为判定表，最后为判定表的每一列设计一个测试用例。 ', '2', '采用黑盒技术设计测试用例有哪几种方法?这些方法各有什么特点?', 10, '黑盒技术', 0, 9),
	(55, 'Gantt图常用水平线段来描述把任务分解成子任务，以及每个子任务的进度安排，动态反映软件开发进度情况。该图可以： (1)表示任务分解成子任务情况。 \n(2)表示每个任务的开始时间和完成时间，线段的长度表示子任务完成所需要的时间。 (3)表示子任务之间的并行和串行关系。 \nGantt图只能表示任务之间的并行与串行的关系，难以反映多个任务之间存在的复杂关系，不能直观表示任务之间相互依赖制约关系，以及哪些任务是关键子任务等信息，因此仅仅用Gantt图作为进度的安排是不够的。 ', '0', '简述Gantt图的功能及不足。', 10, 'Gantt图', 0, 10),
	(56, '根据原型的不同作用，有三类原型模型： \n(1)探索型原型。这种类型的原型模型是把原型用于开发的需求分析阶段，目的是要弄清用户的需求，确定所期望的特性，并探索各种方案的可行性。它主要针对开发目标模糊，用户与开发者对项目都缺乏经验的情况，通过对原型的开发来明确用户的需求。 \n(2)实验型原型。这种原型主要用于设计阶段，考核实现方案是否合适，能否实现。对于一个大型系统，若对设计方案心中没有把握时，可通过这种原型来证实设计方案的正确性。 (3)演化型原型。这种原型主要用于极早向用户提交一个原型系统，该原型系统或者包含系统的框或者包含系统的主要功能，在得到用户的认可后，将原型系统不断扩充演变为最终的软件系统。它将原型的思想扩展到软件开发的全过程。', '0', '快速原型模型有几种?各有何特点?', 10, '快速原型', 0, 10),
	(57, '详细设计是软件设计的第二阶段，其基本任务有： (1)为每个模块进行详细的算法设计。 (2)为模块内的数据结构进行设计。 \n(3)对数据库进行物理设计，即确定数据库的物理结构。 \n(4)其它设计。根据软件系统类型，还可能要进行代码设计、输入/输出格式设计、人机对话设计。 \n(5)编写详细设计说明书。 (6)评审。 \n详细描述处理过程常用三种工具：图形、表格和语言。如结构化程序流程图、盒图和问题分析图。IPO图也是详细设计的主要工具之一。表格工具如判定表可作为详细设计中描述逻辑条件复杂的算法。过程设计语言(PDL)是一种用于描述模块算法设计和处理细节的语言工具。', '0', '详细设计的基本任务是什么?有哪几种描述方法? ', 10, '详细设计', 0, 10),
	(58, 'CASE是一组工具和方法的集合，可以辅助软件开发生命周期各阶段进行软件开发。从学术研究角度讲，CASE是多年来在软件开发管理、软件开发方法、软件开发环境和软件工具等方面研究和发展的产物。CASE把软件开发技术、软件工具和软件开发方法集成到一个统一而一致的框架中，并且吸引了CAD(计算机辅助设计)、软件工程、操作系统、数据库、网络和许多其它计算机领域的原理和技术。因而，CASE领域是一个应用集成和综合的领域。从产业角度讲，CASE是种类繁多的软件开发和系统集成的产品及软件工具的集合。 CASE分类 \n(1)CASE技术种类 \nCASE系统所涉及到的技术有两类：一类是支持软件开发过程本身的技术；另一类是支持软件开发过程管理的技术。从CASE系统产生方式来看，还有一种特殊的CASE技术，即元——CASE技术。它是生成CASE系统的生成器所采用的技术。该生成器可用来创建支持软件开发过程活动及过程管理的CASE系统。 (2)CASE工具的分类 \n对CASE工具分类的标准可分为： \n①功能。功能是对软件进行分类的最常用的标准。 \n②支持的过程。根据支持的过程，工具可分为设计工具、编程工具、维护工具等。 \n③支持的范围。根据支持的范围，可分为窄支持、较宽支持和一般支持工具。窄支持指支持过程中特定的任务，较宽支持是指支持特定过程阶段；一般支持是指支持覆盖软件过程的全部阶段或大多数阶段。 \n1993年,Fuggetta根据CASE系统对软件过程的支持范围，提出CASE系统可分为三类： ①支持单个过程任务的工具。工具可能是通用的，或者也可能归组到工作台。 \n②工作台支持某一过程所有活动或某些活动。它们一般以或多或少的集成度组成工具集。 ③环境支持软件过程所有活动或至少大部分。它们一般包括几个不同的工作台，将这些工作台以某种方式集成起来。 ', '1', '什么是CASE?CASE工具有哪些分类?', 10, 'CASE工具', 0, 10),
	(59, '归纳容错软件的定义，有以下四种： \n(1)规定功能的软件，在一定程度上对自身错误的作用(软件错误)具有屏蔽能力，则称此软件为具有容错功能的软件，即容错软件。 (2)规定功能的软件，在一定程序上能从错误状态自动恢复到正常状态，则称之为容错软件。 (3)规定功能的软件，在因错误而发生错误时，仍然能在一定程度上完成预期的功能，则把\n\n\n\n\n\n\n\n\n\n该软件称为容错软件。 \n(4)规定功能的软件，在一定程度上具有容错能力，则称之为容错软件。 实现容错技术的主要手段是冗余。通常冗余技术分为四类。 \n(1)结构冗余。结构冗余是通常用的冗余技术。按其工作方式，它分为静态、动态和混合冗余三种。 \n(2)信息冗余。为检查或纠正信息在运算或传输中的错误须外加一部分信息，这种现象称为信息冗余。 \n(3)时间冗余。是指以重复执行指令(指令复执)或程序(程序复算)来消除瞬时错误带来的影响。 \n(4)冗余附加技术。是指为实现上述冗余技术所需的资源和技术。包括程序、指令、数据、存放和调动它们的空间和通道等。 ', '1', '说明容错软件的定义与容错的一般方法。', 10, '容错软件', 0, 10),
	(60, '(1)易将某个工具加入到开放式工作台中，还可以用新的工具取代已有的工具。 (2)可以由一个配置管理系统来管理由工具输出的文件。 (3)能不断增强工作台的功能，不断发展工作台。 \n(4)工作台不依赖于某个供应商，而能从不同销售商处购买工具。如果一个工具开发商不提供支持了，最多只影响该工作台的一部分工具，其余的工具还可以继续使用。', '2', '开放式工作台有什么优点? ', 10, '开放式工作台', 0, 10);
/*!40000 ALTER TABLE `t_subjective` ENABLE KEYS */;


-- 导出  表 ass.t_subjective_temp 结构
CREATE TABLE IF NOT EXISTS `t_subjective_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` longtext,
  `question` varchar(255) DEFAULT NULL,
  `skey1` varchar(255) DEFAULT NULL,
  `teacherId` varchar(255) DEFAULT NULL,
  `sectorId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6cl3rbcp1wnny6hbcbcmn6kqi` (`teacherId`),
  KEY `FK_o5g314p0kjek4rob7e1vik928` (`sectorId`),
  CONSTRAINT `FK_6cl3rbcp1wnny6hbcbcmn6kqi` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`),
  CONSTRAINT `FK_o5g314p0kjek4rob7e1vik928` FOREIGN KEY (`sectorId`) REFERENCES `t_sector` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_subjective_temp 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `t_subjective_temp` DISABLE KEYS */;
INSERT INTO `t_subjective_temp` (`id`, `answer`, `question`, `skey1`, `teacherId`, `sectorId`) VALUES
	(2, '需求分析的基本任务是深入描述软件的功能和性能、确定软件设计的约束和软件同其它系统元素的接口细节、定义软件的其它有效性需求，总之，需求分析的任务就是借助于当前系统的逻辑模型导出目标系统的逻辑模型，解决目标系统的 “做什么” 的问题。\r\n 主要步骤：\r\n 1.问题识别\r\n    (1) 功能需求：明确所开发的软件必须具备什么样的功能。\r\n    (2) 性能需求：明确待开发的软件的技术性能指标。\r\n    (3) 环境需求：明确软件运行时所需要的软、硬件的要求。\r\n    (4) 用户界面需求：明确人机交互方式、输入输出数据格式。\r\n  2. 分析与综合，导出软件的逻辑模型\r\n    分析人员对获取的需求，进行一致性的分析检查，在分析、综合中逐步细化软件功能，划分成各个子功能。用图文结合的形式，建立起新系统的逻辑模型。\r\n  3. 编写文档\r\n    (1) 编写“需求规格说明书”，把双方共同的理解与分析结果用规范的方式描述出来，作为今后各项工作的基础。\r\n    (2) 编写初步用户使用手册，着重反映被开发软件的用户功能界面和用户使用的具体要求，用户手册能强制分析人员从用户使用的观点考虑软件。\r\n    (3) 编写确认测试计划，作为今后确认和验收的依据。\r\n    (4) 修改完善软件开发计划。在需求分析阶段对待开发的系统有了更进一步的了解，所以能更准确地估计开发成本、进度及资源要求，因此对原计划要进行适当修正。', '软件需求分析的任务是什么？有哪些主要步骤？', '软件需求', '1', 3),
	(3, '结构化分析方法适合于数据处理类型软件的需求分析。\r\n其要点是"自顶向下" 地开发系统,由整体到各组成部分,由表及里,由抽象到具体,逐步求精.\r\n(1)        模块化\r\n(2)由顶向下,逐步求精.\r\n(3)上层模块分解为下层模块,有三种不同的结构形式,即顺序结构,选择结构和循环结构.', '简述结构化分析、设计的要点：', '结构化', 'df4f88ef73ae41989f1b6a44b43db095', 3),
	(4, '数据字典通常包括数据项、数据结构、数据流、数据存储和处理过程五个部分.\r\n据字典内容包括： \r\n数据库中所有模式对象的信息，如表、视图、簇、及索引等。 \r\n分配多少空间，当前使用了多少空间等。 \r\n列的缺省值。 \r\n约束信息的完整性。 \r\n用户的名字。 \r\n用户及角色被授予的权限。 \r\n用户访问或使用的审计信息。 \r\n其它产生的数据库信息。', '数据字典包含哪些主要内容？', '数据字典', 'df4f88ef73ae41989f1b6a44b43db095', 3),
	(5, '软件测试的目标：\r\n        (1)测试是为了发现程序中的错误而执行程序的过程；\r\n        (2)好的测试方案是极可能发现迄今为止尚未发现的错误的测试方案；\r\n        (3)成功的测试是发现了至今为止尚未发现的错误的测试。\r\n软件测试的方法有动态测试、静态测试和正确性证明3种', '软件测试的目标是什么，有哪几种主要有测试方法？', '软件测试', '1', 4),
	(7, '为了使程序容易测试和维护以减少生命周期的总成本，选用的高级语言应该有理想的模块化机制，以及可读性好的控制结构和数据结构；为了便于调试和提高软件可靠性，语言特点应该使编译程序能够尽可能多地发现程序中的错误；为了降低软件开发和维护的成本，选用的语言应该有良好的独立编译机制。上述这些要求是选择语言的理想标准，但是在实际选用语言时不能仅仅考虑理论上的标准，还必须同时考虑实用方面的各种限制。\r\n    (1)系统用户的要求\r\n     (2)可以使用的编译程序\r\n     (3)可以得到的软件工具\r\n     (4)系统规模\r\n    (5)程序员的知识\r\n    (6)软件可移植性要求\r\n    (7)软件的应用领域', '选择一种程序设计语言的主要有哪些依据？', '程序设计', 'df4f88ef73ae41989f1b6a44b43db095', 4),
	(8, '纠正在使用过程中暴露出来的错误而进行的改进性维护，适应外部环境的变化而进行的适应性维护，改进原有的软件而进行的完善性维护，以及改进将来的可维护性和可靠性而进行的预防性维护。\r\n软件维护的分类：\r\n软件维护主要划分为纠错性维护、适应性维护和完善性维护。\r\n　　(1)纠错性维护。由于前期的测试不可能揭露软件系统中所有潜在的错误，用户在使用软件时仍将会遇到错误，诊断和改正这些错误的过程称为纠错性维护。\r\n　　(2)适应性维护。由于新的硬件设备不断推出，操作系统和编译系统也不断地升级，为了使软件能适应新的环境而引起的程序修改和扩充活动称为适应性维护。\r\n　　(3)完善性维护。在软件的正常使用过程中，用户还会不断地提出新的需求。为了满足用户新的需求而增加软件功能的活动称为完善性维护。', '软件的维护的目标是什么，有哪几种维护类型？', '软件维护', 'df4f88ef73ae41989f1b6a44b43db095', 5),
	(9, '复审：是在软件生命周期每个阶段结束之前，都采用一定的标准对该段产生的软件配置成分进行严格的正式或非正式的检测。\r\n复查：是检查已有的材料，以断定在软件生命周期某个阶段的工作是否能够开始或继续。\r\n管理复审：是向开发组织或使用部门的管理人员提供有关项目的总体状况、成本和进度等方面的情况，以便他们从管理角度对开发工作进行审查。\r\n测试：包括测试计划、测试过程和测试结果3个阶段。', '简述提高软件质量的主要措施。', '软件质量', '1', 6),
	(10, '因为对象是由数据及可以对这些数据施加的操作所组成的统一体，而且对象是以数据为中心的，操作围绕对其数据所需做的处理来设置，没有无关的操作。因此，对象内部各种元素彼此结合得很紧密。内聚性相当强，由于完成对象所需要的元素（数据和方法）基本上都被封装在对象内部，它与外界的联系自然就比较少。因此，对象之间的耦合通常比较松。总之，面向对象使用对象、类、继承和消息的方法，既使用类和继承等机制，而且对象之间仅能通过传递消息实现彼此通信来实现模块的独立性。', '面向对象如何实现模块独立性，其偶合和内聚的含义是什么？', '面向对象', 'df4f88ef73ae41989f1b6a44b43db095', 6),
	(12, '（1)对象是人们要进行研究的任何事物，从最简单的整数到复杂的飞机等均可看作对象，它不仅能表示具体的事物，还能表示抽象的规则、计划或事件。\r\n（2）类是具有相同或相似性质的对象的抽象。对象的抽象是类，类的具体化就是对象，也可以说类的实例是对象。类具有属性，它是对象的状态的抽象，用数据结构来描述类的属性。 类具有操作，它是对象的行为的抽象，用操作名和实现该操作的方法来描述。\r\n(3)对象之间进行通信的结构叫做消息。在对象的操作中，当一个消息发送给某个对象时，消息包含接收对象去执行某种操作的信息。发送一条消息至少要包括说明接受消息的对象名、发送给该对象的消息名（即对象名、方法名）。一般还要对参数加以说明，参数可以是认识该消息的对象所知道的变量名，或者是所有对象都知道的全局变量名。\r\n（4）类中操作的实现过程叫做方法，一个方法有方法名、参数、方法体。', '简述对象、类、消息、方法的基本概念。', '面向对象', 'df4f88ef73ae41989f1b6a44b43db095', 7);
/*!40000 ALTER TABLE `t_subjective_temp` ENABLE KEYS */;


-- 导出  表 ass.t_teacher 结构
CREATE TABLE IF NOT EXISTS `t_teacher` (
  `id` varchar(255) NOT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `isAdmin` int(11) DEFAULT NULL,
  `lastLogDate` varchar(32) DEFAULT NULL,
  `realName` varchar(32) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `tname` varchar(16) NOT NULL,
  `tpwd` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_teacher 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `t_teacher` DISABLE KEYS */;
INSERT INTO `t_teacher` (`id`, `descs`, `email`, `imgurl`, `isAdmin`, `lastLogDate`, `realName`, `remark`, `tname`, `tpwd`) VALUES
	('1', NULL, NULL, NULL, 1, NULL, NULL, NULL, 'admin', '123456'),
	('5e19596c794048ddad43b36799a7f53f', '', '', '', 0, NULL, '于孟博', '', '于孟博', '123456'),
	('b1d3fb359f8f4c7e9093ac3db0d70f45', '', '', '', 0, NULL, '杨恒', '', 'yangh', '123456'),
	('df4f88ef73ae41989f1b6a44b43db095', '', '', '', 0, NULL, '杨恒', '', 'yang', '123456');
/*!40000 ALTER TABLE `t_teacher` ENABLE KEYS */;


-- 导出  表 ass.t_testanswer 结构
CREATE TABLE IF NOT EXISTS `t_testanswer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `judgeAnswers` varchar(255) DEFAULT NULL,
  `singleAnswers` varchar(255) DEFAULT NULL,
  `subjectAnswers` longtext,
  `fillAnswers` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_testanswer 的数据：~13 rows (大约)
/*!40000 ALTER TABLE `t_testanswer` DISABLE KEYS */;
INSERT INTO `t_testanswer` (`id`, `judgeAnswers`, `singleAnswers`, `subjectAnswers`, `fillAnswers`) VALUES
	(1, '0,0,0,0,0,0,1,0,1', 'A,D,C,A,A,D,B,B,D', '结构冗余：包括静态冗余、动态冗余和混合冗余。////1．机器语言、汇编语言：依赖于机器，面向机器2．高级语言：独立于机器，面向过程或面向对象3．面向问题语言：独立于机器，非过程式语言////指相同的操作或函数、过程可作用于多种类型的对象上并获得不同结果。或（不同的对象，收到同一消息可以产生不同的结果。////就程序设计语言的工程特性而言，对程序编码有如下要求：////（1）数据流图（date flow diagram , DFD），是SA方法中用于表示系统逻辑模型的一种工具，它以图形的方式描绘数据在系统中流动和处理的过程，由于它只反映系统必须完成的逻辑功能，所以它是一种功能模型，是从数据的角度来描述一个系统的；而流程图则是从对数据加工的角度来描述系统的；\r\n（2）数据流图中的箭头是数据流，而流程图中的箭头则是控制流,它表达的是程序执行的次序；\r\n（3）数据流图适合于宏观地分析一个组织业务概况,而程序流程图只适合于描述系统中某个加工的执行细节。\r\n（4）数据流程图应该重点描述了数据加工的过程，主要是模块内部，数据流图则是描述模块之间的关系////模块的内聚性包括：////1）一致性2）二义性3）紧致性4）局部性////数据字典是用来定义数据流图中的各个成分的具体含义的。它以一种准确的、无二义性的说明方式为系统的分析、设计及维护提供了有关元素的一致的定义和详细的描述。////描述软件开发过程中各种活动如何执行的模型', NULL),
	(2, '0,0,0,0,0,0,1,1,1', 'D,C,A,D,C,D,B,C,B', 'JSP方法是面向数据结构的设计方法，其定义了一组以数据结构为指导的映射过程，它根据输入，输出的数据结构，按一定的规则映射成软件的过程描述，即程序结构。////1．机器语言、汇编语言：依赖于机器，面向机器2．高级语言：独立于机器，面向过程或面向对象3．面向问题语言：独立于机器，非过程式语言////指相同的操作或函数、过程可作用于多种类型的对象上并获得不同结果。或（不同的对象，收到同一消息可以产生不同的结果。////估算软件项目的成本，作为立项和签合同的依据之一，并在软件开发过程中按计划管理经费的使用////（1）数据流图（date flow diagram , DFD），是SA方法中用于表示系统逻辑模型的一种工具，它以图形的方式描绘数据在系统中流动和处理的过程，由于它只反映系统必须完成的逻辑功能，所以它是一种功能模型，是从数据的角度来描述一个系统的；而流程图则是从对数据加工的角度来描述系统的；\r\n（2）数据流图中的箭头是数据流，而流程图中的箭头则是控制流,它表达的是程序执行的次序；\r\n（3）数据流图适合于宏观地分析一个组织业务概况,而程序流程图只适合于描述系统中某个加工的执行细节。\r\n（4）数据流程图应该重点描述了数据加工的过程，主要是模块内部，数据流图则是描述模块之间的关系////模块的内聚性包括：////1）一致性2）二义性3）紧致性4）局部性////在软件开发的早期，快速开发一个目标软件系统的原型，让用户对其进行评价并提出修改意见，然后开发人员根据用户的意见对原型进行改进。////数据字典是用来定义数据流图中的各个成分的具体含义的。它以一种准确的、无二义性的说明方式为系统的分析、设计及维护提供了有关元素的一致的定义和详细的描述。', NULL),
	(3, '0,0,0,0,0,0,1,0,1', 'B,B,A,A,D,A,D,B,B', '软件测试是（1）为了发现错误而执行程序的过程；（2）一个好的用例能够发现至今尚未发现的错误的测试。（3）一个成功的测试是发现至今尚未发现的错误的测试。////1）3个过程是：软件定义过程、软件开发过程、软件使用与维护过程。////1．机器语言、汇编语言：依赖于机器，面向机器2．高级语言：独立于机器，面向过程或面向对象3．面向问题语言：独立于机器，非过程式语言////需求分析的任务是确定待开发的软件系统“做什么”。具体任务包括确定软件系统的功能需求、性能需求和运行环境约束，编制软件需求规格说明书、软件系统的验收测试准则和初步的用户手册////（1）数据流图（date flow diagram , DFD），是SA方法中用于表示系统逻辑模型的一种工具，它以图形的方式描绘数据在系统中流动和处理的过程，由于它只反映系统必须完成的逻辑功能，所以它是一种功能模型，是从数据的角度来描述一个系统的；而流程图则是从对数据加工的角度来描述系统的；\r\n（2）数据流图中的箭头是数据流，而流程图中的箭头则是控制流,它表达的是程序执行的次序；\r\n（3）数据流图适合于宏观地分析一个组织业务概况,而程序流程图只适合于描述系统中某个加工的执行细节。\r\n（4）数据流程图应该重点描述了数据加工的过程，主要是模块内部，数据流图则是描述模块之间的关系////模块的内聚性包括：////在软件开发的早期，快速开发一个目标软件系统的原型，让用户对其进行评价并提出修改意见，然后开发人员根据用户的意见对原型进行改进。////数据字典是用来定义数据流图中的各个成分的具体含义的。它以一种准确的、无二义性的说明方式为系统的分析、设计及维护提供了有关元素的一致的定义和详细的描述。////描述软件开发过程中各种活动如何执行的模型', NULL),
	(4, '0,0,0,0,0,0', 'A,B,C,C,D,C', '软件测试是（1）为了发现错误而执行程序的过程；（2）一个好的用例能够发现至今尚未发现的错误的测试。（3）一个成功的测试是发现至今尚未发现的错误的测试。', NULL),
	(5, '0,0,0,0,0,1', 'C,D,B,D,C,B', '结构冗余：包括静态冗余、动态冗余和混合冗余。', NULL),
	(6, '0,0,0,0,0,0,1', 'B,D,B,D,D,B', '结构冗余：包括静态冗余、动态冗余和混合冗余。', NULL),
	(7, '0,0,0,0,0,1', 'A,C,C,D,A,B', 'JSP方法是面向数据结构的设计方法，其定义了一组以数据结构为指导的映射过程，它根据输入，输出的数据结构，按一定的规则映射成软件的过程描述，即程序结构。', NULL),
	(8, '0,0,0,1,0,1', 'B,C,A,D,A,B', 'JSP方法是面向数据结构的设计方法，其定义了一组以数据结构为指导的映射过程，它根据输入，输出的数据结构，按一定的规则映射成软件的过程描述，即程序结构。', NULL),
	(9, '0,0,0,0,0,1', 'D,C,B,C,A,B', 'JSP方法是面向数据结构的设计方法，其定义了一组以数据结构为指导的映射过程，它根据输入，输出的数据结构，按一定的规则映射成软件的过程描述，即程序结构。', NULL),
	(13, '0,0,0,0,0,1', 'B,C,D,D,D,D', 'JSP方法是面向数据结构的设计方法，其定义了一组以数据结构为指导的映射过程，它根据输入，输出的数据结构，按一定的规则映射成软件的过程描述，即程序结构。', NULL),
	(19, '0,0,1,0,1,1', 'C,B,A  ,C  ,D,B', '(1)可行性研究和项目开发计划，提交项目开发计划和可行性分析报告； (2)需求分析，提交软件需求说明书； (3)概要设计，提交概要设计说明书； (4)详细设计，提交详细设计说明书； (5)编码，提交源程序清单； (6)测试，提交测试报告； (7)维护，提交维护报告。 ', NULL),
	(20, '0,1,0,1,0,0,0,0', 'B,C,B  ,D,C  ,D', '：（1）模块独立性准则，即尽量高内聚，低耦合，保持模块相对的独立性，并以此原则优化软件结构；（2）控制范围与作用范围之间的准则，一个模块的作用范围应在其控制范围之内，且条件判断所在的模块应与受其影响的模块在层次上尽量靠近；（3）软件结构的形态特征准则，软件结构的深度、宽度。扇入、扇出应适当；（4）模块的大小准则，模块的大小最好控制在50~150条语句左右，以便于阅读研究；（5）模块的接口准则，模块的接口要简单。清晰及含义明确，以便于理解、实现、测试和维护。', NULL),
	(21, '0,0,1,0', 'C,D', '就程序设计语言的工程特性而言，对程序编码有如下要求：,模块的内聚性包括：', NULL);
/*!40000 ALTER TABLE `t_testanswer` ENABLE KEYS */;


-- 导出  表 ass.t_testanswerinfo 结构
CREATE TABLE IF NOT EXISTS `t_testanswerinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `info` int(11) NOT NULL,
  `judgeAnswers` varchar(255) DEFAULT NULL,
  `singleAnswers` varchar(255) DEFAULT NULL,
  `subjectAnswers` longtext,
  `paperId` int(11) DEFAULT NULL,
  `studentId` varchar(32) DEFAULT NULL,
  `unitId` int(11) DEFAULT NULL,
  `fillAnswers` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9hg974uag02s54x760cv2qr2q` (`paperId`),
  KEY `FK_d1qme73tvkmh5vjykt2pbhc1w` (`studentId`),
  KEY `FK_4cd6wgyekfv3lb8vpe5my4lyr` (`unitId`),
  CONSTRAINT `FK_4cd6wgyekfv3lb8vpe5my4lyr` FOREIGN KEY (`unitId`) REFERENCES `t_unit` (`id`),
  CONSTRAINT `FK_9hg974uag02s54x760cv2qr2q` FOREIGN KEY (`paperId`) REFERENCES `t_testpaper` (`id`),
  CONSTRAINT `FK_d1qme73tvkmh5vjykt2pbhc1w` FOREIGN KEY (`studentId`) REFERENCES `t_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_testanswerinfo 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `t_testanswerinfo` DISABLE KEYS */;
INSERT INTO `t_testanswerinfo` (`id`, `createDate`, `info`, `judgeAnswers`, `singleAnswers`, `subjectAnswers`, `paperId`, `studentId`, `unitId`, `fillAnswers`) VALUES
	(1, '2015-06-03 09:39:03', 50, '0,0,0,1,0,1,1,0,0', 'B,B,C,B,B,C,D,D,C', '////////////////////////////////', 5, '201200814101', 1, NULL),
	(2, '2015-06-03 09:51:49', 71, '0,0,0,0', 'B,B,C', '软件周期什么', 1, '201200814101', 2, NULL),
	(3, '2015-06-08 12:16:22', 33, '0,1,0,1,0,0,0,0,0', 'D,C,C,C,C,B,C,C,B', '////////////////////////////////', 5, '201100834429', 1, NULL);
/*!40000 ALTER TABLE `t_testanswerinfo` ENABLE KEYS */;


-- 导出  表 ass.t_testpaper 结构
CREATE TABLE IF NOT EXISTS `t_testpaper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answerDate` datetime DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `isRelease` int(11) NOT NULL,
  `pagerName` varchar(255) DEFAULT NULL,
  `teacherId` varchar(255) DEFAULT NULL,
  `answerId` int(11) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kujf9hxvxwnlsqywjqf2vifsu` (`teacherId`),
  KEY `FK_ab95dxdhx2f3yloryn75fue21` (`answerId`),
  KEY `FK_ls8p1vdijha8u4901muo3gwm1` (`unit_id`),
  CONSTRAINT `FK_ab95dxdhx2f3yloryn75fue21` FOREIGN KEY (`answerId`) REFERENCES `t_testanswer` (`id`),
  CONSTRAINT `FK_kujf9hxvxwnlsqywjqf2vifsu` FOREIGN KEY (`teacherId`) REFERENCES `t_teacher` (`id`),
  CONSTRAINT `FK_ls8p1vdijha8u4901muo3gwm1` FOREIGN KEY (`unit_id`) REFERENCES `t_unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_testpaper 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `t_testpaper` DISABLE KEYS */;
INSERT INTO `t_testpaper` (`id`, `answerDate`, `createDate`, `isRelease`, `pagerName`, `teacherId`, `answerId`, `unit_id`) VALUES
	(1, NULL, '2015-05-28 13:38:05', 1, '第二次人工组卷信息', 'df4f88ef73ae41989f1b6a44b43db095', 1, 2),
	(2, NULL, '2015-05-30 16:26:52', 0, '第一次人工组卷信息', 'df4f88ef73ae41989f1b6a44b43db095', 2, 2),
	(4, NULL, '2015-05-31 03:11:46', 0, '第二次自动组卷测试（希望能插入中间表）', 'df4f88ef73ae41989f1b6a44b43db095', 2, 1),
	(5, NULL, '2015-05-31 03:15:53', 1, '第三次自动组卷测试（希望能插入中间表）', 'df4f88ef73ae41989f1b6a44b43db095', 3, 1),
	(6, NULL, '2015-05-31 03:18:18', 1, '第三次自动组卷测试（希望能插入中间表）返回数据', 'df4f88ef73ae41989f1b6a44b43db095', 4, 1),
	(7, NULL, '2015-05-31 03:52:30', 0, '第四次自动组卷', 'df4f88ef73ae41989f1b6a44b43db095', 5, 1),
	(8, NULL, '2015-06-02 08:48:14', 0, '自动组卷第五次测试结果', 'df4f88ef73ae41989f1b6a44b43db095', 6, 1),
	(9, NULL, '2015-06-02 09:35:06', 0, '第六次自动组卷 第一单元前两章', 'df4f88ef73ae41989f1b6a44b43db095', 7, 1),
	(10, NULL, '2015-06-02 09:53:15', 0, '第七次自动组卷 测试版', 'df4f88ef73ae41989f1b6a44b43db095', 8, 1),
	(11, NULL, '2015-06-02 11:48:52', 0, '自动组卷系统测试', 'df4f88ef73ae41989f1b6a44b43db095', 9, 1),
	(15, NULL, '2015-06-02 22:48:57', 0, '的撒发生的发生的飞洒地方的萨芬', '5e19596c794048ddad43b36799a7f53f', 13, 1),
	(21, NULL, '2015-06-03 09:09:01', 1, '第一单元前两章试题信息', '1', 19, 1),
	(22, NULL, '2015-06-03 09:11:22', 0, '第一单元前两章测试版 完成试卷实现跳转', '1', 20, 1),
	(23, NULL, '2015-06-03 13:58:33', 1, '人工组卷测试版', '1', 21, NULL);
/*!40000 ALTER TABLE `t_testpaper` ENABLE KEYS */;


-- 导出  表 ass.t_unit 结构
CREATE TABLE IF NOT EXISTS `t_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unitName` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_unit 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `t_unit` DISABLE KEYS */;
INSERT INTO `t_unit` (`id`, `unitName`) VALUES
	(1, '项目计划'),
	(2, '需求工程'),
	(3, '系统设计'),
	(4, '代码审查');
/*!40000 ALTER TABLE `t_unit` ENABLE KEYS */;


-- 导出  表 ass.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ass.t_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
