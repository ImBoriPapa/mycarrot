package com.project.carrot.domain.member.entity;

import com.project.carrot.domain.address.repository.AddressRepository;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
class MemberTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AddressRepository addressRepository;

    @BeforeEach
    void beforeEach() {
        String loginId = "tester1";
        String password = "not-password1";
        String nickname = "nickname1";
        String email = "tester1@tester.com";
        String contact = "010-1111-1111";
        String address = "서울시 종로구 1번지";
        Member member1 = Member.CreateMember()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .contact(contact)
                .address(address)
                .build();
        Member savedMember1 = memberRepository.save(member1);
    }

    @AfterEach()
    void AfterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("CreateMemberBuilder TEST")
    void createMemberBuilder() throws Exception {
        //given
        String loginId = "tester2";
        String password = "not-password2";
        String nickname = "nickname2";
        String email = "tester2@tester.com";
        String contact = "010-2222-2222";
        String address = "서울시 종로구 2번지";
        Member member2 = Member.CreateMember()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .contact(contact)
                .address(address)
                .build();
        Member savedMember2 = memberRepository.save(member2);
        //when
        Optional<Member> byMemberId = memberRepository.findByMemberId(savedMember2.getMemberId());
        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
        //then
        Assertions.assertThat(byMemberId.get().getClass()).isEqualTo(byLoginId.get().getClass());

        System.out.println("byMemberId member id = " + byMemberId.get().getMemberId());
        System.out.println("byMemberId login id= " + byMemberId.get().getLoginId());
        System.out.println("byMemberId password= " + byMemberId.get().getPassword());
        System.out.println("byMemberId nickname= " + byMemberId.get().getNickname());
        System.out.println("byMemberId email= " + byMemberId.get().getEmail());
        System.out.println("byMemberId member roll= " + byMemberId.get().getMemberRoll());
        System.out.println("byMemberId address= " + byMemberId.get().getAddress().get(0).toString());
        System.out.println("byMemberId stored image= " + byMemberId.get().getStoredImageName());
        System.out.println("byMemberId sing up date= " + byMemberId.get().getSignUpDate());

    }

    @Test
    @DisplayName("주소 출력 테스트")
    void insert_address() throws Exception {
        //given
        Optional<Member> tester1 = memberRepository.findByLoginId("tester1");
        //when

        //then
        tester1.get().getAddress().forEach((address) -> System.out.println(address.toString()));

    }

    @Test
    @DisplayName("주소 저장 테스트")
    void add_address() throws Exception {
        //given
        String loginId = "tester2";
        String password = "not-password2";
        String nickname = "nickname2";
        String email = "tester2@tester.com";
        String contact = "010-2222-2222";
        String address1 = "서울시 종로구 2번지";
        String address2 = "서울시 종로구 3번지";
        log.info("------------------------------------------------------------------------");
        Member member2 = Member.CreateMember()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .contact(contact)
                .address(address1)
                .build();
        Member savedMember2 = memberRepository.save(member2);
        log.info("------------------------------------------------------------------------");
        //when
        Optional<Member> findMember = memberRepository.findByMemberId(savedMember2.getMemberId());
        //then
        findMember.get().getAddress().forEach((address) -> System.out.println(address.toString()));
    }

    @Test
    @DisplayName("주소 추가 테스트")
    void addAddress() throws Exception{
        //given
        Optional<Member> findMember = memberRepository.findByLoginId("tester1");
        String firstAddress = "서울시 영등포구 여의도동";
        String secondAddress = "서울시 강서구 우장산동";

        String firstAddress2 = "서울시 동작구 동작동";
        String secondAddress2 = "서울시 강서구 발산동";

        //when
        findMember.get().updateSecondAddress(secondAddress);
        findMember.get().getAddress().forEach((address) -> System.out.println(address.toString()));

        findMember.get().updateFirstAddress(firstAddress);
        findMember.get().getAddress().forEach((address) -> System.out.println(address.toString()));

        findMember.get().updateBothAddress(firstAddress2,secondAddress2);
        findMember.get().getAddress().forEach((address) -> System.out.println(address.toString()));

        findMember.get().deleteFirstAddress();
        findMember.get().getAddress().forEach((address) -> System.out.println(address.toString()));

        findMember.get().deleteSecondAddress();
        findMember.get().getAddress().forEach((address) -> System.out.println(address.toString()));

        //then

    }

}