package com.itjf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjf.mapper.StudentMapper;
import com.itjf.pojo.PageResult;
import com.itjf.pojo.Student;
import com.itjf.pojo.StudentQueryParam;
import com.itjf.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    /**
     * 获取学生列表分页查询
     * @param studentQueryParam
     * @return
     */
    @Override
    public PageResult<Student> list(StudentQueryParam studentQueryParam) {
        //1.pagehelper设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //2.执行查询语句
        List<Student> rows = studentMapper.list(studentQueryParam);
        //3.返回结果
        Page<Student> p = (Page<Student>) rows;
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
        log.info("添加学生成功，数据：{}", student);
    }

    @Override
    public Student getInfoById(Integer id) {
        return studentMapper.getInfoById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateById(student);
    }

    @Override
    public void deleteById(List<Integer> ids) {
        studentMapper.deleteById(ids);
    }

    @Override
    public void handleViolate(Integer id, Integer score) {
        studentMapper.handleViolate(id, score);
    }
}
