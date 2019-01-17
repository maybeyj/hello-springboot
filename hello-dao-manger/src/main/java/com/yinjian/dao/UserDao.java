package com.yinjian.dao;

import com.yinjian.domain.UserDO;

import java.util.List;

/**
 * @author: Yin Jian
 * @create: 2019-01-17 10:32
 */
public interface UserDao {
    List<UserDO> listGetUser();
}
