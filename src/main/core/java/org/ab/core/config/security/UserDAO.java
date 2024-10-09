package org.ab.core.config.security;

import org.ab.admin.mapper.UserAccountMapper;
import org.ab.admin.model.UserAccount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * A custom DAO for accessing data from the database.
 * 
 */
@Component
public class UserDAO {

	protected static Logger logger = Logger.getLogger("dao");

	@Autowired
	UserAccountMapper mapper;

	/**
	 * Simulates retrieval of data from a database.
	 */
	public UserAccount searchDatabase(String email) {
		
		return mapper.searchUser(email);
		
	}





}