package org.prj.arachne.domain.fileinfo.repository;

import java.util.Set;

import org.prj.arachne.domain.fileinfo.FileInfo;
import org.prj.arachne.domain.fileinfo.FileInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, FileInfoId> {
	
	public Set<FileInfo> findByIdMAccountEmail(String email);
	
	//public FileInfo findByMAccountEmailAndFileNickName(String email,String fileNickName);

}
