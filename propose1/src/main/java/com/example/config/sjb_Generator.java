package com.example.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 描述: sjb_Generator
 * 公司: 瑞华康源科技有限公司
 * 版权: rivamed2022
 *
 * @author sjb
 * @version V1.1.2
 * @date 2022:07:08 11:10:15
 */
@Component
public class sjb_Generator {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://192.168.111.208:3306/sjb?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2b8&useSSL=false", "root", "123456");

    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("sunjiabao") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\IdeaProjects\\propose1\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
//                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\IdeaProjects\\propose1\\src\\main\\java\\com\\example\\mapper\\xml\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

//    public static void main(String[] args) {
//        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
//                // 开启fileOverrride重新旧文件，disableOpenDir禁用代码生成后打开输出目录对话框
//                .globalConfig(builder -> builder.author("sunjiabao").fileOverride().disableOpenDir().outputDir("D:\\IdeaProjects\\propose1\\src\\main\\java"))
//                // parent指定生成的代码在哪个包下，entity可以指定实体(DO)所在的包名
//                .packageConfig(builder -> builder.parent("com.example").entity("pojo.sjb_mybatisplus"))
//                // addInclude指定包含的表名，不调用该方法默认为所有表生成代码；addTablePrefix可以过滤表前缀，即t_user变成user
//                .strategyConfig(builder -> builder.addInclude("user").addTablePrefix("t_")
//                        // 禁用为实体类生成序列化ID；formatFileName格式化生成的实体类名称，即t_user -> UserDO
//                        .entityBuilder().disableSerialVersionUID().formatFileName("%sDO")
//                        // formatMapperFileName格式化Mapper接口名称，即t_user -> UserMapper
//                        // formatXmlFileName格式化Mapper.xml文件名称，即t_user -> UserMapper.xml
//                        .mapperBuilder().formatMapperFileName("%sMapper").formatXmlFileName("%sMapper")
//                        .build()
//                )
//                // MyBatis-Plus代码生成器是通过模板引擎来渲染文件的，默认模板引擎是Velocity，根据依赖我们使用Freemarker
//                .templateEngine(new FreemarkerTemplateEngine())
//                .execute();
//    }


}
