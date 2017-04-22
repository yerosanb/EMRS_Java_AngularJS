package org.oib.admin.controller;

import org.oib.admin.model.UserAccount;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/account/")
public class UserAccountController {
	
	@RequestMapping(value="user", method = RequestMethod.POST)
	public void create(@RequestBody @Validated UserAccount user){
		System.out.println(user);
	}
	
	@RequestMapping(value="user", method = RequestMethod.DELETE)
	public void delete(){
		
	}

}
