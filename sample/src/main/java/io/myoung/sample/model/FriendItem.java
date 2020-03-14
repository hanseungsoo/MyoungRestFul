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
public class FriendItem {
	
	@NotNull(message = "UserId may not be null")
	@NotBlank(message = "UserId may not be blank")
	private int uSeq;
	
	private String name;
	
	private String id;
	
	@Email(message = "Email should be valid")
	@NotNull(message = "UserEmail may not be null")
	@NotBlank(message = "UserEmail may not be blank")
	private String userMail;
}
