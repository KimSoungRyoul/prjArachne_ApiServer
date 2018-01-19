package org.prj.arachne.domain.member;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.prj.arachne.domain.member.valueObj.MemberId;
import org.prj.arachne.domain.member.valueObj.Password;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(indexes= {
		@Index(name="email_index",columnList="email",unique=true)
})
@Getter @Setter
public class MemberAccount implements UserDetails{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -6331748954539978520L;
	
	@Id
	@GeneratedValue
	private Long memberId;
	
	
	private String email;
		
	@Embedded
	private Password password;
	
	
	@OneToOne
	@JoinColumn(name="m_info_id_fk")
	private MemberInfo mInfo;
	
	@OneToMany(mappedBy="authOwner")
	Set<MemberAuthority> authorities=new HashSet<>();
		
	private boolean accountNonLocked;
	
	
	
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
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "MemberAccount [memberId=" + memberId + ", email=" + email + ", password=" + password + ", mInfo="
				+ mInfo + ", authorities=" + authorities + ", accountNonLocked=" + accountNonLocked + "]";
	}

}
