package org.oib.core.config.security;


import org.oib.admin.model.UserAccount;
import org.oib.model.MobiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityController {


	@Autowired
	private UserDAO userDAO;


    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public @ResponseBody
    UserAccount getUserAccount()  {
        UserAccount user = userDAO.searchDatabase(SecurityUtils.getCurrentLogin());
        user.setPassword(null);
        return user;
    }

    @PreAuthorize("hasAuthority('FINACE_ADMIN')")
    @RequestMapping(value = "/resource1", method = RequestMethod.GET)
    public @ResponseBody String getResource1()  {
        return "Authorized request";
    }
    
   
}
