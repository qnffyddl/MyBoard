package com.example.board_0914.service.board.impl;

import com.example.board_0914.dto.BoardDTO;
import com.example.board_0914.mapper.board.boardMapper;
import com.example.board_0914.service.board.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class boardServiceImpl implements boardService {
  //implements한 boardService의 실질적 구체적 구현을 하는곳.
  
  @Autowired
  private com.example.board_0914.mapper.board.boardMapper boardMapper;

  //1.조회
  public List<BoardDTO> boardDTOList() {
    return boardMapper.boardDTOList();
  }

  //2.등록
  public void boardWrite(BoardDTO boardDTO) throws Exception {
    boardMapper.boardWrite(boardDTO);
  }

  //3.상세
  public BoardDTO boardDetail(int boardNo) {
    return boardMapper.boardDetail(boardNo);
  }

  //4.수정
  public void boardUpdate(BoardDTO boardDTO) throws Exception {
    boardMapper.boardUpdate(boardDTO);
  }

  //5.삭제
  public void boardDelete(BoardDTO boardDTO) throws Exception {
    boardMapper.boardDelete(boardDTO);
  }

  //리스트 선택시 +1
  public void boardViewCountUpdate(int boardNo) throws Exception {
     boardMapper.boardViewCountUpdate(boardNo);
  }

  //팝업
  public BoardDTO boardPwPop(BoardDTO boardDTO) {
    return boardMapper.boardPwPop(boardDTO);
  }

  //총갯수
  public BoardDTO boardCount(int boardNo) {
    return boardMapper.boardCount(boardNo);
  }
}