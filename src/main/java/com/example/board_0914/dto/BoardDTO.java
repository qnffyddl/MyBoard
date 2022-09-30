package com.example.board_0914.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@Valid
public class BoardDTO extends CommonDTO{
  private int boardNo;

  @NotNull(message = "제목 입력은 필수입니다.")
  @NotEmpty(message = "제목은 빈값일 수 없습니다.")
  private String boardTitle;

  @NotNull(message = "내용 입력은 필수입니다.")
  @NotEmpty(message = "내용은 빈값일 수 없습니다.")
  private String boardContent;

  @NotNull(message = "작성자 입력은 필수입니다.")
  @NotEmpty(message = "작성자 빈값일 수 없습니다.")
  private String boardWriter;

  @NotNull(message = "비밀번호 입력은 필수입니다.")
  @NotEmpty(message = "비밀번호는 빈값일 수 없습니다.")
  private String boardPw;

  private Date boardRegdate;
  private int boardViewcount;
  private String boardModidate;
  private String boardDeleteYN;
  private String fileNo;
  private int boardNoFk;
  private String fileName;
  private String filePath;
  private String fileMount;
  private String searchField;
  private String searchText;
}
//Dto 데이터 전송 그냥 형식