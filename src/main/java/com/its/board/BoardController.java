package com.its.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping ("/list")
    public String list(){
        return "board/list";
    }

    // 글쓰기 화면요청
    // @GetMapping("/board/save") - RequestMapping 미적용
    @GetMapping("/save") // - RequestMapping 적용
    public String save() {
        return "board/save";
    }

    // 글쓰기 처리
    // @PostMapping("/board/save") - RequestMapping 미적용
    @PostMapping("/save") //RequestMapping 적용
    public String saveBoard(@ModelAttribute BoardDTO boardDTO){
        boolean boardResult = boardService.saveBoard(boardDTO);
        if(boardResult){
            return "redirect:/board/findAll";
        }else{
            return "board/save";
        }
    }

    // @GetMapping("/board/findAll") - RequestMapping 미적용
    @GetMapping("/findAll") // - RequestMapping 적용
    public String findAll(Model model){
        List<BoardDTO> boardDTOSList = boardService.findAll();
        model.addAttribute("boardList", boardDTOSList);
        return "board/list";
    }

    @GetMapping("/detail")
    public String findById(@RequestParam Long b_id, Model model){
        BoardDTO boardDTO = boardService.findById(b_id);
        model.addAttribute("boardDetail", boardDTO);
        return "detail";
    }


    @GetMapping("/pwCheck")
    public String deleteCheck(@RequestParam Long b_id, Model model){
        BoardDTO boardDTO = boardService.findById(b_id);
        model.addAttribute("boardDetail", boardDTO);
        return "passwordCheck";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long b_id){
        boolean result = boardService.delete(b_id);
        if(result){
            return "redirect:/board/findAll";
        }else {
            return "redirect:/board/deleteCheck";
        }
    }

    @GetMapping("updateCk")
    public String updateCk(@RequestParam Long b_id, Model model){
        BoardDTO boardDTO = boardService.findById(b_id);
        model.addAttribute("boardDetail", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO){
        boolean result = boardService.update(boardDTO);
        if(result){
            return "redirect:/board/detail?b_id=" + boardDTO.getB_id();
        }else{
            return "update";
        }
    }


}
