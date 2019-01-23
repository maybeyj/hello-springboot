package com.yinjian.shiro;

import com.yinjian.domain.sys.UserDO;
import com.yinjian.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Yin Jian
 * @create: 2019-01-22 09:41
 */
@Component
public class StatelessRealm extends AuthorizingRealm {

    private final UserService userService;
    @Autowired
    public StatelessRealm(UserService userService) {
        this.userService = userService;
    }

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持JWTToken类型的token
        return token instanceof StatelessToken;
    }

    /**
     * 认证
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        System.out.println("进入doGetAuthenticationInfo");
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String userName = JwtUtil.getUsername(token);
        if (userName == null) {
            throw new AuthenticationException("token invalid");
        }

        UserDO login = userService.login(userName);
        if (login == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JwtUtil.verify(token, userName, login.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        System.out.println("开始返回");
        return new SimpleAuthenticationInfo(token, token, getName());
    }


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserDO userDO  = (UserDO) principalCollection.getPrimaryPrincipal();
        for(RoleDO role:userDO.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;*/
        return null;
    }

    /**
     * 测试：salt加密
     *
     * @param args
     */
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("abc123", "salt");
        ByteSource bytes = ByteSource.Util.bytes("salt");
        System.out.println(md5Hash);
    }
}
