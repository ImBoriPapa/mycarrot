package com.project.carrot.repository;

import com.project.carrot.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;



public interface BoardRepository extends JpaRepository<Board, Long> {



}
