package com.dooby.million_data_import_and_export_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @Author Dooby Kim
 * @Date 2023/4/21 6:24 下午
 * @Version 1.0
 * @Desc 用户实体类
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    @TableField("user_name")
    private String userName;
    @TableField("age")
    private Integer age;
    @TableField("email")
    private String email;
}
