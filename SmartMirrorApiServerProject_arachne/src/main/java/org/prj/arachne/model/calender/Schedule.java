package org.prj.arachne.model.calender;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.prj.arachne.model.board.valueObj.SchjInfo;
import org.prj.arachne.model.member.MemberAccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Entity
@Data
public class Schedule {

	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Seoul")
	private Date regDate;
	
	@ManyToOne
	@JoinColumn(name="schj_owner_id")
	private MemberAccount schjOwner;
	
	@Embedded
	private SchjInfo sjInfo;
	
	
}
