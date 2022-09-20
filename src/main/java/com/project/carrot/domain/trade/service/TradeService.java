package com.project.carrot.domain.trade.service;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.domain.trade.dto.TradeCreateDto;
import com.project.carrot.domain.trade.entity.Trade;
import com.project.carrot.domain.trade.entity.TradeImage;
import com.project.carrot.domain.trade.repository.TradeImageRepository;
import com.project.carrot.domain.trade.repository.TradeRepository;
import com.project.carrot.exception.customEx.NoExistMemberException;
import com.project.carrot.exception.errorCode.ErrorCode;
import com.project.carrot.utlis.image.ImageProvider;
import com.project.carrot.web.controller.trade.dto.TradeFormList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TradeService {

    private final String PROFILE_DIR = "${tradeImage.dir}";

    @Value(PROFILE_DIR)
    public String uploadDir;
    private final TradeRepository tradeRepository;
    private final MemberRepository memberRepository;

    private final TradeImageRepository tradeImageRepository;

    private final ImageProvider imageProvider;

    public TradeFormList findTradeBoards(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 20, Sort.Direction.DESC, "id");
        Page<Trade> result = tradeRepository.findAll(pageRequest);
        TradeFormList findAll = TradeFormList.builder()
                .tradeList(result.getContent())
                .previousPageNumber(result.getPageable().getPageNumber())
                .hasPrevious(result.getPageable().hasPrevious())
                .hasNext(true)
                .nextPageNumber(result.getPageable().getPageNumber() + 1)
                .previousPageNumber(result.getPageable().getPageNumber() - 1)
                .build();
        return findAll;
    }

    public Trade findTradeBoard(Long id) {
        return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
    }

    public Trade createTradeProcessing(TradeCreateDto dto) throws IOException {

        Member findMember = memberRepository.findByMemberId(dto.getMemberId()).orElseThrow(
                () -> new NoExistMemberException(ErrorCode.NO_EXIST_MEMBER));

        Trade trade = Trade.createTrade()
                .member(findMember)
                .title(dto.getTitle())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .offer(dto.isOffer())
                .share(dto.isShare())
                .location(dto.getLocation())
                .context(dto.getContext())
                .build();

        List<TradeImage> images = imageProvider.saveImageProcessingV2(dto.getTradeImages(), uploadDir).stream().map(form -> TradeImage.createTradeImage()
                .trade(trade)
                .upLoadImageName(form.getUpLoadImage())
                .storedImageName(form.getStoredImage())
                .build()).collect(Collectors.toList());

        trade.addImage(images);

        Trade savedTrade = tradeRepository.save(trade);
        tradeImageRepository.saveAll(images);

        return savedTrade;
    }

}
