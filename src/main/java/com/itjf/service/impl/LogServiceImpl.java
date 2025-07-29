package com.itjf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjf.mapper.OperateLogMapper;
import com.itjf.pojo.Emp;
import com.itjf.pojo.LogQueryParam;
import com.itjf.pojo.OperateLog;
import com.itjf.pojo.PageResult;
import com.itjf.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public PageResult<OperateLog> list(LogQueryParam logQueryParam) {
        PageHelper.startPage(logQueryParam.getPage(),logQueryParam.getPageSize());
        List<OperateLog> rows = operateLogMapper.list(logQueryParam);
        Page<OperateLog> p = (Page<OperateLog>) rows;
        return new PageResult<OperateLog>(p.getTotal(),p.getResult());
    }
}
