package org.prj.arachne.presentation.api;

import java.util.HashMap;
import java.util.Map;

import org.prj.arachne.presentation.api.urlmapper.Version1ApiMapping;
import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoApiController implements Version1ApiMapping{

	
	@GetMapping("/memo/{userId}/{month}")
	public ResponseEntity<Map<String, Object>> requestMemoList(@PathVariable("userId") String userId, 
																	  @PathVariable("month") String month){
		
		

		ResponseEntity<Map<String, Object>> entity=null;
				
				
		
		Map<String, Object> values=new HashMap<>();
		values.put("entity", null);
		values.put("status", new StatusEntity("MemberInfo Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));
		
		entity=new ResponseEntity<>(values,HttpStatus.OK);
		
		return entity;
	}
	
	@PostMapping("/memo/{userId}")
	public ResponseEntity<Map<String, Object>> saveInRemoteStorage(@PathVariable("userId")String userId){
		
		
		
		ResponseEntity<Map<String, Object>> entity=null;
				
				
		
		Map<String, Object> values=new HashMap<>();
		values.put("entity", null);
		values.put("status", new StatusEntity("MemberInfo Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));
		
		entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.CREATED);
		
		return entity;
		
	}
	
	@DeleteMapping("/memo/{memoId}")
	public ResponseEntity<Map<String, Object>> removeOneMemo(@PathVariable("memoId")String memoId){
		
		ResponseEntity<Map<String, Object>> entity=null;
								
		
		Map<String, Object> values=new HashMap<>();
		values.put("entity", null);
		values.put("status", new StatusEntity("MemberInfo Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));
		
		entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.ACCEPTED);
		
		return entity;
		
		
		
	}
	
	
	
	
	
}
