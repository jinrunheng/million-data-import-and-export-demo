package com.dooby.million_data_import_and_export_demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dooby.million_data_import_and_export_demo.entity.Status;
import com.dooby.million_data_import_and_export_demo.mapper.StatusMapper;
import com.dooby.million_data_import_and_export_demo.service.IStatusService;
import org.springframework.stereotype.Service;

/**
 * @Author Dooby Kim
 * @Date 2023/4/28 10:48 上午
 * @Version 1.0
 */
@Service
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements IStatusService {
}
