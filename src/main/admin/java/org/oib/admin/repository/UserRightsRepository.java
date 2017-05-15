package org.oib.admin.repository;

import java.util.List;

import org.oib.admin.mapper.UserRightsMapper;
import org.oib.admin.model.Right;
import org.oib.admin.model.Role;
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
