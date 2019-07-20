package com.aigodata.jessica.basic.service.impl;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aigodata.jessica.basic.common.exception.GlobalException;
import com.aigodata.jessica.basic.common.shiro.realm.UserAuthRealm;
import com.aigodata.jessica.basic.common.shiro.util.SubjectUtil;
import com.aigodata.jessica.basic.domain.User;
import com.aigodata.jessica.basic.mapper.UserMapper;
import com.aigodata.jessica.basic.model.ResultModel.ResultStatus;
import com.aigodata.jessica.basic.service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {

	@Autowired
	private UserAuthRealm realm;

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public void modify(String oldPassword, String newPassword) {
		User user = userMapper.selectByPrimaryKey(SubjectUtil.getId());
		// 密码加密信息
		HashedCredentialsMatcher matcher = (HashedCredentialsMatcher) realm.getCredentialsMatcher();
		// 判断原密码是否正确
		String encryptedOldPassword = new SimpleHash(matcher.getHashAlgorithmName(), oldPassword, user.getSalt(),
				matcher.getHashIterations()).toString();
		if (!encryptedOldPassword.equals(user.getPassword())) {
			throw new GlobalException(ResultStatus.OLD_PASSWORD_ERROR);
		}
		// 新密码
		String encryptedNewPassword = new SimpleHash(matcher.getHashAlgorithmName(), newPassword, user.getSalt(),
				matcher.getHashIterations()).toString();
		user.setPassword(encryptedNewPassword);
		userMapper.updateByPrimaryKey(user);
	}

}
