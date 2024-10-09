package org.ab.admin.mapper;

import java.util.List;

import org.ab.admin.model.Branch;
import org.ab.admin.model.City;
import org.ab.admin.model.Department;
import org.apache.ibatis.annotations.*;

public interface SystemLookupMapper {

	@Select("select * from branches")
	public List<Branch> getAllBranches();
	
	@Select("select * from cities")
	public List<City> getAllCities();
	
	@Select("select * from departments")
	public List<Department> getAllDepartments();

	@Select("select * from branches where id = #{id}")
	public Branch getBranchById(Long id);

	@Insert("insert into branches (code, name) values (#{code}, #{name})")
	@Options(useGeneratedKeys = true)
	public void insertBranch(Branch branch);

	@Update("update branches set code = #{code}, name=#{name} where id=#{id}")
	public void updateBranch(Branch branch);

	@Select("select * from cities where id = #{id}")
	public City getCityById(Long id);

	@Insert("insert into cities (code, name) values (#{code}, #{name})")
	@Options(useGeneratedKeys = true)
	public void insertCity(City city);

	@Update("update cities set code = #{code}, name=#{name} where id=#{id}")
	public void updateCity(City city);

	@Select("select * from departments where id = #{id}")
	public Department getDepartmentById(Long id);

	@Insert("insert into departments (code, name) values (#{code}, #{name})")
	@Options(useGeneratedKeys = true)
	public void insertDepartment(Department department);

	@Update("update departments set code = #{code}, name=#{name} where id=#{id}")
	public void updateDepartment(Department department);
	

}
