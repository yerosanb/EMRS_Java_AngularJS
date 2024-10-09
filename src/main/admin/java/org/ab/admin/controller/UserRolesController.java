package org.ab.admin.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ab.admin.model.Role;
import org.ab.admin.service.UserRoleService;
import org.ab.core.controller.response.AGPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/role")
public class UserRolesController extends BaseController {

	@Autowired
	private UserRoleService service;

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity getAllRoles() {

		return AGPResponse.response(service.getAllRoles());
	}

	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
	public ResponseEntity getRoleById(@PathVariable("id") Long id) {

		return AGPResponse.response(service.getRoleById(id));
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public ResponseEntity<AGPResponse> insert(@RequestBody @Validated Role role) {

		try{
			service.save(role);
		}
		catch(DuplicateKeyException ex){
			return AGPResponse.error(
					getMessage("role.save.error.duplicate.code"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return AGPResponse.success(getMessage("role.saved.successfull"));
	}

}
