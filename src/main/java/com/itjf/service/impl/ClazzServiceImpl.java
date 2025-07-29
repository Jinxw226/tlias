package com.itjf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itjf.mapper.ClazzMapper;
import com.itjf.mapper.EmpMapper;
import com.itjf.mapper.StudentMapper;
import com.itjf.pojo.*;
import com.itjf.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 获取列表
     *
     * @param clazzQueryParam
     * @return
     */
    @Override
    public PageResult<Clazz> list(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2.执行查询
        List<Clazz> rows = clazzMapper.list(clazzQueryParam);
        //3.判断status状态并插入
        rows.forEach(clazz -> {
            if (LocalDate.now().isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            }else if(LocalDate.now().isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            }else {
                clazz.setStatus("在读中");
            }
        });
        //4.返回查询结果
        Page<Clazz> p = (Page<Clazz>) rows;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    /**
     * 添加班级
     * @param clazz
     */
    @Override
    public void add(Clazz clazz) {
        //1.设置创建时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2.执行插入
        clazzMapper.insert(clazz);
        //3.记录操作日志
        ClazzLog clazzLog = new ClazzLog(null,LocalDateTime.now(),"添加班级" + clazz);
    }

    /**
     * 根据id查询班级信息
     * @param id
     * @return
     */
    @Override
    public Clazz getInfoById(Integer id) {
        return clazzMapper.getInfoById(id);
    }

    /**
     * 修改班级信息
     * @param clazz
     */
    @Override
    public void update(Clazz clazz) {
        //1.设置更新 时间
        clazz.setUpdateTime(LocalDateTime.now());
        //2.更新信息
        clazzMapper.updateById(clazz);
    }

    /**
     * 根据id删除班级信息
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        //1.查询所有有学生的班级id
        List<Integer> ids = studentMapper.getClazzIds();
        if(ids.contains(id)){
            throw new ClassHasStudentsException("该班级有学生，不能删除！");
        }else{
            clazzMapper.deleteById(id);
        }
    }

    /**
     * 获取所有班级信息
     * @return
     */
    @Override
    public List<Clazz> getAllInfo() {
        return clazzMapper.getAllInfo();
    }
}
