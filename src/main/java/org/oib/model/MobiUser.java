package org.oib.model;


public class MobiUser {

	/**
	  * The Username
	  */
	 private String username;
	  
	 /**
	  * The password as an MD5 value
	  */
	 private String password;
	
	 private AccountRoleEnum role;
	 
	 private Boolean isEnabled;
	 
	 private Integer access;
	  
	 public String getUsername() {
	  return username;
	 }
	 
	 public void setUsername(String username) {
	  this.username = username;
	 }
	 
	 public String getPassword() {
	  return password;
	 }
	 
	 public void setPassword(String password) {
	  this.password = password;
	 }
	
	public AccountRoleEnum getRole() {
		return role;
	}
	public void setRole(AccountRoleEnum role) {
		this.role = role;
	}

	public Boolean IsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}
	  
}
