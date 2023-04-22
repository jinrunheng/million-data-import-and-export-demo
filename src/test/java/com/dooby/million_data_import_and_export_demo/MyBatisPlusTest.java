package com.dooby.million_data_import_and_export_demo;

import com.dooby.million_data_import_and_export_demo.entity.User;
import com.dooby.million_data_import_and_export_demo.service.IUserService;
import com.dooby.million_data_import_and_export_demo.utils.RandomUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author Dooby Kim
 * @Date 2023/4/22 12:18 下午
 * @Version 1.0
 */
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private IUserService userService;


    @Test
    public void testSaveBatch() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            User build = User
                    .builder()
                    .userName("test" + i)
                    .age(RandomUtil.getRandom(18, 40))
                    .email("test" + i + "@abc.com")
                    .build();
            list.add(build);
        }
        System.out.println(list);
        // 批量插入
        userService.saveBatch(list);
    }


    @Test
    public void testSelect() {
        List<User> list = userService.list();
        Assertions.assertEquals(1000, list.size());
    }
}
