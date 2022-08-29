package com.project.carrot.domain.trade.repository;

import com.project.carrot.domain.trade.entity.Trade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade,Long> {

    Page<Trade> findAll(Pageable pageable);

}
