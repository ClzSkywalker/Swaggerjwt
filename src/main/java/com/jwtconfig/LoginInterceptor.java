package com.jwtconfig;

import com.constants.JwtConstants;
import com.utils.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：
 * 〈登录拦截器〉
 *
 * @author zuiren
 * @create 2019/9/28
 * @since 1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //解决跨域问题
        response.setHeader("Access-Control-Allow-Origin","*");
        String token=request.getHeader(JwtConstants.JWT_KEY_NAME);

        //判断 token 是否为空或者存在空格
        if (StringUtils.isBlank(token)){
            response.sendError(401);
            return false;
        }
        Claims claims=JwtHelper.parseJWT(token);
        JwtHelper.setCurrentClaims(claims);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        JwtHelper.setCurrentClaims(null);
    }
}
