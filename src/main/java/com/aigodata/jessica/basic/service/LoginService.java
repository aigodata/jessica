package com.aigodata.jessica.basic.service;

public interface LoginService {

	public void login(String username, String password, boolean rememberMe);

	public void logout();

}
