package org.oib.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.validator.constraints.NotEmpty;
import org.oib.admin.model.UserAccount;
import org.oib.admin.service.UserAccountService;
import org.oib.core.controller.response.AGPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		
		return AGPResponse.response(getMessage("user.saved.successfully"), HttpStatus.OK);
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
	
	@RequestMapping(value="user", method = RequestMethod.DELETE)
	public void delete(){
		
	}

}
