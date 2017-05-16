package org.oib.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.oib.admin.model.Right;
import org.oib.admin.model.Role;

@Mapper
public interface UserRightsMapper {

    @Select("select * from rights")
	public List<Right> getAll();
    
    @Select("  select rt.* from role_rights rr join roles r on rr.role_id = r.id"
    		+" inner join rights rt on rr.right_id = rt.id "
    		+" where r.id = #{roleId}")
    public List<Right> getByRoleId(Long roleId);
    
    @Select("select rights.code from user_role "
    		+ " join roles  on roles.id = user_role.role_id"
    		+ " join role_rights on role_rights.role_id = roles.id"
    		+ " join rights on rights.id = role_rights.right_id"
    		+ " WHERE "
    		+ " user_role.user_id = #{userId}")
    public List<String> getUserRights(Long userId);
 
}
