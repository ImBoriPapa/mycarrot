package com.project.carrot.domain.trade.service;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.domain.trade.dto.TradeCreateDto;
import com.project.carrot.domain.trade.entity.ItemCategory;
import com.project.carrot.domain.trade.entity.Trade;
import com.project.carrot.domain.trade.repository.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
class TradeServiceTest {

    @Autowired
    TradeService tradeService;
    @Autowired
    TradeRepository repository;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("trade 생성 테스트 ")
    void createTrade() throws Exception {
        //given
        String upload1 = "upLoadName1";
        String upload2 = "upLoadName2";
        String upload3 = "upLoadName3";
        String upload4 = "upLoadName4";
        String upload5 = "upLoadName5";

        List<MockMultipartFile> mockMultipartFiles = List.of(
                new MockMultipartFile("file1", "test1.jpg", null, upload1.getBytes()),
                new MockMultipartFile("file2", "test2.jpg", null, upload2.getBytes()),
                new MockMultipartFile("file3", "test3.jpg", null, upload3.getBytes()),
                new MockMultipartFile("file4", "test4.jpg", null, upload4.getBytes()),
                new MockMultipartFile("file5", "test5.jpg", null, upload5.getBytes())
        );

        List<MultipartFile> images = new ArrayList<>();
        images.addAll(mockMultipartFiles);

        TradeCreateDto dto = TradeCreateDto.builder()
                .memberId(1L)
                .title("에어팟 팔아요")
                .category(ItemCategory.ETC)
                .price(1000000)
                .offer(false)
                .share(false)
                .location("ddd")
                .context("100000만원은 엄청싸다 싸")
                .tradeImages(images)
                .build();
        //when
        Trade board = tradeService.createBoard(dto);
        Trade trade = repository.findById(board.getId()).get();
        Member member = memberRepository.findByMemberId(trade.getMember().getMemberId()).get();
        //then
        assertThat(member.getTradeList()).contains(trade);
        assertThat(member.getMemberId()).isEqualTo(trade.getMember().getMemberId());
        assertThat(trade.getItemImages().size()).isEqualTo(5);

    }


}