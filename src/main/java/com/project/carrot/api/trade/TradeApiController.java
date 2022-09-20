package com.project.carrot.api.trade;

import com.project.carrot.api.trade.form.RequestCreateTradeForm;
import com.project.carrot.domain.trade.dto.TradeCreateDto;
import com.project.carrot.domain.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/trade")
public class TradeApiController {

    private final TradeService tradeService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public void createTrade(@RequestPart RequestCreateTradeForm form,
                            @RequestPart List<MultipartFile> images
    ) throws IOException {
        log.info("form={}",form.getTitle());
        log.info("image={}",images.get(0).getOriginalFilename());

        TradeCreateDto dto = TradeCreateDto.builder()
                .memberId(1L)
                .title(form.getTitle())
//                .category(ItemCategory.convert(form.getCategory()))
                .price(1000000)
                .offer(false)
                .share(false)
                .location("ddd")
                .context("100000만원은 엄청싸다 싸")
                .tradeImages(images)
                .build();

        tradeService.createTradeProcessing(dto);

    }

}
