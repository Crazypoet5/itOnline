package com.rjw.itonline.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rjw.itonline.edu.entity.*;
import com.rjw.itonline.edu.entity.form.CourseInfoForm;
import com.rjw.itonline.edu.entity.vo.CoursePublishVo;
import com.rjw.itonline.edu.entity.vo.CourseQueryVo;
import com.rjw.itonline.edu.entity.vo.CourseVo;
import com.rjw.itonline.edu.mapper.*;
import com.rjw.itonline.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 饶嘉伟
 * @since 2020-12-08
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CourseCollectMapper courseCollectMapper;

    @Override
    public boolean removeCourseById(String id) {
        //收藏信息：course_collect
        QueryWrapper<CourseCollect> courseCollectQueryWrapper = new QueryWrapper<> ();
        courseCollectQueryWrapper.eq ("course_id", id);
        courseCollectMapper.delete (courseCollectQueryWrapper);
        //评论信息：comment
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<> ();
        commentQueryWrapper.eq ("course_id", id);
        commentMapper.delete (commentQueryWrapper);
        //课时信息：video
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<> ();
        videoQueryWrapper.eq ("course_id", id);
        videoMapper.delete (videoQueryWrapper);
        //章节信息：chapter
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<> ();
        chapterQueryWrapper.eq ("course_id", id);
        chapterMapper.delete (chapterQueryWrapper);
        //课程详情：course_description
        courseDescriptionMapper.deleteById (id);
        //课程信息：course
        return this.removeById (id);
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {

        Course course = new Course();
        course.setId(id);
        //设置课程状态，随后更新课程
        course.setStatus(Course.COURSE_NORMAL);
        return this.updateById(course);
    }

    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        //从course表中取数据
        Course course = baseMapper.selectById (id);
        if (course == null) {
            return null;
        }
        //从course_description表中取数据
        CourseDescription courseDescription = courseDescriptionMapper.selectById (id);
        //创建courseInfoForm对象
        CourseInfoForm courseInfoForm = new CourseInfoForm ();
        BeanUtils.copyProperties (course, courseInfoForm);
        courseInfoForm.setDescription (courseDescription.getDescription ());
        return courseInfoForm;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        Course course = new Course ();
        BeanUtils.copyProperties (courseInfoForm, course);
        baseMapper.updateById (course);
        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription ();
        courseDescription.setDescription (courseInfoForm.getDescription ());
        courseDescription.setId (course.getId ());
        courseDescriptionMapper.updateById (courseDescription);
    }

    @Override
    public IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo) {
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<> ();
        queryWrapper.orderByDesc ("c.gmt_create");
        String title = courseQueryVo.getTitle ();
        String teacherId = courseQueryVo.getTeacherId ();
        String subjectParentId = courseQueryVo.getSubjectParentId ();
        String subjectId = courseQueryVo.getSubjectId ();
        if (!StringUtils.isEmpty (title)) {
            queryWrapper.like ("c.title", title);
        }
        if (!StringUtils.isEmpty (teacherId)) {
            queryWrapper.eq ("c.teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty (subjectParentId)) {
            queryWrapper.eq ("c.subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty (subjectId)) {
            queryWrapper.eq ("c.subject_id", subjectId);
        }
        Page<CourseVo> pageParam = new Page<> (page, limit);
        //放入分页参数和查询条件参数，mp会自动组装
        List<CourseVo> records = baseMapper.selectPageByCourseQueryVo (pageParam, queryWrapper);
        pageParam.setRecords (records);
        return pageParam;
    }

    @Transactional(rollbackFor = Exception.class)
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        Course course = new Course ();
        course.setStatus (Course.COURSE_DRAFT);
        BeanUtils.copyProperties (courseInfoForm, course);
        baseMapper.insert (course);
        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription ();
        courseDescription.setDescription (courseInfoForm.getDescription ());
        courseDescription.setId (course.getId ());
        courseDescriptionMapper.insert (courseDescription);
        return course.getId ();
    }
}
