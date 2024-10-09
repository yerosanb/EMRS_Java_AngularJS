package org.ab.admin.repository;

import java.util.List;

import org.ab.admin.mapper.UserRightsMapper;
import org.ab.admin.model.Right;
import org.ab.admin.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRightsRepository {
	
	@Autowired
	private UserRightsMapper mapper;

	public List<Right> getAll() {
		return mapper.getAll();
	}


}
