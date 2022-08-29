package com.project.carrot.web.controller.trade.dto;

import com.project.carrot.domain.trade.entity.Trade;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class TradeFormList {
    private List<TradeForm> tradeList = new ArrayList<>();
    private int totalPage;

    private boolean hasPrevious;

    private boolean hasNext;

    private int pageNumber;

    private int nextPageNumber;

    private int previousPageNumber;

    @Builder
    public TradeFormList(List<Trade> tradeList, int totalPage , Boolean hasPrevious, Boolean hasNext, int pageNumber, int nextPageNumber, int previousPageNumber) {
        this.tradeList = tradeList.stream()
                .map(TradeForm::new).collect(Collectors.toList());
        this.totalPage = totalPage;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
        this.pageNumber = pageNumber;
        this.nextPageNumber = nextPageNumber;
        this.previousPageNumber = previousPageNumber;

    }
}
