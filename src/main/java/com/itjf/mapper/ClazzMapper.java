package com.itjf.mapper;

import com.itjf.pojo.Clazz;
import com.itjf.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 获取班级列表（分页查询）
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 添加班级
     * @param clazz
     */
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    /**
     * 根据id获取班级信息
     * @param id
     * @return
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    Clazz getInfoById(Integer id);

    /**
     * 根据id更新班级信息
     * @param clazz
     */
    void updateById(Clazz clazz);

    /**
     * 根据id删除班级信息
     * @param id
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 获取所有班级信息
     * @return
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz")
    List<Clazz> getAllInfo();
}
