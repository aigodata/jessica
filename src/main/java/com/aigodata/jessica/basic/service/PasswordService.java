package com.aigodata.jessica.basic.service;


public interface PasswordService {

	/**
	 * 修改密码
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 */
	public void modify(String oldPassword, String newPassword);

}
