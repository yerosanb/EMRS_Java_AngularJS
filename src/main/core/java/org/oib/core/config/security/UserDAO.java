package org.oib.core.config.security;

import org.apache.log4j.Logger;
import org.oib.admin.mapper.UserAccountMapper;
import org.oib.admin.model.UserAccount;
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