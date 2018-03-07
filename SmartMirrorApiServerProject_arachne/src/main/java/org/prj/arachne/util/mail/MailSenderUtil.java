package org.prj.arachne.util.mail;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.prj.arachne.util.mail.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSenderUtil {

	@Autowired
	JavaMailSender mailSender;

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

		File htmlMailForm=new 
		
		mailMessage.setContent(o, type);
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);

			helper.setFrom(dto.getFrom());
			helper.setTo(dto.getTo());
			helper.setSubject(dto.getTitle());
			helper.setText(dto.getContents());

			List<File> files = dto.getFiles();

			for (File file : files) {

				helper.addAttachment(file.getName(), new FileSystemResource(file));
			}

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mailSender.send(mailMessage);

	}

}
