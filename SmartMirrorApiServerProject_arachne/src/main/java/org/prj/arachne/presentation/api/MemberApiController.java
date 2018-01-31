package org.prj.arachne.presentation.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/members")
public class MemberApiController implements DefaultApiMapping{
	
	
	@GetMapping
	public String asdf() {
		
		
		return "asdf";
	}
	

}
