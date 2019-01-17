package com.yinjian.controller;

import com.yinjian.domain.UserDO;
import com.yinjian.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Yin Jian
 * @create: 2019-01-17 10:37
 */
@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;
    private final Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list")
    public List<UserDO> listGetUser() {
        logger.info("进入listGetUser方法");
        List<UserDO> userDOS = userService.listGetUser();
        logger.info("返回结果集:{}",userDOS);
        return userDOS;
    }

}
