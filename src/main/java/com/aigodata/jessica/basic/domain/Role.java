package com.aigodata.jessica.basic.domain;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_role")
public class Role extends Base{
 
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	/*
	 * 角色名称
	 */
	private String name;

	/*
	 * 角色描述
	 */
	private String description;


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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
