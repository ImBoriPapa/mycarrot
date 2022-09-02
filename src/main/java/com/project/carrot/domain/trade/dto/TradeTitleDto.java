package com.project.carrot.domain.trade.dto;

import com.project.carrot.domain.trade.entity.Trade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TradeTitleDto {

    private Long id;
    private String title;
    private String context;
    private int price;

    public TradeTitleDto(Trade trade) {
        this.id = trade.getId();
        this.title = trade.getTitle();
        this.context = trade.getContext();
        this.price = trade.getPrice();
    }
}
