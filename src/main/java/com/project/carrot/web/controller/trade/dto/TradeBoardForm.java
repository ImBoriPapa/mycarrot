package com.project.carrot.web.controller.trade.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeBoardForm {

    private String itemImage;
    private String profileImage;
    private String nickname;
    private String title;
    private String context;
    private String location;

    public TradeBoardForm() {
    }

    public TradeBoardForm(String itemImage, String profileImage, String nickname, String title, String context, String location) {
        this.itemImage = itemImage;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.title = title;
        this.context = context;
        this.location = location;
    }
}
