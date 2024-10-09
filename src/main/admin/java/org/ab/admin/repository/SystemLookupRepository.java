package org.ab.admin.repository;

import java.util.List;

import org.ab.admin.mapper.SystemLookupMapper;
import org.ab.admin.model.Branch;
import org.ab.admin.model.City;
import org.ab.admin.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SystemLookupRepository {

	@Autowired
	private SystemLookupMapper mapper;
	
	public List<Branch> getAllBranches() {
		return mapper.getAllBranches();
	}

	public List<City> getAllCities() {
		return mapper.getAllCities();
	}

	public List<Department> getAllDepartments() {
		return mapper.getAllDepartments();
	}

	public Branch getBranchById(Long id) {
		return mapper.getBranchById(id);
	}

	public void saveBranch(Branch branch) {
		if(branch.getId() == null)
			mapper.insertBranch(branch);
		else
			mapper.updateBranch(branch);
	}

	public City getCityById(Long id) {
		return mapper.getCityById(id);
	}

	public void saveCity(City city) {
		if(city.getId() == null)
			mapper.insertCity(city);
		else
			mapper.updateCity(city);
	}

	public Department getDepartmentById(Long id) {
		return mapper.getDepartmentById(id);
	}

	public void saveDepartment(Department department) {
		if(department.getId() == null)
			mapper.insertDepartment(department);
		else
			mapper.updateDepartment(department);
	}

}
