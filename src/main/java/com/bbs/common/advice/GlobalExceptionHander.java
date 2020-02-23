package com.bbs.common.advice;

import com.bbs.common.exception.BusinessRuntimeException;
import com.bbs.common.utils.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: GlobalExceptionHander
 * @author: q-linyu
 * @description: 全局异常处理器
 * @date: 2020/02/18 15:26
 * @version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHander {

    /**
     * 业务异常处理
     * @author q-linyu
     * @date 2020/2/18 0018 15:27
     * @param  e
     * @return
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    @ResponseBody
    public ResponseData<String> businessHandlerException(BusinessRuntimeException e){
        System.out.println(e);
        return ResponseData.error(e.getMessage());
    }

}
