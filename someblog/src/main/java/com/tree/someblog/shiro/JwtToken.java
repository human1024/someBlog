package com.tree.someblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author human1024
 * @Date 2020/6/15 13:19
 */
public class JwtToken implements AuthenticationToken {

    /**
     * 密钥
     */
    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    /**
     * 身份和凭证都是token
     * @return token
     */
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
