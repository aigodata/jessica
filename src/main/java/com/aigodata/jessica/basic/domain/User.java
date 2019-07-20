package com.aigodata.jessica.basic.domain;

import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.crazycake.shiro.AuthCachePrincipal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户
 * 
 * @author mengxiangyun
 *
 */
@JsonIgnoreProperties(value = { "password", "salt", "roleId" }, allowSetters = true)
@Table(name = "sys_user")
public class User extends Base implements AuthCachePrincipal {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	/*
	 * 用户名称(显示名称)
	 */
	private String name;

	/*
	 * 用户名
	 */
	private String username;

	/*
	 * 密码
	 */
	private String password;

	/*
	 * 盐值: 在密码中混入一段"随机"的字符串再进行哈希加密, 这个字符串被称作盐值
	 */
	private String salt;

	/*
	 * 电话
	 */
	private String phone;

	/*
	 * 邮箱
	 */
	private String email;

	/*
	 * 状态(1:正常 2:锁定)
	 */
	private Integer status;

	/*
	 * 账号登录错误次数, 达到指定次数账号将被锁定
	 */
	private Integer loginErrorCount;

	/*
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/*
	 * 角色ID, 用于添加用户接口前台传递参数
	 */
	@Transient
	private Integer roleId;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String getAuthCacheKey() {
		return String.valueOf(this.id);
	}

}
