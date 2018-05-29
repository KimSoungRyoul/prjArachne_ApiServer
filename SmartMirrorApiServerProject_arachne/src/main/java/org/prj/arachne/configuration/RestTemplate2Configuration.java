package org.prj.arachne.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplate2Configuration {

	@Qualifier("weather")
	@Bean
	public RestTemplate restTemplate() {
		
		RestTemplate restTemplate=new RestTemplate();
		
		
		
		return restTemplate;
	}

	@Qualifier("fcm")
	@Bean
	public RestTemplate restTemplateForFCM() {

		RestTemplate restTemplate=new RestTemplate();


		return restTemplate;
	}
	
	
	
}
