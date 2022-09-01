package com.project.carrot.domain.address.repository;

import com.project.carrot.domain.addressdata.entity.AddressData;
import com.project.carrot.domain.addressdata.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Slf4j
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Test
    void show_all_address() throws Exception{
        //given
        List<AddressData> all = addressRepository.findAll();
        all.stream().forEach(System.out::println);
        //when

        //then

    }
    
    @Test
    @DisplayName("주소 검색 테스트")
     void search() throws Exception{
        //given
        String town = "신사동";

        //when
        List<AddressData> findByTown = addressRepository.findByTown(town);
        //then
        if(findByTown.isEmpty()){
            log.info("검색 값이 없습니다.");
        }
        for (AddressData address : findByTown) {
            System.out.println("address.toString() = " + address.toString());
        }
    
    }


}