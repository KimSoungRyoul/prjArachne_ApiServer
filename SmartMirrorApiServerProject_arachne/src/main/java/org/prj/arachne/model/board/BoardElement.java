package org.prj.arachne.model.board;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.prj.arachne.model.board.valueObj.CommunityType;

import lombok.Data;

@Entity
@Data
public class BoardElement {
	
	@Id
	@Enumerated(EnumType.STRING)
	private CommunityType communityType;
		
	private int communityPostTotalCnt;
		
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date communityRegDate;
	
	
	
	
}
