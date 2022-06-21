package com.project.carrot.entity;

import com.project.carrot.dto.BoardDTO;
import com.project.carrot.entity.locationItem.city.CityList;
import com.project.carrot.entity.locationItem.district.District;
import com.project.carrot.entity.locationItem.town.Town;
import com.project.carrot.repository.BoardRepository;
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
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle("제목");
        boardDTO.setContent("내용");
        boardDTO.setWriter("글쓴이");
        boardDTO.setCreateDate(createDate);
//        boardDTO.setModifiedDate(modifiedDate);
        boardDTO.setLocation(new Location(CityList.SEOUL, District.DONGDAEMUN, Town.APGUJEONG));

        Board board = new Board.BoardBuilder(boardDTO).build();

        //when
        boardRepository.save(board);

        //then
        Assertions.assertThat(board.getTitle()).isEqualTo(board.getTitle());

    }
}