package com.itjf.service;

import com.itjf.pojo.PageResult;
import com.itjf.pojo.Student;
import com.itjf.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 获取学生列表
     * @param studentQueryParam
     */
    PageResult<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 添加学生
     * @param student
     */
    void add(Student student);

    /**
     * 根据id获取学生信息
     * @param id
     */
    Student getInfoById(Integer id);

    /**
     * 修改学生信息
     * @param student
     */
    void update(Student student);

    /**
     * 根据id删除学生信息
     * @param ids
     */
    void deleteById(List<Integer> ids);

    /**
     * 处理学生违纪
     * @param id
     * @param score
     */
    void handleViolate(Integer id, Integer score);
}
