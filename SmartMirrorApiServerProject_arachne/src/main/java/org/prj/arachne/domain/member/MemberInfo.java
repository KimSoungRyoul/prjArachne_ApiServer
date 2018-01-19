package org.prj.arachne.domain.member;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.prj.arachne.domain.member.valueObj.Gender;
import org.prj.arachne.domain.member.valueObj.PhysicalInfo;

import lombok.Data;

@Entity
@Data
public class MemberInfo implements Serializable{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -6937016640932842364L;


	@Id
	@GeneratedValue
	private Long mInfoId;
	
	
	private String name;
	
	private String phoneNum;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	@Embedded
	private PhysicalInfo physicalInfo;
	
	
}
