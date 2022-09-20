package com.project.carrot.utlis.converter;

import com.project.carrot.domain.trade.entity.ItemCategory;
import org.springframework.core.convert.converter.Converter;


public class ItemCategoryConverter implements Converter<String, ItemCategory> {

    @Override
    public ItemCategory convert(String code) {
        return ItemCategory.convert(code);
    }
}
