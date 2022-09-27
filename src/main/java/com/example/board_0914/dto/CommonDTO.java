package com.example.board_0914.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonDTO {

  private String PageGubun;
  private int nowPage, startPage, endPage, total, cntPerPage, lastPage, start, end;
  private int cntPage = 5;
  public CommonDTO() {	}
  public CommonDTO(int total, int nowPage, int cntPage) {
    setNowPage(nowPage);
    //현재 페이지
    setCntPerPage(cntPerPage);
    //페이지 당 게시글수
    setTotal(total);
    //게시글 총 갯수
    calcLastPage(getTotal(), getCntPerPage());
    //제일 마지막 페이지 계산
    //시작 끝 페이지 계산 - 아래.
    calcStartEndPage(getNowPage(), cntPage);
    calcStartEnd(getNowPage(), getCntPerPage());

  }
  // 제일 마지막 페이지 계산
  public void calcLastPage(int total, int cntPerPage) {
    setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
  }
  // 시작, 끝 페이지 계산
  public void calcStartEndPage(int nowPage, int cntPage) {
    setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
    if (getLastPage() < getEndPage()) {
      setEndPage(getLastPage());
    }
    setStartPage(getEndPage() - cntPage + 1);
    if (getStartPage() < 1) {
      setStartPage(1);
    }
  }
  // DB 쿼리에서 사용할 start, end값 계산
  public void calcStartEnd(int nowPage, int cntPerPage) {
    setEnd(nowPage * cntPerPage);
    setStart(getEnd() - cntPerPage + 1);
  }

}
