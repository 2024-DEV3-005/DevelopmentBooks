package com.store.book.response.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ExceptionResponse {

	@Schema(example = "Error While processing data", description = "Error/Exception message")
	private String message;

}
