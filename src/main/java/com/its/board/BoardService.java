package com.its.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    public void saveBoard(BoardDTO boardDTO) {
        boardRepository.saveBoard(boardDTO);
    }




}
