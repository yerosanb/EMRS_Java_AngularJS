package org.ab.repository;

import java.util.List;

import org.ab.model.Employee;
import org.ab.model.MedicalInstitute;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReportRepository {

	@Select("select * from medicalInstitute")
	public List<MedicalInstitute> getAllInstitutes();
	
	@Select("select * from medicalInstitute where name = #{name}")
	public List<MedicalInstitute> getMedicalReport(@Param("name") String name);
 
	
 
	


}
