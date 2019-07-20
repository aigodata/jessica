package com.aigodata.jessica.basic.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aigodata.jessica.basic.common.constant.UserStatus;
import com.aigodata.jessica.basic.common.exception.GlobalException;
import com.aigodata.jessica.basic.domain.User;
import com.aigodata.jessica.basic.mapper.UserMapper;
import com.aigodata.jessica.basic.model.ResultModel.ResultStatus;
import com.aigodata.jessica.basic.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Value("${service.user.login_error_count}")
	private Integer loginErrorCount;

	@Autowired
	private UserMapper userMapper;

	@Override
	public void login(String username, String password, boolean rememberMe) {
		// 获取当前登录账号的数据库对象
		User query = new User();
		query.setUsername(username);
		User user = userMapper.selectOne(query);

		// 获取当前用户
		Subject currentUser = SecurityUtils.getSubject();
		// 判断用户是否已认证登录
		// if (!currentUser.isAuthenticated()) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(rememberMe);
		try {
			currentUser.login(token);
			user.setStatus(UserStatus.NORMAL.value());
			user.setLoginErrorCount(0);
			userMapper.updateByPrimaryKey(user);
		} catch (UnknownAccountException e) {
			throw new GlobalException(ResultStatus.USERNAME_PASSWORD_ERROR);
		} catch (LockedAccountException e) {
			throw new GlobalException(ResultStatus.ACCOUNT_LOCKED);
		} catch (Exception e) {
			user.setLoginErrorCount(user.getLoginErrorCount() + 1);
			if (user.getLoginErrorCount() >= loginErrorCount) {
				user.setStatus(UserStatus.LOCKED.value());
			}
			userMapper.updateByPrimaryKey(user);
			throw new GlobalException(ResultStatus.USERNAME_PASSWORD_ERROR);
		}
		// }
	}

	@Override
	public void logout() {
		SecurityUtils.getSubject().logout();
	}

}
