package org.ab.admin.model;

import org.ab.core.config.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanUsedPercentModel extends BaseModel {
	private String accountNo;
	private Float loanUsedPercent;
	private Float collateralUsedPerc;

}
