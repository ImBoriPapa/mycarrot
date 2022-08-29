package com.project.carrot.domain.trade.service;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.domain.trade.entity.Trade;
import com.project.carrot.domain.trade.repository.TradeRepository;
import com.project.carrot.web.controller.trade.dto.TradeFormList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TradeService {

    private final TradeRepository tradeRepository;
    private final MemberRepository memberRepository;

    public TradeFormList findTradeBoards(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),20, Sort.Direction.DESC,"id");
        Page<Trade> result = tradeRepository.findAll(pageRequest);
        TradeFormList findAll = TradeFormList.builder()
                .tradeList(result.getContent())
                .previousPageNumber(result.getPageable().getPageNumber())
                .hasPrevious(result.getPageable().hasPrevious())
                .hasNext(true)
                .nextPageNumber(result.getPageable().getPageNumber()+1)
                .previousPageNumber(result.getPageable().getPageNumber()-1)
                .build();
        return findAll;
    }

    public Trade findTradeBoard(Long id) {
        return tradeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("게시물이 존재하지 않습니다."));
    }

    public Trade createBoard(Trade trade,Long memberId) {
        Optional<Member> findMember = memberRepository.findByMemberId(memberId);
        Trade newBoard = Trade.createBoard(findMember.get(), trade.getTitle(), trade.getCategory(), trade.getPrice(), trade.isOffer(), trade.isShare(), trade.getContext());
        return newBoard;
    }
}
