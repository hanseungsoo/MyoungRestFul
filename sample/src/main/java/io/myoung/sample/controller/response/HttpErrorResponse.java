package io.myoung.sample.controller.response;

import java.util.List;

import io.myoung.sample.model.ErrorItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpErrorResponse {
	private String message;
	private String status;
	private List<ErrorItem> errors;
}
