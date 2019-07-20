package com.aigodata.jessica.basic.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aigodata.jessica.basic.domain.Role;
import com.aigodata.jessica.basic.domain.RolePermission;
import com.aigodata.jessica.basic.mapper.RoleMapper;
import com.aigodata.jessica.basic.mapper.RolePermissionMapper;
import com.aigodata.jessica.basic.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	@Transactional
	public Role add(Role role, Integer[] permissionIds) {
		roleMapper.insertUseGeneratedKeys(role);
		// 插入角色权限
		List<RolePermission> rolePermissions = Arrays.stream(permissionIds).map(permissionId -> {
			RolePermission p = new RolePermission();
			p.setRoleId(role.getId());
			p.setPermissionId(permissionId);
			return p;
		}).collect(Collectors.toList());
		rolePermissionMapper.insertList(rolePermissions);
		return role;
	}

	@Override
	@Transactional
	public int update(Role role, Integer[] permissionIds) {
		int count = roleMapper.updateByPrimaryKey(role);
		RolePermission p = new RolePermission();
		p.setRoleId(role.getId());
		// 删除角色之前的权限
		rolePermissionMapper.delete(p);
		// 添加新的权限
		List<RolePermission> rolePermissions = Arrays.stream(permissionIds).map(permissionId -> {
			RolePermission ps = new RolePermission();
			ps.setRoleId(role.getId());
			ps.setPermissionId(permissionId);
			return ps;
		}).collect(Collectors.toList());
		rolePermissionMapper.insertList(rolePermissions);
		return count;
	}

}
