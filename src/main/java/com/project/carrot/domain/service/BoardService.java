package com.project.carrot.domain.service;

import com.project.carrot.dto.BoardDTO;
import com.project.carrot.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {



    void save(BoardDTO boardDTO);

    Page<Board> findAll(Pageable pageable);




}
