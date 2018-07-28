package com.chb.title.base;

import com.chb.title.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description : 全局异常处理
 * Author : LuYun
 * Version : 1.0
 * Create Date Time : 2017/7/6 14:34.
 *
 * @see
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    private String rex = "(?<=Required String parameter\\s).*?(?=\\sis not present)";


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String exception(Exception e, WebRequest request, Throwable th) {
        logger.info(Utils.printStrAll("-","exception log begin",10));
        JSONResult result = new JSONResult();
        logger.warn(e.getMessage());
        if (e instanceof MissingServletRequestParameterException) { //必选参数为空时
            List<String> data = Utils.rexMatcher(rex, e.getMessage());
            result.setFlag("0");
            result.setMsg(Utils.join(data, ",") + "参数不存在!");
        }else if(e instanceof BaseException){
            result.setFlag("0");
            result.setMsg(((BaseException) e).getMsg());
        }
        //打印方法参数列表及值
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> param : map.entrySet()) {
            logger.debug("param name:{}, values {}" , param.getKey(), Arrays.asList(param.getValue()));
        }
        logger.info(Utils.printStrAll("-","exception log end",10));
        return result.toJSON();
    }


}
