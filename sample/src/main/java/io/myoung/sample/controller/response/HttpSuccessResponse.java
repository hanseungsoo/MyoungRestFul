package io.myoung.sample.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpSuccessResponse<T> {
	private T data;
}
