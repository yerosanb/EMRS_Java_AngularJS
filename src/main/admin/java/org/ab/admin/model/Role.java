package org.ab.admin.model;

import java.util.Collection;

import org.ab.core.config.model.BaseModel;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class Role extends BaseModel {

	@NotEmpty(message = "{code.required}")
	private String code;
	
	@NotEmpty(message = "{name.required}")
	private String name;
	
	private String description;
	
	@NotEmpty(message = "{rights.required}")
	private Collection<Right> rights;
}
