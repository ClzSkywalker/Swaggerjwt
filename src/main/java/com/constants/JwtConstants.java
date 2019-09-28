package com.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * 〈〉
 *
 * @author zuiren
 * @create 2019/9/27
 * @since 1.0.0
 */
public class JwtConstants {
    /**
     * 服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
     */
    public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";

    /**
     * 有效期7天
     */
    public static final long JWT_TTL = 7L * 24L * 60L * 60L * 1000L;

    /**
     * Token的名字
     */
    public static final String JWT_KEY_NAME = "Authorization";
}
