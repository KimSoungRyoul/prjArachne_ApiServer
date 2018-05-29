package org.prj.arachne.application;


import java.util.List;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.domain.fashion.FashionTag;
import org.prj.arachne.domain.fashion.FashionTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Log4j
public class MongoTest {

  @Autowired
  private FashionTagRepository fashionTagRepository;


  @Test
  public void testRepositoryIsWork(){

    List<FashionTag> fashionTags=fashionTagRepository.findAll();

    log.info(fashionTags.toString());

  }

}
