package com.example.board_0914.controller;

import com.example.board_0914.dto.BoardDTO;
import com.example.board_0914.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired //boardService와 연동해주기 위해
  private BoardService boardService;

  //1.리스트화면
  @RequestMapping("/boardList") //메소드 줄수가 있다.
  public String boardList(Model model){
    // ADD START
    log.debug("boardList");
    // ADD END

    try {
      model.addAttribute("boardList", boardService.boardDTOList());//model 객체를 파라미터로 받아서 데이터를 넘긴다.
      //model.addAttribute("boardList", null);// 등록된 리스트 없을 때 예외처리

    } catch (Exception e) {
      //log.error(e.toString());
    }
    return "boardList";
  }

  //2.등록화면(단순화면이동)
  @RequestMapping("/boardWrite")
  public String boardWrite(){
    return "boardWrite";
  }

  //3.상세화면
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

  
  //4.게시판 수정 PW팝업
  @PostMapping("/boardPwPop")
  public String boardPwPop(BoardDTO boardDto, Model model, MultipartHttpServletRequest multipartHttpServletRequest){

    try {
      // model.addAttribute("BoardDTO", boardDto);
      List<MultipartFile> multipartFile = multipartHttpServletRequest.getFiles("boardfile");

      model.addAttribute("boardNo", boardDto.getBoardNo());
      model.addAttribute("boardTitle", boardDto.getBoardTitle());
      model.addAttribute("boardContent", boardDto.getBoardContent());
      model.addAttribute("boardWriter", boardDto.getBoardWriter());
      model.addAttribute("boardPw", boardDto.getBoardPw());
      model.addAttribute("PageGubun", boardDto.getPageGubun());//화면으로 모델에 이 이름으로 값을 저장
      model.addAttribute("fileInfo", multipartFile);
    } catch (Exception e) {
      //log.error(e.toString());
    }
    return "boardPwPop";
  }
}