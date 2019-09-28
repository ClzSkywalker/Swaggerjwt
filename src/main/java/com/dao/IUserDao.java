/**
 * FileName: UserDao
 * Author:   zuiren
 * Date:     2019/9/26 19:58
 * Description: 用户底层接口类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dao;

import com.domain.User;
import com.domain.UserLogin;
import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import javax.annotation.Resource;

/**
 * 描述：
 〈用户底层接口类〉
 * @author zuiren
 * @create 2019/9/26
 * @since 1.0.0
 */
@Repository
public interface IUserDao {
    /**
     * 用户登录
     * @param userLogin 登录模板
     * @return 0为登陆失败，1为登陆成功
     */
    int userLogin(UserLogin userLogin);

    /**
     * 通过名字查询用户
     * @param username
     * @return
     */
    User findUserByName(String username);
}
