package com.project.carrot.utlis.converter;

import com.project.carrot.domain.trade.entity.ItemCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
class ItemCategoryConverterTest {

    @Test
    @DisplayName("카테고리 컨버팅 테스트")
    void convertToCategory() throws Exception{
        //given
        String code ="101";
        //when
        ItemCategoryConverter itemCategoryConverter = new ItemCategoryConverter();
        ItemCategory result = itemCategoryConverter.convert(code);
        //then
        log.info("result={}",result);

    }

}