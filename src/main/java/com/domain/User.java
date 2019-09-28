package com.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.Date;

/**
 * 描述：
 * 〈〉
 *
 * @author zuiren
 * @create 2019/9/25
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@ApiModel(value = "用户实体类",description = "用户实体类")
public class User implements Serializable {
    @ApiModelProperty(name = "id",value = "主键",example = "1")
    private Integer id;
    @ApiModelProperty(name = "username",value = "用户名",example = "罪人")
    private String username;
    @ApiModelProperty(name = "password",value = "密码",example = "1045683477")
    private String password;
    @ApiModelProperty(name = "birthday",value = "生日",example = "1998-10-14")
    private Date birthday;
    @ApiModelProperty(name = "address",value = "地址",example = "湖南省")
    private String address;
}
