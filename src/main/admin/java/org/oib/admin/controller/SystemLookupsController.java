package org.oib.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.oib.admin.model.*;
import org.oib.admin.service.SystemLookUpService;
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
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/admin/lookups/")
public class SystemLookupsController extends BaseController {

	private static final String BRANCH = "branch";
	private static final String DEPARTMENT = "department";
	private static final String CITY = "city";
	
	@Autowired
	private SystemLookUpService service;

	@RequestMapping("branchs")
	public ResponseEntity getAllBranches() {
		return AGPResponse.response(service.getAllBranches());
	}
	
	@RequestMapping("branchs/{id}")
	public ResponseEntity<AGPResponse> getBranchById(@PathVariable("id") 
	Long id) {
		return AGPResponse.response(BRANCH, service.getBranchById(id));
	}
	
	@RequestMapping(value="branchs", method=RequestMethod.POST)
	public ResponseEntity<AGPResponse> saveBranch(@RequestBody 
			@Validated Branch branch) {
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try{
			service.saveBranch(branch);
		}
		catch(DuplicateKeyException ex){
			return AGPResponse.error(
					getMessage("branch.save.error.duplicate.code"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return AGPResponse.success(getMessage("branch.saved.successfully"));
	}
	
	@RequestMapping("cities")
	public ResponseEntity getAllCities() {
		return AGPResponse.response(service.getAllCities());
	}
	
	@RequestMapping("cities/{id}")
	public ResponseEntity<AGPResponse> getcityById(@PathVariable("id") 
	Long id) {
		return AGPResponse.response(CITY, service.getCityById(id));
	}
	
	@RequestMapping(value="cities", method=RequestMethod.POST)
	public ResponseEntity<AGPResponse> saveCity(@RequestBody 
			@Validated City city) {
		
		try{
			service.saveCity(city);
		}
		catch(DuplicateKeyException ex){
			return AGPResponse.error(
					getMessage("city.save.error.duplicate.code"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return AGPResponse.success(getMessage("city.saved.successfully"));
	}

	@RequestMapping("departments")
	public ResponseEntity getAllDepartments() {
		return AGPResponse.response(service.getAllDepartments());
	}
	
	@RequestMapping("departments/{id}")
	public ResponseEntity<AGPResponse> getDepartmentById(@PathVariable("id") 
	Long id) {
		return AGPResponse.response(DEPARTMENT, service.getDepartmentById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="departments", method=RequestMethod.POST)
	public ResponseEntity<AGPResponse> saveDepartment(@RequestBody 
			@Validated Department department) {
		
	 try
		{
			service.saveDepartment(department);
		}
		catch(DuplicateKeyException ex){
			return AGPResponse.error(
					getMessage("department.save.error.duplicate.code"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return AGPResponse.success(getMessage("department.saved.successfully"));
	}
	
}
