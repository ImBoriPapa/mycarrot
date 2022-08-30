package com.project.carrot.domain.trade.entity;

import com.project.carrot.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Trade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRADE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String title;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "CATEGORY")
    private Category category;
    private int price;
    private boolean offer;
    private boolean share;
    private String location;
    private String context;
    private String itemImage;

    public Trade() {
    }

    public Trade(Trade trade) {
    }

    public static Trade createBoard(Member member,String title,Category category,int price,boolean offer,boolean share,String context) {
        Trade newTrade = Trade.builder()
                .member(member)
                .title(title)
                .category(category)
                .price(price)
                .offer(offer)
                .share(share)
                .context(context)
                .build();
        return newTrade;
    }

}
