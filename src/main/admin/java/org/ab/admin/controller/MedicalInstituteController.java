package org.ab.admin.controller;


import org.ab.admin.model.MedicalInstituteModel;
import org.ab.admin.service.MedicalInstituteService;
import org.ab.core.controller.response.AGPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicalInstitute/")
public class MedicalInstituteController extends BaseController  {
	@Autowired
	private MedicalInstituteService serv;
	
	@RequestMapping(value="saveMedicalInfo", method = RequestMethod.POST)
	public ResponseEntity<AGPResponse> saveMedicalInfo(@RequestBody @Validated MedicalInstituteModel info){
		
		try {
			if(info.getId()==null) {
			serv.saveMedicalInfo(info);
			}
			else {
				serv.updateMedicalInfo(info);
			}
		}
		catch(DuplicateKeyException ex){
			return AGPResponse.error(
					getMessage("role.save.error.duplicate.code"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return AGPResponse.success(getMessage("user.saved.successfully"));
	}
	
	@RequestMapping(value="searchMedical",method=RequestMethod.GET)
	public ResponseEntity searchMedical(@RequestParam  String searchKey, @RequestParam  String makerId){
		
		return AGPResponse.response(serv.searchMedical(searchKey,makerId));
	}
	
	@RequestMapping(value="getMedicalInfoById/{id}",method=RequestMethod.GET)
	public ResponseEntity getMedicalInfoById(@PathVariable("id")  Long id){
		
		return AGPResponse.response(serv.getMedicalInfoById(id));
	} 
	
	@RequestMapping(value="approveMedical", method = RequestMethod.POST)
	public ResponseEntity<AGPResponse> approveMedical(@RequestBody @Validated MedicalInstituteModel info){
		
		try {
			
			serv.approveMedical(info);
			
		}
		catch(DuplicateKeyException ex){
			return AGPResponse.error(
					getMessage("role.save.error.duplicate.code"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return AGPResponse.success(getMessage("user.saved.successfully"));
	}
	
	@RequestMapping(value="removeActivateMedical", method = RequestMethod.POST)
	public ResponseEntity<AGPResponse> removeActivateMedical(@RequestBody @Validated MedicalInstituteModel info){
		
		try {
			if(info.getStatus()==0) {
			serv.removeMedical(info);
			}
			else {
				serv.activateMedical(info);
			}
			
		}
		catch(DuplicateKeyException ex){
			return AGPResponse.error(
					getMessage("role.save.error.duplicate.code"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return AGPResponse.success(getMessage("user.saved.successfully"));
	}
	
}
