package com.project.carrot.service;

import com.project.carrot.dto.BoardDTO;
import com.project.carrot.dto.MemberDTO;
import com.project.carrot.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {



    void save(BoardDTO boardDTO);

    Page<Board> findAll(Pageable pageable);




}
