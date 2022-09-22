package com.example.board_0914.controller;

import com.example.board_0914.service.board.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/board")
public class boardController {

  @Autowired
  private boardService boardService;

//  @GetMapping("/boardList")
//  public String testHome(){
//    return "boardList";
//  }

  @RequestMapping("/boardList")
  //메소드 줄수가 있다.
  public String boardList(Model model){
    try {
      model.addAttribute("boardList", boardService.boardDTOList());

    } catch (Exception e) {
      //log.error(e.toString());
    }
    return "boardList";
  }

  @GetMapping("/boardResist")
  public String boardResist(Model model){

    return "boardResist";
  }

  @PostMapping("/boardResistPro")
  public String boardResistPro(){

    return "";
  }
}
