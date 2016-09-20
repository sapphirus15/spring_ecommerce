package jpa.board.service;

import org.springframework.data.domain.Page;

import jpa.board.model.Board;

public interface BoardService {
	//게시판 리스트 보기
	public Page<Board> findAll(Integer curPage);
	
	//글쓰기
	public void create(Board board);
	
	//글삭제
	public void delete(Integer id);
	
	//글수정
	public void update(Board board, Integer id);
}
