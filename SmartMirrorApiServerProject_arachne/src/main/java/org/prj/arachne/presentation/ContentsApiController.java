package org.prj.arachne.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.prj.arachne.application.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping(value = "/api/contents")
@Log4j
public class ContentsApiController {
	
	@Autowired
	private String filePathDefault;

	@Autowired
	private ContentsService contentsService;
	
	@GetMapping("/{userEmail}/{fileName}")
	public ResponseEntity<InputStreamResource> requireContents(@RequestParam("requesttype") String requestType,
						@PathVariable("userEmail")String userEmail,
						@PathVariable("fileName") String fileName) {
		
		
		
		
		
		String filePath=new StringBuilder()
							.append(filePathDefault)
							.append(contentsService.requestFileInfo(userEmail, fileName).getFileLocation())
							.toString();
							
		log.info("--------------"+filePath);
		
		
		
		switch (requestType) {
		
			case "streaming":
				return streaming(filePath,fileName);
			
			case "download":
				download();
				break;
				
			
		}
			
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	private void download() {
		// TODO Auto-generated method stub
		
	}


	private ResponseEntity<InputStreamResource> streaming(String filepath,String fileName){
		
		log.info("download : "+filepath);
		File file=new File(filepath);
		
		HttpHeaders headers=new HttpHeaders();
		headers.add("Content-dispositon", "attachment; filename="+fileName); 
		
		
		try {
			return ResponseEntity.ok().headers(headers).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(new InputStreamResource(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	

}
