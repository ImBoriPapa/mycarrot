package com.project.carrot.web.controller.trade;

import com.project.carrot.domain.member.sessionLoginInformation.CurrentMember;
import com.project.carrot.domain.member.entity.Member;
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

@Controller
@Slf4j
@RequiredArgsConstructor
public class TradeController {
    private final TradeRepository tradeRepository;
    private final TradeService tradeService;


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

