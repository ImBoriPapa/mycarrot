package com.project.carrot.domain.trade.repository;

import com.project.carrot.domain.trade.dto.TradeTitleDto;
import com.project.carrot.domain.trade.entity.Trade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade,Long> ,CustomTradeRepository{

    Page<Trade> findAll(Pageable pageable);

    @Override
    List<TradeTitleDto> findAllTradeTitle(int offset);
}
