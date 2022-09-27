package com.example.board_0914.controller;

import com.example.board_0914.dto.BoardDTO;
import com.example.board_0914.model.ResponseVO;
import com.example.board_0914.service.board.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/board")
public class BoardRestController {
  @Autowired
  private boardService boardService;


//2.등록요청시 JSP ajax 타는 부분 개인정보 있으니 POST
  @PostMapping("/boardWriteAjax")
  public ResponseVO boardWriteAjax(@Valid BoardDTO boardDTO) {

    try {
      boardService.boardWrite(boardDTO);

    } catch (Exception e) {
      return ResponseVO.error(e.getMessage());
    }

    return ResponseVO.create(null, ResponseVO.SUCCESS_CODE, "저장되었습니다.");
  }

  //4.수정요청시 JSP ajax 타는 부분
  @PostMapping("/boardUpdateAjax")
  public ResponseVO boardUpdateAjax(@Valid BoardDTO boardDTO) {

    try {
      boardService.boardUpdate(boardDTO);

    } catch (Exception e) {
      return ResponseVO.error(e.getMessage());
    }

    return ResponseVO.create(null, ResponseVO.SUCCESS_CODE, "저장되었습니다.");

  }

  //5.삭제 요청시 JSP ajax 타는 부분
  @PostMapping("/boardDeleteAjax")
  public ResponseVO boardDeleteAjax(@Valid BoardDTO boardDTO) {

    try {
      boardService.boardDelete(boardDTO);

    } catch (Exception e) {
      return ResponseVO.error(e.getMessage());
    }

     return ResponseVO.create(null, ResponseVO.SUCCESS_CODE, "삭제되었습니다.");

  }

  //비밀번호 입력
  @PostMapping("/boardPwAjax")
  public ResponseVO boardPwAjax(BoardDTO boardDTO){

    BoardDTO boardDTOData = new BoardDTO();

    try {
      boardDTOData=boardService.boardPwPop(boardDTO);

    } catch (Exception e) {
      return ResponseVO.error(e.getMessage());
    }

    return ResponseVO.create(boardDTOData, ResponseVO.SUCCESS_CODE, "저장되었습니다.");
  }
}

