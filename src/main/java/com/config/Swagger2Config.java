package com.config;

import com.constants.InterceptorProperties;
import com.constants.JwtConstants;
import com.google.common.base.Predicate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spi.service.contexts.SecurityContextBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/**
 * 描述：
 * 〈swagger 配置〉
 *
 * @author zuiren
 * @create 2019/9/28
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(InterceptorProperties.class)
public class Swagger2Config {
    private InterceptorProperties interceptorProperties;

    public Swagger2Config(InterceptorProperties interceptorProperties) {
        this.interceptorProperties = interceptorProperties;
    }

    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 指定api路径
                .apis(RequestHandlerSelectors.basePackage("com.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("swagger demo")
                .description("swagger demo")
                .termsOfServiceUrl("https://github.com/cjbi")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes(){
        return Arrays.asList(new ApiKey("Authorization","Authorization","header"));
    }

    private List<SecurityContext> securityContexts(){
        SecurityContextBuilder builder=SecurityContext.builder().securityReferences(securityReferences());
        //指定需要认证的 path ，大写的注意，这里就用到了配置文件面的 URL ，需要自己实现 path 选择的逻辑
        builder.forPaths(forExcludeAntPaths(interceptorProperties.getLoginInterceptorExcludePath()));
        return Arrays.asList(builder.build());
    }

    /**
     * 匹配登录拦截器过滤地址
     * @param antPatterns - ant Patterns
     * @return predicate that matches a particular ant pattern
     */
    private Predicate<String> forExcludeAntPaths(final List<String> antPatterns){
        return (input)->{
            //使用 spring 的 ant 路径配置
            AntPathMatcher matcher=new AntPathMatcher();
            //如不不是白名单的 URL ，就需要认证
            return antPatterns.stream().noneMatch(antPattern->matcher.match(antPattern,input));
        };
    }

    /**
     * 这个方法是验证的作用域，不能漏了
     */
    private List<SecurityReference> securityReferences(){
        AuthorizationScope authorizationScope=new AuthorizationScope("global","accessEverything");
        return Arrays.asList(new SecurityReference(JwtConstants.JWT_KEY_NAME,new AuthorizationScope[]{authorizationScope}));
    }
}
