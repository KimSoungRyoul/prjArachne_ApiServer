package org.prj.arachne.model.board;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.prj.arachne.model.fileinfo.FileInfo;
import org.prj.arachne.model.member.MemberAccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Entity
@Data
public class Board {

	@Id
	@GeneratedValue
	private Long id;
	
	//private Long parentBoardId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="board_type")
	private BoardElement boardType;
	
	@ManyToOne
	@JoinColumn(name="parent_board_id")
	private Board parentBoard;
	
	
	private String title;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="writer_id")
	private MemberAccount writer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Seoul")
	private Date regDate;
	
	@Lob
	private String contents;
	
	@OneToMany(mappedBy="parentBoard")
	private List<Board> replies=new LinkedList<>();
	
	@OneToMany
	@JoinColumn(name="file_owned_by_board_id")
	private Set<FileInfo> attachments=new HashSet<>();
	
	
}

