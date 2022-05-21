package com.project.carrot.controller;

import com.project.carrot.entity.Board;
import com.project.carrot.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


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

    private boolean loginCheck(HttpServletRequest request){
        //세션을 얻어서
        HttpSession session = request.getSession();
        //세션에 id가 있으면 true, 없으면 false 반환

//        if(session.getAttribute("id")!=null){
//            return true;
//        }else

            return session.getAttribute("id")!=null;

    }



}
