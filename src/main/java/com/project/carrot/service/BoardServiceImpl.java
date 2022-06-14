package com.project.carrot.service;
import com.project.carrot.dto.BoardDTO;
import com.project.carrot.entity.Board;
import com.project.carrot.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public BoardDTO findId(Long id) {
            Board board =  boardRepository.findById(id).orElse(null);
            BoardDTO dto = new BoardDTO();
             dto.setId(board.getId());
        return dto ;
    }

    @Override
    public void save(BoardDTO boardDTO) {

        Board board = new Board(boardDTO.getId(), boardDTO.getTitle(), boardDTO.getContent(),boardDTO.getWriter(),null,null);

        boardRepository.save(board);
    }

    @Override
    public Page<Board> findAll(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

}
