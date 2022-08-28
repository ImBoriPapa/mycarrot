package com.project.carrot.web.controller.trade;

import com.project.carrot.domain.member.CurrentMember;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.domain.trade.entity.Trade;
import com.project.carrot.domain.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TradeController {

    private final TradeRepository tradeRepository;
    private final MemberService memberService;

    @PostConstruct
    public void initData() {

        Long id = 1L;

        Member member = memberService.findMember(id);

        Trade trade1 = Trade.builder()
                .id(1L)
                .member(member)
                .title("맥북프로1")
                .price(2000000)
                .location("구래동")
                .context("새거에요")
                .offer(false)
                .share(false)
                .build();
        Trade trade2 = Trade.builder()
                .id(2L)
                .member(member)
                .title("맥북프로2")
                .price(2000000)
                .location("구래동")
                .context("새거에요")
                .offer(false)
                .share(false)
                .build();
        Trade trade3 = Trade.builder()
                .id(3L)
                .member(member)
                .title("맥북프로3")
                .price(2000000)
                .location("구래동")
                .context("새거에요")
                .offer(false)
                .share(false)
                .build();
        Trade trade4 = Trade.builder()
                .id(4L)
                .member(member)
                .title("맥북프로4")
                .price(2000000)
                .location("구래동")
                .context("새거에요")
                .offer(false)
                .share(false)
                .build();
        Trade save1 = tradeRepository.save(trade1);
        Trade save2 = tradeRepository.save(trade2);
        Trade save3 = tradeRepository.save(trade3);
        Trade save4 = tradeRepository.save(trade4);
    }

    @GetMapping("/trading-boards")
    public String tradingBoards(@CurrentMember Member member, Model model) {
        List<Trade> findAll = tradeRepository.findAll();

        model.addAttribute("boards", findAll);

        return "trade/trading-boards";
    }

    @GetMapping("/trading-board/{memberId}")
    public String tradingBoard(@PathVariable String memberId) {
        return "ok";
    }

}
