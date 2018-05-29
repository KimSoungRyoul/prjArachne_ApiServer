package org.prj.arachne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableRedisHttpSession
public class SmartMirrorApiServerProjectArachneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMirrorApiServerProjectArachneApplication.class, args);
	}





}
