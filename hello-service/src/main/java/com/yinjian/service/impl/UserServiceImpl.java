package com.yinjian.service.impl;

import com.yinjian.domain.UserDO;
import com.yinjian.manger.UserManager;
import com.yinjian.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yin Jian
 * @create: 2019-01-17 10:44
 */
@Service
public class UserServiceImpl implements UserService {
    private UserManager userManager;

    public UserServiceImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public List<UserDO> listGetUser() {
        return userManager.listGetUser();
    }
}
