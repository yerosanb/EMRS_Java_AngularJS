package org.ab.controller;

import java.util.List;

import org.ab.model.Employee;
import org.ab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	public List<Employee> sayHi(){
		
		return service.getAllEmployee();
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public List<Employee> deleteEmployee(@PathVariable("id") Long id){
		service.delete(id);
		return service.getAllEmployee();
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public List<Employee> create(@RequestBody Employee emp){
		service.insert(emp);
		return service.getAllEmployee();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.PUT)
	public List<Employee> editEmployee(@RequestBody Employee emp){
		service.updateEmployee(emp);
		return service.getAllEmployee();
	}
	
	//localhost:8080/employee/get/jibril/name/sanom
	@RequestMapping("/get/{fName}/name/{name}")
	public String sayBye(@PathVariable("fName") String fathersName, @PathVariable("name") String employeeName){
		return "Name :"+fathersName+ " Name:"+employeeName;
	}
	
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public @ResponseBody String showEmployeee(){
		return "Employee showed";
	}

}