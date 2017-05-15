package org.oib.admin.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.oib.admin.mapper.UserRoleMapper;
import org.oib.admin.model.Right;
import org.oib.admin.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepository {

	@Autowired
	private UserRoleMapper mapper;

	public List<Role> getAllRoles() {
		return mapper.getAllRoles();
	}

	public Role getRoleById(Long id) {
		return mapper.getRoleById(id);
	}

	public void save(Role role) {
		if (role.getId() != null)
			mapper.update(role);
		else
			mapper.insert(role);

	}

	public void removeAllRoleRights(Role role) {
		mapper.removeAllRoleRights(role.getId());
	}

	public void addAllRoleRights(Role role) {
		for(Right right : role.getRights())
			mapper.addRoleRight(role.getId(), right);
	}
}
