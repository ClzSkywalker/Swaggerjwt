package com.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述：
 * 〈spring 对于 RuntimeException 异常会进行事务回滚〉
 *
 * @author zuiren
 * @create 2019/9/26
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {
    private Integer code;
    private Exception exception;

}
