package org.oib.model;

import java.util.List;

import org.oib.admin.model.Role;
import org.oib.core.config.model.BaseModel;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MobiUser extends BaseModel {

	/**
	  * The Username
	  */
	 private String username;
	  
	 /**
	  * The password as an MD5 value
	  */
	 private String password;
	
	 private List<Role> roles;
	 
	 private Boolean isEnabled;
	 
	 private Integer access;
	 
	 private String firstName;
	 
	 private String lastName;
	 
	 private String email;
	  
	  
}
