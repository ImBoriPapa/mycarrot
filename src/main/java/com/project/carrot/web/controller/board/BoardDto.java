package com.project.carrot.web.controller.board;

import com.project.carrot.domain.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    //필수 매개변수
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createDate;
    private Location location;
    //선택 매개변수
    private LocalDateTime modifiedDate;

    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createDate, LocalDateTime modifiedDate, Location location) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.location = location;
    }

    public BoardDto() {
    }
}