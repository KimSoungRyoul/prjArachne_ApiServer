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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import lombok.extern.log4j.Log4j;

@Api(value="회원정보 조회Api",description="회원 정보 관련 Api")
@RestController
@Log4j
public class MemberApiController implements Version1ApiMapping{
	
	@Autowired
	private MemberInfoService mInfoService;
	
	
	
	

	 @ApiOperation(value = "회원 정보 조회",response=MemberInfo.class,produces="application/json")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "memberSerialNum", value = "회원고유번호", required = true, paramType="path", dataType = "Long", defaultValue = ""),        
	    		@ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
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
	
	 @ApiOperation(value = "회원 가입",response=StatusEntity.class,produces="application/json")
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
	
	 @ApiOperation(value="회원정보 수정",response=StatusEntity.class,produces="application/json")
	 @ApiImplicitParams({
		 @ApiImplicitParam(name="memberSerialNum",required=true,dataType="Long",paramType="path",value="회원 고유 식별 번호 입니다,이메일 아니에요 ,\r\n 인증서버에서 로그인시 넘어오는 시리얼 번호입니다"),
		 @ApiImplicitParam(name="infoType",
		 						value="accountInfo,memberInfo 두가지 값이 존재합니다 다른 타입은 현재 없습니다",
		 						required=true,dataType="String",paramType="query",example="accountInfo"),
		 @ApiImplicitParam(name="memberDto",dataType="MemberDTO",
		 						value="body로 들어오는수정되야하는 회원 정보입니다 \r\n 넘길때 변경되지 않는내용도 같이 넘겨주셔야합니다 \r\n 주의: Gender는 MAN WOMAN만 있고 ptype은 SLIM ,FAT만 존재합니다",
		 						 example="memberDto"
				 ),
			 @ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
		 
	 }
	)			 
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
