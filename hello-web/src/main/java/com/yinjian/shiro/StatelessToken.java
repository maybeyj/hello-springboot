package com.yinjian.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.Map;

/**
 * @author: Yin Jian
 * @create: 2019-01-22 14:14
 */
public class StatelessToken implements AuthenticationToken {
    private String token;

    public StatelessToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
