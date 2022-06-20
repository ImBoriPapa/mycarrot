package com.project.carrot.service;

import com.project.carrot.entity.Location;
import com.project.carrot.entity.TestBoard;
import com.project.carrot.entity.locationItem.City;
import com.project.carrot.entity.locationItem.district.SeoulDistrict;
import com.project.carrot.entity.locationItem.town.GangNamTown;
import com.project.carrot.repository.TestBoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestBoardServiceTest {

    @Autowired
    TestBoardRepository testBoardRepository;


    @Test
    public void save() throws Exception {
        //given
        TestBoard testBoard = new TestBoard();
        testBoard.setName("name");
        testBoard.setTitle("title");
        testBoard.setLocation(new Location(City.SEOUL, SeoulDistrict.GANGNAM, GangNamTown.APGUJEONG));
        testBoardRepository.save(testBoard);

        //when

        //then

    }
}