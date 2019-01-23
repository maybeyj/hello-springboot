package com.yinjian.controller;

import com.yinjian.domain.sys.UserDO;
import com.yinjian.service.UserService;
import com.yinjian.shiro.JwtUtil;
import com.yinjian.shiro.StatelessToken;
import com.yinjian.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Yin Jian
 * @create: 2019-01-17 10:37
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户api")
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

    @PostMapping("login")
    @ApiOperation(value = "登陆api")
    public ResponseEntity<String> login(@RequestBody UserVO userVO, HttpServletResponse response){
        logger.info("登陆");
        Subject subject = SecurityUtils.getSubject();
        String token = JwtUtil.sign(userVO.getUserName(), userVO.getPassword());
        subject.login(new StatelessToken(token));
        response.addHeader("token",token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
