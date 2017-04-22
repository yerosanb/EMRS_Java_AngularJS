package org.oib.security;


import org.oib.model.MobiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeurityController {


	@Autowired
	private UserDAO userDAO;


    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public @ResponseBody
    MobiUser getUserAccount()  {
        MobiUser user = userDAO.searchDatabase(SecurityUtils.getCurrentLogin());
        user.setPassword(null);
        return user;
    }

    @PreAuthorize("hasAuthority('FINACE_ADMIN')")
    @RequestMapping(value = "/resource1", method = RequestMethod.GET)
    public @ResponseBody String getResource1()  {
        return "Authorized request";
    }
    
   
}
