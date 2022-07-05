package com.project.carrot.domain.service;

import com.project.carrot.dto.BoardDto;
import com.project.carrot.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {



    void save(BoardDto boardDTO);

    Page<Board> findAll(Pageable pageable);




}
