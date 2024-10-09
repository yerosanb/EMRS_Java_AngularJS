package org.ab.admin.service;

import java.util.List;
import java.util.Locale;

import org.ab.admin.mapper.UserAccountMapper;
import org.ab.admin.model.Role;
import org.ab.admin.model.UserAccount;
import org.ab.admin.model.dto.ChangeMyPasswordDto;
import org.ab.admin.model.dto.ChangePasswordDto;
import org.ab.core.config.message.SerializableResourceBundleMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public SerializableResourceBundleMessageSource messageService;

	public void save(UserAccount user) {
		
		if(user.getId() == null){ 
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			mapper.insert(user);
		}
		
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

	public void changePassword(ChangePasswordDto passDto) {
		passDto.setPassword((passwordEncoder.encode(passDto.getPassword())));
		mapper.changePassword(passDto);
	}

	public Boolean passwordMatches(String newPassword, String email){
		String oldPassword = mapper.getUserPassword(email);
		return passwordEncoder.matches(newPassword, oldPassword);
	}
	
	public void changeMyPassword(ChangeMyPasswordDto passDto, String email) {
		passDto.setPassword(passwordEncoder.encode(passDto.getPassword()));
		mapper.changeMyPassword(passDto.getPassword(), email);
	}
	
	

}
