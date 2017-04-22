package org.oib.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("finance")
public class FinanceController {
	
	@RequestMapping("/hello")
	public  String sayHi(Model model){
		
		
		
		return "Hi OIB!";
	}
	
	
	@RequestMapping("/bye")
	public String sayBye(){
		return "Good bye OIB!";
	}
	

}
