package com.project.carrot.service;

import com.project.carrot.dto.BoardDTO;
import com.project.carrot.entity.Board;

import java.util.List;

public interface BoardService {

    BoardDTO findId(Long id);

    void listSave(BoardDTO boardDTO);

    List<Board> findAll();
}
