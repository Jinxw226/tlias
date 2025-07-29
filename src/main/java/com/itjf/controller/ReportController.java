package com.itjf.controller;

import com.itjf.pojo.CountClazzStudent;
import com.itjf.pojo.JobOption;
import com.itjf.pojo.Result;
import com.itjf.pojo.StudentDegreeData;
import com.itjf.service.EmpService;
import com.itjf.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别人数
     * @return
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计班级学生数量
     * @return
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData (){
        log.info("统计班级学生数量");
        CountClazzStudent countClazzStudent = reportService.getStudentCountData();
        return Result.success(countClazzStudent);
    }

    /**
     * 统计学员学历
     * @return
     */
    @GetMapping("/studentDegreeData")
    public Result getstudentDegreeData(){
        log.info("统计学员学历");
        List<StudentDegreeData> studentDegreeData = reportService.getStudentDegreeData();
        return Result.success(studentDegreeData);
    }
}
