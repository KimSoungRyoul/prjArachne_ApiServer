package org.prj.arachne.domain.calender;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.prj.arachne.domain.member.MemberAccount;

import lombok.Data;

@Entity
@Data
public class Schedule {

	@Id
	@GeneratedValue
	private Long id;
	private Date regDate;
	
	@ManyToOne
	@JoinColumn(name="schj_owner_id")
	private MemberAccount schjOwner;
	
	private String title;
	private String contents;
	private Date schjDate;
	
	
}
