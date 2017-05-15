package org.oib.core.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import org.oib.admin.model.Role;
import org.oib.model.AccountRoleEnum;
import org.oib.model.MobiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;


/**
 * A custom service for retrieving users from a custom datasource, such as a
 * database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Transactional(readOnly = true)
@Component("userDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private UserDAO userDAO;

	/**
	 * Retrieves a user record containing the user's credentials and access.
	 */
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException, DataAccessException {
		
		

		// Declare a null Spring User
		UserDetails user = null;

		try {

			// Search database for a user that matches the specified email
			// You can provide a custom DAO to access your persistence layer
			// Or use JDBC to access your database
			// DbUser is our custom domain user. This is not the same as
			// Spring's User
			MobiUser mobiUser = userDAO.searchDatabase(email);

			// Populate the Spring User object with details from the dbUser
			// Here we just pass the email, password, and access level
			// getAuthorities() will translate the access level to the correct
			// role type

			user = new User(mobiUser.getEmail().toLowerCase(), mobiUser.getPassword()
					, true, true, true, true,
					getAuthorities(mobiUser.getRoles()));

		} catch (Exception e) {
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}

		// Return user to Spring for processing.
		// Take note we're not the one evaluating whether this user is
		// authenticated or valid
		// We just merely retrieve a user that matches the specified email
		return user;
	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where
	 * access level is an Integer. Basically, this interprets the access value
	 * whether it's for a regular user or admin.
	 * 
	 * @param access
	 *            an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	@SuppressWarnings("deprecation")
	public Collection<GrantedAuthority> getAuthorities(List<Role> roles) {
		
		// Create a list of grants for this user
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);

		for(Role role : roles){
			
			authList.add(
				new GrantedAuthority() {
				@Override
				public String getAuthority() {
					// TODO Auto-generated method stub
					return role.getCode().toString();
				}
			});
		}

		// Return list of granted authorities
		return authList;
	}
}