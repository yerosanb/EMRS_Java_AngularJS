package org.oib.security;

import lombok.NoArgsConstructor;

import org.oib.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is responsible for checking if the user has the given rights.
 */

@Component
@NoArgsConstructor
public class PermissionEvaluator {

  @Autowired
  private EmployeeRepository mapper;

  public Boolean hasPermission(Long userId, String commaSeparatedRights) {
	  
    //1. Get the list of rights of the user based on the userId

	//2. Compare the --commaSeparatedRights-- and the  current user rights
	  
	// if the rights are existing return true else false
	  
	 return true;
  }



}
