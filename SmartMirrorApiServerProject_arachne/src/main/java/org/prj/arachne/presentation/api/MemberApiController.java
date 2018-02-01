package org.prj.arachne.presentation.api;

import java.util.HashMap;
import java.util.Map;

import org.prj.arachne.application.MemberInfoService;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.MemberInfo;
import org.prj.arachne.domain.member.valueObj.Gender;
import org.prj.arachne.domain.member.valueObj.Password;
import org.prj.arachne.domain.member.valueObj.PhysicalInfo;
import org.prj.arachne.domain.member.valueObj.PhysicalType;
import org.prj.arachne.presentation.api.urlmapper.Version1ApiMapping;
import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.prj.arachne.presentation.dto.signup.MemberSignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class MemberApiController implements Version1ApiMapping{
	
	@Autowired
	private MemberInfoService mInfoService;
	
	@GetMapping("/members/{memberSerialNum}")
	public ResponseEntity<Map<String, Object>> GETMemberInfo(@PathVariable("memberSerialNum")Long memberSerialNum) {
		
		
		
		ResponseEntity<Map<String, Object>> entity=null;
		
		
		
		MemberInfo mInfo= mInfoService.requestMemberInfo(memberSerialNum);
				
		
		Map<String, Object> values=new HashMap<>();
		values.put("entity", mInfo);
		values.put("status", new StatusEntity("MemberInfo Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));
				
		entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.OK);
		
		return entity;
	}
	
	@PostMapping("/members")
	public ResponseEntity<Map<String, Object>> signUpMember(@RequestBody MemberSignUpDTO mDto ){
				
		ResponseEntity<Map<String, Object>> entity=null;
		
		log.info(mDto.toString());
		
		MemberAccount newMember=new MemberAccount(null, mDto.getUserEmail(),
				null, 
				new Password(mDto.getPassword()),
				new MemberInfo(null, null, 
						mDto.getMInfo().getName(),
						mDto.getMInfo().getPhoneNum(),
						Gender.valueOf(mDto.getMInfo().getGender()), 
						new PhysicalInfo(mDto.getPhyInfo().getHeight(),
										 mDto.getPhyInfo().getWeight(),
										 PhysicalType.valueOf(mDto.getPhyInfo().getPType())
										 )
						),
				null, true);
		
		
		
		mInfoService.signUpMember(newMember);
				
		
		Map<String, Object> values=new HashMap<>();
		
		values.put("status", new StatusEntity("MemberInfo Api", ArachneStatus.CREATED, "회원가입이 정상적으로 처리되었습니다"));
		
		
		
		entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.CREATED);
		
		return entity;			
	}
	
}
