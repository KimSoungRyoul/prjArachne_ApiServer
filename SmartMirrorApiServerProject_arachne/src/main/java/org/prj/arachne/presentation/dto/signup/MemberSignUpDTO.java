package org.prj.arachne.presentation.dto.signup;


import lombok.Data;

@Data
public class MemberSignUpDTO {

	
	private String userEmail;
	private String password;
	private MInfo mInfo=new MInfo();
	private PhyInfo phyInfo=new PhyInfo();
	
	
	
	


	
	
}


