package com.stewart.web.mbg.controller;

import com.stewart.web.annotation.PermissionCheck;
import com.stewart.web.common.R;
import com.stewart.web.common.ResultStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Stewart
 * @create 2022/1/9
 * @Description
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @PermissionCheck(role = "admin")
    @GetMapping("/hello")
    public R hello() {
        return R.ok(ResultStatus.SUCCESS);
    }
}
