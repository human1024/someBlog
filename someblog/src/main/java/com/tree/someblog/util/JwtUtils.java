package com.tree.someblog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author human1024
 * @Date 2020/6/15 13:01
 */
@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "jwt.properties")
public class JwtUtils {

    /**
     * 密钥，服务器私有，这里都是可以在配置文件中修改的
     */
    private String secret;
    /**
     * 过期时间
     */
    private long expire;
    /**
     * Headr头信息
     * 判断用户是否要登录，检测header里面是否包含Authorization字段即可
     */
    private String header;

    /**
     * 根据用户的id生成的token
     */
    public String generateToken(long userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 解析token，取出里面的body
     * @param token
     * @return 解码得到原数据
     */
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
