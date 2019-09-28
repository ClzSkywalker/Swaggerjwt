package com.jwtconfig;

import com.constants.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 〈创建与解析 JWT〉
 *
 * @author zuiren
 * @create 2019/9/28
 * @since 1.0.0
 */
public class JwtHelper {
    /**
     * 登录信息
     */
    private static final ThreadLocal<Claims> currentClaims=new ThreadLocal<>();

    /**
     * 获得传输的信息
     * @return
     */
    public static Claims getCurrentClaims() {
        return currentClaims.get();
    }

    static void setCurrentClaims(Claims claims){
        currentClaims.set(claims);
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private static SecretKey generalKey(){
        //本地配置文件中加密的密文
        String stringKey= JwtConstants.JWT_KEY_NAME;
        //本地的密码解码
        byte[] encodeKey= Base64.decodeBase64(stringKey);
        // 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。
        SecretKey key=new SecretKeySpec(encodeKey,0,encodeKey.length,"AES");
        return key;
    }

    /**
     * 创建jwt
     *
     * @param id
     * @param subject
     * @param ttlMillis 过期的时间长度
     * @return
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        //指定签名的时候使用的签名算法，也就是 header 那部分，jjwt 已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成 jwt 时间
        long nowMills=System.currentTimeMillis();
        Date now=new Date(nowMills);
        //创建 payload 的私有声明 (根据特定的业务需要添加，如果要那这个做验证，一般是需要和 jwt 的接收方提前沟通好的验证方式的)
        Map<String,Object> claims=new HashMap<>(16);
        claims.put("uid",id);
        //生成签名的时候使用密钥 secret,这个方法本地封装了的，一般可以从本地配置文件中读取
        //切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key=generalKey();
        //下main就是在为 payload 添加各种标准的声明和私有声明了
        //这里其实就是 new 一个 jwtBuilder，设置 jwt 的 body
        JwtBuilder builder= Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有声明，
                // 这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                 .setClaims(claims)
                //设置jti(JWT ID):时 jwt 的唯一标识，根据业务需要，这个可以设置为一个不重复的的值
                //只要用来作为一次性 token,从而回避重放攻击
                .setId(id)
                //iat:jwt 的签发时间
                .setIssuedAt(now)
                //sub(subject):代表这个 jwt 的主题，即他的所有人，这个是一个 json 字符串
                //可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm,key);

        if (ttlMillis>=0){
            long expMills=nowMills+ttlMillis;
            Date exp=new Date(expMills);
            //设置过期时间
            builder.setExpiration(exp);
        }
        //就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
        return builder.compact();
        //打印了一哈哈确实是下面的这个样子
        //eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiIiLCJ1c2VyX25hbWUiOiJhZG1pbiIsIm5pY2tfbmFtZSI6IkRBU0RBMTIxIiwiZXhwIjoxNTE3ODI4MDE4LCJpYXQiOjE1MTc4Mjc5NTgsImp0aSI6Imp3dCJ9.xjIvBbdPbEMBMurmwW6IzBkS3MPwicbqQa2Y5hjHSyo
    }

    public static Claims parseJWT(String jwt){
        //签名密钥，和生成的密钥一摸一样
        SecretKey key=generalKey();
        //得到 DefaultJwtParser
        Claims claims=Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(key)
                //设置需要解析的jwt
                .parseClaimsJws(jwt).getBody();

        return claims;
    }
}
