package org.ab.admin.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.ab.core.config.model.BaseModel;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollateralInfoModel extends BaseModel {
	private String collateralCode;
//	@NotBlank(message = "select collateral type")
	private String collateralType;
//	@NotBlank(message = "enter collateral name")
	private String collateralName;
//	@NotNull(message = "enter estimated amt")
//	@Range(min=1,message="less than minimum")
	//@Size(min=1,message="less than minimum")
	private Double estimatedAmount;
//	@NotBlank(message = "select estimated date")
	private String estimatedDate;
	private String branch;
	private String branchCode;
	
	private String yearOfMake;
	
	private String maker;
	@NotEmpty(message = "list not added")
	private List<CollateralLinkedModel> collInfo;
	
	private List<CollateralInfoModel> collateralReg;
	
	private String enteredDate;
	
	private String guarantorName;
	
	

}
