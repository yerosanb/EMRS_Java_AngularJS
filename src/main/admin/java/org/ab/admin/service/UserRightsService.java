package org.ab.admin.service;

import java.util.List;

import org.ab.admin.model.Right;
import org.ab.admin.model.Role;
import org.ab.admin.repository.UserRightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRightsService {
	@Autowired
	private UserRightsRepository repo;

	public List<Right> getAll() {
		return repo.getAll();
	}
 

}
