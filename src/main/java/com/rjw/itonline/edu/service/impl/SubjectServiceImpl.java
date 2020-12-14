package com.rjw.itonline.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.rjw.itonline.edu.entity.ExcelSubjectData;
import com.rjw.itonline.edu.entity.Subject;
import com.rjw.itonline.edu.entity.vo.SubjectVo;
import com.rjw.itonline.edu.listener.ExcelSubjectDataListener;
import com.rjw.itonline.edu.mapper.SubjectMapper;
import com.rjw.itonline.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 饶嘉伟
 * @since 2020-12-08
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    public void batchImport(InputStream inputStream){
        // 这里需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, ExcelSubjectData.class, new ExcelSubjectDataListener (baseMapper))
                .excelType(ExcelTypeEnum.XLS).sheet().doRead();
    }
   public List<SubjectVo> nestedList(){
        return baseMapper.selectNestedListByParentId ("0");
   }
}
