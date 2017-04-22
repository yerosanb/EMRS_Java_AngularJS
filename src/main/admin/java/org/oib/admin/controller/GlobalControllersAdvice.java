package org.oib.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
class GlobalControllersAdvice  {

	/**
	 * Handle exceptions thrown by handlers.
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<HashMap<String, String>> exception(MethodArgumentNotValidException exception) {
		
		HashMap<String, String> msg = new HashMap<String, String>();
		
	    for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            msg.put(error.getField(), error.getDefaultMessage());
        }
	    
		return new ResponseEntity<HashMap<String, String>>(msg, HttpStatus.BAD_REQUEST);
		
	}
	
 
}
