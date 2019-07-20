package com.aigodata.jessica.basic.domain;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限
 * @author mengxiangyun
 *
 */
@Table(name = "sys_permission")
public class Permission extends Base {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	/*
	 * 权限模块标识
	 */
	private String module;

	/*
	 * 权限模块名称
	 */
	private String moduleName;

	/*
	 * 权限行为
	 */
	private String action;

	/*
	 * 权限描述
	 */
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
