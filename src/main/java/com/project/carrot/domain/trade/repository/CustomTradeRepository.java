package com.project.carrot.domain.trade.repository;

import com.project.carrot.domain.trade.dto.TradeTitleDto;

import java.util.List;

public interface CustomTradeRepository {

    List<TradeTitleDto> findAllTradeTitle(int offset);
}
