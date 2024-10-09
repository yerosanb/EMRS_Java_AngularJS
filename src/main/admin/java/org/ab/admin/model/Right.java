package org.ab.admin.model;

import org.ab.core.config.model.BaseModel;

import lombok.Data;

@Data
public class Right extends BaseModel {
	
	private String code;
	private String description;

}
