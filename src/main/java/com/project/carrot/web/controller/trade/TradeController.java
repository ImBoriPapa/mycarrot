package com.project.carrot.web.controller.trade;

import com.project.carrot.domain.member.CurrentMember;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.domain.trade.entity.Trade;
import com.project.carrot.domain.trade.repository.TradeRepository;
import com.project.carrot.domain.trade.service.TradeService;
import com.project.carrot.web.controller.trade.dto.TradeBoardForm;
import com.project.carrot.web.controller.trade.dto.TradeFormList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TradeController {
    private final TradeRepository tradeRepository;
    private final TradeService tradeService;
    private final MemberService memberService;

    @PostConstruct
    public void initData() {

        Long id = 1L;

        Member member = memberService.findMember(id);
        List<Trade> values = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

            Trade value = Trade.builder()
                    .member(member)
                    .title("맥북프로"+i)
                    .price(2000000)
                    .location("서울시 강서구 화곡동"+i)
                    .context("새거에요")
                    .offer(false)
                    .share(false)
                    .build();
            values.add(value);
        }
        tradeRepository.saveAll(values);
    }

    @GetMapping("/trading-boards")
    public String tradingBoards(@CurrentMember Member member, Model model, Pageable pageable) {

        if (member == null) {
            return "/";
        }

        TradeFormList boards = tradeService.findTradeBoards(pageable);

        model.addAttribute("boards", boards);

        return "trade/trading-boards";
    }


    @GetMapping("/trading-board/{memberId}")
    public String tradingBoard(@PathVariable Long memberId,@CurrentMember Member member,Model model) {
        Trade tradeBoard = tradeService.findTradeBoard(memberId);

        TradeBoardForm tradeBoardForm = TradeBoardForm.builder()
                .itemImage(tradeBoard.getItemImage())
                .profileImage(tradeBoard.getMember().getStoredImageName())
                .nickname(tradeBoard.getMember().getNickname())
                .build();

        return "ok";
    }

    @GetMapping("/trading-board/create/{memberId}")
    public String createBoard(@PathVariable Long memberId){

        return "ok";
    }


}

