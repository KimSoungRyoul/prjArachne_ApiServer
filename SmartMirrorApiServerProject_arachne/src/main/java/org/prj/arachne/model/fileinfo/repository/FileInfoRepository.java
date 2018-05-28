package org.prj.arachne.model.fileinfo.repository;

import java.util.Set;

import org.prj.arachne.model.fileinfo.FileInfo;
import org.prj.arachne.model.fileinfo.valueObj.FileInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
	
	public Set<FileInfo> findByFileSerialInfoMAccountEmail(String email);
	
	public FileInfo findByFileSerialInfo(FileInfoId infoId);
	
	//public FileInfo findByMAccountEmailAndFileNickName(String email,String fileNickName);

}
