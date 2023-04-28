# 用户表
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
# 状态表
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status`
(
    `seq`         varchar(50)  NOT NULL COMMENT '序号',
    `status`      varchar(50)  NOT NULL COMMENT '导出状态',
    `path`        varchar(100) NULL     DEFAULT null COMMENT '文件下载路径',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    PRIMARY KEY (`seq`) USING BTREE
) engine = InnoDB
  character set utf8
  ROW_FORMAT = Dynamic;