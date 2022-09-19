package com.project.carrot.domain.trade.entity;

import com.project.carrot.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "trade")
    private List<TradeImage> itemImages;

    @Builder(builderMethodName = "createTrade")
    protected Trade(Member member, String title, Category category, int price, boolean offer, boolean share, String location, String context, List<TradeImage> itemImage) {

        this.member = member;
        this.title = title;
        this.category = category;
        this.price = price;
        this.offer = offer;
        this.share = share;
        this.location = location;
        this.context = context;
        this.itemImages = itemImage;
    }
}
