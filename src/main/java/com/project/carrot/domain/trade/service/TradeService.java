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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Trade createBoard(TradeCreateDto dto) throws IOException {

        Member findMember = memberRepository.findByMemberId(dto.getMemberId()).orElseThrow(
                () -> new NoExistMemberException(ErrorCode.NO_EXIST_MEMBER));

        Trade newBoard = Trade.createTrade()
                .member(findMember)
                .title(dto.getTitle())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .offer(dto.isOffer())
                .share(dto.isShare())
                .location(dto.getLocation())
                .context(dto.getContext())
                .build();

        List<TradeImage> images = new ArrayList<>();
        List<MultipartFile> tradeImages = dto.getTradeImages();
        for (MultipartFile t : tradeImages) {
            String originalFilename = t.getOriginalFilename();
            log.info("original name={}", originalFilename);

            //get ext
            int pos = originalFilename.lastIndexOf(".");
            String ext = originalFilename.substring(pos + 1);

            //generateStoreName(ext)
            String uuid = UUID.randomUUID().toString();
            String storedFileName = uuid + "." + ext;
            //getFullPath(storeFileName)
            String fullPath = uploadDir + storedFileName;

            t.transferTo(new File(fullPath));
            TradeImage image = TradeImage.builder()
                    .upLoadImageName(originalFilename)
                    .storedImageName(storedFileName)
                    .trade(newBoard)
                    .build();
            images.add(image);
        }


        newBoard.addImage(images);


        Trade savedTrade = tradeRepository.save(newBoard);
        tradeImageRepository.saveAll(images);

        return savedTrade;
    }
}
