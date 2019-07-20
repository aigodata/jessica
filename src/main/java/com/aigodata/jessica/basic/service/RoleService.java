package com.aigodata.jessica.basic.service;

import com.aigodata.jessica.basic.domain.Role;

public interface RoleService {

	public Role add(Role role, Integer[] permissionIds);

	public int update(Role role, Integer[] permissionIds);

}
