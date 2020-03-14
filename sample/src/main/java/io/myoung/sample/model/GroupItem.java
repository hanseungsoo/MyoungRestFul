package io.myoung.sample.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class GroupItem {
	
	private int gSeq;
	
	private String name;
}
