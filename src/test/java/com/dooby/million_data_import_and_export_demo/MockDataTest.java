package com.dooby.million_data_import_and_export_demo;

import com.dooby.million_data_import_and_export_demo.entity.User;
import com.dooby.million_data_import_and_export_demo.service.IUserService;
import com.dooby.million_data_import_and_export_demo.utils.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Dooby Kim
 * @Date 2023/4/22 3:29 下午
 * @Version 1.0
 */
@SpringBootTest
public class MockDataTest {

    @Autowired
    private IUserService userService;

    /**
     * 生成一百万数据
     */
    @Test
    public void generateMockData() {
        // 每 1000 条执行一次批量插入, 循环执行 1000 次，共插入一百万条数据
        for (int t = 0; t < 1000; t++) {
            List<User> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                User build = User
                        .builder()
                        .userName("test" + t + i)
                        .age(RandomUtil.getRandom(18, 40))
                        .email("test" + t + i + "@abc.com")
                        .build();
                list.add(build);
            }
            // 批量插入
            userService.saveBatch(list);
        }

    }
}
