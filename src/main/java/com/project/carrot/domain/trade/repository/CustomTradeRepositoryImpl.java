package com.project.carrot.domain.trade.repository;

import com.project.carrot.domain.trade.dto.TradeTitleDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomTradeRepositoryImpl implements CustomTradeRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TradeTitleDto> findAllTradeTitle(int offset) {
        return em.createQuery("SELECT new com.project.carrot.domain.trade.dto.TradeTitleDto(t.id,t.title,t.context,t.price) FROM Trade t",TradeTitleDto.class)
                .setFirstResult(offset)
                .setMaxResults(19)
                .getResultList();
    }
}
