package org.prj.arachne.util.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class FileUploadUtil {

	
	@Autowired
	@Qualifier("uploadFilePath")
	@Getter
	private String uploadPath;
	
	
	
	public String uploadFile(String originalName,String fileOwnerEmail ,byte[] fileData)  {

		UUID uid = UUID.randomUUID();

		try {
			originalName = new String(originalName.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.info("지원되지 않는 인코딩 형식입니다");
			e.printStackTrace();
		}

		String savedName = uid.toString() + "_" + originalName;

		String savedPath = calcPath(fileOwnerEmail);

		File target = new File(uploadPath + savedPath, savedName);

		try {
			FileCopyUtils.copy(fileData, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			log.info("파일 데이터  카피에 실패했습니다......");
			e.printStackTrace();
		}

		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);

		String uploadedFileName = null;

		if (MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}

		return uploadedFileName;

	}
	
	
	
	
	private String makeIcon(String uploadPath, String path, String fileName)  {

		String iconName = uploadPath + path + File.separator + fileName;

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	
	private String calcPath(String userEmail) {

		Calendar cal = Calendar.getInstance();

		String userPath=File.separator+userEmail;
		
		String yearPath =userPath+ File.separator + cal.get(Calendar.YEAR);

		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		
		
		makeDir(uploadPath,userPath ,yearPath, monthPath);

		log.info("파일 저장 경로 : "+uploadPath+monthPath);

		return monthPath;

	}
	
	
	
	
	private void makeDir(String uploadPath, String... paths) {

		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		for (String path : paths) {

			File dirPath = new File(uploadPath + path);

			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	
	
	private String makeThumbnail(String uploadPath, String path, String fileName) {
	
		String thumbnailName =null;
		
		try {
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 200);

		thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		
			ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("썸네일 생성에 실패했습니다 ........");
			e.printStackTrace();
		}
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

}
