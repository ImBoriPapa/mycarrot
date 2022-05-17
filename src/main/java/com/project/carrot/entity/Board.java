package com.project.carrot.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long board_id;
    @Column(name = "title")
    private String board_title;
    @Column(name = "content")
    private String board_content;

    public Board(){}

    public Board(Long board_id, String board_title, String board_content) {
        this.board_id = board_id;
        this.board_title = board_title;
        this.board_content = board_content;
    }
}
