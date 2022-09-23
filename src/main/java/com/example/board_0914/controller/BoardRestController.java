package com.example.board_0914.controller;

import com.example.board_0914.dto.BoardDTO;
import com.example.board_0914.model.ResponseVO;
import com.example.board_0914.service.board.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardRestController {
  @Autowired
  private boardService boardService;

  @PostMapping("/boardWriteAjax")
  public ResponseVO boardWriteAjax(BoardDTO boardDTO){

    try {
      boardService.boardWrite(boardDTO);

    } catch (Exception e) {
      return ResponseVO.error(e.getMessage());
    }

    return ResponseVO.create(null, ResponseVO.SUCCESS_CODE, "저장되었습니다.");
  }
}


