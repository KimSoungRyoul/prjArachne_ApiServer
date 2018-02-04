package org.prj.arachne.application;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.prj.arachne.application.exception.ArachneNickAndUserEmialDuplicatedException;
import org.prj.arachne.domain.fileinfo.FileInfo;
import org.prj.arachne.domain.fileinfo.repository.FileInfoRepository;
import org.prj.arachne.domain.fileinfo.valueObj.FileInfoId;
import org.prj.arachne.domain.fileinfo.valueObj.FileType;
import org.prj.arachne.domain.fileinfo.valueObj.SaveStatus;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.util.file.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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

		FileInfo info = fileInfoRepo.findByFileSerialInfo(new FileInfoId(new MemberAccount(email), fileNickName));

		return info;

	}

	@Transactional
	public FileInfo registerContents(String fileNickName, String userEmail, String originalFileName, byte[] fileBytes) throws ArachneNickAndUserEmialDuplicatedException {
		FileInfo fileInfo=null;
		String fileLocation=null;
		try {
		fileLocation = fileUtil.uploadFile(originalFileName, userEmail, fileBytes);

		log.info("파일 저장 fileLocation : " + fileLocation);

		FileInfoId fileInfoId = new FileInfoId(new MemberAccount(userEmail), fileNickName);

		 fileInfo = new FileInfo(null,fileInfoId, fileLocation, new Date(),
				FileType.valueOf(originalFileName
						.substring(originalFileName.indexOf(".") + 1, originalFileName.length()).toUpperCase()),
				SaveStatus.HAS_OWNER);
		
			
		fileInfoRepo.save(fileInfo);
		
		
		log.info("---------------컨텐츠  저장------------------------");
		log.info(" registered FileInfo : " + fileInfo.getFileSerialInfo().getFileNickName());
		log.info(" registered FileInfo : " + fileInfo.getFileLocation());
		log.info("-------------------------------------------------------");

		/*
		 * fileInfo.getId().setMAccount(new MemberAccount(userEmail));
		 * fileInfoRepo.save(fileInfo);
		 */

		fileInfo = fileInfoRepo.findByFileSerialInfo(fileInfoId);
		fileInfo.getFileSerialInfo().setMAccount(new MemberAccount(userEmail));
		// fileInfo.setFileLocation(null);
		} catch (Exception e) {
			// TODO: handle exception
			File fileThumnail=new File(fileUtil.getUploadPath()+fileLocation);
			fileThumnail.delete();
			File fileOrigin = new File(fileUtil.getUploadPath()+fileLocation.replaceFirst("s_", ""));
			fileOrigin.delete();
			
			throw new ArachneNickAndUserEmialDuplicatedException("같은 userEmail과 file의 NickName 가진 파일이 이미 존재합니다 ..");
			
		}
		return fileInfo;
	}

	public void deleteContents(String userEmil, String fileNickName) {

		FileInfo fileInfo = fileInfoRepo.findByFileSerialInfo(new FileInfoId(new MemberAccount(userEmil), fileNickName));

		String fileAbsolutePath = fileUtil.getUploadPath() + fileInfo.getFileLocation();
		File file = new File(fileAbsolutePath);

		if (file.exists()) {
			if (file.delete()) {

				// 이미지 파일이면 섬네일도 같이 삭제
				if (fileInfo.getFileType().equals(FileType.JPG) || 
						fileInfo.getFileType().equals(FileType.GIF) ||
						  fileInfo.getFileType().equals(FileType.PNG)) {

					File fileOrigin = new File(
							fileAbsolutePath.replaceFirst("s_", ""));
					fileOrigin.delete();
				}

				fileInfoRepo.delete(fileInfo);

			} else {
				log.info("알수 없는 이유로  파일 삭제에 실패하였습니다 ");
				log.info("파일의 경로 : " + file.getAbsolutePath());
			}
		} else {
			log.info("파일이 존재 하지 않지만 파일의 정보가 database에 존재합니다...");
			log.info("fileLocation : " + file.getAbsolutePath());
		}

	}

}
