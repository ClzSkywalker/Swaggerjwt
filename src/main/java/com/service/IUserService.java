package com.service;

import com.domain.User;
import com.domain.UserLogin;

/**
 * 描述：
 * 〈〉
 *
 * @author zuiren
 * @create 2019/9/26
 * @since 1.0.0
 */
public interface IUserService {
    int loginUser(UserLogin userLogin);

    User findUserByName(String username);
}
