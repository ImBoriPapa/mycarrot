package com.project.carrot.service;

import com.project.carrot.entity.TestBoard;
import com.project.carrot.repository.TestBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestBoardService {

    TestBoardRepository testBoardRepository;

    public void save(){
        TestBoard testBoard = new TestBoard();
        testBoardRepository.save(testBoard);
    }
}
