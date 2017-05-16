package org.oib.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.oib.admin.model.UserAccount;
import org.oib.model.Employee;
import org.oib.model.MobiUser;

@Mapper
public interface EmployeeRepository {

	@Select("select * from employee")
	public List<Employee> getAllEmployee();

	@Delete("delete from employee where id = #{id}")
	public void delete(Long id);

	@Insert("insert into employee (`fname`,`lastname`,`age`) values (#{fname}, #{lastname}, #{age})")
	public void insert(Employee emp);

	@Update("update employee set fname=#{fname}, lastname=#{lastname}, age=#{age} where id = #{id}")
	public void updateEmployee(Employee emp);
	


}
