package org.prj.arachne.presentation.exceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = FileNotFoundException.class)
	public ResponseEntity<Map<String, Object>> fileFoundFailExctpion() {

		Map<String, Object> response = new HashMap<>();

		response.put("status", new StatusEntity("contentsApi FileStorage", ArachneStatus.NO_RESOURCE,
				"해당 파일을 찾을수 없음 fileName 또는 userEmail에서 오탈자 검토하세요 "));

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

	}
/*
	@ExceptionHandler(value = { MultipartException.class})
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public Map<String, Object> fileSizeLimitedExcetion() {

		Map<String, Object> response = new HashMap<>();

		response.put("status", new StatusEntity("contentsApi FileStorage", ArachneStatus.SIZELIMITED,
				"컨텐츠의 용량이 허용치를 초과했습니다(max : 100MB) 관리자에게 개선또는 파일의 용량을 줄이셔야합니다 "));

		return response;
	}*/

	
	//@ExceptionHandler(value=IOException.class)
	public ResponseEntity<Map<String, Object>> FileByteNullException(){
		
		
		Map<String, Object> response = new HashMap<>();

		response.put("status", new StatusEntity("contentsApi FileStorage", ArachneStatus.BADIO,
				"file의  내용물[bytes]가 없습니다  Http요청에서 내용물을 검토하세요  IOException"));

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		
		
	}
	
}
