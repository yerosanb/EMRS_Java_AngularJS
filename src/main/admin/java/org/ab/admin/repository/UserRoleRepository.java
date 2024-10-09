package org.ab.admin.repository;

import java.util.List;

import org.ab.admin.mapper.UserRoleMapper;
import org.ab.admin.model.Right;
import org.ab.admin.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
