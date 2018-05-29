package org.prj.arachne.domain.member;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.prj.arachne.domain.member.valueObj.Gender;
import org.prj.arachne.domain.member.valueObj.PhysicalInfo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfo implements Serializable{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -6937016640932842364L;


	@Id
	@GeneratedValue
	private Long mInfoId;
	
	
	@OneToOne
	@JoinColumn(name="info_owner")
	@JsonBackReference
	private MemberAccount infoOwner;
	
	
	private String name;
	
	private String phoneNum;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	
	
	@Embedded
	private PhysicalInfo physicalInfo;
	
	
}
