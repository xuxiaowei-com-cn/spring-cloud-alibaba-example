
-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (1, 'application-user.yml', 'DEFAULT_GROUP', 'user:\n  password: xuxiaowei.com.cn', 'c36aff5e042c94dada929fb06d036d1a', '2025-12-17 11:47:24', '2025-12-17 11:47:24', 'nacos_namespace_migrate', '127.0.0.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (2, 'application-user.yml', 'DEFAULT_GROUP', 'user:\n  password: xuxiaowei.com.cn', 'c36aff5e042c94dada929fb06d036d1a', '2025-12-17 11:47:24', '2025-12-17 11:47:24', 'nacos', '127.0.0.1', '', 'public', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (3, 'application-test.yml', 'DEFAULT_GROUP', 'test:\n  name: zhangsan\n', 'f08c287a83b123a83557b724969df67d', '2025-12-17 14:23:09', '2025-12-17 14:38:34', 'nacos_namespace_migrate', '127.0.0.1', '', '', '', NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (4, 'application-test.yml', 'DEFAULT_GROUP', 'test:\n  name: zhangsan\n', 'f08c287a83b123a83557b724969df67d', '2025-12-17 14:23:09', '2025-12-17 14:38:34', 'nacos', '127.0.0.1', '', 'public', '', NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (5, 'application-user-env.yml', 'DEFAULT_GROUP', 'user:\n  env:\n    name: lisi\n', '4ea4f54809d560c153cdbd435ae8aa8c', '2025-12-22 20:22:10', '2025-12-22 20:22:10', 'nacos_namespace_migrate', '127.0.0.1', '', '', NULL, NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`, `encrypted_data_key`) VALUES (6, 'application-user-env.yml', 'DEFAULT_GROUP', 'user:\n  env:\n    name: lisi\n', '4ea4f54809d560c153cdbd435ae8aa8c', '2025-12-22 20:22:10', '2025-12-22 20:22:10', 'nacos', '127.0.0.1', '', 'public', NULL, NULL, NULL, 'yaml', NULL, '');
COMMIT;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` (`username`, `role`) VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Records of users，用户名：nacos，密码：nacos
-- ----------------------------
BEGIN;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('nacos', '$2a$10$Fmla71mMX7roa8MUxmiRbOzI1KmXLPAnd4/FCaxdp4OOJMxR1vMa2', 1);
COMMIT;
