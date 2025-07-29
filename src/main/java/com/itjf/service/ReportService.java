package com.itjf.service;

import com.itjf.pojo.CountClazzStudent;
import com.itjf.pojo.JobOption;
import com.itjf.pojo.StudentDegreeData;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 获取员工职位数据
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 获取员工性别数据
     * @return
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 班级人数统计
     * @return
     */
    CountClazzStudent getStudentCountData();

    /**
     * 学生学历统计
     * @return
     */
    List<StudentDegreeData> getStudentDegreeData();


//    CountClazzStudent getStudentCountData();
}
