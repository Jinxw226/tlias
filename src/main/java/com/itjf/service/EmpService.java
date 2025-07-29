package com.itjf.service;

import com.itjf.pojo.Emp;
import com.itjf.pojo.EmpQueryParam;
import com.itjf.pojo.LoginInfo;
import com.itjf.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
//    PageResult<Emp> page(Integer page, Integer pageSize,
//                         String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 分页查询
     * @param empQueryParam
     * @return
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工信息
     * @param emp
     */
    void save(Emp emp) throws Exception;

    /**
     * 批量删除员工信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    void update(Emp emp);

    /**
     * 搜索所有员工信息
     * @return
     */
    List<Emp> searchAll();

    /**
     * 员工登录
     * @param emp
     * @return
     */
    LoginInfo login(Emp emp);
}
