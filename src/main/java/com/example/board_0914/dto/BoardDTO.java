package com.example.board_0914.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class BoardDTO {
  private int boardNo;
  private String boardTitle;
  private String boardContent;
  private String boardWriter;
  private Date boardRegdate;
  private int boardViewcount;
  private String boardPw;
  private String boardModidate;
  private String boardDeleteYN;
  private String fileNo;
  private int boardNoFk;
  private String fileName;
  private String filePath;
  private String fileMount;
}
