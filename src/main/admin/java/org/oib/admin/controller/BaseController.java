package org.oib.admin.controller;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

public class BaseController {

	@Autowired
	public MessageSource messageService;
	
	
	public String getMessage(String key){
		return messageService.getMessage(key, null, key, Locale.ENGLISH);
	}
}


