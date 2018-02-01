package org.prj.arachne.presentation.dto;


import org.prj.arachne.domain.member.valueObj.Gender;
import org.prj.arachne.domain.member.valueObj.PhysicalType;
import org.prj.arachne.presentation.dto.signup.MemberSignUpDTO;

import com.google.gson.Gson;

public class SignUpJsonCreater {

	public static void main(String[] args) {
		
		
		
		MemberSignUpDTO dto=new MemberSignUpDTO();
		dto.setUserEmail("test@naver.com");
		dto.setPassword("abcd");
		
		
		dto.getMInfo().setName("권원");
		dto.getMInfo().setPhoneNum("010-7237-6602");
		dto.getMInfo().setGender(Gender.WOMAN.toString());
		
		
		dto.getPhyInfo().setHeight(170);
		dto.getPhyInfo().setWeight(58);
		dto.getPhyInfo().setPType(PhysicalType.SLIM.toString());
		
		
		String jsonStr=new Gson().toJson(dto);

		System.out.println(jsonStr);
		
		
		
		System.out.println(new Gson().fromJson(jsonStr, MemberSignUpDTO.class));
		
		
		
		
		
		
		
		
	}
	
}
