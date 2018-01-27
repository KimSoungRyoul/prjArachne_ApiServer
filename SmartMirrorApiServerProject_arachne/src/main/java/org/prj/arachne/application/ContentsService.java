package org.prj.arachne.application;

import org.prj.arachne.domain.fileinfo.FileInfo;
import org.prj.arachne.domain.fileinfo.repository.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentsService {

	@Autowired
	private FileInfoRepository fileInfoRepo;
	
	@Transactional
	public FileInfo requestFileInfo(String email,String fileName) {
		
		
		FileInfo info= fileInfoRepo.findByMAccountEmailAndFileName(email, fileName);
		
		return info;
		
	}
	
	
	
	@Transactional
	public void registerContents(FileInfo fileInfo) {
		
		
		
	}
}
