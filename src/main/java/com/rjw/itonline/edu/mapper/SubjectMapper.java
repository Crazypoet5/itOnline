package com.rjw.itonline.edu.mapper;

import com.rjw.itonline.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjw.itonline.edu.entity.vo.SubjectVo;
import java.util.List;
/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author 饶嘉伟
 * @since 2020-12-08
 */
public interface SubjectMapper extends BaseMapper<Subject> {
    List<SubjectVo> selectNestedListByParentId(String parentId);
}
