package org.oib.core.controller.advice;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
class GlobalControllersAdvice {

	@Autowired
	private MessageSource messageSource;

	/**
	 * Handle exceptions thrown by handlers.
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class, MissingServletRequestParameterException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<HashMap<String, String>> exception(Exception exception) {

		HashMap<String, String> msg = new HashMap<String, String>();

		if (exception instanceof MethodArgumentNotValidException) {
			for (FieldError error : ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors()) {
				msg.put(error.getField(), error.getDefaultMessage());
			}
		} else {
			msg.put("error", messageSource.getMessage("mvc.method.param.required", 
					null, "Please fill required field",
					Locale.ENGLISH));
		}

		return new ResponseEntity<HashMap<String, String>>(msg, HttpStatus.BAD_REQUEST);

	}

	/**
	 * Handle internal server error exceptions thrown by handlers.
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<HashMap<String, String>> exception500(Exception exception, HttpServletRequest request) {

		HashMap<String, String> msg = new HashMap<String, String>();
		msg.put("error",
				messageSource.getMessage("error.internal.server", null, "Sorry something goes wrong", Locale.ENGLISH));

		return new ResponseEntity<HashMap<String, String>>(msg, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
