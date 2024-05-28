package com.portfolio.www.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.portfolio.www.dto.BoardDto;

public interface BoardRepository {
	public List<BoardDto> findAllBoards(@Param("startBoardSeq") Integer startBoardSeq, @Param("postsPerPage") Integer postsPerPage, @Param("boardTypeSeq") Integer boardTypeSeq);
	public int cntTotalBoards(Integer boardTypeSeq);
	public int addBoard(BoardDto boardDto);
}
