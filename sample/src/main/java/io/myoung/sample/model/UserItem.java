package io.myoung.sample.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserItem {
	
	private int uSeq;
	
	@NotNull(message = "Name may not be null")
	@NotBlank(message = "Name may not be blank")
	private String name;
	
	@NotNull(message = "Id may not be null")
	@NotBlank(message = "Id may not be blank")
	private String id;
	
	@NotNull(message = "Password may not be null")
	@NotBlank(message = "Password may not be blank")
	private String password;
	
	@NotNull(message = "Phone may not be null")
	@NotBlank(message = "Phone may not be blank")
	private String phone;
	
	@Email(message = "Email should be valid")
	@NotNull(message = "Email may not be null")
	@NotBlank(message = "Email may not be blank")
	private String email;
}
