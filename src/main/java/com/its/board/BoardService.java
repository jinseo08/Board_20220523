package com.its.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

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
}
