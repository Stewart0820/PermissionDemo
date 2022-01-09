package com.stewart.web.annotation;

import java.lang.annotation.*;

/**
 * @author 陈鸿杰
 * @create 2022/1/9
 * @Description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionCheck {
    //自定义角色值，如果是多个角色，用逗号分割。
    public String role() default "";
}
