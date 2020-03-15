package io.myoung.sample.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserItem {
	
	public UserItem() {
	}

	private int uSeq;
	
	@NotNull(message = "Name may not be null")
	@NotBlank(message = "Name may not be blank")
	@Max(20)
	private String name;
	
	@NotNull(message = "Id may not be null")
	@NotBlank(message = "Id may not be blank")
	@Max(10)
	private String id;
	
	@NotNull(message = "Password may not be null")
	@NotBlank(message = "Password may not be blank")
	@Max(24)
	private String password;
	
	@NotNull(message = "Phone may not be null")
	@NotBlank(message = "Phone may not be blank")
	@Max(20)
	private String phone;
	
	@Email(message = "Email should be valid")
	@NotNull(message = "Email may not be null")
	@NotBlank(message = "Email may not be blank")
	@Max(20)
	private String email;
}
