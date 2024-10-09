package org.ab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalInstitute {
	private Long id;
	private String name;
	private String address;
//	private Integer type;

}
