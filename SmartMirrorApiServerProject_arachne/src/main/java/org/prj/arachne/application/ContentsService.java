package org.prj.arachne.application;

import java.io.IOException;
import java.util.Date;

import org.prj.arachne.domain.fileinfo.FileInfo;
import org.prj.arachne.domain.fileinfo.FileInfoId;
import org.prj.arachne.domain.fileinfo.repository.FileInfoRepository;
import org.prj.arachne.domain.fileinfo.valueObj.FileType;
import org.prj.arachne.domain.fileinfo.valueObj.SaveStatus;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ContentsService {

	@Autowired
	private FileInfoRepository fileInfoRepo;

	@Autowired
	private FileUploadUtil fileUtil;

	@Transactional
	public FileInfo requestFileInfo(String email, String fileNickName) {

		FileInfo info = fileInfoRepo.findOne(new FileInfoId(new MemberAccount(email), fileNickName));

		return info;

	}

	//@Transactional
	public FileInfo registerContents(String fileNickName, String userEmail, String originalFileName, byte[] fileBytes) {

		String fileLocation = fileUtil.uploadFile(originalFileName, userEmail, fileBytes);

		log.info("파일 저장 fileLocation : "+ fileLocation);
		
		FileInfoId fId=new FileInfoId(new MemberAccount(userEmail),fileNickName);
		
		FileInfo fileInfo = new FileInfo(fId, fileLocation, new Date(),
				FileType.valueOf(originalFileName
								.substring(originalFileName.indexOf(".")+1,
											originalFileName.length())
								.toUpperCase()
								),
				SaveStatus.HAS_OWNER);
		fileInfoRepo.save(fileInfo);
		
		log.info("---------------컨텐츠  저장------------------------");
		log.info(" registered FileInfo : " + fileInfo.getId().getFileNickName());
		log.info(" registered FileInfo : " + fileInfo.getFileLocation());
		log.info("-------------------------------------------------------");

		/*fileInfo.getId().setMAccount(new MemberAccount(userEmail));
		fileInfoRepo.save(fileInfo);
		*/
		
		fileInfo= fileInfoRepo.findOne(fId);
		fileInfo.getId().setMAccount(new MemberAccount(userEmail));
		//fileInfo.setFileLocation(null);
		
		
		return fileInfo;
	}
}
