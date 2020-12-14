package com.rjw.itonline.edu.service;

import com.rjw.itonline.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rjw.itonline.edu.entity.vo.SubjectVo;
import java.util.List;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 * @author 饶嘉伟
 * @since 2020-12-08
 */
public interface SubjectService extends IService<Subject> {
    void batchImport(InputStream inputStream);
    List<SubjectVo> nestedList();
}
