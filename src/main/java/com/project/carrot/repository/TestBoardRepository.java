package com.project.carrot.repository;

import com.project.carrot.entity.TestBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestBoardRepository extends JpaRepository<TestBoard,Long> {

   TestBoard save(TestBoard testBoard);
}
