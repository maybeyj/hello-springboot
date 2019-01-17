package com.yinjian.manger.impl;

import com.yinjian.dao.UserDao;
import com.yinjian.domain.UserDO;
import com.yinjian.manger.UserManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Yin Jian
 * @create: 2019-01-17 10:43
 */
@Component
public class UserManagerImpl implements UserManager {
    private UserDao userDao;

    public UserManagerImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDO> listGetUser() {
        return userDao.listGetUser();
    }
}
