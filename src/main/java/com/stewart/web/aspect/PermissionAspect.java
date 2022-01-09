package com.stewart.web.aspect;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.stewart.web.annotation.PermissionCheck;
import com.stewart.web.common.R;
import com.stewart.web.common.ResultStatus;
import com.stewart.web.component.JwtTokenUtil;
import com.stewart.web.mbg.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author 陈鸿杰
 * @create 2022/1/9
 * @Description
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class PermissionAspect {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IUserService userService;
    /**
     *     切入点表达式决定了用注解方式的方法切还是针对某个路径下的所有类和方法进行切，
     *     方法必须是返回void类型
     */
    @Pointcut("@annotation(com.stewart.web.annotation.PermissionCheck)")
    private void permissionCheckCut(){};

    /**
     * 定义了切面的处理逻辑。即方法上加了@PermissionCheck
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("permissionCheckCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("==========进入AOP============================");
        //1.记录日志信息
        Signature signature = pjp.getSignature();
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        log.info("className:{},methodName:{}",className,methodName);

        //2.角色权限校验
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        if(targetMethod.isAnnotationPresent(PermissionCheck.class)){
            //获取方法上注解中表明的权限
            PermissionCheck permission = (PermissionCheck)targetMethod.getAnnotation(PermissionCheck.class);
            String role = permission.role();
            log.info("当前接口请求的用户角色role:{}",role);
            if(StringUtils.isNotEmpty(role)){
                //接口允许的角色
                String[] roles = role.split(",");
                List<String> list = Arrays.asList(roles);
                //如果该接口只允许老师角色访问。则要获取当前用户是不是老师角色。
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String token = request.getHeader("Authorization");
                String account = jwtTokenUtil.getUserNameFromToken(token);
                //用户的角色
                String userRole = userService.getUserRole(account);
                if(list.contains(userRole)){
                    log.info("AOP权限角色校验通过，进入业务层处理！");
                    //3.执行业务逻辑，放行
                    return pjp.proceed();
                }else{
                    //如果没有权限,抛出异常,由Spring框架捕获,跳转到错误页面
                    return R.error(ResultStatus.ERROR);
                }
            }
        }
        return R.error(ResultStatus.ERROR);
    }
}
