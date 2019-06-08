package cn.mutounao.demo.util;

import cn.mutounao.demo.util.pojo.apiResult;
import cn.mutounao.demo.util.pojo.errResult;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Order(100)
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public apiResult allExceptionHandler(Exception e) throws Exception
    {
        return apiResult.errorOf(errResult.SERVER_ERROR, e.getMessage());
    }


}
