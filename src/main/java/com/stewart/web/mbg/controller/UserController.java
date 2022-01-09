package com.stewart.web.mbg.controller;


import com.stewart.web.common.R;
import com.stewart.web.mbg.service.IUserService;
import com.stewart.web.param.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Stewart
 * @since 2022-01-09
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户登录获取token")
    @PostMapping("/login")
    public R login(@RequestBody LoginParam param) {
        return userService.login(param);
    }
}
