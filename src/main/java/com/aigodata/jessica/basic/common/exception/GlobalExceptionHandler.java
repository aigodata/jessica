package com.aigodata.jessica.basic.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aigodata.jessica.basic.model.ResultModel;
import com.aigodata.jessica.basic.model.ResultModel.ResultStatus;

/**
 * 全局异常处理
 * 
 * @author mengxiangyun
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 捕获权限异常
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ShiroException.class)
	public ResultModel handleShiroException(ShiroException e, HttpServletRequest request) {
		logger.error(String.format("AuthError@%s :", request.getRequestURI()), e);
		return ResultModel.fail(ResultStatus.UNAUTHORIZED);
	}

	/**
	 * 捕获HttpRequestMethodNotSupportedException类型的异常
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(GlobalException.class)
	public ResultModel handleException(GlobalException e, HttpServletRequest request) {
		logger.error(String.format("Error@%s :", request.getRequestURI()), e);
		Map<String, Object> data = new HashMap<>();
		data.put("errorCode", e.getCode());
		return ResultModel.fail(ResultStatus.SERVER_ERROR.code(), e.getMessage(), data);
	}

	/**
	 * 捕获所有类型的异常
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResultModel handleException(Exception e, HttpServletRequest request) {
		logger.error(String.format("Error@%s :", request.getRequestURI()), e);
		return ResultModel.fail(ResultStatus.SERVER_ERROR.code(), e.getMessage());
	}

}
