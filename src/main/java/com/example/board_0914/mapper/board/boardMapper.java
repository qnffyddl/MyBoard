package com.example.board_0914.mapper.board;

import com.example.board_0914.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface boardMapper {
  List<BoardDTO> boardDTOList();
  BoardDTO boardDetail(int boardNo);

  void boardWrite(BoardDTO boardDTO);
//중간 선언
}