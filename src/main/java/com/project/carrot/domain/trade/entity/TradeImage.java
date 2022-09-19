package com.project.carrot.domain.trade.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TradeImage {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TRADE_ID")
    private Trade trade;

    private String upLoadImgName;
    private String storedImgName;
}
