package com.its.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    public boolean saveBoard(BoardDTO boardDTO) {
        int boardResult = boardRepository.saveBoard(boardDTO);
        if(boardResult > 0){
            return true;
        }else{
            return false;
        }
    }


    public List<BoardDTO> findAll() {
        List<BoardDTO> boardDTOSList = boardRepository.findAll();
        return boardDTOSList;
    }

    public BoardDTO findById(Long b_id) {
        BoardDTO boardDTO = boardRepository.findById(b_id);
        return boardDTO;
    }

    public boolean delete(Long b_id) {
        int result = boardRepository.delete(b_id);
        if(result > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean update(BoardDTO boardDTO) {
        int result = boardRepository.update(boardDTO);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

    public void saveFile(BoardDTO boardDTO) throws IOException {
        /**
         *  1. DTO 객체에 담긴 파일을 꺼냄
         *  2. 파일의 이름을 가져옴.
         *  2-1. 파일 이름 중복을 피하기 위한 조치
         *  3. 파일 이름을 DTO 겍체의 boardFileName에 저장
         *  4. 파일의 저장 위치 지정.
         *  5. 파일 저장처리
         *  6. DTO 객체 repository로 전달
         */
        MultipartFile boardFile = boardDTO.getBoardFile(); // 1
        String boardFileName = boardFile.getOriginalFilename(); // 2
        boardFileName = System.currentTimeMillis() + "-" + boardFileName; //2-1
        boardDTO.setBoardFileName(boardFileName); //3
        String savePath = "C:\\spring_img\\" + boardFileName; //4
        //5
        if(!boardFile.isEmpty()){
            boardFile.transferTo(new File(savePath));
        }
        boardRepository.saveFile(boardDTO); //6
    }

    private static final int PAGE_LIMIT = 3;  // 한 페이지에 보여줄 글 개수
    private static final int BLOCK_LIMIT = 3; // 페이징 개수 ( [이전][1][2][3][다음])

    
    // 요청한 페이지에 해당하는 글 목록을 DB에서 가져오는 역할
    public List<BoardDTO> pagingList(int page) {
        int pagingStart = (page-1) * PAGE_LIMIT;
        Map<String, Integer> pagingParam = new HashMap<>();
        pagingParam.put("start", pagingStart);
        pagingParam.put("limit", PAGE_LIMIT);
        List<BoardDTO> pagingList = boardRepository.pagingList(pagingParam);
        return pagingList;
    }

    public PageDTO paging(int page) {
        int boardCount = boardRepository.boardCount(); //글 갯수 조회
        // 필요한 전체 페이지 개수 : 소수점은 올림으로 계산(10,3이라 10/3 = 3.333인데 올려서 4 )
        int maxPage = (int)(Math.ceil((double)boardCount / PAGE_LIMIT));
        // 시작페이지 (1,4,7...)
        int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
        // 끝페이지 (3,6,9...)
        int endPage = startPage + BLOCK_LIMIT - 1;
        // 끝페이지가 (3,6,9..등이 아니면 마지막페이지 8을 하겠다는 것)
        if(endPage > maxPage)
            endPage = maxPage;
        PageDTO paging = new PageDTO();
        paging.setPage(page);
        paging.setStartPage(startPage);
        paging.setEndPage(endPage);
        paging.setMaxPage(maxPage);
        return paging;
    }

}
