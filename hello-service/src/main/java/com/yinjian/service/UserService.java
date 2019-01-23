package com.yinjian.service;


import com.yinjian.domain.sys.UserDO;

import java.util.List;

/**
 * @author: Yin Jian
 * @create: 2019-01-17 10:41
 */
public interface UserService {
    List<UserDO> listGetUser();
    UserDO login(String userName);
}
