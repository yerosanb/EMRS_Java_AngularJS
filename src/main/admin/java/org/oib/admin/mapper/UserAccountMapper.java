package org.oib.admin.mapper;

import java.util.List;

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
import org.oib.admin.model.Right;
import org.oib.admin.model.UserAccount;
import org.oib.model.Employee;
import org.oib.model.MobiUser;

@Mapper
public interface UserAccountMapper {

	@Select("select * from employee")
	public List<Employee> getAllEmployee();

	@Delete("delete from employee where id = #{id}")
	public void delete(Long id);

	@Insert("INSERT INTO `users` "
			+ "(`password`, `enabled`, `firstname`, `address`, `lastname`, `city`, `branch`, `department`, `phone_number`, `cellphone_number`, `email`) "
			+ "VALUES "
			+ "(#{password}, b'1', #{firstName}, #{address}, #{lastName}, #{city}, #{branch}, #{department}, #{officePhoneNumber}, #{cellPhoneNo}, #{email});")
	@Options(useGeneratedKeys = true)
	public void insert(UserAccount account);

	@Update("update employee set fname=#{fname}, lastname=#{lastname}, age=#{age} where id = #{id}")
	public void updateEmployee(Employee emp);

	@Select("select * from users where email = #{email}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			 @Result(property="roles", javaType=List.class, column = "id", 
         	many=@Many(select="org.oib.admin.mapper.UserRoleMapper.getRolesByUserId")),
			 @Result(property="rights", javaType=List.class, column = "id", 
         	many=@Many(select="org.oib.admin.mapper.UserRightsMapper.getUserRights"))
			 
	    })
	public UserAccount searchUser(String email);

	@Select("select * from users where firstname like concat('%', #{searchKey}, '%') "
			+ "or lastname like concat('%', #{searchKey}, '%') "
			+ "or email like concat('%', #{searchKey}, '%') ")
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
            	many=@Many(select="org.oib.admin.mapper.UserRoleMapper.getRolesByUserId"))
	    })
	public UserAccount getUserAccountById(Long id);
	

	@Insert("insert into user_role (user_id, role_id) values (#{user_id}, #{role_id})")
	public void addUserRoles(@Param("user_id") Long user_id, @Param("role_id") Long role_id);

	@Update("update `users` set `enabled`=b'1', `firstname`=#{firstName}, `address`=#{address}, `lastname`=#{lastName}, `city`=#{city},"
			+ " `branch`=#{branch}, `department`=#{department}, `phone_number`=#{officePhoneNumber}, `cellphone_number`=#{cellPhoneNo}, `email`=#{email}"
			+ " where id = #{id}")
	public void update(UserAccount user);
	
	@Delete("delete from user_role where user_id = #{id}")
	public void removeAllUserRoles(UserAccount user);

}
