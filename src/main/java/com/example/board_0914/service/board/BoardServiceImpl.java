package com.example.board_0914.service.board;

import com.example.board_0914.common.FileUtils;
import com.example.board_0914.dto.BoardDTO;
import com.example.board_0914.dto.FileDTO;
import com.example.board_0914.mapper.board.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
  //implements한 boardService의 실질적 구체적 구현을 하는곳.
  
  @Autowired
  private BoardMapper boardMapper;

  @Autowired
  private FileUtils fileUtils;

  //1.조회
  @Override
  public List<BoardDTO> boardDTOList() {
    return boardMapper.boardDTOList();
  }

  //2.등록
  @Override
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public void boardWrite(BoardDTO boardDTO, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
      log.debug("boardWrite서비스시작");
      boardMapper.boardWrite(boardDTO);

      List<FileDTO> list = fileUtils.parseFileInfo(boardDTO.getBoardNo(), multipartHttpServletRequest);

      if (!ObjectUtils.isEmpty(list)) {
        for(int i=0; i<list.size(); i++){
          //list.get(0).setBoardNoFk(0);
          //list.get(1).setBoardNoFk(0);
          boardMapper.insertBoardFileList(list.get(i));
        }

      }
  }

  //3.상세
  @Override
  public BoardDTO boardDetail(int boardNo) throws Exception {
    BoardDTO boardDetail = boardMapper.boardDetail(boardNo);
    List<FileDTO> fileList = boardMapper.selectBoardFileList(boardNo);
    boardDetail.setFileList(fileList);

    return boardDetail;
  }

  //4.수정
  @Override
  public void boardUpdate(BoardDTO boardDTO) throws Exception {
    try {
      boardMapper.boardUpdate(boardDTO);

    } catch (Exception e) {
      log.error("text : {}", e.getMessage());
    }
  }

  //5.삭제
  @Override
  public void boardDelete(BoardDTO boardDTO) throws Exception {
    try {
      boardMapper.boardDelete(boardDTO);
    } catch (Exception e) {
      log.error("text : {}", e.getMessage());
    }
  }

  //리스트 선택시 +1
  @Override

  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
  public void boardViewCountUpdate(int boardNo) throws Exception {

    try {
      boardMapper.boardViewCountUpdate(boardNo);
      //throw new Exception("예외를 강제로 발생시켰습니다.");

    } catch (Exception e) {
      log.error("text : {}", e.getMessage());
    }
  }

  //팝업
  @Override
  public BoardDTO boardPwPop(BoardDTO boardDTO) {
    return boardMapper.boardPwPop(boardDTO);
  }

  //
  @Override
  public List<BoardDTO> boardPageAjaxList(BoardDTO boardDTO) {
    return boardMapper.boardPageAjaxList(boardDTO);
  }
}