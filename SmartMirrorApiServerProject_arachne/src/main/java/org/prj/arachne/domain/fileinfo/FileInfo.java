package org.prj.arachne.domain.fileinfo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.prj.arachne.domain.fileinfo.valueObj.FileType;
import org.prj.arachne.domain.fileinfo.valueObj.SaveStatus;
import org.prj.arachne.domain.member.MemberAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

	@Id
	@GeneratedValue
	private Long id;
		
	@ManyToOne
	@JoinColumn(name="file_owner")
	private MemberAccount mAccount;
	
	private String fileLocation;
	
	@Column(unique=true)
	private String fileName;	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	
	@Enumerated(EnumType.STRING)
	private FileType fileType;
	
	@Enumerated(EnumType.STRING)
	private SaveStatus saveStatus;
	
	
	
	
}
