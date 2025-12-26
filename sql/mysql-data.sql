use `spring-cloud-alibaba-example`;

-- ----------------------------
-- Records of account_tbl
-- ----------------------------
BEGIN;
INSERT INTO `account_tbl` (`id`, `user_id`, `money`) VALUES (1, 'xuxiaowei', 100);
COMMIT;

-- ----------------------------
-- Records of storage_tbl
-- ----------------------------
BEGIN;
INSERT INTO `storage_tbl` (`id`, `commodity_code`, `count`) VALUES (1, 'A001', 200);
COMMIT;
