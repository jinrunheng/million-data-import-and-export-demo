package com.dooby.million_data_import_and_export_demo.entity;

import lombok.*;

/**
 * @Author Dooby Kim
 * @Date 2023/4/21 6:24 下午
 * @Version 1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer userId;
    private String userName;
    private Integer age;
}
