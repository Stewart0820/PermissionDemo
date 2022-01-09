package com.stewart.web;

import com.stewart.web.component.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Stewart
 * @create 2022/1/9
 * @Description
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class PermissionText {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Test
    public void Text() {
        String token = jwtTokenUtil.generateToken("123456");
        log.error(token);

        String username = jwtTokenUtil.getUserNameFromToken(token);
        log.error(username);
    }
}
