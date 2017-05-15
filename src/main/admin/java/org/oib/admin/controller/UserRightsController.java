package org.oib.admin.controller;

import java.util.List;

import org.oib.admin.model.Right;
import org.oib.admin.model.Role;
import org.oib.admin.service.UserRightsService;
import org.oib.core.controller.response.AGPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/right")
public class UserRightsController extends BaseController {
	
	@Autowired
	private UserRightsService service;
	
	
	@RequestMapping("/rights")
	public ResponseEntity getAllRights(){
		return AGPResponse.response(service.getAll());
	}

}
