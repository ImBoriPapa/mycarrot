package com.project.carrot.web.controller;


import com.project.carrot.dto.BoardDto;
import com.project.carrot.dto.LocationDto;
import com.project.carrot.domain.Board.entity.Board;
import com.project.carrot.domain.category.locationItem.city.City;
import com.project.carrot.domain.Board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;




@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @ModelAttribute("cities")
    public City[] city(){
        return City.values();
    }


    @GetMapping("/boardForm")
    public String boardForm(){
        return "/board/boardForm";
    }

    @PostMapping("/boardSave")
    public String save(BoardDto boardDTO){
        boardService.save(boardDTO);
        return "redirect:/boardForm";
    }

    @GetMapping("/boardList")
    public String list( Model model,@PageableDefault(size=3) Pageable pageable) {

        Page<Board> boards = boardService.findAll(pageable);
        LocationDto locationDTO = new LocationDto();

        int startPage = Math.max(1,boards.getPageable().getPageNumber()-4);
        int endPage = Math.max(boards.getTotalPages(),boards.getPageable().getPageNumber()+1);

        model.addAttribute("locationDTO",locationDTO );
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards);

        return "board/boardList";
    }




}
