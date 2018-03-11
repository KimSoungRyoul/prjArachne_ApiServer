package org.prj.arachne.configuration;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartConfiguration {

	
	private final static Long MAX_REQUEST_SIZE=1024*1024*40L; //40MB
	private final static Long MAX_UPLOAD_FILE_SIZE=1024*1024*100L;//100MB
	private final static int FILE_SIZE_THREADHOLD=1024*1024*512;
	
	@Bean("uploadPath")
	@Qualifier("uploadFilePath")
	@Profile("dev")
	public String uploadPath() {
		return "/home/kimsoungryoul/문서/testfile";
	}

	@Bean("uploadPath")
	@Qualifier("uploadFilePath")
	@Profile("product")
	public String uploadPath2() {
		return "/home/ubuntu/arachneFileStorage";
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();

	    factory.setMaxFileSize(MAX_UPLOAD_FILE_SIZE);
	    factory.setMaxRequestSize(MAX_REQUEST_SIZE);
	    factory.setFileSizeThreshold(FILE_SIZE_THREADHOLD);
	    

	    return factory.createMultipartConfig();
	}

	
	@Bean
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}

	
	
	
	
	
	
	
	
	
	//Servlet 3.0 이하  버젼 호환 
	// 관련 내용 http://java.ihoney.pe.kr/351 참조 
	/*@Bean
	public MultipartResolver multpartResolver() {

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

		multipartResolver.setMaxUploadSize(1000000000);
		multipartResolver.setMaxInMemorySize(1000000000);
	

		return multipartResolver;
	}*/
}
