package com.project.carrot.entity;

import com.project.carrot.dto.BoardDTO;
import com.project.carrot.entity.locationItem.City;
import com.project.carrot.entity.locationItem.District;
import com.project.carrot.entity.locationItem.Town;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "WRITER")
    private String writer;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "MODIFiED_DATE")
    private LocalDateTime modifiedDate;

    @Column(name = "LOCATION")
    @Embedded
    private Location location;




    public Board() {}

    public Board(BoardBuilder boardBuilder) {
        this.id = boardBuilder.id;
        this.title = boardBuilder.title;
        this.content = boardBuilder.content;
        this.writer = boardBuilder.writer;
        this.createDate = boardBuilder.createDate;
        this.modifiedDate = boardBuilder.modifiedDate;
        this.location = boardBuilder.location;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public static class BoardBuilder{
        private final Long id;
        private final String title;
        private final String content;
        private final String writer;
        private final LocalDateTime createDate;
        private final LocalDateTime modifiedDate;
        private final Location location;

        public BoardBuilder(BoardDTO boardDTO){
            this.id = boardDTO.getId();
            this.title = boardDTO.getTitle();
            this.content = boardDTO.getWriter();
            this.writer = boardDTO.getWriter();
            this.createDate = LocalDateTime.now();
            this.modifiedDate = boardDTO.getModifiedDate();
            this.location = boardDTO.getLocation();
        }

        public Board build(){
            return new Board(this);
        }
    }
}

