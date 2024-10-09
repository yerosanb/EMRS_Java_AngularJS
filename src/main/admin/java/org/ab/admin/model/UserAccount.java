package org.ab.admin.model;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.ab.core.config.model.BaseModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAccount extends BaseModel {

	private String empId;
	@NotEmpty(message = "{firstName.required}")
	private String firstName;

	@NotEmpty(message = "{lastName.required}")
	private String lastName;

	private String address;

	private String city;

	private String branch;
	private String branchCode;

	private String department;

	@NotEmpty(message = "{password.required}")
	private String password;

	@NotEmpty(message = "{confirmPassword.required}")
	private String confirmPassword;

	private String officePhoneNumber;

	private String cellPhoneNo;

	private int enabled;
	
	private List<String> rights;
	
//	@Email(message = "{email.invalid}")
//	@NotEmpty(message = "{email.required}")
	private String email;

	@NotEmpty(message = "{roles.required}")
	private Collection<Role> roles;
	
	@AssertTrue(message = "{confirmPassword.not.match}")
	private boolean isValid() {
		if (password == null) {
			return confirmPassword == null;
		} else {
			return password.equals(confirmPassword);
		}
	}
}
