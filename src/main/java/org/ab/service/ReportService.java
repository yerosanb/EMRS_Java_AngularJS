package org.ab.service;

import java.util.List;

import org.ab.model.Employee;
import org.ab.model.MedicalInstitute;
import org.ab.repository.EmployeeRepository;
import org.ab.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ReportService {
	
	@Autowired
	ReportRepository mapper;

	public List<MedicalInstitute> getAllInstitutes() {
		System.out.println("this is service");
		return mapper.getAllInstitutes();
		
	}

	public List<MedicalInstitute> getMedicalReport(String name) {
		System.out.println("this is service");
		return mapper.getMedicalReport(name);
		
	}
	 

	


}
