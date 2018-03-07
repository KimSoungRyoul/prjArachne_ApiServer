package org.prj.arachne.domain.board.valueObj;

import lombok.Data;

@Data
public class SearchCriteria {

	
	private String searchWord;
	private int maxBoardCnt;
	private int pageNum;
	
	
}
