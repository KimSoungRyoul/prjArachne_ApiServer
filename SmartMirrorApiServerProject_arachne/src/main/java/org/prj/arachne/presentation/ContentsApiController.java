package org.prj.arachne.presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/contents")
public class ContentsApiController {
	
	@Autowired
	private String filePathDefault;

	@GetMapping("/{userEmail}/{fileNo}")
	public String asdf(@RequestParam("requesttype") String requestType,
						@PathVariable("userEmail")String userEmail,
						@PathVariable("fileName") String fileName) {
		
		
		
		
		
		String filePath=new StringBuilder()
							.append(filePathDefault)
							.append(File.separator)
							.append(userEmail)
							.append(File.separator)
							
		
		
		
		switch (requestType) {
		
			case "streaming":
				streaming();
				break;
				
			case "download":
				download();
				break;
				
			default:
				break;
		}
			
		return null;
	}
	
	
	private void download() {
		// TODO Auto-generated method stub
		
	}


	private ResponseEntity<InputStreamResource> streaming(){
		
		
		
		return null;
	}
	
	
	
	

}
