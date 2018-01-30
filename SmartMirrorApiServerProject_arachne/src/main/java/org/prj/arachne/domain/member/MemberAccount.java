package org.prj.arachne.domain.member;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.prj.arachne.domain.member.valueObj.Password;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class MemberAccount implements UserDetails{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -6331748954539978520L;
	
	@Id
	private String email;
		
	
	
	@Embedded
	@JsonIgnore
	private Password password;
	
	
	@OneToOne
	@JoinColumn(name="m_info_id_fk")
	private MemberInfo mInfo;
	
	@OneToMany(mappedBy="authOwner")
	Set<MemberAuthority> authorities=new HashSet<>();
		
	private boolean accountNonLocked;
	
	
	
	
	//Hibernate 외래키 연결을 위한 기본키 값만 넣을수 있는 생성자 
	public MemberAccount(String email) {
		super();
		this.email = email;
	}
	
	
	
	
	
	@Override
	public Collection<MemberAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password.getValue();
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "MemberAccount [email=" + email + ", password=" + password + ", mInfo="
				+ mInfo + ", authorities=" + authorities + ", accountNonLocked=" + accountNonLocked + "]";
	}



}
