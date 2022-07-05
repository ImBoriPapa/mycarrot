package com.project.carrot.entity;

import com.project.carrot.domain.entity.Board;
import com.project.carrot.dto.BoardDto;
import com.project.carrot.domain.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void save() throws Exception
    {
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime modifiedDate = LocalDateTime.now();
        //given
        BoardDto boardDTO = new BoardDto();
        boardDTO.setTitle("제목");
        boardDTO.setContent("내용");
        boardDTO.setWriter("글쓴이");
        boardDTO.setCreateDate(createDate);
//      boardDTO.setModifiedDate(modifiedDate);


        Board board = new Board.BoardBuilder(boardDTO).build();

        //when
        boardRepository.save(board);

        //then
        Assertions.assertThat(board.getTitle()).isEqualTo(board.getTitle());

    }
}