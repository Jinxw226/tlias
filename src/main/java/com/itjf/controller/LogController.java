package com.itjf.controller;

import com.itjf.mapper.OperateLogMapper;
import com.itjf.pojo.LogQueryParam;
import com.itjf.pojo.OperateLog;
import com.itjf.pojo.PageResult;
import com.itjf.pojo.Result;
import com.itjf.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 查询日志列表
     * @param logQueryParam
     * @return
     */
    @GetMapping("/page")
    public Result getLogList(LogQueryParam logQueryParam){
        log.info("查询日志列表");
        PageResult<OperateLog> logList = logService.list(logQueryParam);
        return Result.success(logList);
    }
}
