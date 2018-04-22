package org.prj.arachne.domain.fileinfo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.prj.arachne.domain.fileinfo.valueObj.FileInfoId;
import org.prj.arachne.domain.fileinfo.valueObj.FileType;
import org.prj.arachne.domain.fileinfo.valueObj.OwnerType;
import org.prj.arachne.domain.fileinfo.valueObj.SaveStatus;
import org.prj.arachne.domain.member.MemberAccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
		name="FileInfo",
		uniqueConstraints={
			@UniqueConstraint(
				columnNames={"file_owner","fileNickName"}
			)
		}
	)
public class FileInfo {

	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private FileInfoId fileSerialInfo;
	
	

	
	@Column(unique=true)
	private String fileLocation;
		
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Seoul")
	private Date regDate;
	
	@Enumerated(EnumType.STRING)
	private FileType fileType;
	
	@Enumerated(EnumType.STRING)
	private SaveStatus saveStatus;
	
	@Enumerated
	private OwnerType ownerType;
	
	
	public FileInfo excludedSecurityInfo() {
		
		this.setFileLocation(null);
		this.fileSerialInfo.setMAccount(null);
		
		return this;
	}
	
	
	
	
}
