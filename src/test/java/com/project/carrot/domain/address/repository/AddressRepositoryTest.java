package com.project.carrot.domain.address.repository;

import com.project.carrot.domain.address.entity.Address;
import lombok.extern.slf4j.Slf4j;
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
        List<Address> all = addressRepository.findAll();
        all.stream().forEach(System.out::println);
        //when

        //then

    }


}