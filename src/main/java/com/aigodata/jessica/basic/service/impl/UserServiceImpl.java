package com.aigodata.jessica.basic.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aigodata.jessica.basic.common.constant.UserStatus;
import com.aigodata.jessica.basic.common.exception.GlobalException;
import com.aigodata.jessica.basic.common.shiro.realm.UserAuthRealm;
import com.aigodata.jessica.basic.common.util.Encryption;
import com.aigodata.jessica.basic.domain.User;
import com.aigodata.jessica.basic.domain.UserRole;
import com.aigodata.jessica.basic.mapper.UserMapper;
import com.aigodata.jessica.basic.mapper.UserRoleMapper;
import com.aigodata.jessica.basic.model.ResultModel.ResultStatus;
import com.aigodata.jessica.basic.service.UserService;
import com.google.common.base.Strings;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAuthRealm realm;

	@Value("${service.user.default_password}")
	private String defaultPassword;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	@Transactional
	public User add(User user) {
		// 判断 用户名 是否已存在
		User query = new User();
		query.setUsername(user.getUsername());
		User existedUser = userMapper.selectOne(query);
		if (existedUser != null) {
			throw new GlobalException(ResultStatus.USERNAME_EXIST);
		}
		// 盐值设为用户名
		user.setSalt(user.getUsername());
		// 密码加密信息
		HashedCredentialsMatcher matcher = (HashedCredentialsMatcher) realm.getCredentialsMatcher();
		String originPassword = null;
		if (Strings.isNullOrEmpty(user.getPassword())) {
			originPassword = defaultPassword;
		} else {
			// 密码解密
			try {
				originPassword = Encryption.desEncrypt(user.getPassword()).trim();
			} catch (Exception e) {
				throw new GlobalException(ResultStatus.PASSWORD_DECRYPT_ERROR);
			}
		}
		String password = new SimpleHash(matcher.getHashAlgorithmName(), originPassword, user.getSalt(),
				matcher.getHashIterations()).toString();
		user.setPassword(password);
		// 新增用户默认值
		user.setCreateTime(LocalDateTime.now());
		user.setStatus(UserStatus.NORMAL.value());
		// 保存用户
		userMapper.insertUseGeneratedKeys(user);
		// 添加角色
		userRoleMapper.insert(new UserRole(user.getId(), user.getRoleId()));
		return user;
	}

	@Override
	@Transactional
	public int update(User user) {
		// 判断 用户名 是否已存在
		User query = new User();
		query.setUsername(user.getUsername());
		User existedUser = userMapper.selectOne(query);
		if (existedUser != null && existedUser.getId() != user.getId()) {
			throw new GlobalException(ResultStatus.USERNAME_EXIST);
		}
		// 如果请求中包含了密码字段, 表示要更改密码, 则需要更新用户的数据库密码字段
		if (!Strings.isNullOrEmpty(user.getPassword())) {
			// 对密码进行解密, 获取原始密码内容
			String decryptPassword = null;
			try {
				decryptPassword = Encryption.desEncrypt(user.getPassword()).trim();
			} catch (Exception e) {
				throw new GlobalException(ResultStatus.PASSWORD_DECRYPT_ERROR);
			}
			// 盐值设为用户名
			user.setSalt(user.getUsername());
			// 密码加密信息
			HashedCredentialsMatcher matcher = (HashedCredentialsMatcher) realm.getCredentialsMatcher();
			String password = new SimpleHash(matcher.getHashAlgorithmName(), decryptPassword, user.getSalt(),
					matcher.getHashIterations()).toString();
			user.setPassword(password);
		} else {
			// password 为空字符串时不更新 (updateByPrimaryKeySelective 接口会更新空字符串字段)
			user.setPassword(null);
		}
		// 更新用户
		int count = userMapper.updateByPrimaryKeySelective(user);
		// 更新角色
		UserRole userRole = userRoleMapper.selectOne(new UserRole(user.getId()));
		if (userRole == null) {
			userRoleMapper.insert(new UserRole(user.getId(), user.getRoleId()));
		} else {
			if (userRole.getRoleId() != user.getRoleId()) {
				userRoleMapper.delete(userRole);
				userRoleMapper.insert(new UserRole(user.getId(), user.getRoleId()));
			}
		}
		return count;
	}

	@Override
	public List<User> getAll() {
		return userMapper.selectAll();
	}

}
