DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`   bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '用户 id',
    `name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户姓名',
    `age`  int(11)     NULL     DEFAULT null COMMENT '用户年龄',
    PRIMARY KEY (`id`) USING BTREE
) engine = InnoDB
  character set utf8
  ROW_FORMAT = Dynamic;