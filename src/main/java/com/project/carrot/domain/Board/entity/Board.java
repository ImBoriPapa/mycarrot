package com.project.carrot.domain.Board.entity;
import com.project.carrot.dto.BoardDto;
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

    public Board() {}

    public Board(BoardBuilder boardBuilder) {
        this.id = boardBuilder.id;
        this.title = boardBuilder.title;
        this.content = boardBuilder.content;
        this.writer = boardBuilder.writer;
        this.createDate = boardBuilder.createDate;
        this.modifiedDate = boardBuilder.modifiedDate;

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


        public BoardBuilder(BoardDto boardDTO){
            this.id = boardDTO.getId();
            this.title = boardDTO.getTitle();
            this.content = boardDTO.getWriter();
            this.writer = boardDTO.getWriter();
            this.createDate = LocalDateTime.now();
            this.modifiedDate = boardDTO.getModifiedDate();

        }

        public Board build(){
            return new Board(this);
        }
    }
}

