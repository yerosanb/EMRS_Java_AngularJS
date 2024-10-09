package org.ab.admin.mapper;

import java.util.List;

import org.ab.admin.model.Right;
import org.ab.admin.model.UserAccount;
import org.ab.admin.model.dto.ChangeMyPasswordDto;
import org.ab.admin.model.dto.ChangePasswordDto;
import org.ab.model.Employee;
import org.ab.model.MobiUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserAccountMapper {

	@Select("select * from employee")
	public List<Employee> getAllEmployee();

	@Delete("delete from employee where id = #{id}")
	public void delete(Long id);

	@Insert("INSERT INTO users "
			+ "(password, enabled, firstname, address, lastname, city, branch, branchCode,department, phone_number, cellphone_number, email,empId) "
			+ "VALUES "
			+ "(#{password}, 1, #{firstName}, #{address}, #{lastName}, #{city}, #{branch},#{branchCode}, #{department}, #{officePhoneNumber}, #{cellPhoneNo}, #{email},#{empId} );")
	@Options(useGeneratedKeys = true)
	public void insert(UserAccount account);

	@Update("update employee set fname=#{fname}, lastname=#{lastname}, age=#{age} where id = #{id}")
	public void updateEmployee(Employee emp);

	@Select("select * from users where email = #{email}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			 @Result(property="roles", javaType=List.class, column = "id", 
         	many=@Many(select="org.ab.admin.mapper.UserRoleMapper.getRolesByUserId")),
			 @Result(property="rights", javaType=List.class, column = "id", 
         	many=@Many(select="org.ab.admin.mapper.UserRightsMapper.getUserRights"))
			 
	    })
	public UserAccount searchUser(String email);

	@Select("select * from users where firstname like '%'+#{searchKey}+'%' "
			+ "or lastname like '%'+#{searchKey}+'%' "
			+ "or email like '%'+#{searchKey}+'%' ")
	@Results(value = {
			@Result(property = "officePhoneNumber", column = "phone_number"),
			@Result(property = "cellPhoneNo", column = "cellphone_number"),
	    })
	public List<UserAccount> searchUserByKeyWord(String searchKey);

	@Select("select * from users where id = #{id}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "officePhoneNumber", column = "phone_number"),
			@Result(property = "cellPhoneNo", column = "cellphone_number"),
			 @Result(property="roles", javaType=List.class, column = "id", 
            	many=@Many(select="org.ab.admin.mapper.UserRoleMapper.getRolesByUserId"))
	    })
	public UserAccount getUserAccountById(Long id);
	

	@Insert("insert into user_role (user_id, role_id) values (#{user_id}, #{role_id})")
	public void addUserRoles(@Param("user_id") Long user_id, @Param("role_id") Long role_id);

	@Update("update users set enabled=1, firstname=#{firstName}, address=#{address}, lastname=#{lastName}, city=#{city},"
			+ " branch=#{branch},branchCode=#{branchCode} department=#{department}, phone_number=#{officePhoneNumber}, cellphone_number=#{cellPhoneNo}, email=#{email},empId=#{empId} "
			+ " where id = #{id}")
	public void update(UserAccount user);
	
	@Delete("delete from user_role where user_id = #{id}")
	public void removeAllUserRoles(UserAccount user);

	@Update("update users set password = #{password} where id = #{id}")
	public void changePassword(ChangePasswordDto passDto);

	@Select("select password from users where email = #{email}")
	public String getUserPassword(String email);

	@Update("update users set password = #{param1} where email = #{param2}")
	public void changeMyPassword(String password, String email);

}
