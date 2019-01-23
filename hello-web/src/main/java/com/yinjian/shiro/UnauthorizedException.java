package com.yinjian.shiro;

/**
 * @author: Yin Jian
 * @create: 2019-01-22 13:59
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
