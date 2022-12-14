package com.example.board_0914.mapper.board;

import com.example.board_0914.dto.BoardDTO;
import com.example.board_0914.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
  //중간 선언이라고 생각하면 됨.

  //1.조회
  List<BoardDTO> boardDTOList();

  //2.등록
  void boardWrite(BoardDTO boardDTO);

  //3.상세
  BoardDTO boardDetail(int boardNo);

  //4.수정
  void boardUpdate(BoardDTO boardDTO);

  //5.삭제
  void boardDelete(BoardDTO boardDTO);

  //리스트 선택시 +1
  void boardViewCountUpdate(int boardNo);

  //팝업
  BoardDTO boardPwPop(BoardDTO boardDTO);

  //페이징
  List<BoardDTO> boardPageAjaxList(BoardDTO boardDTO);


  void insertBoardFileList(FileDTO fileDTO) throws Exception;

  List<FileDTO> selectBoardFileList(int boardNo) throws Exception;
}