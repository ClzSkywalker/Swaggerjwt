package com.controller;

import com.jwtconfig.JwtHelper;
import com.response.GenericResponse;
import com.response.MyException;
import com.response.ResponseFormat;
import com.domain.User;
import com.service.IUserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

/**
 * 描述：
 * 〈〉
 *
 * @author zuiren
 * @create 2019/9/26
 * @since 1.0.0
 */
@RestController
@Api(value = "testController",tags = {"测试"})
@RequestMapping("/api")
public class testController {
    @Autowired
    private IUserService userService;

    private static final String APPLICATION_JSON_UTF8_VALUE="application/json;charset=UTF-8";

    @RequestMapping(name = "测试jwt查找",path = "/findByName",method = RequestMethod.GET,produces = APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse findByName(@RequestParam(value = "用户名",required = true) String username){
        User user;
        try {
            //request.getParameter("username")
            user=userService.findUserByName(username);
        }catch (Exception e){
            throw new MyException(5004,e);
        }
        if (user!=null){
            return ResponseFormat.retParam(200,user);
        }
        return ResponseFormat.retParam(50001,"");
    }

    @RequestMapping(name = "得到 Claim",method = RequestMethod.GET,produces = APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse getClaim(
            HttpServletResponse response, HttpServletRequest request){
        String jwt=request.getHeader("Authorization");
        Claims claims;
        try {
            claims= JwtHelper.parseJWT(jwt);
        }catch (Exception e){
            throw new MyException(-1001,e);
        }
        return ResponseFormat.retParam(200,claims);



    }
}
