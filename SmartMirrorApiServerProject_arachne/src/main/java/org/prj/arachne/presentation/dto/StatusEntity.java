package org.prj.arachne.presentation.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusEntity {

	private String apiType;
	private HttpStatus statusCode;
	private String message;
	
	
}
