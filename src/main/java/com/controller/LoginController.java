package com.controller;

import com.constants.JwtConstants;
import com.jwtconfig.JwtHelper;
import com.response.GenericResponse;
import com.response.MyException;
import com.response.ResponseFormat;
import com.domain.UserLogin;
import com.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 * 〈〉
 *
 * @author zuiren
 * @create 2019/9/26
 * @since 1.0.0
 */
@RestController
@Api(value = "LoginController",tags = {"用户登录"})
@RequestMapping("/loginController")
public class LoginController {
    @Autowired
    private IUserService userService;

    private static final String APPLICATION_JSON_UTF8_VALUE="application/json;charset=UTF-8";

    @RequestMapping(name = "登陆",path = "/login",method = RequestMethod.POST,produces = APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse login(@RequestBody UserLogin userLogin){
        int exits=0;
        try {
            exits=userService.loginUser(userLogin);
        }catch (Exception e){
            throw new MyException(50004,e);
        }
        if (exits==1){
            return ResponseFormat.retParam(200, JwtHelper.createJWT("login",userLogin.getUsername(), JwtConstants.JWT_TTL));
        }else {
            return ResponseFormat.retParam(20002, "");
        }
    }
}
