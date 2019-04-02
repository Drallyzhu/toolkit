package com.drally.toolkit.common.exception;

/**
 * @author zhujianrong
 * @date 2018-11-8 20:41
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 3006991224624822528L;

	private final String code;
	public static final String OK = "200";
	public static final String ERR = "500";

	public BusinessException(String message) {
		super(message);
		this.code = ERR;
	}
	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}
	public String getCode() {
		return code;
	}

}
