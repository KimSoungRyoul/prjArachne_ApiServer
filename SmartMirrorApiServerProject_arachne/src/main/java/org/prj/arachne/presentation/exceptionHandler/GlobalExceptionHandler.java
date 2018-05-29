package org.prj.arachne.presentation.exceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.prj.arachne.application.exception.ArachneNickAndUserEmialDuplicatedException;
import org.prj.arachne.application.exception.FailTOFCMPushMessageException;
import org.prj.arachne.application.exception.FailToDoItemServiceException;
import org.prj.arachne.application.exception.FailUpdateToDoItemServiceException;
import org.prj.arachne.application.exception.UnSignedMemberException;
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


    @ExceptionHandler(value = FailToDoItemServiceException.class)
    public ResponseEntity<Map<String, Object>> ToDoItemServiceException() {

        Map<String, Object> response = new HashMap<>();

        response.put("status", new StatusEntity("Schedule Api ", ArachneStatus.BADIO,
                "toDoItem 관련 요청이 실패했습니다 input 형식을 검토해주세요"));

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = FailTOFCMPushMessageException.class)
    public ResponseEntity<Map<String, Object>> FailTOFCMPushMessageException() {

        Map<String, Object> response = new HashMap<>();

        response.put("status", new StatusEntity("MirrorSetting Api ", ArachneStatus.BADIO,
            "서버내부 데이터저장은 성공했지만 fcm에서 실패응답이 반환됨 회원의 to 가 잘못됬을 가능성이 큽니다"));

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = FailUpdateToDoItemServiceException.class)
    public ResponseEntity<Map<String, Object>> ToDoItemUpdateServiceException() {

        Map<String, Object> response = new HashMap<>();

        response.put("status", new StatusEntity("Schedule Api ", ArachneStatus.BADIO,
                "해당 TodoItem이 존재하지 않아서 수정할수 없습니다. id를 확인해주세요"));

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

    }

    //@ExceptionHandler(value = UnSignedMemberException.class)
    public ResponseEntity<Map<String, Object>> UnSignedMemberException() {

        Map<String, Object> response = new HashMap<>();

        response.put("status", new StatusEntity("Schedule Api ", ArachneStatus.BADIO,
                "존재하지 않는 회원입니다. id를 확인해주세요"));

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
    public ResponseEntity<Map<String, Object>> FileByteNullException() {


        Map<String, Object> response = new HashMap<>();

        response.put("status", new StatusEntity("contentsApi FileStorage", ArachneStatus.BADIO,
                "file의  내용물[bytes]가 없습니다  Http요청에서 내용물을 검토하세요  IOException"));

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);


    }


    @ExceptionHandler(value = ArachneNickAndUserEmialDuplicatedException.class)
    public ResponseEntity<Map<String, Object>> DuplicatedException() {


        Map<String, Object> response = new HashMap<>();

        response.put("status", new StatusEntity("contentsApi FileStorage", ArachneStatus.BADIO,
                "같은 userEmail과 file의 NickName 가진 파일이 이미 존재합니다 .."));

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);


    }


}
