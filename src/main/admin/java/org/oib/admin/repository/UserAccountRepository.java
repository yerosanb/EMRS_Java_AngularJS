package org.oib.admin.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.oib.model.Employee;
import org.oib.model.MobiUser;

@Mapper
public interface UserAccountRepository {

	@Select("select * from employee")
	public List<Employee> getAllEmployee();

	@Delete("delete from employee where id = #{id}")
	public void delete(Long id);

	@Insert("INSERT INTO `oib`.`users` "
			+ "(`username`, `password`, `enabled`, `firstname`, `address`, `lastname`, `city`, `branch`, `department`, `phone_number`, `cellphone_number`, `email`) "
			+ "VALUES "
			+ "('__NOT_PROVIDED_', #{password}, b'1', #{firstName}, #{address}, '#{lastName}, #{city}, #{branch}, #{department}, '#{phoneNumber}, #{cellPhoneNo}, #{officePhoneNumber});")
	public void insert(Employee emp);

	@Update("update employee set fname=#{fname}, lastname=#{lastname}, age=#{age} where id = #{id}")
	public void updateEmployee(Employee emp);
	
	@Select("select * from users where username = #{username}")
	public MobiUser searchUser(String username);

}
