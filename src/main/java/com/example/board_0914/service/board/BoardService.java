package com.example.board_0914.service.board;

import  com.example.board_0914.dto.BoardDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;


public interface BoardService {
  //별다른 내용을 담지 않고 간략하게 서비스에 대해서 볼수 있게(추상화)
  //구체적인것 구현체에서 담는다.

  //1.조회
  List<BoardDTO> boardDTOList() throws Exception;

  //2.등록
  void boardWrite(BoardDTO boardDTO, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

  //3.상세
  BoardDTO boardDetail(int boardNo) throws Exception;

  //4.수정
  void boardUpdate(BoardDTO boardDTO) throws Exception;
  
  //5.삭제
  void boardDelete(BoardDTO boardDTO) throws Exception;
  
  //리스트 선택시 +1
  void boardViewCountUpdate(int boardNo) throws Exception;

  //팝업
  BoardDTO boardPwPop(BoardDTO boardDTO) throws Exception;

  //
  List<BoardDTO> boardPageAjaxList(BoardDTO boardDTO) throws Exception;

}
