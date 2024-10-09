package org.ab.admin.service;

import java.util.List;

import org.ab.admin.mapper.MedicalInstituteMapper;
import org.ab.admin.model.CollateralInfoModel;
import org.ab.admin.model.MedicalInstituteModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MedicalInstituteService {
	@Autowired
	private MedicalInstituteMapper map;
	public void saveMedicalInfo(MedicalInstituteModel info) {
		map.saveMedicalInfo(info);
		
		
	}
	public void updateMedicalInfo(MedicalInstituteModel info) {
		map.updateMedicalInfo(info);
		
	}
	public List<MedicalInstituteModel> searchMedical(@Param("searchKey") String searchKey, @Param("makerId") String makerId) {
		
		return map.searchMedical(searchKey);
	}
	public MedicalInstituteModel getMedicalInfoById(Long id) {
		
		return map.getMedicalInfoById(id);
	}
	public void approveMedical(MedicalInstituteModel info) {
		map.approveMedical(info);
		
	}
	public void removeMedical(MedicalInstituteModel info) {
		map.removeMedical(info);
		
	}
	public void activateMedical(MedicalInstituteModel info) {
		map.activateMedical(info);
		
	}
	

}
