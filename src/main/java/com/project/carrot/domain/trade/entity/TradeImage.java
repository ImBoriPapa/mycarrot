package com.project.carrot.domain.trade.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TradeImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRADE_ID")
    private Trade trade;

    @Column(name="UPLOAD_IMAGE_NAME")
    private String upLoadImageName;
    @Column(name="STORED_IMAGE_NAME")
    private String storedImageName;

    @Builder(builderMethodName = "createTradeImage")
    public TradeImage(Trade trade, String upLoadImageName, String storedImageName) {
        this.trade = trade;
        this.upLoadImageName = upLoadImageName;
        this.storedImageName = storedImageName;
    }
}
