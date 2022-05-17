package com.project.carrot.controller;
import com.project.carrot.dto.BoardDTO;
import com.project.carrot.entity.Board;
import com.project.carrot.service.BoardService;
import com.project.carrot.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardValidator validator;

    @GetMapping("/list")
    public String list(Model model,@PageableDefault(size=3) Pageable pageable
                       ) {

        Page<Board> boards = boardService.findAll(pageable);

       int startPage = Math.max(1,boards.getPageable().getPageNumber()-4);
       int endPage = Math.max(boards.getTotalPages(),boards.getPageable().getPageNumber()+4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
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
    public String submit(@Valid BoardDTO boardDTO, BindingResult bindingResult){
        validator.validate(boardDTO,bindingResult);
        if(bindingResult.hasErrors()){
            return "board/form";
        }

        boardService.listSave(boardDTO);
        return "redirect:/board/list";
    }
}
