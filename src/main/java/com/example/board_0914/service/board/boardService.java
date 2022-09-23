package com.example.board_0914.service.board;

import com.example.board_0914.dto.BoardDTO;

import java.util.List;


public interface boardService {
  List<BoardDTO> boardDTOList() throws Exception;

  BoardDTO boardDetail(int boardNo) throws Exception;

  void boardWrite(BoardDTO boardDTO) throws Exception;

  void boardUpdate(BoardDTO boardDTO) throws Exception;

  void boardDelete(BoardDTO boardDTO) throws Exception;

  void boardViewCountUpdate(int boardNo) throws Exception;

  BoardDTO boardPwPop(BoardDTO boardDTO) throws Exception;

}

//별다른 내용을 담지 않고 간략하게 서비스에 대해서 볼수 있게(추상화)
//구체적인것 구현체에서 담는다.