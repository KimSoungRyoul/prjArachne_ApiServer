package org.prj.arachne.configuration;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JsonConfiguration {
	
	@Bean
	public RestTemplate restTemplate(MappingJackson2HttpMessageConverter converter) {
		
		RestTemplate restTemplate=new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(converter));
		
		return restTemplate;
		
	}
	
	
	
	@Bean
	   public MappingJackson2HttpMessageConverter jacksonMessageConverter(ObjectMapper mapper) {
	      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	      converter.setObjectMapper(mapper);
	      List<MediaType> mediaTypeList = new ArrayList<>();
	      Charset utf8 = Charset.forName("UTF-8");
	      mediaTypeList.add(new MediaType("application", "json", utf8));
	      mediaTypeList.add(new MediaType("text", "html", utf8));
//	      mediaTypeList.add(new MediaType("text", "plain", utf8));
	      converter.setSupportedMediaTypes(mediaTypeList);
	      return converter;
	   }

}
