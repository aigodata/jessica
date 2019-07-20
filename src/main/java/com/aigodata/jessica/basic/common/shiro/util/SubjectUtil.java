package com.aigodata.jessica.basic.common.shiro.util;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.aigodata.jessica.basic.vo.UserInfo;
import com.google.common.collect.Iterables;

public class SubjectUtil {

	/**
	 * 获取当前登录用户的ID
	 * 
	 * @return
	 */
	public static Integer getId() {
		Subject currentUser = SecurityUtils.getSubject();
//		Object principal = currentUser.getPrincipals().getPrimaryPrincipal();
//		Integer userId = Integer.parseInt(principal.toString());
		PrincipalCollection principals = currentUser.getPrincipals();
		if (principals == null) {
			return 0;
		}
		Collection<Integer> integers = principals.byType(Integer.class);
		Integer userId = Iterables.getFirst(integers, null);
		return userId == null ? 0 : userId;
	}

	/**
	 * 获取当前登录用户的名字
	 * 
	 * @return
	 */
	public static String getName() {
		Subject currentUser = SecurityUtils.getSubject();
		Collection<String> strings = currentUser.getPrincipals().byType(String.class);
		String name = Iterables.getFirst(strings, null);
		return name;
	}

	public static UserInfo getUser() {
		Subject currentUser = SecurityUtils.getSubject();
		PrincipalCollection principals = currentUser.getPrincipals();
		if (principals == null) {
			return new UserInfo();
		}
		/*
		 * Integer类型, 包含用户ID
		 */
		Collection<Integer> integers = principals.byType(Integer.class);
		Integer userId = Iterables.getFirst(integers, null);
		/*
		 * String类型, 包含用户姓名, 用户名, 角色ID, 角色名
		 */
		Collection<String> strings = principals.byType(String.class);
		String name = Iterables.getFirst(strings, null);
		name = name.split(":")[1];
		String username = Iterables.get(strings, 1);
		username = username.split(":")[1];
		String roleIdString = Iterables.get(strings, 2);
		Integer roleId = Integer.parseInt(roleIdString.split(":")[1]);
		String role = Iterables.get(strings, 3);
		role = role.split(":")[1];
		return new UserInfo(userId, name, username, roleId, role);
	}

}
