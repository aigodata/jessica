package com.aigodata.jessica.basic.common.constant;

/**
 * 用户状态
 * 
 * @author mengxiangyun
 *
 */
public enum UserStatus {

	/**
	 * 正常
	 */
	NORMAL(1),

	/**
	 * 锁定
	 */
	LOCKED(2);

	private final int value;

	private UserStatus(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

}
