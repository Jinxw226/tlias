package com.itjf.controller;

import com.itjf.pojo.Emp;
import com.itjf.pojo.LoginInfo;
import com.itjf.pojo.Result;
import com.itjf.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * 登录Controller
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录，数据：{}", emp);
        LoginInfo info = empService.login(emp);
        if(info != null){
            return Result.success(info);
        }else{
            return Result.error("用户名或密码错误");
        }

    }
}
