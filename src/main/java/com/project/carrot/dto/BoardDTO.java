package com.project.carrot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDTO {
    //필수 매개변수
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createDate;
    //선택 매개변수
    private LocalDateTime modifiedDate;

    public BoardDTO(Long id, String title, String content, String writer, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public BoardDTO() {
    }
}