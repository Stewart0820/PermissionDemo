package com.stewart.web.mbg.service;

import com.stewart.web.common.R;
import com.stewart.web.mbg.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stewart.web.param.LoginParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-09
 */
public interface IUserService extends IService<User> {

    /**
     * 通过账号获取用户的角色
     * @param account
     * @return
     */
    String getUserRole(String account);

    /**
     * 用户登录
     * @param param
     * @return
     */
    R login(LoginParam param);
}
