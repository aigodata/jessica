package com.aigodata.jessica.basic.vo;

public class UserInfo {

	private Integer id;

	private String name;

	private String username;

	private Integer roleId;

	private String role;

	public UserInfo() {
	}

	public UserInfo(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public UserInfo(Integer id, String name, String username, Integer roleId, String role) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.roleId = roleId;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
