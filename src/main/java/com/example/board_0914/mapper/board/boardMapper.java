package com.example.board_0914.mapper.board;

import com.example.board_0914.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface boardMapper {
  List<BoardDTO> boardDTOList();
  BoardDTO boardDetail(int boardNo);

  void boardWrite(BoardDTO boardDTO);

  void boardUpdate(BoardDTO boardDTO);

  void boardDelete(BoardDTO boardDTO);
  void boardViewCountUpdate(int boardNo);

  BoardDTO boardPwPop(BoardDTO boardDTO);
//중간 선언
}