package org.ab.admin.model;


import org.ab.core.config.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch extends BaseModel {
	
	
	
	private String branch;
	
	
	private String branchCode;
	private Long status;

}
