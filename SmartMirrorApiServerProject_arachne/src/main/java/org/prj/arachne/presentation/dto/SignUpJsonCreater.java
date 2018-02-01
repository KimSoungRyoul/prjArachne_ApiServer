package org.prj.arachne.presentation.dto;


import org.prj.arachne.domain.member.valueObj.Gender;
import org.prj.arachne.domain.member.valueObj.PhysicalType;
import org.prj.arachne.presentation.dto.signup.MInfo;
import org.prj.arachne.presentation.dto.signup.MemberSignUpDTO;
import org.prj.arachne.presentation.dto.signup.PhyInfo;

import com.google.gson.Gson;

public class SignUpJsonCreater {

	public static void main(String[] args) {
		
		
		
		MemberSignUpDTO dto=new MemberSignUpDTO();
		dto.setUserEmail("test@naver.com");
		dto.setPassword("abcd");
		
		
		MInfo mmInfo=new MInfo();
		mmInfo.setName("권원");
		mmInfo.setPhoneNum("010-7237-6602");
		mmInfo.setGender(Gender.WOMAN.toString());
		
				
		PhyInfo pphyInfo=new PhyInfo();
		pphyInfo.setHeight(170);
		pphyInfo.setWeight(58);
		pphyInfo.setPType(PhysicalType.SLIM.toString());
		
		
		dto.setMmInfo(mmInfo);
		dto.setPhyInfo(pphyInfo);
		
		
		
		String jsonStr=new Gson().toJson(dto);

		System.out.println(jsonStr);
		
		
		
		System.out.println(new Gson().fromJson(jsonStr, MemberSignUpDTO.class).toString());
		
		
		
		
		
		
		
		
	}
	
}
