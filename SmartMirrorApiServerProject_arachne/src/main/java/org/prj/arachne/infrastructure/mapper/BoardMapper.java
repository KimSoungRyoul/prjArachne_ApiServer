package org.prj.arachne.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.prj.arachne.domain.board.Board;
import org.prj.arachne.domain.board.valueObj.SearchCriteria;

@Mapper
public interface BoardMapper {

	public void insert(Board board);
	
	public List<Board> selectList(SearchCriteria cri);
	
	
}
