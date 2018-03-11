package org.prj.arachne.util.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.prj.arachne.util.mail.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class MailSenderUtil {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("uploadFilePath")
	private String uploadPath;
	
	public void sendSimpleMail(MailDTO dto) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		
		mailMessage.setFrom(dto.getFrom());
		mailMessage.setTo(dto.getTo());
		mailMessage.setSubject(dto.getTitle());
		mailMessage.setText(dto.getContents());

		mailSender.send(mailMessage);

	}

	public void sendMimeMail(MailDTO dto) {
		MimeMessage mailMessage = mailSender.createMimeMessage();

		
		
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

			helper.setFrom(dto.getFrom());
			helper.setTo(dto.getTo());
			helper.setSubject(dto.getTitle());
			//helper.setText(dto.getContents());
			
			
			
			
			List<File> files = dto.getFiles();

			for (File file : files) {

				helper.addAttachment(file.getName(), new FileSystemResource(file));
			}

			mailMessage.setContent(dto.getContents(), "text/html;charset=utf-8");
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		mailSender.send(mailMessage);

	}
	
	
	@Async("threadPoolTaskExecutor")
	public void sendWithHTML(MailDTO dto){
		
		log.info("비 동기적 요청을 진행합니다 ... 메일 서비스 진행중 ......... ");

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
		
		
		
		this.sendMimeMail(dto);
		
		log.info("비 동기적 요청을 종료합니다 .. 메일 서비스 완료되었습니다............ ");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
