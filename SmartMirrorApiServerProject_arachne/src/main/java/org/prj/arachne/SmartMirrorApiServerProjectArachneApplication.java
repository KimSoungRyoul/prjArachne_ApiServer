package org.prj.arachne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@SpringBootApplication
public class SmartMirrorApiServerProjectArachneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMirrorApiServerProjectArachneApplication.class, args);
	}
	
	
}
