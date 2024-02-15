package com.shihui;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url="jdbc:mysql://rm-cn-wwo3lxblv000livo.rwlb.rds.aliyuncs.com:3306/testdb";
        String username="shihui";
        String password="Shihui666";
        String moduleName="fd";
        String mapperLocation="E:\\zzcspring\\zzctest\\src\\main\\resources\\mapper\\"+moduleName;
        String tables="browse_history,dish,evaluation,merchant,ownership,store,user,user_favorite_dish,user_like_dish,";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("shihui") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\zzcspring\\zzctest\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.shihui") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables); // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
