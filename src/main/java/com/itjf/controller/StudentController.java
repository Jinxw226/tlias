package com.itjf.controller;

import com.itjf.pojo.PageResult;
import com.itjf.pojo.Result;
import com.itjf.pojo.Student;
import com.itjf.pojo.StudentQueryParam;
import com.itjf.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    /**
     * 获取学生列表
     * @param studentQueryParam
     * @return
     */
    @GetMapping
    public Result list(StudentQueryParam studentQueryParam){
        log.info("分页查询,参数：{}", studentQueryParam);
        PageResult<Student> pageResult =studentService.list(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增学生
     * @param student
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加学生，数据：{}", student);
        studentService.add(student);
        return Result.success();
    }

    /**
     * 根据id查找学生信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("根据id查找学生信息，id：{}", id);
        return Result.success(studentService.getInfoById(id));
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生信息：{}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 删除学生信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable List<Integer> ids){
        log.info("删除学生信息：{}", ids);
        studentService.deleteById(ids);
        return Result.success();
    }

    /**
     * 违纪处理
     * @param id
     * @return
     */
    @PutMapping("/violation/{id}/{score}")
    public Result handleViolate(@PathVariable Integer id, @PathVariable Integer score){
        log.info("处理学生id为：{}的违纪，扣分：{}", id, score);
        studentService.handleViolate(id, score);
        return Result.success();
    }
}
