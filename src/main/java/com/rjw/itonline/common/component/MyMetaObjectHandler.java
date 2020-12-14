package com.rjw.itonline.common.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: itonline
 * @description:
 * @author: 饶嘉伟
 * @create: 2020-12-10 16:20
 **/


@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName ("gmtCreate", new Date (), metaObject);
        this.setFieldValByName ("gmtModified", new Date (), metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName ("gmtModified", new Date (), metaObject);
    }
}
