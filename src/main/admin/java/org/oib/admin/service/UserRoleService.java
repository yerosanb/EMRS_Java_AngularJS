package org.oib.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.oib.admin.model.Right;
import org.oib.admin.model.Role;
import org.oib.admin.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
	
	
	@Autowired
	private UserRoleRepository repository;
	
	@Autowired
	private UserRightsService rightService;

	public List<Role> getAllRoles() {
		
		return repository.getAllRoles();
	}

	public Role getRoleById(Long id) {
		return repository.getRoleById(id);
	}

	public void save(Role role) {
		
		repository.save(role);
		repository.removeAllRoleRights(role);
		repository.addAllRoleRights(role);
		
	}

	 
}
