package org.prj.arachne.util.mail.dto;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class MailDTO {

	private String title;

	private String contents;

	@Setter(value = AccessLevel.PRIVATE)
	private String from = "KimSoungRyoul@gmail.com";

	private String to;

	// private String filePath;

	private List<File> files;

	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {

		File conFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(conFile);
		return conFile;

	}

}
