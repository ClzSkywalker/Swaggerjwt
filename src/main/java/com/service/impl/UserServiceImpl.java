package com.service.impl;

import com.dao.IUserDao;
import com.domain.User;
import com.domain.UserLogin;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：
 * 〈〉
 *
 * @author zuiren
 * @create 2019/9/26
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public int loginUser(UserLogin userLogin) {
        return userDao.userLogin(userLogin);
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }
}
