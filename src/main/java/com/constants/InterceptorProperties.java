package com.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 描述：
 * 〈获取 yaml 中拦截的白名单配置〉
 *
 * @author zuiren
 * @create 2019/9/28
 * @since 1.0.0
 */
@ConfigurationProperties("login-interceptor")
public class InterceptorProperties {
    private List<String> loginInterceptorExcludePath;

    public List<String> getLoginInterceptorExcludePath() {
        return loginInterceptorExcludePath;
    }

    public void setLoginInterceptorExcludePath(List<String> loginInterceptorExcludePath) {
        this.loginInterceptorExcludePath = loginInterceptorExcludePath;
    }
}
