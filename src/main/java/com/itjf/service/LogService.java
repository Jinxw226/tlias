package com.itjf.service;

import com.itjf.pojo.LogQueryParam;
import com.itjf.pojo.OperateLog;
import com.itjf.pojo.PageResult;

import java.util.List;

public interface LogService {

    /**
     * 获取日志列表
     * @return 日志列表
     */
    public PageResult<OperateLog> list(LogQueryParam logQueryParam);
}
