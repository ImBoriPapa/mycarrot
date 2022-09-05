package com.project.carrot.api.addressdata.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.carrot.domain.addressdata.entity.AddressData;
import com.project.carrot.domain.addressdata.repository.AddressDataRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AddressDataApiController {

    private final AddressDataRepository addressDataRepository;
    @GetMapping("/api/address_data/open-pop")
    public ModelAndView open(){
        ModelAndView mav = new ModelAndView("address/search_address_pop");
        return mav;
    }

    @GetMapping("/api/address_data/{town}")
    public ResponseEntity<AddressDataResponseForm> address(@PathVariable String town) throws JsonProcessingException {
        AddressDataResponseForm success = new AddressDataResponseForm();
        log.info("town ={}",town);
        List<AddressData> addressList = addressDataRepository.findAll();
        List<AddressData> findByTown = addressDataRepository.findByTown(town);
        log.info("findByTown={}",findByTown.stream().count());

        if (!findByTown.isEmpty()) {
            success = AddressDataResponseForm.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("주소 조회 성공")
                    .addressList(new ArrayList<>(findByTown))
                    .addressToString(findByTown.stream().map(AddressData::toString).collect(Collectors.toList()))
                    .count(findByTown.size()).build();


        } else {
            success = AddressDataResponseForm.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message("주소를 조회할 수 없습니다.")
                    .addressList(Collections.emptyList())
                    .count(0).build();
        }
        return new ResponseEntity<>(success, success.getHttpStatus());
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddressDataResponseForm {
        private Integer code;
        private HttpStatus httpStatus;
        private String message;
        private String name;
        private Integer count;
        private List<Object> addressToString = new ArrayList<>();
        private List<Object> addressList = new ArrayList<>();

    }
}
