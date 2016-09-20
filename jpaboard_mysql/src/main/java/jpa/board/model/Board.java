package jpa.board.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Board {
	
	@Id	
	@GeneratedValue	
	@Column(length=5)
	protected Integer id;		
	
	@Column(length=20, nullable=false)
	protected String name;
	
	@Column(length=20, nullable=false)
	protected String passwd;
	
	@Column(length=500, nullable=false)
	protected String title;
	
	@Column(length=4000, nullable=false)
	protected String content;
	
	//날짜기본형식 time, day, month, year 형태저장
	@Column(nullable=false, columnDefinition = "datetime default now()")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date regdate = new Date();	
	
	@Column(nullable=false, columnDefinition = "int(5) default 0")
	protected Integer readcount = 0;
	
	// 답변인경우 어느글의 답변인지 상위글번호
	// 최상위글인 경우 자신의 글번호 동일하다.
	// 리스트보기에서 정렬시 우선적으로 reply로 정렬
	@Column(nullable=false, columnDefinition = "int(5) default 0 ")
	protected Integer reply = 0 ;
	
	// 글아래 모든 답변들에 대해 reply_level과 관계없이 1씩 증가    
	@Column(nullable=false, columnDefinition = "int(5) default 0")
	protected Integer replystep = 0;
	
	// 1차,2차 답글인지 여부
	// 하나의 글에 답변이 두개면 그 두답변은 reply_level이 같다. 
	// 리스트보기에서 reply_level에 따라 들여쓰기를 한다. 
	@Column(nullable=false,columnDefinition = "int(5) default 0", length=10)
	protected Integer replylevel = 0;
	
	@PostPersist
	protected void onCreate() {
		reply = id;
	}

}
