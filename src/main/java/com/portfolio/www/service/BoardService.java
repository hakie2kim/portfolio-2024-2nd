package com.portfolio.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.www.dao.mybatis.BoardRepository;
import com.portfolio.www.dto.BoardDto;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;
	
	public List<BoardDto> findAllBoards(Integer currPage, Integer postsPerPage, Integer boardTypeSeq) {
		int startBoardSeq = (currPage - 1) * postsPerPage;
		return boardRepository.findAllBoards(startBoardSeq, postsPerPage, boardTypeSeq);
	}
	
	public int cntTotalBoards(Integer boardTypeSeq) {
		return boardRepository.cntTotalBoards(boardTypeSeq);
	}
}
