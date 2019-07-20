package com.aigodata.jessica.basic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.aigodata.jessica.basic.common.generic.mapper.BaseMapper;
import com.aigodata.jessica.basic.domain.Permission;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

	@Select("select distinct p.module, p.module_name, p.action " + 
		"    from sys_permission p " + 
		"    right join sys_role_permission rp on p.id = rp.permission_id " + 
		"    where rp.role_id in (" + 
		"      select r.id from sys_user u " + 
		"      right join sys_user_role ur on u.id = ur.user_id " + 
		"      left join sys_role r on ur.role_id = r.id " + 
		"      where u.id = #{userId}" + 
		")")
	public List<Permission> selectByUserId(@Param("userId") Integer userId);

}
