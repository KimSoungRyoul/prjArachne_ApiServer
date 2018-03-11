package org.prj.arachne.util.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.util.mail.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class MailSendTest {

	
	@Autowired
	MailSenderUtil mailSenderUtil;
	
	@Autowired
	@Qualifier("uploadFilePath")
	private String uploadPath;
	
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
		
		/*File htmlFile=new File(uploadPath+"/index.html");
		
		StringBuilder sb=new StringBuilder();
		 BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(htmlFile));
		
	      String s;

	     
			while ((s = in.readLine()) != null) {
			   sb.append(s);
			  }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/



		Resource resource = new ClassPathResource("/templates/simpleform.html");


		String mailHtml="";

		try {

			byte[] encoded = Files.readAllBytes(Paths.get(resource.getURI()));
			mailHtml=new String(encoded, "UTF-8");



			//System.out.println(s);
		}catch (Exception e){
			e.printStackTrace();
		}





		dto.setContents(mailHtml);
		//dto.setContents("<h1>html메일 되나?</h1>");
		dto.setTo("KimSoungRyoul@gmail.com");
		
		List<File> files=new ArrayList<>();
		files.add(new File("/home/kimsoungryoul/문서/testfile/test1.doc"));
	
		
		dto.setFiles(files);
		
		
		
		mailSenderUtil.sendMimeMail(dto);
		
	}

}
