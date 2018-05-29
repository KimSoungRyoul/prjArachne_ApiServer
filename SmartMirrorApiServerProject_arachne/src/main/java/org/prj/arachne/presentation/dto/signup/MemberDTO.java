package org.prj.arachne.presentation.dto.signup;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="회원 정보")
public class MemberDTO {

	@ApiModelProperty(dataType="String",example="rlatjduf510@naver.com")
	private String userEmail;
	
	@ApiModelProperty(dataType="String",example="1234")
	private String password;

	@ApiModelProperty(dataType = "String", example = "dBbekBDi8Q0:APA91bFK6TDHICKwJ2IUGBUeKHUoUewoGeqX-prgP1NesOoAER8tkQlzXYwZT4ZWwqdgB4no63TymV_br1UC0PwTgui9T3dyA2RaoiKreQze0bLpNz3sTfxMrvQgfQuV4VtO20O8GtDE")
	private String fcmToken;
	

	@ApiModelProperty(dataType="PhyInfo",value="phyInfo")
	private PhyInfo phyInfo;
	

	@ApiModelProperty(dataType="MInfo",value="memberInfo")
	private MInfo memberInfo;
	
	
	


	
	
}


