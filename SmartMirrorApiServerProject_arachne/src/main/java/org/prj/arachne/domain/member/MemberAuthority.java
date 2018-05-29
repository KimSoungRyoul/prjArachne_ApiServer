package org.prj.arachne.domain.member;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.prj.arachne.domain.member.valueObj.AuthorityType;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAuthority implements GrantedAuthority {

	@Transient
	private static final long serialVersionUID = -6331748954539978521L;

	@Id
	@GeneratedValue
	private Long authorityId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Seoul")
	private Date grantedDate;
	
	@Enumerated(EnumType.STRING)
	private AuthorityType authorityType;
	
	@ManyToOne
	@JoinColumn(name="auth_owner")
	@JsonBackReference
	private MemberAccount authOwner;
	
	
	
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authorityType.toString();
	}




	@Override
	public String toString() {
		
		
		return "MemberAuthority [authorityId=" + authorityId + ", grantedDate=" + grantedDate + ", authorityType="
				+ authorityType + ", authOwner=" + authOwner.getEmail() + "]";
	}

	
	
	
	
	
	
	
	
	
	
}
