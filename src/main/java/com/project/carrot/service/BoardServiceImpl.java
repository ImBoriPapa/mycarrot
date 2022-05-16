package com.project.carrot.service;
import com.project.carrot.dto.BoardDTO;
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
    public BoardDTO findId(Long id) {
        Board board =  boardRepository.findById(id).orElse(null);
         board.getId();
         BoardDTO dto = new BoardDTO();
         dto.setId(board.getId());
        return dto;

    }

    @Override
    public void listSave(BoardDTO boardDTO) {

        Board board = new Board(boardDTO.getId(), boardDTO.getTitle(),boardDTO.getContent());

        boardRepository.save(board);
    }

    @Override
    public List<Board> findAll() {

        return boardRepository.findAll();
    }
}
