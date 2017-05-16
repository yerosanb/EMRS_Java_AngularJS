package org.oib.admin.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.validator.constraints.NotEmpty;
import org.oib.admin.model.UserAccount;
import org.oib.admin.model.dto.ChangeMyPasswordDto;
import org.oib.admin.model.dto.ChangePasswordDto;
import org.oib.admin.service.UserAccountService;
import org.oib.core.controller.response.AGPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/account/")
public class UserAccountController extends BaseController {
	
	@Autowired 
	UserAccountService service;
	
	@RequestMapping(value="user", method = RequestMethod.POST)
	public ResponseEntity<AGPResponse> create(@RequestBody @Validated UserAccount user){
		
		try {
			
			service.save(user);
		}
		catch(DuplicateKeyException ex){
			return AGPResponse.error(
					getMessage("role.save.error.duplicate.code"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return AGPResponse.success(getMessage("user.saved.successfully"));
	}
	
	@RequestMapping(value="user", method = RequestMethod.GET)
	public ResponseEntity searchUser(
			@RequestParam  String searchKey){
		
		return AGPResponse.response(service.searchUserByKeyWord(searchKey));
	}
	
	@RequestMapping(value="user/{id}", method = RequestMethod.GET)
	public ResponseEntity searchUser(
			@PathVariable("id") Long id){
		
		return AGPResponse.response(service.getUserAccountById(id));
	}
	
	@RequestMapping(value="changePassword", method = RequestMethod.POST)
	public ResponseEntity<AGPResponse> changePassword(@RequestBody @Validated ChangePasswordDto passDto){
		
		service.changePassword(passDto);
		
		return AGPResponse.success(getMessage("password.changed.successfully"));
		
	}
	
	@RequestMapping(value="changeMyPassword", method = RequestMethod.POST)
	public ResponseEntity<AGPResponse> changeMyPassword(@RequestBody @Validated ChangeMyPasswordDto passDto, 
			Principal principal){
		
		if(!service.passwordMatches(passDto.getOldPassword(), principal.getName()))
			return AGPResponse.error(getMessage("wrong.old.password"), HttpStatus.BAD_REQUEST);
		
		service.changeMyPassword(passDto, principal.getName());
		
		return AGPResponse.success(getMessage("password.changed.successfully"));
		
	}

}
