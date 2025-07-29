package com.itjf.controller;

import com.itjf.pojo.Clazz;
import com.itjf.pojo.ClazzQueryParam;
import com.itjf.pojo.PageResult;
import com.itjf.pojo.Result;
import com.itjf.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    /**
     * 获取班级列表分页查询
     * @return
     */
    @GetMapping
    public Result list(ClazzQueryParam clazzQueryParam){
        log.info("分页查询,参数：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.list(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加班级
     * @param clazz
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级，数据：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    /**
     * 获取班级信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询班级id为：{}", id);
        Clazz clazz = clazzService.getInfoById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     * @param clazz
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 删除班级
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级id为：{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAll(){
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.getAllInfo();
        return Result.success(clazzList);
    }
}
