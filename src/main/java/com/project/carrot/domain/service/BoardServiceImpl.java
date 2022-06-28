package com.project.carrot.domain.service;
import com.project.carrot.dto.BoardDTO;
import com.project.carrot.domain.entity.Board;
import com.project.carrot.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;


    @Override
    public void save(BoardDTO boardDTO) {
        Board board = new Board.BoardBuilder(boardDTO).build();
        boardRepository.save(board);
    }

    @Override
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

}
