package com.example.board_0914.service.board.impl;

import com.example.board_0914.dto.BoardDTO;
import com.example.board_0914.mapper.board.boardMapper;
import com.example.board_0914.service.board.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class boardServiceImpl implements boardService {
  @Autowired
  private com.example.board_0914.mapper.board.boardMapper boardMapper;

  public List<BoardDTO> boardDTOList() {
    return boardMapper.boardDTOList();
  }

  public BoardDTO boardDetail(int boardNo) {
    return boardMapper.boardDetail(boardNo);
  }

  public void boardWrite(BoardDTO boardDTO) throws Exception {
     boardMapper.boardWrite(boardDTO);
  }
}