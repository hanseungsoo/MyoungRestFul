package io.myoung.sample.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class GroupItem {
	
	@NotNull(message = "GroupId may not be null")
	@NotBlank(message = "GroupId may not be blank")
	private int groupId;
	
	@NotNull(message = "GroupName may not be null")
	@NotBlank(message = "GroupName may not be blank")
	private String groupName;
}
