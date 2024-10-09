package org.ab.admin.mapper;

import java.util.List;

import org.ab.admin.model.Right;
import org.ab.admin.model.Role;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRoleMapper {

	@Select("select * from roles")
	@Results(value = {
			 @Result(property = "id", column = "id"),
			 @Result(property="rights", javaType=List.class, column = "id", 
                     many=@Many(select="org.ab.admin.mapper.UserRightsMapper.getByRoleId"))
	    })
	public List<Role> getAllRoles();

	@Select("select * from roles where id = #{id}")
	@Results(value = {
			 @Result(property = "id", column = "id"),
			 @Result(property="rights", javaType=List.class, column = "id", 
                     many=@Many(select="org.ab.admin.mapper.UserRightsMapper.getByRoleId"))
	    })
	public Role getRoleById(Long id);
	
	@Select("select r.* from roles r join user_role ur on r.id = ur.role_id where ur.user_id = #{userId}")
	public List<Role> getRolesByUserId(Long userId);

	@Update("update roles set code = #{code}, name= #{name} , description = #{description} where id=#{id}")
	public void update(Role role);

	@Insert("insert into roles (code, name, description) values (#{code}, #{name}, #{description})")
	@Options(useGeneratedKeys = true)
	public void insert(Role role);

	@Delete("delete from role_rights where role_id = #{id}")
	public void removeAllRoleRights(Long id);
	
	@Insert("insert into role_rights (role_id, right_id) values (#{id}, #{right.id})")
	public void addRoleRight(@Param("id") Long id, @Param("right") Right right);
	
}
