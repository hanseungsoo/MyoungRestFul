package io.myoung.sample.model;

import lombok.Data;

@Data
public class ErrorItem {
	private String field;
	private Object value;
	private String reason;
}
