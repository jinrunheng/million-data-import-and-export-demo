package com.dooby.million_data_import_and_export_demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dooby.million_data_import_and_export_demo.enummeration.StatusEnum;
import lombok.*;

/**
 * @Author Dooby Kim
 * @Date 2023/4/28 10:36 上午
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@TableName("status")
public class Status {
    @TableId
    private String seq;
    @TableField("status")
    private StatusEnum status;
    @TableField("path")
    private String path;
}
