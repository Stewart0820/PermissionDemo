/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.35 : Database - building
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`building` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

/*Table structure for table `menu` */

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `path` varchar(100) NOT NULL,
  `component` varchar(100) NOT NULL,
  `title` varchar(64) NOT NULL,
  `icon` varchar(64) NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

/*Data for the table `menu` */

insert  into `menu`(`id`,`url`,`name`,`path`,`component`,`title`,`icon`,`pid`) values 
(1,'/demo/**','AdManagement','/ad-management','Layout','广告模块','link',0),
(16,'/menu/**','RecycleOrderStaffManagement','/recycle-order-staff-management','Layout','随手回收订单模块','form',17),
(17,'/','RoleManagement','/role-management','Layout','角色模块','form',0),
(18,'/role/**','role','/role-management/role','role-management/role','角色','form',17);

/*Table structure for table `role` */

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values 
(1,'admin'),
(2,'teacher'),
(3,'teacher_lab'),
(4,'student'),
(5,'admin_super');

/*Table structure for table `role_menu` */

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role_menu` */

insert  into `role_menu`(`id`,`role_id`,`menu_id`) values 
(1,1,18),
(3,1,16);

/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `account` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL COMMENT '0 false FEMALE\n1 true  MALE',
  `create_time` varchar(12) NOT NULL,
  `update_time` varchar(12) DEFAULT NULL,
  `type` int(1) NOT NULL COMMENT '0:admin,1:任课老师,2:实验老师3：任课老师+实验老师5：学生',
  `email` varchar(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`account`,`password`,`mobile`,`gender`,`create_time`,`update_time`,`type`,`email`) values 
(1,'sir','admin','$2a$10$ogvUqZZAxrBwrmVI/e7.SuFYyx8my8d.9zJ6bs9lPKWvbD9eefyCe',NULL,NULL,'123',NULL,0,''),
(4,'老师1','13123133912','$2a$10$KZUuqEQ8TvU0ZOM0Y9Nx9uUrSdpO.F3l9B6WQsn0gaoftK5c2ySxW','13123133912',0,'1640152673',NULL,1,''),
(5,'测试老师1','13123133927','$2a$10$/f6hfxCjr9r3TplmGg7QCOeG7qnvQ3H3MADsTP66H2xa8gssm.vdi','13123133927',0,'1640249488',NULL,3,''),
(6,'测试老师1','13123133920','$2a$10$S9shG5nEATGe/D309Xav5.ceBHScx0SRFt3yIrGVb.No3UsEBkP12','13123133920',0,'1640319296',NULL,1,'1939136076@qq.com'),
(7,'陈鸿杰老师1','13123133922','$2a$10$ocBK//t9u5lB7Nrs5V9OkOdHHsVu3eFXma/vtZKKqRfrvWImolbca','13123133922',0,'1641605392',NULL,1,'1936979988@qq.com'),
(8,'陈鸿杰老师1','13123133921','$2a$10$EqXw5bJgDRYbphdn5I4i2OEWt1kJ3S6teD078rt2X/NBmxsoIRPM6','13123133921',0,'1641605799',NULL,1,'1936979988@qq.com');

/*Table structure for table `user_role` */

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values 
(1,1,1),
(2,4,1),
(7,5,1),
(8,5,2),
(9,6,1),
(10,7,1),
(11,8,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
