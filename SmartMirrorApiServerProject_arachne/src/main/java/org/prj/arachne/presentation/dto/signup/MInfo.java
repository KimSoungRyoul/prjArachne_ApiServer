package org.prj.arachne.presentation.dto.signup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="회원 개인정보")
public class MInfo{
	

	@ApiModelProperty(dataType="String",example="김성렬")
	private String name;

	@ApiModelProperty(dataType="String",example="010-7237-6602")
	private String phoneNum;

	@ApiModelProperty(dataType="String",example="MAN")
	private String gender;
	
}