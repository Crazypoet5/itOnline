package com.rjw.itonline.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjw.itonline.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjw.itonline.edu.entity.vo.CoursePublishVo;
import com.rjw.itonline.edu.entity.vo.CourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 饶嘉伟
 * @since 2020-12-08
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> selectPageByCourseQueryVo(
            Page<CourseVo> pageParam,
            //mp会自动组装queryWrapper：
            //@Param(Constants.WRAPPER) 和 xml文件中的 ${ew.customSqlSegment} 对应
            @Param(Constants.WRAPPER) QueryWrapper<CourseVo> queryWrapper);
    CoursePublishVo selectCoursePublishVoById(String id);
}
