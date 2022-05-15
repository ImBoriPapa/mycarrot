package com.project.carrot.service;

import com.project.carrot.entity.Board;
import com.project.carrot.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public List<Board> findAll() {

        return boardRepository.findAll();
    }
}
