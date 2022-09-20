package com.project.carrot.domain.trade.dto;

import com.project.carrot.domain.trade.entity.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeCreateDto {

    private Long memberId;
    private String title;
    private ItemCategory category;
    private List<MultipartFile> tradeImages;
    private int price;
    private boolean offer;
    private boolean share;
    private String location;
    private String context;

}
