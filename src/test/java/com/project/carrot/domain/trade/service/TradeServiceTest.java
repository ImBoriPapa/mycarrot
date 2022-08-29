package com.project.carrot.domain.trade.service;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.domain.trade.entity.Trade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class TradeServiceTest {

    @Autowired
    TradeService tradeService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("거래 게시물 생성 테스트")
    public void createBoard() throws Exception{
        //given

        Optional<Member> findMember = memberRepository.findByMemberId(1L);
        Trade trade = Trade.builder()
                .id(Long.valueOf(1))
                .member(findMember.get())
                .title("맥북프로")
                .price(2000000)
                .location("서울시 강서구 화곡동")
                .context("새거에요")
                .offer(false)
                .share(false)
                .build();

        tradeService.createBoard(trade,findMember.get().getMemberId());
        //when

        //then

    }

}