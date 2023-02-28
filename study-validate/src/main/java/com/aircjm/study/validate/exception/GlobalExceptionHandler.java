package com.aircjm.study.validate.exception;

import cn.hutool.core.collection.CollectionUtil;
import com.rimlook.framework.core.enums.ErrorCodeEnum;
import com.rimlook.framework.core.pojo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 全局异常捕获
 *
 * @author aircjm
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<String> handlerConstraintViolationException(Exception e) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
        String msg = constraintViolationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        return Response.fail(ErrorCodeEnum.PARAM_ERROR.getCode(), "参数校验失败 " + msg);
    }

    /**
     * MethodArgumentNotValidException异常捕获 hibernate 校验使用
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage(), e);
        List<FieldError> list = e.getBindingResult().getFieldErrors();
        if (CollectionUtil.isNotEmpty(list)) {
            String message = list.stream().map(v -> v.getField() + ":" + v.getDefaultMessage()).collect(Collectors.joining("; "));
            return Response.fail(ErrorCodeEnum.PARAM_ERROR.getCode(), message);
        }
        return Response.fail(ErrorCodeEnum.PARAM_ERROR.getCode(), "参数校验有误");
    }
//
//    /**
//     * 统一处理自定义异常
//     *
//     * @param e 异常
//     * @return 结果
//     */
//    @ExceptionHandler(CustomException.class)
//    @ResponseBody
//    public Response customExceptionExceptionHandle(CustomException e) {
//        log.warn("[business exception] [ErrorMessage={}]", e.getMessage(), e);
//        String code = StringUtils.isNotBlank(e.getCode()) ? e.getCode() : ErrorCodeEnum.SYSTEM_ERROR.getCode();
//        return Response.fail(code, e.getMessage());
//    }


    /**
     * 统一处理HttpMessageNotReadableException
     *
     * @param e 异常
     * @return 返回
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Response httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException,参数转换失败", e);
        return Response.fail(ErrorCodeEnum.PARAM_ERROR.getCode(), "浏览器入参类型错误，参数转化失败");
    }


    /**
     * 统一处理Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response exceptionHandle(HttpServletResponse response, Exception e) {
        log.error("system error", e);
        sendDingTalkAlert(e, "Exception");
        return Response.fail(ErrorCodeEnum.SYSTEM_ERROR.getCode(), "系统内部异常");
    }


    /**
     * 发送钉钉报警
     *
     * @param e             异常
     * @param exceptionType 异常类型
     */
    private void sendDingTalkAlert(Exception e, String exceptionType) {
        // 1. 判断环境是否需要发送 只有生产需要发送
        // 2. 异步进行推送处理
        // 3. 添加日志

    }

}
