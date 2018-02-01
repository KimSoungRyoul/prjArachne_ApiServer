package org.prj.arachne.presentation;

import java.util.HashMap;
import java.util.Map;

import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping("/user")
	public String index() {
		
		return "helloAPIServer";
	}
	
	
	@GetMapping("/aaa")
	public String aaaa() {
		return "NewFile";
	}
	
	
	
	
	
	
	@GetMapping("/403")
	//@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="no Autorized Token!!!")
	public ResponseEntity<Map<String,Object>> NotAuthroize403(){
		
		
		Map<String, Object> response=new HashMap<>();
		
		response.put("status", new StatusEntity("Arachne Api(ResourceServer) 담당자 김성렬", 
												ArachneStatus.UNAUTHORIZED,
												"토큰에 해당api 사용권한 권한없음 토큰의 만료, 토큰의 권한 등을 검토하세요 ")
				);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.FORBIDDEN);
		
	}
	
	
	
	
	
}
