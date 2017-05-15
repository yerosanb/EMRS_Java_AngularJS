package org.oib.admin.service;

import java.util.List;

import org.oib.admin.model.Branch;
import org.oib.admin.model.City;
import org.oib.admin.model.Department;
import org.oib.admin.repository.SystemLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLookUpService {

	@Autowired 
	private SystemLookupRepository repository;
	
	public List<Branch> getAllBranches() {
		return repository.getAllBranches();
	}

	public List<City> getAllCities() {
		return repository.getAllCities();
	}

	public List<Department> getAllDepartments() {
		return repository.getAllDepartments();
	}

	public Branch getBranchById(Long id) {
		return repository.getBranchById(id);
	}

	public void saveBranch(Branch branch) {
		repository.saveBranch(branch);
	}

	public City getCityById(Long id) {
		return repository.getCityById(id);
	}

	public void saveCity(City city) {
		repository.saveCity(city);
	}

	public Department getDepartmentById(Long id) {
		return repository.getDepartmentById(id);
	}

	public void saveDepartment(Department department) {
		repository.saveDepartment(department);
		
	}

}
