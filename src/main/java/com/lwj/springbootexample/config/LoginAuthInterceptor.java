package com.lwj.springbootexample.config;

import com.alibaba.fastjson.JSONObject;
import com.lwj.springbootexample.enumeration.Auth;
import com.lwj.springbootexample.exception.ServiceException;
import org.springframework.core.NamedInheritableThreadLocal;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    /**
     * 线程内变量: 用户信息
     */
    private static final ThreadLocal<JSONObject> X_TOKEN_USER = new NamedInheritableThreadLocal<>("X_TOKEN_USER");

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String BEARER = "Bearer ";

    private static final String USER_TOKEN = "USER_TOKEN";

    private static final String AUTHORIZATION = "Authorization";


    public static String getUserNickName(){
        return X_TOKEN_USER.get() == null ? null : X_TOKEN_USER.get().getString("nickname");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = null;
        String authorization = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith(BEARER)) { // Bearer token
            token = authorization.substring(7);
        }
        if (ObjectUtils.isEmpty(authorization)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (USER_TOKEN.equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }
        if (ObjectUtils.isEmpty(token)) {
            throw ServiceException.UNAUTHORIZED;
        }
        JSONObject user = JSONObject.parseObject(stringRedisTemplate.opsForValue().get(token));
        if (ObjectUtils.isEmpty(user)) {
            throw ServiceException.UNAUTHORIZED;
        }
        boolean isAuth = isAuthMethod(request, handler, user);
        if (!isAuth) {
            throw ServiceException.UNAUTHORIZED;
        }
        X_TOKEN_USER.set(user);
        return true;
    }

    private boolean isAuthMethod(HttpServletRequest request, Object handler, JSONObject user) {
        if (!(handler instanceof HandlerMethod)) return false;
        Auth classAuth = AnnotationUtils.findAnnotation(((HandlerMethod) handler).getBeanType(), Auth.class);
        if (Objects.isNull(classAuth)) return true;
        Auth methodAuth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
        if (Objects.isNull(methodAuth)) return false;
        int authValue = methodAuth.value();
        if (authValue == -1) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        X_TOKEN_USER.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
