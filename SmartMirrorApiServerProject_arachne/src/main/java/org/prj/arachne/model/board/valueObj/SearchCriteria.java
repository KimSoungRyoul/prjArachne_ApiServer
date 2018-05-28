package org.prj.arachne.model.board.valueObj;

import lombok.Data;

@Data
public class SearchCriteria {

	
	private String searchWord;
	private int maxBoardCnt;
	private int pageNum;
	
	
}
