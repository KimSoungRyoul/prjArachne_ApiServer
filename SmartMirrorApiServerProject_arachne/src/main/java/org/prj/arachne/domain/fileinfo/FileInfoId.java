package org.prj.arachne.domain.fileinfo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.prj.arachne.domain.member.MemberAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoId implements Serializable{

	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -2561055826372706789L;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="file_owner")
	private MemberAccount mAccount;
	
	private String fileNickName;

	public FileInfoId(MemberAccount mAccount) {
		super();
		this.mAccount = mAccount;
	}	
	
	
	
	
	
}
