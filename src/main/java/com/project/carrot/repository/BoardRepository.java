package com.project.carrot.repository;

import com.project.carrot.entity.Board;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long> {

     Board save(Board board);

     Page<Board> findAll(Pageable pageable);

}
