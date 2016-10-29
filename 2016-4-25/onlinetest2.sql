/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : onlinetest2

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-04-25 00:22:21
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of academydepartment
-- ----------------------------
INSERT INTO `academydepartment` VALUES ('1', '001', '政教学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('2', '002', '经管学院', null, null, '0');
INSERT INTO `academydepartment` VALUES ('3', '001', '政教学院', '001MP', '马克思主义哲学', '0');
INSERT INTO `academydepartment` VALUES ('4', '001', '政教学院', '001CP', '中国哲学', '0');
INSERT INTO `academydepartment` VALUES ('5', '002', '经管学院', '002EC', '经济学', '0');
INSERT INTO `academydepartment` VALUES ('6', '002', '经管学院', '002FI', '金融证券', '0');
INSERT INTO `academydepartment` VALUES ('7', '003', '计算机学院', null, null, '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('26', '00000001', '01', '第1章', '0');
INSERT INTO `chapter` VALUES ('27', '00000001', '02', '第2章', '0');
INSERT INTO `chapter` VALUES ('28', '00000001', '03', '第3章', '0');
INSERT INTO `chapter` VALUES ('29', '00000001', '04', '第4章', '0');
INSERT INTO `chapter` VALUES ('30', '00000001', '05', '第5章', '0');
INSERT INTO `chapter` VALUES ('31', '00000001', '06', '第6章', '0');
INSERT INTO `chapter` VALUES ('32', '00000002', '01', '近代史一', '0');
INSERT INTO `chapter` VALUES ('33', '00000002', '02', '近代史二', '0');
INSERT INTO `chapter` VALUES ('34', '00000002', '03', '近代史三', '0');
INSERT INTO `chapter` VALUES ('35', '00000002', '04', '近代史四', '0');
INSERT INTO `chapter` VALUES ('36', '00000002', '05', '近代史五', '0');
INSERT INTO `chapter` VALUES ('37', '00000002', '06', '近代史六', '0');
INSERT INTO `chapter` VALUES ('38', '00000002', '07', '近代史七', '0');
INSERT INTO `chapter` VALUES ('39', '00000003', '01', '微观1', '0');
INSERT INTO `chapter` VALUES ('40', '00000003', '02', '微观2', '0');
INSERT INTO `chapter` VALUES ('41', '00000003', '03', '微观3', '0');
INSERT INTO `chapter` VALUES ('42', '00000003', '04', '微观4', '0');
INSERT INTO `chapter` VALUES ('43', '00000003', '05', '微观5', '0');
INSERT INTO `chapter` VALUES ('44', '00000005', '01', '证券投资1', '0');
INSERT INTO `chapter` VALUES ('45', '00000005', '02', '证券投资2', '0');
INSERT INTO `chapter` VALUES ('46', '00000005', '03', '证券投资3', '0');
INSERT INTO `chapter` VALUES ('47', '00000005', '04', '证券投资4', '0');
INSERT INTO `chapter` VALUES ('48', '00000005', '05', '证券投资5', '0');
INSERT INTO `chapter` VALUES ('49', '00000005', '06', '证券投资6', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('2', '001MP', '2012', '001MP201201', '马哲1201', '0');
INSERT INTO `class` VALUES ('3', '002FI', '2013', '002FI201301', '证券1301', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('9', '00000001', '马克思主义基本原理概论', '0');
INSERT INTO `course` VALUES ('10', '00000002', '中国近代史纲要', '0');
INSERT INTO `course` VALUES ('11', '00000003', '微观经济学', '0');
INSERT INTO `course` VALUES ('12', '00000004', '宏观经济学', '0');
INSERT INTO `course` VALUES ('13', '00000005', '证券投资学', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of datadictionary
-- ----------------------------
INSERT INTO `datadictionary` VALUES ('1', 'SYS', '系统角色', 'SYS01', '教师', '0');
INSERT INTO `datadictionary` VALUES ('2', 'SYS', '系统角色', 'SYS02', '学生', '0');
INSERT INTO `datadictionary` VALUES ('3', 'SYS', '系统角色', 'SYS03', '管理员', '0');
INSERT INTO `datadictionary` VALUES ('4', 'SYS', '系统角色', 'SYS04', '超级管理员', '0');
INSERT INTO `datadictionary` VALUES ('5', '100', '性别', '1001', '男', '0');
INSERT INTO `datadictionary` VALUES ('6', '100', '性别', '1002', '女', '0');
INSERT INTO `datadictionary` VALUES ('7', '100', '性别', '1003', '保密', '0');
INSERT INTO `datadictionary` VALUES ('8', '200', '难度等级', '2001', '简单', '0');
INSERT INTO `datadictionary` VALUES ('9', '200', '难度等级', '2002', '一般', '0');
INSERT INTO `datadictionary` VALUES ('10', '200', '难度等级', '2003', '难', '0');
INSERT INTO `datadictionary` VALUES ('11', '300', '填空判断', '3000', '填空题', '0');
INSERT INTO `datadictionary` VALUES ('12', '300', '填空判断', '3001', '判断题', '0');
INSERT INTO `datadictionary` VALUES ('13', '400', '试卷类型', '4001', '试题固定', '0');
INSERT INTO `datadictionary` VALUES ('14', '400', '试卷类型', '4002', '试题不固定', '0');
INSERT INTO `datadictionary` VALUES ('15', '600', '判断题正误', '6000', '正确', '0');
INSERT INTO `datadictionary` VALUES ('16', '600', '判断题正误', '6001', '错误', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of essayquestion
-- ----------------------------
INSERT INTO `essayquestion` VALUES ('1', '00000001', '01', '2001', '利用价值规律的作用，就能自动地实现资源的最优配置', '此观点错误。价值规律的积极作用表现在：第一，自发地调节生产资料和劳动力在社会各部门之间的分配，从而调节生产和流通，使生产和销售、供给和需求保持大体平衡；第二，促使商品生产者改进技术，改善经营管理提高劳动生产率；第三，促使商品生产者展开竞争。价值规律作用的形式表现为商品的价格受供求关系影响围绕着价值上下波动。生产者在价格的引导和利益的驱动下，必然将资源从投人过多，商品供过于求、价格低、获利减少的部门，向资源投人少，商品供不应求、价格高、获利多的部门转移，从而起到自动调节社会资源配置的作用。   但是，价值规律的调节即市场机制有其自身弱点和消极面：市场机制的启动，是基于微观经济主体对自身近期局部利益的追求；市场信息反馈的只是资源配置的方向而非精确的数量，而且具有滞后性；它不能自发地实行国民经济总量平衡和稳定增长，对某些社会效益重于经济效益的经济活动难以达到预期的目的，甚至会导致垄断，妨碍自由竞争，造成资源的巨大浪费等。市场的这些缺陷和不足，需要国家对市场的宏观指导和调控来弥补和克服。计划和市场的有机结合，才有可能实现资源的最优化配置。', '19950103', '1461070382896', '0');
INSERT INTO `essayquestion` VALUES ('2', '00000001', '02', '2001', '劳动是一切财富和文化的源泉', '此观点错误。财富的物质内容是使用价值。价值要以使用价值即财富为物质承担者，但生产使用价值即财富的劳动，并不必然生产价值。在一切自然经济中的劳动都生产财富，但不形成价值。但劳动也不是财富生产的唯一要素。财富是所有生产要素共同创造的。在财富生产中，劳动、资本、土地、技术等都是不可缺少的，马克思肯定各种生产要素在财富生产中的同等重要性，他说：劳动并不是它所生产的使用价值即物质财富的唯一源泉。正如威廉·配第所说，“劳动是财富之父，土地是财富之母。”财富是由各种生产要素共同生产的，这就为生产要素的所有者共同参与财富分配提供了经济依据。   文化是以意识形态为主要内容，包括行为规范、风俗习惯在内的意识形式，包括劳动在内的整个社会生活是文化的来源，劳动也只是文化的来源之一。', '19950103', '1461070382896', '0');
INSERT INTO `essayquestion` VALUES ('3', '00000001', '03', '2001', '劳动力的使用价值是其价值的源泉，并且是大于它自身价值的源泉', '此观点正确。一般商品在消费过程中，随着使用价值的消失，它的价值也会消失，或者转移到新产品中去。劳动力商品则不同，劳动力的使用价值就是进行生产劳动的能力，它的使用和消费就是劳动，而劳动能形成价值，因此劳动力商品使用价值的特点在于它是剩余价值的源泉。剩余价值的本质就是雇佣工人创造的价值中超过劳动力价值以上的而被资本家无偿占有的部分，它的源泉是雇佣工人的剩余劳动。货币转化为资本的过程，既在流通领域进行（以流通为媒介，在市场上买到劳动力这种特殊商品），又不在流通领域进行（剩余价值是在使用劳动力的生产过程中产生的），整个过程的关键在于劳动力具有特殊的使用价值，能创造出大于自身价值更大的价值，劳动力的使用能为其购买者创造剩余价值。价值增殖的秘密就在这里。劳动力商品的这种特殊的使用价值，对货币转化为资本具有决定性意义。资本家购买到这种特殊商品，才能获得剩余价值', '19950103', '1461071609354', '0');
INSERT INTO `essayquestion` VALUES ('4', '00000001', '04', '2001', '资本不是一种物，而是一种以物为媒介的人和人之间的社会关系', '此观点正确。资本是自行增殖的价值，是能够带来剩余价值的价值。在现实生活中，资本总是表现为一定的物，例如货币、机器设备、商品等，但这些物本身并不是资本，只有在一定的社会关系下，这些物被用来从事以获得剩余价值为目的的生产活动，也就是成为带来剩余价值的手段时，它才成为资本。所以马克思强调指出，资本的本质不是物，而是在物的外壳掩盖下的一种社会生产关系，即资本主义剥削关系。', '19950103', '1461071760490', '0');
INSERT INTO `essayquestion` VALUES ('5', '00000001', '05', '2001', '剩余价值的产生，既不在流通领域，又离不开流通领域', '此观点正确。剩余价值是在生产过程中产生，它是由雇佣工人创造的被资本家无偿占有的，超过劳动力价值以上的那部分价值。   剩余价值不能在流通领域中产生，因为在商品流通过程中，等价交换只是价值形式的变换，不等价交换只是对既定的总价值量的重新分配，都不能产生剩余价值。   剩余价值的产生又离不开流通领域。只有在流通领域中商品生产者之间才能发生关系，货币才能转化为资本。资本家不把货币投人流通，购买劳动力商品，剩余价值无法产生；凝结了劳动者剩余劳动的商品不在市场上销售出去，剩余价值也不可能实现。', '19950103', '1461071760491', '0');
INSERT INTO `essayquestion` VALUES ('6', '00000001', '06', '2001', '资本的价值构成决定资本的技术构成', '此观点错误。对任何资本家的资本，都可以从价值形态和实物形态两方面加以分析和考察。 从实物形态上看，任何资本家的资本都是由生产资料和劳动力两部分组成。而生产资料和劳动力的比例是由生产技术水平决定的，这种由生产技术水平决定的生产资料和劳动力的比例叫资本的技术构成。  从价值形态上看，资本家的资本由不变资本和可变资本两部分构成。不变资本和可变资本的比例叫资本的价值构成。资本的技术构成和资本的价值构成之间存在着有机的联系，资本的技术构成决定资本的价值构成，而资本价值构成的变化能反映资本技术构成的变化。由资本的技术构成决定并且反映资本技术构成变化的资本价值构成叫资本有机构成', '19950103', '1461071760492', '0');
INSERT INTO `essayquestion` VALUES ('7', '00000001', '02', '2001', '剩余价值转化为利润，价值就转化为生产价格', '此观点正确。把剩余价值看做是全部预付资本的产物时，剩余价值就转化为利润，但这并不直接导致价值转化为生产价格。生产价格是由成本价格加平均利润构成。只有利润转化为平均利润，价值才转化为生产价格。   在资本主义生产过程中，不仅耗费的资本在发生作用，全部预付资本都在发挥作用，这样剩余价值就表现为全部预付资本的增加额。当剩余价值被看作是全部预付资本的增加值时，剩余价值就转化为利润。既然利润是预付资本的增加值，等量资本获得等量利润就成为资本的必然要求，于是在竞争过程中，利润转化为平均利润，资本家按预付资本的比例分配利润。这样，商品就不能按价值进行销售，而必然按生产成本加平均利润的价格即生产价格出售，价值转化为生产价格。可见，生产价格的形成以平均利润率的形成为前提。', '19950103', '1461071910503', '0');
INSERT INTO `essayquestion` VALUES ('8', '00000001', '05', '2001', '资本创造利润（利息）、土地产生地租、劳动获得工资', '此观点错误。这是马克思批判过的资产阶级庸俗经济学家提出的“三位一体”公式。资本主义生产关系的实质，决定了社会各阶级的收人采取工资、利润和地租的形式，而获得这些收人的条件是劳动力的出卖、资本的占有和土地的资本主义私有制。但各种收人的源泉都是工人创造的新价值，资本主义条件下生产要素参与价值分配的实质是资本所有者和土地所有者共同瓜分工人创造的剩余价值，但这种分配关系在社会表面和资本家的意识形态中却歪曲地表现为资本家获得利润是由资本提供了服务而应得的报酬；土地所有者获得地租是由于土地提供了服务而应得的报酬；工人获得的工资是劳动这种服务的报酬。资本、土地、劳动这三种要素是三种收人的源泉，三种要素创造三种收人，谁也没剥削谁；这就是混淆了使用价值的创造和价值的创造，混淆了价值的创造和价值的分配，掩盖了资本主义剥削。', '19950103', '1461071970801', '0');
INSERT INTO `essayquestion` VALUES ('9', '00000001', '02', '2002', '流动资本是指在生产过程中价值发生增殖的资本', '此观点错误。资本按周转方式的不同划分为固定资本和流动资本，按在价值增殖过程中的作用不同划分为不变资本和可变资本，这是两种不同的划分方法。   投在劳动力上的资本，在生产过程中发生了价值增殖，是可变资本。但就价值周转方式来讲，这部分资本也同投在劳动对象上的资本一样，属于流动资本。不能混淆这两种不同划分方法，把流动资本看做是在生产过程中其价值发生增殖的资本', '19950103', '1461073203730', '0');
INSERT INTO `essayquestion` VALUES ('10', '00000001', '03', '2003', '资本循环和周转所研究的问题是一样的', '此观点错误。资本循环和资本周转都是研究个别资本的运动，但是它们研究的侧重点不同。资本循环侧重研究个别资本经过的阶段、采取的形式及资本循环保持连续性的条件；而资本周转侧重研究资本运动的速度及其对剩余价值生产的影响。   资本循环和资本周转都是产业资本运动的形式。但是它们考察的角度不同。资本循环主要是从资本运动的连续性方面揭示剩余价值的生产和实现。而资本周转则主要从资本运动的速度方面揭示有关价值增殖的问题。   与此相适应，它们考察的目的也不同。考察资本循环，主要分析资本在运动中要经过的阶段和要采取的职能形式，揭示资本循环必须具备的条件；而考察资本周转，则要分析制约资本运动速度的因素，揭示资本周转速度对商品生产和价值增殖的影响。   可见，资本循环和资本周转研究的问题既有联系，又有区别', '19950103', '1461073203730', '0');
INSERT INTO `essayquestion` VALUES ('11', '00000001', '04', '2002', '如何理解认识过程的反复性和无限性？', '从实践到认识，再从认识到实践，如此实践、认识、再实践、再认识，循环往复以至无穷，一步步地深化和提高，这就是认识发展的总过程。   “实践、认识、再实践、再认识”作为认识发展的总过程，不只是实践到认识和认识到实践多次飞跃的综合，而且表现了认识过程的反复性和无限性。认识过程的反复性和无限性是指人们的认识过程既不是封闭式的循环，也不是直线式的前进，而是螺旋式的曲折上升运动。这个运动，从形式上看，表现为认识和实践的反复循环；从内容上看，实践和认识之每一循环，都比较地进到了高一级的程度。正是认识运动中实践和认识的这种循环往复和无限发展，体现了认识的本质和一般发展规律。   造成认识过程反复性和无限性的原因是：   第一，人们对事物的认识，由于主客观条件的限制，往往不是一次完成的从主观方面说，人们总是受到自己认识能力和实践活动范围的限制。从客观方面说，受到科学技术条件的限制，以及客观过程的发展和表现程度的限制客观事物的本质有一个显露的过程，人的认识也就需要一个过程。  第二，从人们具体的认识过程看，当某一思想、理论、计划、方案等，经过多次反复，在实践中达到了预想的结果，就算完成了。然而，对于认识过程的推移而言，人们的认识运动还没有完成，并且也永远不会完成。因为物质世界及其发展是无限的。所以，人的认识的任务，就在于不断地克服主观和客观、认识和实践之间的矛盾，求得它们之间的具体的历史的统一，而不是企图去发现所谓的“永恒真理”、“终极真理”。   “主观和客观、认识和实践的具体的历史的统一”表明，这个统一应当是具体的、历史的。所谓具体的，即主观认识要同一定时间、地点、条件下的客观实践相符合；所谓历史的，即主观认识要同特定历史发展阶段的客观实践相适应。由于客观实践是具体的、历史的，所以，主观认识也应当是具体的、历史的。当事物的具体过程已经向前推移，转变到另一个具体过程的时候，主观认识就应当随之而转变。如果主观认识仍然停留在原来的阶段上，这就脱离了客观实践的具体的、历史的特点，思想落后于实际，就容易犯保守的错误。当事物的具体过程尚未结束，原有的矛盾尚未得到充分的暴露和展开，向另一个具体过程推移转变的条件还不具备的时候，如果人们硬要把将来可能做的事情勉强拿到现在来做，企图超越阶段，这同样是脱离了客观实践的具体的、历史的特点，就容易犯冒进的错误。   绪论  马克思主义是关于无产阶级和人类解放的科学', '19950103', '1461073203731', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fillblank
-- ----------------------------
INSERT INTO `fillblank` VALUES ('1', '00000005', '01', '2001', '有价证券可分为    、   和    。', '3000', '债券、股票、基金', '19950103', '1461051732539', '0');
INSERT INTO `fillblank` VALUES ('2', '00000005', '02', '2001', '期货商品一般具有  、 和   三大功能。', '3000', '套期保值、投机、价格发现', '19950103', '1461051826565', '0');
INSERT INTO `fillblank` VALUES ('3', '00000005', '03', '2001', '期权一般有两大类：即     和          ', '3000', '看涨期权（买进期权）  看跌期权（卖进期权）', '19950103', '1461052084153', '0');
INSERT INTO `fillblank` VALUES ('4', '00000005', '04', '2001', '投资基金按运作和变现方式分为   和     。', '3000', '封闭型基金   开放型基金', '19950103', '1461052236679', '0');
INSERT INTO `fillblank` VALUES ('5', '00000005', '05', '2003', '牛市的时候，因为看跌期权被执行的可能性不大，因此可        和         。', '3000', '买入看涨期权    卖出看跌期权', '19950103', '1461052333771', '0');
INSERT INTO `fillblank` VALUES ('6', '00000005', '06', '2002', '证券投资风险有两大类：           和           。', '3000', '系统性风险    非系统性风险', '19950103', '1461052380296', '0');
INSERT INTO `fillblank` VALUES ('7', '00000005', '03', '2001', '我国目前上市公司的重组主要有以下三种方式              、              和              。', '3000', '股权转让方式   资产置换方式   收购兼并方式', '19950103', '1461052448618', '0');
INSERT INTO `fillblank` VALUES ('8', '00000005', '01', '2001', '收益的不确定性即为投资的风险，风险的大小与投资时间的长短成反比', '3001', '6001', '19950103', '1461072395211', '0');
INSERT INTO `fillblank` VALUES ('9', '00000005', '02', '2001', '所谓证券投资是指个人或法人对有价证券的购买行为，这种行为会使投资者在证券持有期内获得与其所承担的风险相称的收益', '3001', '6000', '19950103', '1461072395212', '0');
INSERT INTO `fillblank` VALUES ('10', '00000005', '03', '2001', '投资者将资金存入商业银行或其他金融机构，以储蓄存款或企业存款、机构存款的形式存在，从融资者的角度看，是间接融资', '3001', '6000', '19950103', '1461072395213', '0');
INSERT INTO `fillblank` VALUES ('11', '00000005', '04', '2001', '证券投资净效用是指收益带来的正效用减去风险带来的负效用', '3001', '6000', '19950103', '1461072944762', '0');
INSERT INTO `fillblank` VALUES ('12', '00000005', '05', '2003', '投资的主要目的是承担宏观调控的重任', '3001', '6000', '19950103', '1461072944763', '0');
INSERT INTO `fillblank` VALUES ('13', '00000005', '06', '2002', '根据我国《证券法》规定，证券公司是专营证券业务的金融机构，综合类证券公司可用其资本金、营运资金和 其他经批注的资金进行证券投资', '3001', '6000', '19950103', '1461072944764', '0');

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
  `trueanswer` varchar(6) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `createTime` varchar(40) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0' COMMENT '1表示已经被删除，0表示未被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of multichoose
-- ----------------------------
INSERT INTO `multichoose` VALUES ('1', '00000001', '01', '2001', '作为一个完整的科学体系，马克思主义理论体系的三个主要组成部分是', '作为马克思主义政治学', '马克思主义政治经济学', '科学社会主义', '马克思主义哲学', ' ', '', 'BCD', '19950103', '1461054496011', '0');
INSERT INTO `multichoose` VALUES ('2', '00000005', '01', '2001', '间接投资的优点有', '积少成多', '续短为长', '化分散为集中', '分散风险', ' ', '', 'ABCD', '19950103', '1461054651345', '0');
INSERT INTO `multichoose` VALUES ('3', '00000001', '02', '2001', '作为马克思主义产生阶级基础的19世纪三大工人起义是', '巴黎公社', '1831年和1834年法国里昂工人两次起义', '1838年在英国爆发的延续十余年的宪章运动', '1844年德国西里西亚工人起义', ' ', '', 'BCD', '19950103', '1461054929557', '0');
INSERT INTO `multichoose` VALUES ('4', '00000001', '03', '2001', '马克思主义产生的直接理论渊源是', '德国古典哲学', '英国古典政治经济学', '法国英国的空想社会主义', '法国启蒙思想', ' ', '', 'ABCD', '19950103', '1461056371343', '0');
INSERT INTO `multichoose` VALUES ('5', '00000001', '04', '2001', '德国古典哲学的代表性人物是', '康德', '黑格尔', '费尔巴哈', '笛卡尔', ' ', '', 'BC', '19950103', '1461067659346', '0');
INSERT INTO `multichoose` VALUES ('6', '00000001', '05', '2001', '资产阶级古典政治经济学的代表人物', '亚当•斯密', '大卫•李嘉图', '马尔萨斯', '西斯蒙', ' ', '', 'AB', '19950103', '1461067765024', '0');
INSERT INTO `multichoose` VALUES ('7', '00000001', '06', '2001', '空想社会主义的最杰出的代表是', '昂利•圣西门', '沙尔•傅立叶', '罗伯特•欧文', '托马斯•莫尔', ' ', '', 'ABC', '19950103', '1461068304813', '0');
INSERT INTO `multichoose` VALUES ('8', '00000005', '02', '2001', '投资的特点有', '投资是现在投入一定价值量的经济活动', '投资具有时间性', '投资的目的在于得到报酬', '投资具有风险性', ' ', '', 'ABCD', '19950103', '1461068416138', '0');
INSERT INTO `multichoose` VALUES ('9', '00000005', '03', '2001', '证券投资在投资活动中占有突出的地位，其作用表现在()促进经济增长等方面', '使社会的闲散货币转化为投资资金', '储蓄转化为投资', '促进资金合理流动', '促进资源有效配臵', ' ', '', 'ABCD', '19950103', '1461068517657', '0');
INSERT INTO `multichoose` VALUES ('10', '00000005', '04', '2001', '间接投资的优点有', '积少成多', '续短为长', '化分散为集中', '分散风险', ' ', '', 'ABCD', '19950103', '1461068580376', '0');
INSERT INTO `multichoose` VALUES ('11', '00000001', '02', '2002', '马克思恩格斯最重要的理论贡献是', '辩证法', '劳动价值论', '唯物史观', '剩余价值学说', ' ', '', 'CD', '19950103', '1461068667505', '0');
INSERT INTO `multichoose` VALUES ('12', '00000001', '03', '2003', '马克思主义的根本特性是', '阶级性', '实践性', '客观性', '人民性', ' ', '', 'AB', '19950103', '1461068731864', '0');
INSERT INTO `multichoose` VALUES ('13', '00000001', '05', '2003', '马克思主义中国化的三大理论成果是', '李大钊的理论', '毛泽东思想', '邓小平理论', '“三个代表”重要思想', ' ', '', 'BCD', '19950103', '1461068808590', '0');
INSERT INTO `multichoose` VALUES ('14', '00000001', '05', '2003', '对待马克思主义，必须', '解放思想、实事求是、与时俱进、求真务实', '坚持实践创新和理论创新', '把马克思主义作为进一步研究的出发点和供这种研究的方法，而不是教义', '把马克思主义作为永恒真理，只能坚持不能发展', '既要坚持马克思主义，又要发展马克思主义', ' ', 'ABCE', '19950103', '1461072686427', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of singlechoose
-- ----------------------------
INSERT INTO `singlechoose` VALUES ('1', '00000001', '01', '2001', '马克思主义理论从狭义上说是', '无产阶级争取自身解放和整个人类解放的学说体系', '关于无产阶级斗争的性质、目的和解放条件的学说', '马克思和恩格斯创立的基本理论、基本观点和基本方法构成的科学体系', '关于资本主义转化为社会主义以及社会主义和共产主义发展的普遍规律的学说', 'C', '19950103', '1461048146147', '0');
INSERT INTO `singlechoose` VALUES ('2', '00000001', '02', '2002', '马克思主义理论从广义上说是', '不仅指马克思恩格斯创立的基本理论、基本观点和学说的体系，也包括继承者对它的发展', '无产阶级争取自身解放和整个人类解放的学说体系', '关于无产阶级斗争的性质、目的和解放条件的学说', '马克思和恩格斯创立的基本理论、基本观点和基本方法构成的科学体系', 'A', '19950103', '1461048146149', '0');
INSERT INTO `singlechoose` VALUES ('3', '00000001', '03', '2003', '作为中国共产党和社会主义事业指导思想的马克思主义是指', '不仅指马克思恩格斯创立的基本理论、基本观点和学说的体系，也包括继承者对它的发展', '无产阶级争取自身解放和整个人类解放的学说体系', '关于无产阶级斗争的性质、目的和解放条件的学说', '列宁创立的基本理论、基本观点和基本方法构成的科学体系', 'A', '19950103', '1461048281567', '0');
INSERT INTO `singlechoose` VALUES ('4', '00000001', '04', '2001', '在19世纪三大工人运动中，集中反映工人政治要求的是', '法国里昂工人起义', '英国宪章运动', '芝加哥工人起义', '德国西里西亚纺织工人起义', 'B', '19950103', '1461048475476', '0');
INSERT INTO `singlechoose` VALUES ('5', '00000001', '05', '2001', '马克思主义产生的经济根源是', '工业革命', '资本主义经济危机', '资本主义社会生产力和生产关系的矛盾运动', '阶级斗争', 'C', '19950103', '1461048475478', '0');
INSERT INTO `singlechoose` VALUES ('6', '00000001', '06', '2001', '马克思主义产生的阶级基础和实践基础是', '资本主义的剥削和压迫', '无产阶级作为一支独立的政治力量登上了历史舞台', '工人罢工和起义', '工人运动得到了“农民的合唱\"', 'B', '19950103', '1461048475480', '0');
INSERT INTO `singlechoose` VALUES ('7', '00000001', '01', '2001', '马克思把黑格尔的辩证法称为', '合理内核', '基本内核', '精髓', '核心', 'A', '19950103', '1461049899505', '0');
INSERT INTO `singlechoose` VALUES ('8', '00000001', '03', '2002', '在第一次世界大战中成为东西方矛盾焦点和帝国主义政治体系最薄弱环节的国家是', '德国', '奥地利', '中国', '德国', 'D', '19950103', '1461049899506', '0');
INSERT INTO `singlechoose` VALUES ('9', '00000001', '04', '2001', '“哲学把无产阶级当作自己的物质武器，同样，无产阶级把哲学当作自己的精神武器”，这个论断的含义是', '马克思主义是无产阶级的世界观和方法论', '哲学的存在方式是物质', '无产阶级的存在方式是精神', '无产阶级掌握哲学就由自为阶级转变为自在阶级', 'A', '19950103', '1461049899507', '0');
INSERT INTO `singlechoose` VALUES ('10', '00000001', '05', '2001', '马克思主义生命力的根源在于', '以实践为基础的科学性与革命性的统一', '与时俱进', '科学性与阶级性的统一', '科学性', 'A', '19950103', '1461049961262', '0');
INSERT INTO `singlechoose` VALUES ('11', '00000001', '06', '2002', '无产阶级的科学世界观和方法论是', '辩证唯物主义', '历史唯物主义', '辩证唯物主义和历史唯物主义', '唯物主义', 'C', '19950103', '1461050016238', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teachercourse
-- ----------------------------
INSERT INTO `teachercourse` VALUES ('3', '19950103', '00000001-00000005-', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of testpaper
-- ----------------------------
INSERT INTO `testpaper` VALUES ('1', '00000001', '', '04/19/2016', '17:45', '19:00', 'ForAllStudent', '19950103', '1461060072650', '70', '19950103', '7-9-4-6-5', '10', '4-3', '10', '', '', '', '', '', '', '4001', '0');
INSERT INTO `testpaper` VALUES ('2', '00000001', '', '04/19/2016', '21:45', '23:45', 'ForAllStudent', '19950103', '1461073892978', '100', '19950103', '1-5-10-7-9', '10', '5-3-7', '10', '', '', '', '', '8-3', '10', '4001', '0');
INSERT INTO `testpaper` VALUES ('3', '00000001', '', '04/19/2016', '22:15', '23:30', 'ForAllStudent', '19950103', '1461075681663', '100', '19950103', '5-4-1-0', '10', '3-2001', '10', '', '', '', '', '2-2001', '10', '4002', '0');
INSERT INTO `testpaper` VALUES ('4', '00000005', '', '04/19/2016', '22:30', '23:45', 'ForAllStudent', '19950103', '1461076363594', '100', '19950103', '', '', '9-2-10', '10', '10-11-8', '10', '1-7-2-4', '10', '', '', '4001', '0');
INSERT INTO `testpaper` VALUES ('5', '00000005', '', '04/19/2016', '22:30', '23:45', 'ForAllStudent', '19950103', '1461076371335', '100', '19950103', '', '', '3-2001', '10', '3-2001', '10', '4-2001', '10', '', '', '4002', '0');
INSERT INTO `testpaper` VALUES ('6', '00000001', '', '04/20/2016', '15:15', '16:15', 'ForAllStudent', '19950103', '1461137039751', '100', '19950103', '10-1-9-5-7-8', '10', '4-3', '10', '', '', '', '', '8-7', '10', '4001', '0');
INSERT INTO `testpaper` VALUES ('7', '00000001', '', '04/21/2016', '13:30', '14:30', 'ForAllStudent', '19950103', '1461217764366', '100', '19950103-19950101', '4-1-7-9-6', '10', '5-7-1', '10', '', '', '', '', '4-3', '10', '4001', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tests
-- ----------------------------
INSERT INTO `tests` VALUES ('1', '2012011222', '', '1', '23', 'A`B`C`D`B', 'A-B`B-C', '', '', '', '0');
INSERT INTO `tests` VALUES ('2', '2012011222', '', '2', '24', 'A`B`C`D`B', 'A-B`B-C`C-D', '', '', 'n不打卡说的那番话萨菲罗斯的奶粉的身份了大大撒附件是打算的飞洒发 反倒是你发达了发送哪里凡事都家美乐打开哈饭莱德斯基反`打电话撒范德萨垃圾附近的萨克附近的死灵法师就立刻发货单进来撒风景很多事了份电话是咖啡和电视剧放大和萨芬换电脑上看看发的哈萨克符合你快点撒份电话上看到', '0');
INSERT INTO `tests` VALUES ('3', '2012011222', '', '3', '40', 'A`10`B`6`C`9`C`1`A`11`', 'A-B`7`B-C`5`C-D`3', '', '', '发夹分类法第三款分两块多舒服拉上来收费了拉了拉了浏览U偶偶很快会尽快发V型这尼玛`7`去玩儿推哦了晚上的飞规划局快乐破我犹太人发的是 中心对称， 阿斯顿飞规划局快乐阿是大法官和健康阿斯顿飞规划局快乐阿斯顿飞规划局快乐阿斯顿飞规划局快乐`2', '0');
INSERT INTO `tests` VALUES ('4', '2012011222', '', '4', '71', '', 'A-B`B-C`C-D', '6000`6000`6001', '去维尔体育是大法官会尽快`去维尔体育ID法规和健康`是大法官会尽快让他与偶`让他Yui哦皮肤更好看了', '', '0');
INSERT INTO `tests` VALUES ('5', '2012011222', '', '5', '57', '', 'A-B`8`B-C`10`C-D`2', '6001`11`6001`10`6000`9', '破我宇突然看见韩国`3`脚后跟抚养费的`2`离开我聚合与规范的`7`为儿童预防的宣传`4', '', '0');
INSERT INTO `tests` VALUES ('6', '2012011222', '', '6', '39', 'A`B`C`D`C`B', 'A-B`C-D', '', '', '发货啦房间打开了范德萨了发的健康啦发开发和第三加拉解散了`附近都洒了附近三轮ksd发货的撒的离开附近的书法家的卡上了附近的萨芬的上来看刷卡', '0');
INSERT INTO `tests` VALUES ('7', '2012011222', '', '7', '50', 'A`B`C`D`B', 'A-B`B-C`C-D', '', '', 'wwrgrgtrh\n\nddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddee\n\ne\ne\ne\ne\ne\ne\ne\ne\ne\ne\ne\n\ne\ne\n`sfdsfdsfsfdsfsdfsdfs', '0');

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
  `photo1` varchar(60) DEFAULT NULL,
  `photo2` varchar(60) DEFAULT NULL,
  `passed` varchar(2) DEFAULT NULL,
  `isDelete` varchar(2) DEFAULT '0' COMMENT '0表示有效，1为已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2012011222', '丁鹏', '202CB962AC59075B964B07152D234B70', '1001', '123456789099', 'dingpeng@163.com', '1334728154', 'SYS02', '', null, null, '', '0');
INSERT INTO `user` VALUES ('2', '19950103', 'DingPeng', '202CB962AC59075B964B07152D234B70', '1001', '15652797338', '1767685713@qq.com', '1767', 'SYS01', '', null, null, '1', '0');
INSERT INTO `user` VALUES ('3', '19911102', 'Admin', '202CB962AC59075B964B07152D234B70', '1002', '15652797338', null, null, 'SYS03', '', null, null, null, '0');
INSERT INTO `user` VALUES ('4', '88889999', 'SuperAdmin', '202CB962AC59075B964B07152D234B70', '1002', null, null, null, 'SYS04', null, null, null, null, '0');
INSERT INTO `user` VALUES ('5', '19110101', 'peng', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS02', null, null, null, null, '0');
INSERT INTO `user` VALUES ('6', '19950101', 'peng', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS01', null, null, null, '1', '0');
INSERT INTO `user` VALUES ('7', '88889999', 'SuperAdmin', 'C20AD4D76FE97759AA27A0C99BFF6710', '1002', null, null, null, 'SYS04', null, null, null, null, '1');
INSERT INTO `user` VALUES ('8', '19990101', 'wang', '202CB962AC59075B964B07152D234B70', '1002', null, null, null, 'SYS01', null, 'img/tc/1461483660317201489.jpg', 'img/tc/1461483660317989757.jpg', '0', '0');
INSERT INTO `user` VALUES ('9', '19990102', 'cang', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS02', null, null, null, null, '0');
INSERT INTO `user` VALUES ('10', '19800101', '阿拉丁', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS01', null, 'img/tc/1461486352716199357.jpg', 'img/tc/1461486352717052410.jpg', '0', '0');
INSERT INTO `user` VALUES ('11', '19900101', 'dasaa', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS01', null, 'img/tc/1461486563857449499.jpg', 'img/tc/1461486563858747232.jpg', '0', '0');
INSERT INTO `user` VALUES ('12', '19950108', '1234', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS01', null, 'img/tc/1461493858229371966.jpg', 'img/tc/1461493858230495742.jpg', '1', '0');
INSERT INTO `user` VALUES ('13', '.941399408', 'dingpeng', '202CB962AC59075B964B07152D234B70', '1003', null, 'dingpeng.1107@163.com', null, 'SYS01', null, 'img/tc/1461510521614143677.jpg', 'img/tc/1461510521614196370.jpg', '0', '1');
INSERT INTO `user` VALUES ('14', '.262026181', 'ding', '202CB962AC59075B964B07152D234B70', '1001', null, 'dingpeng@google.com', null, 'SYS02', null, null, null, null, '1');
INSERT INTO `user` VALUES ('15', '8838542597', 'dingpeng', '202CB962AC59075B964B07152D234B70', '1001', null, 'dingpeng.1107@163.com', null, 'SYS01', null, 'img/tc/1461512131204669442.jpg', 'img/tc/1461512131204151600.jpg', '0', '0');
INSERT INTO `user` VALUES ('16', '2688511127', '123', '202CB962AC59075B964B07152D234B70', '1001', null, 'dingpeng@gmail.com', null, 'SYS02', null, 'img/tc/1461512131204669442.jpg', 'img/tc/1461512131204151600.jpg', '0', '0');
INSERT INTO `user` VALUES ('17', '5839178423', '12332131', '202CB962AC59075B964B07152D234B70', '1003', null, 'dingpeng@126.com', null, 'SYS02', null, null, null, null, '0');
INSERT INTO `user` VALUES ('18', '3123131212', '32131231', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS01', null, null, null, '1', '0');
INSERT INTO `user` VALUES ('19', '1212131231', '3123123', '202CB962AC59075B964B07152D234B70', '1001', null, null, null, 'SYS02', '002FI201301', null, null, null, '0');
INSERT INTO `user` VALUES ('20', '1212121121', '21212', '202CB962AC59075B964B07152D234B70', '1003', null, null, null, 'SYS03', null, null, null, null, '0');
