package org.ab.admin.model;

import org.ab.core.config.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalInstituteModel extends BaseModel{
	private String name;
	private String address;
	private String contactPerson;
	private String phoneNo;
	private String tin;
	private String discountPercentage;
	private String instituteType;
	private String paymentMode;
	private String category;
	private String savedDate;
	private String maker;
	private Long approved;
	private String approver;
	private String changedDate;
	private Long status;

}
