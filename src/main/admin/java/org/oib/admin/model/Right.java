package org.oib.admin.model;

import org.oib.core.config.model.BaseModel;

import lombok.Data;

@Data
public class Right extends BaseModel {
	
	private String code;
	private String description;

}
