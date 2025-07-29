package com.itjf.controller;

import com.itjf.anno.Log;
import com.itjf.pojo.Dept;
import com.itjf.pojo.Result;
import com.itjf.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping()
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //删除部门 - 方式一： HttpServe
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("删除部门id为：" + id);
//        return Result.success();
//    }
//    注意事项：一旦声明了@RequestParam，该参数在请求时必须传递，如果不传递会报错
    //方式二： @RequestParam
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id", required = false) Integer deptId){
//        System.out.println("删除部门id为：" + deptId);
//        return Result.success();
//    }

    //方式三
    @Log
    @DeleteMapping()
    public Result delete(Integer id){
        log.info("删除部门id为：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @Log
    @PostMapping()
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据id查询部门
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id")Integer deptId){
//        System.out.println("查询部门id为：" + deptId);
//        return Result.success();
//    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询部门id为：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门的方法
    @Log
    @PutMapping()
    public Result update(@RequestBody Dept dept){
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
