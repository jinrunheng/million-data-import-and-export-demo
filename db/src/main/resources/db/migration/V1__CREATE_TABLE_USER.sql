DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `user_id`   bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '用户 id',
    `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户姓名',
    `age`       int(11)     NULL     DEFAULT null COMMENT '用户年龄',
    `email`     varchar(50) NULL     DEFAULT NULL COMMENT '用户邮箱',
    PRIMARY KEY (`user_id`) USING BTREE
) engine = InnoDB
  character set utf8
  ROW_FORMAT = Dynamic;