package com.itjf.service.impl;

import com.itjf.mapper.DeptMapper;
import com.itjf.mapper.EmpMapper;
import com.itjf.pojo.ClassHasStudentsException;
import com.itjf.pojo.Dept;
import com.itjf.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper  deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        List<Integer> ids = empMapper.getDeptIds();
        if(ids.contains(id)){
            throw new ClassHasStudentsException("该部门有员工，不能删除！");
        }else{
            deptMapper.deleteById(id);
        }
    }

    @Override
    public void add(Dept dept) {
        //1.补全基础属性 - createTime, updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper接口方法插入数据
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
