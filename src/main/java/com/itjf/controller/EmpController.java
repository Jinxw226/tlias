package com.itjf.controller;

import com.itjf.pojo.Emp;
import com.itjf.pojo.EmpQueryParam;
import com.itjf.pojo.PageResult;
import com.itjf.pojo.Result;
import com.itjf.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;
    /**
     * 分页查询
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize,name,gender,begin,end);
//        PageResult<Emp>  pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//    }

    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询，参数：{}", empQueryParam);
        PageResult<Emp>  pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {
        log.info("新增员工，数据：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    //删除员工 - 数组
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("删除员工，ids：{}", Arrays.toString(ids));
////        empService.delete(ids);
//        return Result.success();
//    }

    //删除员工 - List
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工，ids：{}", ids);
        empService.delete(ids);
        return Result.success();
    }
    /**
     *
     * 根据id查询员工信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工id为：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改员工
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询所有员工信息
     */
    @GetMapping("/list")
    public Result searchAll(){
        log.info("查询所有员工信息");
        List<Emp> empList = empService.searchAll();
        return Result.success(empList);
    }
}
