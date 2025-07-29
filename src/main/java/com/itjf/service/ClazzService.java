package com.itjf.service;

import com.itjf.pojo.Clazz;
import com.itjf.pojo.ClazzQueryParam;
import com.itjf.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 获取班级列表分页查询
     * @param clazzQueryParam
     * @return
     */
    PageResult<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 添加班级
     * @param clazz
     */
    void add(Clazz clazz);

    /**
     * 根据id获取班级信息
     * @param id
     * @return
     */
    Clazz getInfoById(Integer id);

    /**
     * 修改班级信息
     * @param clazz
     */
    void update(Clazz clazz);

    /**
     * 删除班级
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 获取所有班级信息
     * @return
     */
    List<Clazz> getAllInfo();
}
