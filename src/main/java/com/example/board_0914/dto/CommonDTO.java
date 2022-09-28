package com.example.board_0914.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonDTO {

  private String PageGubun;     //페이지 구분 U, D
  private int page;             // 현재 페이지 번호
  private int totalCount;       // 총 갯수
  private int startRow;         // 시작페이지 번호
  private int endRow;           // 끝 페이지 번호
  private int pageSize = 10;         // 화면 하단에 출력할 페이지 사이즈

  private int RowNum;

  public void setPageIndex(Integer pageIndex) {
    this.page = pageIndex == null || pageIndex == 0 ? 1: pageIndex;
    this.startRow = (this.pageSize * (this.page - 1)) + 1 ; //11
    this.endRow = (this.startRow - 1) + this.pageSize;  //20
  }

}
