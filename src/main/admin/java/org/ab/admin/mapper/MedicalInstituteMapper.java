package org.ab.admin.mapper;

import java.util.List;

import org.ab.admin.model.MedicalInstituteModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MedicalInstituteMapper {
	@Insert("insert into medicalInstitute(name,address,contactPerson,phoneNo,tin,discountPercentage,instituteType,paymentMode,category,maker) "
			+ "values(#{name},#{address},#{contactPerson},#{phoneNo},#{tin},#{discountPercentage},#{instituteType},#{paymentMode},#{category},#{maker})")
	public void saveMedicalInfo(MedicalInstituteModel info);
	
	@Update("update medicalInstitute set name=#{name},address=#{address},contactPerson=#{contactPerson},phoneNo=#{phoneNo},tin=#{tin},discountPercentage=#{discountPercentage},instituteType=#{instituteType},paymentMode=#{paymentMode},category=#{category},maker=#{maker},approved=0 where id=#{id} ")
	public void updateMedicalInfo(MedicalInstituteModel info);
	 
	 @Select("select * from medicalInstitute where name like '%'+#{searchKey}+'%' or contactPerson like '%'+#{searchKey}+'%' or phoneNo like '%'+#{searchKey}+'%' or tin like '%'+#{searchKey}+'%'")
	public List<MedicalInstituteModel> searchMedical(String searchKey);
	 @Select("select * from medicalInstitute where id=#{id}")
	public MedicalInstituteModel getMedicalInfoById(Long id);
	 @Update("update medicalInstitute set approver=#{approver},approved=1 where id=#{id} ")
	public void approveMedical(MedicalInstituteModel info);
	 @Update("update medicalInstitute set maker=#{maker},status=1 where id=#{id} ")
	public void removeMedical(MedicalInstituteModel info);
	 @Update("update medicalInstitute set maker=#{maker},status=0 where id=#{id} ")
	public void activateMedical(MedicalInstituteModel info);

	

}
