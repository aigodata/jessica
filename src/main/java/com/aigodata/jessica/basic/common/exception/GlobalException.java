package com.aigodata.jessica.basic.common.exception;

import com.aigodata.jessica.basic.model.ResultModel.ResultStatus;

/**
 * 全局异常, 包含错误状态
 * @author mengxiangyun
 *
 */
public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;

	private String message;

	public GlobalException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public GlobalException(ResultStatus resultStatus) {
		this(resultStatus.code(), resultStatus.message());
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
