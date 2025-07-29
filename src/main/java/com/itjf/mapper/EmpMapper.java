package com.itjf.mapper;

import com.itjf.pojo.Emp;
import com.itjf.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    //-------------------------原始分页查询-------------------------
    /**
     * 查询总记录数
     *
     */
    //@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    //public Long count();

    /**
     * 分页查询
     */
    //@Select("select e.*, d.name dept_name from emp e left join dept d on e.dept_id = d.id " +
    //        "order by e.update_time desc limit #{start}, #{pageSize}")
    //public List<Emp> list(Integer start, Integer pageSize);

    //@Select("select e.*, d.name dept_name from emp e left join dept d on e.dept_id = d.id order by e.update_time desc ")
    //public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 条件查询员工信息的方法
     * @param empQueryParam
     * @return
     */
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 添加员工信息
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "    values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息以及员工工作经历信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     * @return
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     * @return
     */
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 查询所有员工信息
     * @return
     */
    @Select("select id, username, password, name, gender, image, job, salary, entry_date, dept_id, create_time, update_time from emp")
    List<Emp> searchAll();

    /**
     * 查询所有部门id
     * @return
     */
    @Select("select distinct dept_id from emp")
    List<Integer> getDeptIds();

    /**
     * 根据用户名和密码查询员工信息
     * @param emp
     * @return
     */
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
