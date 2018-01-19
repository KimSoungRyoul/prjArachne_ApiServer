package org.prj.arachne.domain.member.valueObj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import lombok.Getter;

@Embeddable
public class Password implements Serializable{

	@Transient
	private static final long serialVersionUID = -5018144015828045109L;
	
	@Column(name="password")
	@Getter
	private String value;
	
	protected Password() {
		
	}

	public Password(String value) {
		this.value = value;
	}
	
	
	boolean isMatch(String value) {
		return this.value.equals(value);
	}
	
	
	
	
}
