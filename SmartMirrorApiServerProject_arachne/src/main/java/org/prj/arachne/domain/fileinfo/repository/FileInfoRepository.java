package org.prj.arachne.domain.fileinfo.repository;

import java.util.Set;

import org.prj.arachne.domain.fileinfo.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
	
	public Set<FileInfo> findByMAccountEmail(String email);

}
