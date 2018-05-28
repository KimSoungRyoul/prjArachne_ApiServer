package org.prj.arachne.presentation.dto.signup;


import org.prj.arachne.model.member.valueObj.Gender;
import org.prj.arachne.model.member.valueObj.PhysicalType;

import com.google.gson.Gson;

public class SignUpJsonCreater {

	public static void main(String[] args) {
		
		
		
		MemberDTO dto=new MemberDTO();
		dto.setUserEmail("test@naver.com");
		dto.setPassword("a");
		
		
		MInfo mmInfo=new MInfo();
		mmInfo.setName("권성렬");
		mmInfo.setPhoneNum("010-1234-5678");
		mmInfo.setGender(Gender.MAN.toString());
		
				
		PhyInfo pphyInfo=new PhyInfo();
		pphyInfo.setHeight(181);
		pphyInfo.setWeight(555);
		pphyInfo.setPType(PhysicalType.SLIM.toString());
		
		
		dto.setMemberInfo(mmInfo);
		dto.setPhyInfo(pphyInfo);
		
		
		
		String jsonStr=new Gson().toJson(dto);

		System.out.println(jsonStr);
		
		
		
		System.out.println(new Gson().fromJson(jsonStr, MemberDTO.class).toString());
		
		
		
		
		
		
		
		
	}
	
}
