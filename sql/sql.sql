CREATE TABLE `dreamface_account` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `token` text COMMENT '用户令牌',
  `useragent` text COMMENT '用户代理（浏览器/设备信息）',
  `cookie` text COMMENT '用户Cookie信息',
  `is_used` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '1-使用 2-不使用',
  `checktime` datetime DEFAULT NULL COMMENT '检测时间',
  `userId` varchar(1000) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES(2000, 'dreamface', 0, 1, '#', 'menuItem', 'M', '0', '1', NULL, 'fa fa-diamond', 'admin', '2026-01-16 14:25:46', '', NULL, '');
INSERT INTO sys_menu
(menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, is_refresh, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES(2001, '账号管理', 2000, 1, '/system/account', 'menuItem', 'C', '0', '1', NULL, 'fa fa-user-plus', 'admin', '2026-01-16 14:28:22', '', NULL, '');
