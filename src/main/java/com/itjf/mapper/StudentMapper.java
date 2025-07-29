package com.itjf.mapper;

import com.itjf.pojo.Student;
import com.itjf.pojo.StudentDegreeData;
import com.itjf.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 获取所有班级id
     * @return
     */
    @Select("select distinct clazz_id from student group by clazz_id;")
    List<Integer> getClazzIds();

    /**
     * 获取所有学生信息 分页查询
     * @return
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 添加学生
     * @return
     */
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) " +
            "values (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{createTime}, #{updateTime})")
    void insert(Student student);

    /**
     * 根据id查询学生信息
     * @param id
     */
    @Select("select * from student where id = #{id}")
    Student getInfoById(Integer id);

    /**
     * 根据id更新学生信息
     * @param student
     */
    void updateById(Student student);

    /**
     * 根据id删除学生信息
     * @param ids
     */
    void deleteById(List<Integer> ids);

    /**
     * 处理学生违纪信息
     * @param id
     * @param score
     */
    void handleViolate(Integer id, Integer score);

    /**
     * 统计所有学生信息
     * @return
     */
    List<Map<String, Object>> countClazzStudent();

    /**
     * 统计所有学生学历信息
     * @return
     */
    List<StudentDegreeData> getStudentDegreeData();


//    List<Map<String, Object>> countClazzStudent();
}
