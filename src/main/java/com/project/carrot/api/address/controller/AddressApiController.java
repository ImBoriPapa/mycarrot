package com.project.carrot.api.address.controller;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.address.repository.AddressRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AddressApiController {

    private final AddressRepository addressRepository;

    @GetMapping("/api/address")
    public ResponseEntity<AddressResponseForm> address() {
        AddressResponseForm success = new AddressResponseForm();

        List<Address> addressList = addressRepository.findAll();

        if (!addressList.isEmpty()) {
            success = AddressResponseForm.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("주소 조회 성공")
                    .addressList(new ArrayList<>(addressList))
                    .addressToString(addressList.stream().map(Address::toString).collect(Collectors.toList()))
                    .count(addressList.size()).build();


        } else {
            success = AddressResponseForm.builder()
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
    public static class AddressResponseForm {
        private Integer code;
        private HttpStatus httpStatus;
        private String message;
        private String name;
        private Integer count;
        private List<Object> addressToString = new ArrayList<>();
        private List<Object> addressList = new ArrayList<>();

    }
}
