package com.yinjian.manger;

import com.yinjian.domain.sys.UserDO;

import java.util.List;

/**
 * @author: Yin Jian
 * @create: 2019-01-17 10:42
 */
public interface UserManager {
    List<UserDO> listGetUser();
    UserDO geuUserByUserName(String userName);
}
