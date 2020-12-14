package com.rjw.itonline.edu.controller;



import com.rjw.itonline.common.utils.Result;
import com.rjw.itonline.edu.entity.vo.SubjectVo;
import com.rjw.itonline.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 饶嘉伟
 * @since 2020-12-08
 */
@CrossOrigin //允许跨域
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/admin/edu/subject")
@Slf4j
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //Excel批量导入课程分类
    @PostMapping("import")
    public Result batchImport(
            @ApiParam(value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            subjectService.batchImport(inputStream);
            return Result.ok().message("批量导入成功");
        } catch (Exception e) {
            log.error(e.getMessage ());
        }
        return Result.ok().message("批量导入失败");
    }
    @ApiOperation("嵌套数据列表")
    @GetMapping("nested-list")
    public Result nestedList(){
        List<SubjectVo> subjectVoList = subjectService.nestedList();
        return Result.ok().data("items", subjectVoList);
    }
}

