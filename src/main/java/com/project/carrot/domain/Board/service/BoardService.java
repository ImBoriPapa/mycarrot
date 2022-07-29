package com.project.carrot.domain.Board.service;
import com.project.carrot.web.controller.board.BoardDto;
import com.project.carrot.domain.Board.entity.Board;
import com.project.carrot.domain.Board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService  {

    private final BoardRepository boardRepository;



    public void save(BoardDto boardDTO) {
        Board board = new Board.BoardBuilder(boardDTO).build();
        boardRepository.save(board);
    }


    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

}
