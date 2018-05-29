package org.prj.arachne.domain.member.valueObj;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
// 사용 안하는중임 EmbeddedId 사용법을 모른다
@Deprecated
public class MemberId implements Serializable {
    /**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -138050651862192384L;
	
	@Column(name="member_id")
    private String id;

    @SuppressWarnings("unused")
	private MemberId() {
    }

    public MemberId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberId memberId = (MemberId) o;
        return Objects.equals(id, memberId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}