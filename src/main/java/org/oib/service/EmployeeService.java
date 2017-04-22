package org.oib.service;

import java.util.List;

import org.oib.model.Employee;
import org.oib.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmployeeService {
	
	@Autowired
	EmployeeRepository mapper;

	public List<Employee> getAllEmployee() {
		return mapper.getAllEmployee();
		
	}

	public void delete(Long id) {
	 mapper.delete(id);
		
	}

	public void insert(Employee emp) {
		mapper.insert(emp);
		
	}

	public void updateEmployee(Employee emp) {
		mapper.updateEmployee(emp);
		
	}

	


}
