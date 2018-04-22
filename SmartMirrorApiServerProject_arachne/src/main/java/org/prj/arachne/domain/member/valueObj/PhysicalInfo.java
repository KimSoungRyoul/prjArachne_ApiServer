package org.prj.arachne.domain.member.valueObj;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalInfo implements Serializable{

	@Transient
	private static final long serialVersionUID = -2353684287861173674L;

	private int height;
	
	private int weight;
	
	@Enumerated(EnumType.STRING)
	private PhysicalType pType;

	
}
