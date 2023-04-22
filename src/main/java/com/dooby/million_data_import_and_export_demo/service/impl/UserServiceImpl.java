package com.dooby.million_data_import_and_export_demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dooby.million_data_import_and_export_demo.entity.User;
import com.dooby.million_data_import_and_export_demo.mapper.UserMapper;
import com.dooby.million_data_import_and_export_demo.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Author Dooby Kim
 * @Date 2023/4/22 12:23 下午
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
