package org.prj.arachne.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentController {

	
	@GetMapping("/test/mail")
	public String mailHtml() {
		
		
		return "simpleform";
		
	}


	
}
