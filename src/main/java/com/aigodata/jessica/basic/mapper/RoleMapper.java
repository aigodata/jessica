package com.aigodata.jessica.basic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.aigodata.jessica.basic.common.generic.mapper.BaseMapper;
import com.aigodata.jessica.basic.domain.Role;

@Repository
public interface RoleMapper extends BaseMapper<Role> {

	@Select("select r.id, r.name, r.description from sys_role r right join sys_user_role ur on r.id = ur.role_id where ur.user_id = #{userId}")
	public List<Role> selectByUserId(@Param("userId") Integer userId);

	@Select("select name from sys_role r right join sys_user_role ur on r.id = ur.role_id where ur.user_id = #{userId}")
	public List<String> selectNamesByUserId(@Param("userId") Integer userId);

}
