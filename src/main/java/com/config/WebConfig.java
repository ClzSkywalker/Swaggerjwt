package com.config;

import com.constants.InterceptorProperties;
import com.jwtconfig.LoginInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述：
 * 〈〉
 *
 * @author zuiren
 * @create 2019/9/28
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(InterceptorProperties.class)
public class WebConfig implements WebMvcConfigurer {
    /**
     * 配置文件类
     */
    private final InterceptorProperties interceptorProperties;

    /**
     * 获取配置的构造方法
     * @param interceptorProperties
     */
    public WebConfig(InterceptorProperties interceptorProperties) {
        this.interceptorProperties = interceptorProperties;
    }

    /**
      *拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(interceptorProperties.getLoginInterceptorExcludePath().toArray(new String[]{}));
    }
}
