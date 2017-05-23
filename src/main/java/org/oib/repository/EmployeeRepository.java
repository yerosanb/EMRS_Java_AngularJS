package org.oib.repository;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.oib.model.Employee;

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
