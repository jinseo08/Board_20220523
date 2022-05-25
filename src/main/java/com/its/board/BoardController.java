package com.its.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @GetMapping ("/list")
    public String list(){
        return "board/list";
    }

    // 글쓰기 화면요청
    // @GetMapping("/board/save") - RequestMapping 미적용
    @GetMapping("/save") // - RequestMapping 적용
    public String save() {
        return "redirect:/board/paging";
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
    public String findById(@RequestParam Long b_id, Model model,
                           @RequestParam(value = "page",required = false, defaultValue = "1") int page){
        BoardDTO boardDTO = boardService.findById(b_id);
        model.addAttribute("boardDetail", boardDTO);
        model.addAttribute("page",page);
        // 댓글 목록도 가져가야함
        List<CommentDTO> commentDTOList = commentService.findAll(b_id);
        model.addAttribute("commentList",commentDTOList);
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

    @GetMapping("/saveFile")
    public String saveFileForm(){
        return "board/saveFile";
    }

    @PostMapping("/saveFile")
    public String saveFile(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.saveFile(boardDTO);
        return "redirect:/board/findAll";
    }

    @GetMapping("/paging")
    public String paging(@RequestParam(value="page", required=false, defaultValue="1") int page, Model model) {
        // required-false로 요청하면 /board/paging 요청도 가능
        // 별도의 페이지 값을 요청하지 않으면 첫페이지(page=1)를 보여주자
        List<BoardDTO> boardList = boardService.pagingList(page);
        PageDTO paging = boardService.paging(page);
        model.addAttribute("boardList", boardList);
        model.addAttribute("paging", paging);
        return "board/pagingList";
    }


    @GetMapping("/search")
    public String search(@RequestParam("searchType") String searchType,
                         @RequestParam("q") String q, Model model){
        List<BoardDTO> searchList = boardService.search(searchType,q);
        model.addAttribute("boardList",searchList);
        return "board/list";
    }

}
