package com.stewart.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 陈鸿杰
 * @create 2022/1/9
 * @Description
 */
@SpringBootApplication
@MapperScan("com.stewart.web.mbg.mapper")
public class PermissionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class,args);
    }
}
