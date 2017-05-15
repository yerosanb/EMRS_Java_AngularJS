package org.oib.admin.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.oib.core.config.model.BaseModel;

import lombok.Data;

@Data
public class City extends BaseModel {
	
	@NotBlank(message = "{code.required}")
	@Size(max=50, message="{code.max.size}")
	private String code;
	
	@NotBlank(message = "{name.required}")
	private String name;

}
