package io.myoung.sample.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpResponse<T> {
	private T data;
}
