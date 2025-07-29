package com.itjf.service.impl;

import com.itjf.mapper.EmpMapper;
import com.itjf.mapper.StudentMapper;
import com.itjf.pojo.CountClazzStudent;
import com.itjf.pojo.JobOption;
import com.itjf.pojo.StudentDegreeData;
import com.itjf.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        //2.组装结束，并返回
        List<Object> jobList = list.stream().map(map -> map.get("pos")).toList();
        List<Object> dataList = list.stream().map(map -> map.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public CountClazzStudent getStudentCountData() {
        List<Map<String, Object>> list = studentMapper.countClazzStudent();
        List<Object> clazzList = list.stream().map(map -> map.get("clazzName")).toList();
        List<Object> countList = list.stream().map(map -> map.get("studentCount")).toList();
        return new CountClazzStudent(clazzList, countList);
    }

    @Override
    public List<StudentDegreeData> getStudentDegreeData() {
        List<StudentDegreeData> list = studentMapper.getStudentDegreeData();
        return list;
    }

//    @Override
//    public CountClazzStudent getStudentCountData() {
//        List<Map<String, Object>> list = studentMapper.countClazzStudent();
//        List<Object> clazzList = list.stream().map(map -> map.get("clazzName")).toList();
//        List<Object> countList = list.stream().map(map -> map.get("studentCount")).toList();
//        return new CountClazzStudent(clazzList, countList);
//    }
}
