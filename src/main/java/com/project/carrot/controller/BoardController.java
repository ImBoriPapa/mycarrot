package com.project.carrot.controller;
import com.project.carrot.dto.BoardDTO;
import com.project.carrot.entity.Board;
import com.project.carrot.repository.BoardRepository;
import com.project.carrot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model) {

        List<Board> boards = boardService.findAll();
        model.addAttribute("boards",boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false)Long id){
        if(id==null){

            model.addAttribute("board",new BoardDTO());
        }else{
             BoardDTO dto =  boardService.findId(id);
             model.addAttribute("board",dto);
        }

        return "board/form";
    }
    @PostMapping("/form")
    public String submit(@ModelAttribute BoardDTO boardDTO){
        boardService.listSave(boardDTO);
        return "redirect:/board/list";
    }
}
