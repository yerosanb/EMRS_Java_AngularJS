package org.ab.admin.model;

import java.util.List;

import org.ab.core.config.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReportCollateralLinkedModel extends BaseModel{
	private String collateralCode;
//	private String collateralType;
//	private String collateralName;
//	private String estimatedAmount;
//	private String accountNo;
//	private String linkedcollateralAmt;
//	private String maker;
	
	private List<CollateralInfoModel> colInfo;
	private List<CollateralLinkedModel> linkedAct;

}
