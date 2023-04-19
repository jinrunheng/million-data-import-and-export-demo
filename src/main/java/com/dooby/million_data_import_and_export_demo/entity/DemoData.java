package com.dooby.million_data_import_and_export_demo.entity;

import lombok.*;

import java.util.Date;

/**
 * @Author Dooby Kim
 * @Date 2023/4/19 6:37 下午
 * @Version 1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemoData {
    private Integer userId;
    private String userName;
    private Date birthday;
}
