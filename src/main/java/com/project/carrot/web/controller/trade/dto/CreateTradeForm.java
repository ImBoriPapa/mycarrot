package com.project.carrot.web.controller.trade.dto;

import com.project.carrot.domain.trade.entity.Category;
import lombok.Data;

@Data
public class CreateTradeForm {

    private String title;
    private Category category;
    private int price;
    private boolean offer;
    private boolean share;
    private String location;
    private String context;
    private String uploadTradeImage;
}
