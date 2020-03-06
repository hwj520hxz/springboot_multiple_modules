package com.hwj.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：项目启动类
 * SpringBootApplication：禁止自动注入数据源的配置
 * mapper的扫描配置在MybatisConfig类中，所以不需要加@MapperScan再次扫描mapper
 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class Startup {
    public static void main(String[] args){
        SpringApplication.run(Startup.class,args);

    }
}
