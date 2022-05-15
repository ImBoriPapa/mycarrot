package com.project.carrot.controller;

import com.project.carrot.entity.Board;
import com.project.carrot.repository.BoardRepository;
import com.project.carrot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {

        List<Board> boards = boardService.findAll();
        model.addAttribute("boards",boards);
        return "board/list";
    }
}
