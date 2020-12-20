package com.rjw.itonline.edu.service;

import com.rjw.itonline.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rjw.itonline.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 饶嘉伟
 * @since 2020-12-08
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
