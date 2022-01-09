package com.stewart.web.mbg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.stewart.web.common.R;
import com.stewart.web.common.ResultStatus;
import com.stewart.web.component.JwtTokenUtil;
import com.stewart.web.mbg.mapper.RoleMapper;
import com.stewart.web.mbg.mapper.UserRoleMapper;
import com.stewart.web.mbg.pojo.Role;
import com.stewart.web.mbg.pojo.User;
import com.stewart.web.mbg.mapper.UserMapper;
import com.stewart.web.mbg.pojo.UserRole;
import com.stewart.web.mbg.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stewart.web.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Stewart
 * @since 2022-01-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 根据用户的账号获取对应的角色
     * @param account
     * @return
     */
    @Override
    public String getUserRole(String account) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("account", account));
        UserRole userRole = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
        Role role = roleMapper.selectById(userRole.getId());
        return role.getName() ;
    }

    /**
     * 用户登录
     * @param param
     * @return
     */
    @Override
    public R login(LoginParam param) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("account", param.getAccount()
        ).eq("password", param.getPassword()));
        if (user==null){
            return R.error(ResultStatus.ERROR);
        }
        String token = jwtTokenUtil.generateToken(user.getAccount());
        return R.ok(ResultStatus.LOGIN_SUCCESS,token);
    }
}
