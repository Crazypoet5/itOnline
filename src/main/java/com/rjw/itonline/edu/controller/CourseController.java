package com.rjw.itonline.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.rjw.itonline.common.utils.Result;
import com.rjw.itonline.edu.entity.form.CourseInfoForm;
import com.rjw.itonline.edu.entity.vo.CoursePublishVo;
import com.rjw.itonline.edu.entity.vo.CourseQueryVo;
import com.rjw.itonline.edu.entity.vo.CourseVo;
import com.rjw.itonline.edu.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 饶嘉伟
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/admin/edu/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("save-course-info")
    public Result saveCourseInfo(
            @RequestBody CourseInfoForm courseInfoForm) {
        String courseId = courseService.saveCourseInfo (courseInfoForm);
        return Result.ok ().data ("courseId", courseId).message ("保存成功");
    }

    @ApiOperation("根据ID查询课程")
    @GetMapping("course-info/{id}")
    public Result getById(
            @PathVariable String id) {
        CourseInfoForm courseInfoForm = courseService.getCourseInfoById (id);
        if (courseInfoForm != null) {
            return Result.ok ().data ("item", courseInfoForm);
        } else {
            return Result.error ().message ("数据不存在");
        }
    }

    @ApiOperation("更新课程")
    @PutMapping("update-course-info")
    public Result updateCourseInfoById(
            @RequestBody CourseInfoForm courseInfoForm) {
        courseService.updateCourseInfoById (courseInfoForm);
        return Result.ok ().message ("修改成功");
    }

    @ApiOperation("分页课程列表")
    @GetMapping("list/{page}/{limit}")
    public Result index(

            @PathVariable Long page, // 当前页码
            @PathVariable Long limit,//每页记录数//
            CourseQueryVo courseQueryVo//前端传过来的对象
    ) {
        IPage<CourseVo> pageModel = courseService.selectPage (page, limit, courseQueryVo);
        List<CourseVo> records = pageModel.getRecords ();
        long total = pageModel.getTotal ();
        return Result.ok ().data ("total", total).data ("rows", records);
    }

    @ApiOperation("根据ID删除课程")
    @DeleteMapping("remove/{id}")
    public Result removeById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id) {
        //TODO 删除视频：VOD
        //在此处调用vod中的删除视频文件的接口
        //删除封面：OSS
        //courseService.removeCoverById(id);
        //删除课程
        boolean result = courseService.removeCourseById (id);
        if (result) {
            return Result.ok ().message ("删除成功");
        } else {
            return Result.error ().message ("数据不存在");
        }
    }
    @GetMapping("course-publish/{id}")
    public Result getCoursePublishVoById(
            @PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);
        if (coursePublishVo != null) {
            return Result.ok().data("item", coursePublishVo);
        } else {
            return Result.error().message("数据不存在");
        }
    }
    @ApiOperation("根据id发布课程")
    @PutMapping("publish-course/{id}")
    public Result publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id){
        boolean result = courseService.publishCourseById(id);
        if (result) {
            return Result.ok().message("发布成功");
        } else {
            return Result.error().message("数据不存在");
        }
    }

}
