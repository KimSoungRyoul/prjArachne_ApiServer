package org.prj.arachne.util.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.util.mail.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class MailSendTest {

	
	@Autowired
	MailSenderUtil mailSenderUtil;
	
	
	@Test
	public void testSendMail(){
		
		MailDTO dto=new MailDTO();
		dto.setTitle("메일 전송 테스트 제목");
		dto.setContents("--------------------------!!!!!!!!!!!!!!!!!!!! 메일 간다_______");
		dto.setTo("KimSoungRyoul@gmail.com");
		
		mailSenderUtil.sendSimpleMail(dto);
		
		
	}
	
	
	@Test
	public void testSendWithAttachedFile(){
		
		MailDTO dto=new MailDTO();
		dto.setTitle("메일 전송 테스트 제목 첨부파일 포함");
		dto.setContents("--------------------------!!!!!!!!!!!!!!!!!!!! 메일 간다_______");
		dto.setTo("KimSoungRyoul@gmail.com");
		
		List<File> files=new ArrayList<>();
		files.add(new File("/home/kimsoungryoul/문서/testfile/test1.doc"));
	
		
		dto.setFiles(files);
		
		
		
		mailSenderUtil.sendMimeMail(dto);
		
	}
	
}
