package com.aigodata.jessica.basic.domain;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_user_role")
public class UserRole extends Base {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer userId;

	@Id
	private Integer roleId;

	public UserRole() {

	}

	public UserRole(Integer userId) {
		this.userId = userId;
	}

	public UserRole(Integer userId, Integer roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
