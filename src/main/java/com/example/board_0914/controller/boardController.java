package com.example.board_0914.controller;

import com.example.board_0914.service.board.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class boardController {

  @Autowired//boardService와 연동해주기 위해
  private boardService boardService;

  //리스트화면
  @RequestMapping("/boardList")
  //메소드 줄수가 있다.
  public String boardList(Model model){
    try {
      model.addAttribute("boardList", boardService.boardDTOList());
      //model 객체를 파라미터로 받아서 데이터를 넘긴다.
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
      //model 객체를 파라미터로 받아서 데이터를 넘긴다.
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

  //Test 단순 화면 이동
  @GetMapping("/test")
  public String test() {

    return "test";
  }

  @PostMapping("/test")
  @ResponseBody
  public Object testAjax(@RequestBody String boardTitle) {
    // ex_1
    String text = "답변";

    // ex_2
    Map<String, String> map = new HashMap<String,String>();

    map.put("title", boardTitle);


    return map;
  }
}