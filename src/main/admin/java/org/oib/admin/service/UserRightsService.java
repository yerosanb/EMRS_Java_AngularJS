package org.oib.admin.service;

import java.util.List;

import org.oib.admin.model.Right;
import org.oib.admin.model.Role;
import org.oib.admin.repository.UserRightsRepository;
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
