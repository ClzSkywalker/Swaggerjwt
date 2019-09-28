package com.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 描述：
 * 〈登陆模板〉
 *
 * @author zuiren
 * @create 2019/9/25
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class UserLogin {
    @ApiModelProperty(value="用户名", required = true)
    private String username;
    @ApiModelProperty(value="用户密码", required = true)
    private String password;
}
