package jpa.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import jpa.board.model.Board;
import jpa.board.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Override
	//-----------------------------------------
	// 게시판 리스트 보기, 한페이지에 3개씩
	// curPage:요청하는 페이지, 첫페이지는 0
	//-----------------------------------------
	public Page<Board> findAll(Integer curPage) {
		PageRequest pr = new PageRequest(curPage, 3, 
				                    new Sort(
				                    		new Order(Direction.DESC, "reply"),
				                    		new Order(Direction.ASC, "replystep")));
		return boardRepository.findAll(pr);
	}

	@Override
	//-----------------------------------------
	// 글 쓰기
	//-----------------------------------------
	public void create(Board board) {		
	    boardRepository.save(board);
	}

	@Override
	//-----------------------------------------
	// 글 삭제
	//-----------------------------------------
	public void delete(Integer id) {
		boardRepository.delete(id);		
	}

	@Override
	//-----------------------------------------
	// 글 수정
	//-----------------------------------------
	public void update(Board board, Integer id) {
		board.setId(id);
		boardRepository.save(board);
	}
}
