package org.prj.arachne.util.tistory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.domain.tistory.TistoryBoard;
import org.prj.arachne.domain.tistory.TistoryCategory;
import org.prj.arachne.domain.tistory.repository.TistoryBoardRepository;
import org.prj.arachne.util.TistoryParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("product")
public class TistoryParsingApplication {

	@Autowired
	private TistoryBoardRepository tistoryBoardRepository;

	@Autowired
	private TistoryParserUtil tistoryParserUtil;



	@Test
	public void AontextLoads() {

		List<TistoryBoard> tistory1Boards= tistoryParserUtil.parsingPostList(TistoryCategory.DEVDIARY);
		List<TistoryBoard> tistory2Boards= tistoryParserUtil.parsingPostList(TistoryCategory.SPRING_JAVA);
		List<TistoryBoard> tistory3Boards= tistoryParserUtil.parsingPostList(TistoryCategory.OS_CPROGRAMMING);
		List<TistoryBoard> tistory4Boards= tistoryParserUtil.parsingPostList(TistoryCategory.BUSSINESSCLUB);

		tistoryBoardRepository.save(tistory1Boards);
		tistoryBoardRepository.save(tistory2Boards);
		tistoryBoardRepository.save(tistory3Boards);
		tistoryBoardRepository.save(tistory4Boards);


	}


	@Test
	public void BestData(){

		List<TistoryBoard> tistoryBoards=tistoryBoardRepository.findByCategory(TistoryCategory.BUSSINESSCLUB.toString());

		System.out.println(tistoryBoards.toString());

	}



}
