package org.oib.admin.controller;

import java.util.HashMap;
import java.util.Locale;

import org.oib.core.config.message.SerializableMessageBundleController;
import org.oib.core.config.message.SerializableResourceBundleMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

public class BaseController {

	@Autowired
	public SerializableResourceBundleMessageSource messageService;
	
	
	public String getMessage(String key){
		return messageService.getMessage(key, null, key, Locale.ENGLISH);
	}
}


