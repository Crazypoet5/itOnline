package com.rjw.itonline.edu.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: itonline
 * @description: 利用excel分类课表
 * @author: 饶嘉伟
 * @create: 2020-12-13 21:00
 **/
@Data
public class ExcelSubjectData {

    @ExcelProperty(value = "一级分类")
    private String levelOneTitle;
    @ExcelProperty(value = "二级分类")
    private String levelTwoTitle;

}
