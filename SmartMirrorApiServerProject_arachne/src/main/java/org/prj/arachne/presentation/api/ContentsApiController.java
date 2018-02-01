package org.prj.arachne.presentation.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.prj.arachne.application.ContentsService;
import org.prj.arachne.application.exception.ArachneNickAndUserEmialDuplicatedException;
import org.prj.arachne.domain.fileinfo.FileInfo;
import org.prj.arachne.presentation.api.urlmapper.Version1ApiMapping;
import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.prj.arachne.util.MultipartFileStreamingSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ContentsApiController implements Version1ApiMapping{

	
	
	@Autowired
	private String filePathDefault;

	@Autowired
	private ContentsService contentsService;

	
	@GetMapping("/contents/contents/{userEmail}/{fileName}")
	public ResponseEntity<InputStreamResource> requireContents(@RequestParam("requesttype") String requestType,
			@PathVariable("userEmail") String userEmail, @PathVariable("fileName") String fileName) {

		String filePath = new StringBuilder()
							.append(filePathDefault)
							.append(contentsService.requestFileInfo(userEmail, fileName)
									.getFileLocation())
							.toString();

		log.info("--------------" + filePath);

		switch (requestType) {

			case "streaming":
				return streaming(filePath, fileName);
	
			case "download":
				return download(filePath, fileName);

		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	
	@PostMapping("/contents/{userEmail}/{fileNickName}")
	public ResponseEntity<Map<String, Object>> uploadContents(@PathVariable("userEmail")String userEmail,
															  @PathVariable("fileNickName")String fileNickName,
															 @RequestBody @RequestParam("file")MultipartFile file) throws IOException, ArachneNickAndUserEmialDuplicatedException{
		
		ResponseEntity<Map<String, Object>> entity=null;
		
				
		log.info("--------------Registered Data-------------------");
		log.info("userEmail : "+userEmail);
		log.info("fileNickName : "+fileNickName);
		log.info("file contentType : "+ file.getContentType());
		log.info("file size : "+ file.getSize());
		log.info("file Name : "+ file.getName());
		log.info("file originalName : "+ file.getOriginalFilename());
		log.info("-----------------------------------------------");
				
		
		FileInfo fileInfo= contentsService.registerContents(fileNickName,userEmail,file.getOriginalFilename(),file.getBytes());
				
		
		Map<String, Object> values=new HashMap<>();
		
		
		values.put("entity", fileInfo.excludedSecurityInfo());
		values.put("status", new StatusEntity("Contents Api", ArachneStatus.CREATED, "컨텐츠 전송(저장)에  성공했습니다 "));
				
		entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.CREATED);
				
		return entity; 
	}
	
	
	

	
	@DeleteMapping("/contents/{userEmail}/{fileNickName}")
	public ResponseEntity<Map<String, Object>> removeContents(@PathVariable("userEmail")String userEmail,
															  @PathVariable("fileNickName")String fileNickName){
		
		
		ResponseEntity<Map<String, Object>> entity=null;
		
		
		contentsService.deleteContents(userEmail, fileNickName);
		
		
		Map<String, Object> values=new HashMap<>();
		
		values.put("status", new StatusEntity("Contents Api", ArachneStatus.DELETED, "컨텐츠 삭제에  성공했습니다 "));
		
		
		entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.OK);
		
		
		return entity;
		
	}
	
	
	
	
	
	
	@RequestMapping(value = "/contents/video/{fileName}", method = RequestMethod.GET)
	  public void getVideo(HttpServletRequest req, HttpServletResponse res, @PathVariable("fileName") String fileName) {
	    
	    
	    // 데이터 조회
	   // FileModel fileModel = fileService.getFileInfo(Integer.parseInt(id));
	    
	    	    
	    log.info("동영상 스트리밍 요청 : "  + fileName);
	    
	    File getFile = new File("C:\\ArachneProject\\testMedia\\" +fileName+".mkv");
	    
	    try {
	      // 미디어 처리
	      MultipartFileStreamingSender
	        .fromFile(getFile)
	        .with(req)
	        .with(res)
	        .serveResource();
	      
	    } catch (Exception e) {
	      // 사용자 취소 Exception 은 콘솔 출력 제외
	      if (!e.getClass().getName().equals("org.apache.catalina.connector.ClientAbortException")) e.printStackTrace();
	    }
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	private ResponseEntity<InputStreamResource> download(String filepath, String fileName) {
		// TODO Auto-generated method stub
		log.info("download : " + filepath);
		File file = new File(filepath);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-dispositon", "attachment; filename=" + fileName);

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

	private ResponseEntity<InputStreamResource> streaming(String filepath, String fileName) {

		log.info("streaming : " + filepath);
		File file = new File(filepath);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-dispositon", "attachment; filename=" + fileName);

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
