package org.ab.controller;

import java.util.List;

import org.ab.model.Employee;
import org.ab.model.MedicalInstitute;
import org.ab.service.EmployeeService;
import org.ab.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report/")
public class ReportController {
	
	@Autowired
	ReportService service;
	
	@RequestMapping(value="medicalInstitute/getall", method=RequestMethod.GET)
	
	public List<MedicalInstitute> getAllInstitutes(){		
		return service.getAllInstitutes();
	}
	
   @RequestMapping(value="medicalReport/getall", method=RequestMethod.GET)
	public List<MedicalInstitute> getMedicalReport(@RequestParam String name, @RequestParam("reportType") String reportType){
		System.out.println("this is controller"+ name + reportType);
		System.out.println("this is controller"+ service.getMedicalReport(name));

		
		
		return service.getMedicalReport(name);
		
	}
}
