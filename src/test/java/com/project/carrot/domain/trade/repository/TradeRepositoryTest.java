package com.project.carrot.domain.trade.repository;

import com.project.carrot.domain.trade.dto.TradeTitleDto;
import com.project.carrot.domain.trade.entity.Trade;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Slf4j
class TradeRepositoryTest {

    @Autowired
    TradeRepository tradeRepository;

    @Test
    @DisplayName("Test findAllTradeTitle()")
    public void findAllBy() throws Exception{
        //given
        Trade trade = Trade.builder()
                .title("mac")
                .price(20000)
                .context("싸게 팔아요")
                .build();
        tradeRepository.save(trade);

        int offset = 0;
        List<TradeTitleDto> result = tradeRepository.findAllTradeTitle(offset);
        for (TradeTitleDto findTrade : result) {
            Long id = findTrade.getId();
            String title = findTrade.getTitle();
            int price = findTrade.getPrice();
            System.out.println("id = " + id);
            System.out.println("title = " + title);
            System.out.println("price = " + price);
        }
        //when

        //then

    }

}