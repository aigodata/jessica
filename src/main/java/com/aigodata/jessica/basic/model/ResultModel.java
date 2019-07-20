package com.aigodata.jessica.basic.model;

/**
 * 返回结果封装
 * 
 * @author mengxiangyun
 *
 */
public class ResultModel {

	// 状态码
	private int code;

	// 状态消息
	private String message;

	// 结果数据
	private Object data;

	public ResultModel() {}

	public ResultModel(int code, String message) {
		this(code, message, "");
	}

	public ResultModel(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResultModel code(int code) {
		this.code = code;
		return this;
	}

	public ResultModel message(String message) {
		this.message = message;
		return this;
	}

	public ResultModel data(Object data) {
		this.data = data;
		return this;
	}

	/**
	 * 返回成功, 无结果
	 * 
	 * @param data
	 * @return
	 */
	public static ResultModel success() {
		return new ResultModel(ResultStatus.SUCCESS.code(), ResultStatus.SUCCESS.message());
	}

	/**
	 * 返回成功结果
	 * 
	 * @param data
	 * @return
	 */
	public static ResultModel success(Object data) {
		return new ResultModel(ResultStatus.SUCCESS.code(), ResultStatus.SUCCESS.message(), data);
	}

	/**
	 * 返回成功结果, 自定义消息
	 * 
	 * @param data
	 * @return
	 */
	public static ResultModel success(String message, Object data) {
		return new ResultModel(ResultStatus.SUCCESS.code(), message, data);
	}

	/**
	 * 返回通用失败结果
	 * 
	 * @param status
	 * @return
	 */
	public static ResultModel fail() {
		return new ResultModel(ResultStatus.SERVER_ERROR.code(), ResultStatus.SERVER_ERROR.message());
	}

	/**
	 * 返回通用失败结果
	 * 
	 * @param status
	 * @return
	 */
	public static ResultModel fail(ResultStatus status) {
		return fail(status.code(), status.message());
	}

	/**
	 * 返回通用失败结果
	 * 
	 * @param status
	 * @return
	 */
	public static ResultModel fail(ResultStatus status, Object data) {
		return new ResultModel(status.code(), status.message(), data);
	}

	/**
	 * 返回失败结果, 自定义状态码和消息
	 * 
	 * @param status
	 *            返回结果状态
	 * @param message
	 *            返回结果消息
	 * @return
	 */
	public static ResultModel fail(String message) {
		return new ResultModel(ResultStatus.SERVER_ERROR.code(), message);
	}

	/**
	 * 返回失败结果, 自定义状态码和消息
	 * 
	 * @param status
	 *            返回结果状态
	 * @param message
	 *            返回结果消息
	 * @return
	 */
	public static ResultModel fail(int code, String message) {
		return new ResultModel(code, message);
	}

	/**
	 * 返回失败结果, 自定义状态码和消息
	 * 
	 * @param status
	 *            返回结果状态
	 * @param message
	 *            返回结果消息
	 * @param data
	 *            返回结果数据
	 * @return
	 */
	public static ResultModel fail(int code, String message, Object data) {
		return new ResultModel(code, message, data);
	}

	/**
	 * 返回失败结果, 包含错误码
	 * 
	 * @param status
	 * @return
	 */
	public static ResultModel failWithError(ResultStatus status) {
		return new ResultModel(ResultStatus.SERVER_ERROR.code(), status.message(), new ErrorData(status.code()));
	}

	/**
	 * 返回失败结果, 包含错误码
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static ResultModel failWithError(int code, String message) {
		return new ResultModel(ResultStatus.SERVER_ERROR.code(), message, new ErrorData(code));
	}

	/**
	 * Session 过期
	 * 
	 * @return
	 */
	public static ResultModel sessionOut() {
		return new ResultModel(ResultStatus.FORBIDDEN.code(), ResultStatus.NOT_LOGGED.message(),
				new ErrorData(ResultStatus.NOT_LOGGED.code()));
	}

	/**
	 * 返回结果状态
	 *
	 */
	public enum ResultStatus {

		/*
		 * 应用
		 */
		SUCCESS(200, "ok"), BAD_REQUEST(400, "Bad Request"), UNAUTHORIZED(401, "Unauthorized"), FORBIDDEN(403,
				"Forbidden"), SERVER_ERROR(500, "Internal Server Error"),

		/*
		 * 登录
		 */
		NOT_LOGGED(1004, "用户未登录"), USERNAME_PASSWORD_ERROR(1005, "用户名或密码错误"), CAPTCHA_ERROR(1006,
				"验证码错误"), CAPTCHA_LOSE(1007,
						"验证码失效"), ACCOUNT_LOCKED(1008, "账户已锁定"), PASSWORD_DECRYPT_ERROR(1009, "密码解密失败"),

		/*
		 * 用户
		 */
		USERNAME_EXIST(1101, "用户名已存在"), PHONE_EXIST(1102, "电话已存在"), EMAIL_EXIST(1103, "邮箱已存在"),

		/*
		 * 角色
		 */
		ROLE_EXIST(1201, "角色已存在"), ROLE_NOT_EXIST(1202, "角色不存在"),

		/*
		 * 密码
		 */
		OLD_PASSWORD_ERROR(9301, "原密码错误"), NEW_PASSWORD_NOT_CHANGE(9302, "新密码不能与原密码相同");

		// 状态码
		private final int code;

		// 状态消息
		private final String message;

		ResultStatus(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int code() {
			return this.code;
		}

		public String message() {
			return this.message;
		}
	}

	static class ErrorData {

		private Integer errorCode;

		public ErrorData(Integer errorCode) {
			this.errorCode = errorCode;
		}

		public Integer getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(Integer errorCode) {
			this.errorCode = errorCode;
		}

	}

}
