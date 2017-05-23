package org.oib.admin.controller;

import org.oib.admin.service.I18nService;
import org.oib.core.controller.response.AGPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class I18nController {

	public static final String MESSAGES = "messages";
	
	@Autowired
	private I18nService i18nService;

	@RequestMapping(value = "/locales", method = GET)
	public ResponseEntity<AGPResponse> getLocales(HttpServletRequest request) {
		
		i18nService.setCurrentLocale(RequestContextUtils.getLocale(request));
		return AGPResponse.response("locales", i18nService.getLocales());
	}

	@RequestMapping(value = "/changeLocale", method = PUT, headers = "Accept=application/json")
	public ResponseEntity<AGPResponse> changeLocale(HttpServletRequest request) {
		i18nService.setCurrentLocale(RequestContextUtils.getLocale(request));
		return AGPResponse.success("localeChanged");
	}
 

	@RequestMapping(value = "/messages", method = GET, headers = "Accept=application/json")
	public ResponseEntity<AGPResponse> getAllMessages() throws UnsupportedEncodingException {
		 return AGPResponse.response(MESSAGES, i18nService.allMessages());
	}

	@RequestMapping(value="messages", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<AGPResponse> list(@RequestParam(required=false) String lang) {
		if(lang == null)
			 return AGPResponse.response(MESSAGES, i18nService.allMessages());
		
		return AGPResponse.response(MESSAGES, i18nService.getAllProperties(lang));
	}
}
