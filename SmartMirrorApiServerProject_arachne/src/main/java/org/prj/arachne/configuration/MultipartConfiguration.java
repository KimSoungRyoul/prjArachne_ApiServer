package org.prj.arachne.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class MultipartConfiguration {

	@Bean("uploadPath")
	@Profile("dev")
	public String uploadPath() {
		return "D:\\personalProject\\SummerTeamProject2017";
	}

	@Bean("uploadPath")
	@Profile("product")
	public String uploadPath2() {
		return "/home/ubuntu/arachneFileStorage";
	}

	@Bean
	public MultipartResolver multpartResolver() {

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

		multipartResolver.setMaxUploadSize(10000000);
		multipartResolver.setMaxInMemorySize(10000000);

		return multipartResolver;
	}
}
