package com.project.carrot.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BoardDTO {

    private Long id;

    private String title;

    private String content;

    public BoardDTO(){};

    public BoardDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
