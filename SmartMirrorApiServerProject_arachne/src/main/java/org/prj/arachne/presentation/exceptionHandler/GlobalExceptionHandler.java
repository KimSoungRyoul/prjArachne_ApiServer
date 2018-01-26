package org.prj.arachne.presentation.exceptionHandler;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.prj.arachne.presentation.dto.StatusEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value=FileNotFoundException.class)
	public ResponseEntity<Map<String, Object>> fileFoundFailExctpion(){
		
		Map<String, Object> response=new HashMap<>();
		
		response.put("status", new StatusEntity("contentsApi FileStorage", 
												HttpStatus.NOT_FOUND,
												"해당 파일을 찾을수 없음 fileName 또는 userEmail에서 오탈자 검토하세요 ")
				);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		
	}
	
	
	
}
