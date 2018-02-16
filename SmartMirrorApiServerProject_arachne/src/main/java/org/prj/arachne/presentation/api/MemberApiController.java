package org.prj.arachne.presentation.api;

import java.util.Date;
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
import org.prj.arachne.presentation.dto.signup.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class MemberApiController implements Version1ApiMapping{
	
	@Autowired
	private MemberInfoService mInfoService;
	
	 @ApiOperation(value = "회원 정보 조회")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "memberSerialNum", value = "회원고유번호", required = true, dataType = "Long", paramType = "query", defaultValue = ""),        
	    })
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
	
	 @ApiOperation(value = "회원 가입")
	    @ApiImplicitParams({
	           
	    })
	@PostMapping("/members")
	public ResponseEntity<Map<String, Object>> signUpMember(
			@ApiParam(name = "회원가입 정보", 
						value = "회원 가입에 필요한 데이터들\n id,password 를 포함한 개인정보들", required = true)
			@RequestBody MemberDTO mDto ){
				
		ResponseEntity<Map<String, Object>> entity=null;
		
		log.info(mDto.toString());
		
		MemberAccount newMember=new MemberAccount(null, mDto.getUserEmail(),
				new Date(), 
				new Password(mDto.getPassword()),
				new MemberInfo(null, null, 
						mDto.getMemberInfo().getName(),
						mDto.getMemberInfo().getPhoneNum(),
						Gender.valueOf(mDto.getMemberInfo().getGender()), 
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
	
	@PutMapping("/members/{memberSerialNum}")
	public ResponseEntity<Map<String, Object>> modifiedMemberInfo(@PathVariable("memberSerialNum") Long memberId,
																  @RequestParam("infoType")String infoType,
																  @RequestBody MemberDTO memberDto){

		
		ResponseEntity<Map<String, Object>> entity=null;
		
		log.info(memberDto.toString());
		
		switch (infoType) {
		case "accountInfo":
			
			mInfoService.changedAccountInfo(memberId,memberDto.getUserEmail(),memberDto.getPassword());			
			
			break;
			
			
		case "memberInfo":
			
			MemberInfo changedInfo=new MemberInfo(null, new MemberAccount(memberId), memberDto.getMemberInfo().getName(),
					memberDto.getMemberInfo().getPhoneNum(),
					Gender.valueOf(memberDto.getMemberInfo().getGender()),
					new PhysicalInfo(memberDto.getPhyInfo().getHeight(), 
									 memberDto.getPhyInfo().getWeight(),
									 PhysicalType.valueOf(memberDto.getPhyInfo().getPType())
									 )
					);
						
			mInfoService.modifiedMInfo(changedInfo);
			
			break;
			
		default:
			
			Map<String, Object> values=new HashMap<>();
			
			values.put("status", new StatusEntity("MemberInfo Api", ArachneStatus.BADIO, "infoType은  memberInfo 또는 accountInfo만 가질수있습니다"));
			
			
			
			entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.BAD_REQUEST);
			
			
			break;
		}
		
		
		
		
		
		
	
				
		
		Map<String, Object> values=new HashMap<>();
		
		values.put("status", new StatusEntity("MemberInfo Api", ArachneStatus.CREATED, "정상적으로  회원정보가 수정되었습니다. "));
		
		
		
		entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.CREATED);
		
		return entity;
		
	}
	
	
	
}
