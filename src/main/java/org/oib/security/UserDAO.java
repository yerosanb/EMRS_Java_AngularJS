package org.oib.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.oib.model.MobiUser;
import org.oib.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * A custom DAO for accessing data from the database.
 * 
 */
@Component
public class UserDAO {

	protected static Logger logger = Logger.getLogger("dao");

	@Autowired
	EmployeeRepository mapper;

	/**
	 * Simulates retrieval of data from a database.
	 */
	public MobiUser searchDatabase(String username) {

		
		//Retrieve user by username from the database
		MobiUser user = mapper.searchUser(username);
		return mapper.searchUser(username);
		
		
		/*MobiUser mobiUser;

		// Search user based on the parameters
		for (Map<String, Object> userMap : usersMap) {
			
			List<MobiUser> usders = new ArrayList<MobiUser>();
			
			MobiUser user = null;

			user = new MobiUser();
			
			user.setUsername(userMap.get("username").toString());

			user.setPassword(userMap.get("password").toString());
			
			user.setRole(AccountRoleEnum.valueOf((userMap.get("role").toString())));
			
			user.setIsEnabled(Boolean.parseBoolean(userMap.get("enabled").toString()));
			
			user.setAccess( user.getRole() == AccountRoleEnum.ADMIN ? 1 : 0);

			logger.debug("User found");
			
			// return matching user
			return user;
			
		}
		*/

		
		//throw new RuntimeException("User does not exist!");
	}





}