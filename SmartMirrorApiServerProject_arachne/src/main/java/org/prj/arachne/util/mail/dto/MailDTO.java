package org.prj.arachne.util.mail.dto;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {

	private String title;

	private String contents;

	@Setter(value = AccessLevel.PRIVATE)
	private String from = "arachne0823@gmail.com";

	private String to;

	// private String filePath;

	private List<File> files;

	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {

		File conFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(conFile);
		return conFile;

	}

}
