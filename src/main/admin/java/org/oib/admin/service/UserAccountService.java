package org.oib.admin.service;

import java.util.List;

import org.oib.admin.mapper.UserAccountMapper;
import org.oib.admin.model.Role;
import org.oib.admin.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountMapper mapper;

	public void save(UserAccount user) {
		
		if(user.getId() == null) 
			mapper.insert(user);
		
		else
		{
			mapper.update(user);
			mapper.removeAllUserRoles(user);
		}
		
		for(Role role : user.getRoles())
			mapper.addUserRoles(user.getId(), role.getId());
	}

	public List<UserAccount> searchUserByKeyWord(String searchKey) {
		return mapper.searchUserByKeyWord(searchKey);
	}

	public UserAccount getUserAccountById(Long id) {
		return mapper.getUserAccountById(id);
	}
	
	

}
