package com.project.carrot.web.controller.trade.dto;

import com.project.carrot.domain.trade.entity.Trade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public  class TradeForm {
    Long id;
    String title;
    String location;
    int price;

    public TradeForm(Trade trade) {
        this.id = trade.getId();
        this.title = trade.getTitle();
        this.location = trade.getLocation();
        this.price = trade.getPrice();

    }
}