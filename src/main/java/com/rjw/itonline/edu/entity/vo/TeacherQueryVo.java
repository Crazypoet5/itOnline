package com.rjw.itonline.edu.entity.vo;

import lombok.Data;

/**
 * @program: itonline
 * @description: 给讲师条件查询专用
 * @author: 饶嘉伟
 * @create: 2020-12-09 19:47
 **/

@Data
public class TeacherQueryVo {
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer level;
    private String joinDateBegin;
    private String joinDateEnd;
}
