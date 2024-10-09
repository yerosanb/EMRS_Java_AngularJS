package org.ab.admin.model;

import java.util.List;

import org.ab.core.config.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollateralLinkedModel extends BaseModel {
	private String accountNo;
	private String collateralCode;
	private Double linkedcollateralAmt;
	private Double loanAmount;
	private String customerName ;
	
	
	
	

}
