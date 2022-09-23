package com.example.board_0914.controller;

import com.example.board_0914.dto.BoardDTO;
import com.example.board_0914.service.board.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class boardController {

  @Autowired //boardService와 연동해주기 위해
  private boardService boardService;

  //리스트화면
  @RequestMapping("/boardList") //메소드 줄수가 있다.

  public String boardList(Model model){
    try {
      model.addAttribute("boardList", boardService.boardDTOList());//model 객체를 파라미터로 받아서 데이터를 넘긴다.

    } catch (Exception e) {
      //log.error(e.toString());
    }
    return "boardList";
  }

  //상세화면
  @RequestMapping("/boardDetail")
  public String boardDetail(int boardNo, Model model){
    try {
      model.addAttribute("boardDetail", boardService.boardDetail(boardNo));
      boardService.boardViewCountUpdate(boardNo);//model 객체를 파라미터로 받아서 데이터를 넘긴다.

    } catch (Exception e) {
      //log.error(e.toString());
    }
    return "boardDetail";
  }

  //등록화면(단순화면이동)
  @RequestMapping("/boardWrite")
  public String boardWrite(){
    return "boardWrite";
  }

  //게시판 수정 팝업
  @PostMapping("/boardPwPop")
  public String boardPwPop(BoardDTO boardDto, Model model){

    try {
      model.addAttribute("boardNo", boardDto.getBoardNo());
      model.addAttribute("boardTitle", boardDto.getBoardTitle());
      model.addAttribute("boardContent", boardDto.getBoardContent());
      model.addAttribute("boardWriter", boardDto.getBoardWriter());
      model.addAttribute("boardPw", boardDto.getBoardPw());
      model.addAttribute("PageGubun", boardDto.getPageGubun());//화면으로 모델에 이 이름으로 값을 저장
    } catch (Exception e) {
      //log.error(e.toString());
    }
    return "boardPwPop";
  }
}