package com.project.carrot.domain.trade.entity;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.domain.trade.repository.TradeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TradeTest {

    @Autowired
    TradeRepository tradeRepository;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("거래 게시판 저장 테스트")
    public void create() throws Exception {
        //given
        // createBoard(Member member,String title,Category category,int price,boolean offer,boolean share,String context) {
        Optional<Member> member = memberRepository.findByMemberId(1L);

        Trade board = Trade.createBoard(
                member.get(),
                "맥북프로16인치"
                , Category.ETC
                , 2000000
                , false
                , false
                , "정말 싸게 팔아여"
        );
        Trade newBoard = tradeRepository.save(board);
        //when
        Optional<Trade> findBoard = tradeRepository.findById(newBoard.getId());

        //then
        assertThat(findBoard.get().getId()).isEqualTo(newBoard.getId());
        assertThat(findBoard.get().getMember()).isInstanceOf(member.get().getClass());



    }

}