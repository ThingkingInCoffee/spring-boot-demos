CREATE TABLE `demo_entry` (
      `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
      `demo_name` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '测试名称',
      `demo_type` tinyint(3) DEFAULT NULL COMMENT '测试类型',
      `zip_code` char(6) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '邮编',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `update_time` datetime DEFAULT NULL COMMENT '更新时间',
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
