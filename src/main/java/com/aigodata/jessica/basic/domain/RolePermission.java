package com.aigodata.jessica.basic.domain;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_role_permission")
public class RolePermission extends Base {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	/*
	 * 数据id
	 */
	private Integer roleId;

	/*
	 * 权限ID
	 */
	private Integer permissionId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

}
