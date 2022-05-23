package com.its.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/save")
    public String saveBoard(@ModelAttribute BoardDTO boardDTO){
        boardService.saveBoard(boardDTO);
        return null;
    }






}
