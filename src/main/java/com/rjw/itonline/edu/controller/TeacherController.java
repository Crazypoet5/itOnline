package com.rjw.itonline.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjw.itonline.common.utils.Result;
import com.rjw.itonline.edu.entity.Teacher;
import com.rjw.itonline.edu.entity.vo.TeacherQueryVo;
import com.rjw.itonline.edu.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 饶嘉伟
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    TeacherServiceImpl tsi;

    @GetMapping("/list")

    /**
     * @Author: 饶嘉伟
     * @Description:
     * @Date: 2020/12/13 16:09
     * @Param: []
     * @return: com.rjw.itonline.common.utils.Result
     **/
    public Result list() {
        return Result.ok ().data ("items", tsi.list ()).message ("获取讲师列表成功");
    }
    @GetMapping("/list/{page}/{limit}")
    //注意表单传参可以不需要任何参数
    /**
     * @Author: 饶嘉伟
     * @Description: 在老师哪里的多条件查询,被封装在了teacher中
     * @Date: 2020/12/13 16:08
     * @Param: [page, limit, teacher]
     * @return: com.rjw.itonline.common.utils.Result
     **/
    public Result listPage(@PathVariable("page") Long page, @PathVariable("limit") Long limit, TeacherQueryVo teacher) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<> ();

        if (teacher.getName () != null) {
            teacherQueryWrapper.eq ("name", teacher.getName ());
        }
        if (teacher.getJoinDateBegin () != null) {
            teacherQueryWrapper.ge ("join_date", teacher.getJoinDateBegin ());
        }
        if (teacher.getJoinDateEnd () != null) {
            teacherQueryWrapper.lt ("join_date", teacher.getJoinDateEnd ());
        }
        if (teacher.getLevel () != null) {
            teacherQueryWrapper.eq ("level", teacher.getLevel ());
        }
        Page<Teacher> pageParam = new Page<> (page, limit);
        IPage<Teacher> pageModel = tsi.page (pageParam, teacherQueryWrapper);
        List<Teacher> records = pageModel.getRecords ();
        long total = pageModel.getTotal ();
        return Result.ok ().data ("total", total).data ("rows", records);
    }
    @GetMapping("/list/name/{name}")
    /**
     * @Author: 饶嘉伟
     * @Description: 根据名字的模糊查询
     * @Date: 2020/12/13 16:09
     * @Param: [name]
     * @return: com.rjw.itonline.common.utils.Result
     **/
    public Result getListByName(@PathVariable("name") String name) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<> ();
        teacherQueryWrapper.select ("name");
        teacherQueryWrapper.likeRight("name",name);
        List<Map<String, Object>> mapList = tsi.listMaps (teacherQueryWrapper);
        return Result.ok ().data ("nameList",mapList);
    }
    @DeleteMapping("remove/{id}")
    /**
     * @Author: 饶嘉伟
     * @Description: 根据id删除
     * @Date: 2020/12/13 16:10
     * @Param: [id]
     * @return: com.rjw.itonline.common.utils.Result
     **/
    public Result removedById(@PathVariable Long id) {
        boolean f = tsi.removeById (id);
        return Result.ok ().success (f);
    }
    @DeleteMapping("batch-remove")
    /**
     * @Author: 饶嘉伟
     * @Description: 提供一个idList，根据idList中的id列表进行批量删除
     * @Date: 2020/12/13 16:10
     * @Param: [idList]
     * @return: com.rjw.itonline.common.utils.Result
     **/
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean f = tsi.removeByIds (idList);
        return Result.ok ().success (f);
    }
    @PostMapping("save")
    /**
     * @Author: 饶嘉伟
     * @Description: 负责将教师保存至数据库
     * @Date: 2020/12/13 16:11
     * @Param: [teacher]
     * @return: com.rjw.itonline.common.utils.Result
     **/
    public Result save(@RequestBody Teacher teacher){
        return Result.ok().success (tsi.save (teacher));
    }
    @GetMapping("get/{id}")

    /**
     * @Author: 饶嘉伟
     * @Description: 根据id保存
     * @Date: 2020/12/13 16:13
     * @Param: [id]
     * @return: com.rjw.itonline.common.utils.Result
     **/
    public Result save(@PathVariable Long id){
        return Result.ok().data ("item",tsi.getById (id));
    }


    @PostMapping("update")
    /**
     * @Author: 饶嘉伟
     * @Description: 更新
     * @Date: 2020/12/13 16:13
     * @Param: [teacher]
     * @return: com.rjw.itonline.common.utils.Result
     **/
    public Result update(@RequestBody Teacher teacher){
        return Result.ok ().success ( tsi.updateById (teacher));
    }
}

