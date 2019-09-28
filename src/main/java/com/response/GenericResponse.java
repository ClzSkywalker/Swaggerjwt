package com.response;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 描述：
 * 〈API 接口的基础返回类〉
 *
 * @author zuiren
 * @create 2019/9/28
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回说明")
public class GenericResponse implements Serializable {
    @ApiModelProperty(value = "返回状态码")
    private int code;
    @ApiModelProperty("描述信息")
    private String message;
    @ApiModelProperty("返回数据")
    private Object data;

    @Override
    public String toString(){
        if (Objects.isNull(this.data)){
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }
}
