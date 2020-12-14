package com.rjw.itonline;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.rjw.itonline.common.component.ALiOssComponent;
import com.rjw.itonline.common.utils.Result;
import com.rjw.itonline.edu.entity.Teacher;
import com.rjw.itonline.edu.service.impl.TeacherServiceImpl;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
@RunWith (SpringRunner.class)
class ItonlineApplicationTests {

//    @Test
//    void contextLoads() {
//        String prefix = "dbxxx_";
//        String moduleName = "edu";
//        // 1、创建代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//        // 2、全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("饶嘉伟");
//        gc.setOpen(false); //生成后是否打开资源管理器
////        gc.setFileOverride(false); //重新生成时文件是否覆盖
//        gc.setServiceName("%sService"); //去掉Service接口的首字母I
//        gc.setIdType(IdType.ASSIGN_ID); //主键策略
//        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
//        gc.setSwagger2(true);//开启Swagger2模式
//        mpg.setGlobalConfig(gc);
//        // 3、数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/itonline?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("admin");
//        dsc.setDbType(DbType.MYSQL);
//        mpg.setDataSource(dsc);
//        // 4、包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(moduleName); //模块名
//        pc.setParent("com.rjw.itonline");
//        pc.setController("controller");
//        pc.setEntity("entity");
//        pc.setService("service");
//        pc.setMapper("mapper");
//        mpg.setPackageInfo(pc);
//        // 5、策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
//        strategy.setTablePrefix(moduleName + "_");//设置表前缀不生成
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
//        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
//        strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
//        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀
//        //自动填充
//        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
//        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
//        ArrayList<TableFill> tableFills = new ArrayList<>();
//        tableFills.add(gmtCreate);
//        tableFills.add(gmtModified);
//        strategy.setTableFillList(tableFills);
//        strategy.setRestControllerStyle(true); //restful api风格控制器
//        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
//        mpg.setStrategy(strategy);
//        // 6、执行
//        mpg.execute();
//    }

    @Autowired
    TeacherServiceImpl tsi;
    @Test
    void testInsert(){

        Page<Teacher> pageParam = new Page<>(1, 5);

        IPage<Teacher> pageModel = tsi.page(pageParam);

        List<Teacher> records = pageModel.getRecords();

        long total = pageModel.getTotal();
        records.forEach (System.out::println);
        System.out.println (total);
    }
    @Autowired
    ALiOssComponent asc;
    @Test
    void TestInsertProperty(){
        System.out.println (asc.getEndpoint ());
    }
    @Test
    void mockData(){

    }
}
