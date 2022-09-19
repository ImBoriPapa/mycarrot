package com.project.carrot.domain.trade.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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


        //when

        //then

    }

}