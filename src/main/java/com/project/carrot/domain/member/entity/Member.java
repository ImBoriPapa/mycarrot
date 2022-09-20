package com.project.carrot.domain.member.entity;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.addressdata.entity.AddressData;
import com.project.carrot.domain.trade.entity.Trade;
import com.project.carrot.exception.member_exception.NoAddressException;
import com.project.carrot.exception.member_exception.ToManyAddressException;
import com.project.carrot.web.controller.member.ImagePath;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(name = "MEMBER")
@AllArgsConstructor
@EqualsAndHashCode(of = "memberId")
@Transactional
@Slf4j
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "LOGIN_ID", unique = true)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "NICKNAME", unique = true)
    private String nickname;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "UPLOAD_IMAGE_NAME")
    private String upLoadImageName;
    @Column(name = "STORED_IMAGE_NAME")
    private String storedImageName;
    @Column(name = "CONTACT", unique = true)
    private String contact;

    @Column(name = "ROLL")
    @Enumerated(value = EnumType.STRING)
    private MemberRoll memberRoll;

    @OneToMany(mappedBy = "member")
    private List<Trade> tradeList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //일대다 관계 주소를 1개 혹은 2개 까지 저장하고 수정이 가능
    @JoinColumn(name = "MEMBER_ID")
    private List<Address> address = new ArrayList();

    @Column(name = "SIGN_UP_DATE", nullable = false) //회원 등록일
    private LocalDateTime signUpDate;

    @Column(name = "MODIFY_DATE") //회원정보 수정일
    private LocalDateTime modifyDate;

    protected Member() {
    }

    /**
     * 최초 가입시 Member Entity 생성
     * storedImageName -> ImagePath.DEFAULT_PROFILE_IMAGE.PATH : 기본 프로필 이미지로 설정
     * signUpDate 설정
     * memberRoll -> MemberRoll.R0LL_USER : 최초 가입시 Authorization 정보는 USER
     * 주소 설정 -> 최초 회원 생성시에는 주소를 1개만 저장 addressSaveProcessing()
     */
    @Builder(builderMethodName = "CreateMember")
    protected Member(String loginId, String password, String nickname, String email, String contact, List<AddressData> address) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.storedImageName = ImagePath.DEFAULT_PROFILE_IMAGE.PATH;
        this.contact = contact;
        this.memberRoll = MemberRoll.R0LL_USER;
        this.address = setAddress(address);
        this.signUpDate = LocalDateTime.now();
    }



    /**
     * 첫번째 주소 변경
     */
    protected void updateFirstAddress(Address address) {
        this.address.set(0, address);
    }

    /**
     * 두번째 주소 변경
     */
    protected void updateSecondAddress(Address address) {
        this.address.set(1, address);
    }

    /**
     * 첫번째 두번째 주소 둘다 변경
     */
    protected void updateBothAddress(List<Address> address) {
        this.address.clear();
        this.address.addAll(address);
    }

    /**
     * 첫번째 주소 삭제
     * 두번째 주소가 첫번째 주소로 저장
     */
    protected void deleteFirstAddress() {
        this.address.remove(0);
    }

    /**
     * 두번째 주소 변경
     * 두번째 주소가 기본 second address 로 변경
     */
    protected void deleteSecondAddress() {
        this.address.remove(1);
    }

    /**
     * 주소 세팅 기능
     * Address 가 없으면 NoAddressException
     * 주소가 1개일때 -> 2번째 주소에 city,district 에 "NOT SETTING VALUE" 저장 town 에 "두번째 주소를 설정할수 있습니다." 를 저장
     * 주소가 2개일때 -> 정상 저장
     * 주소가 3개 이거나 이상이면 ToManyAddressException
     *
     * @param addressData
     * @return
     */
    private List<Address> setAddress(List<AddressData> addressData) {
        final String DEFAULT_ADDRESS_CITY = "NOT SETTING CITY";
        final String DEFAULT_ADDRESS_DISTRICT = "NOT SETTING DISTRICT";
        final String DEFAULT_ADDRESS_TOWN = "두번째 주소를 설정할수 있습니다.";

        if (addressData.isEmpty()) {
            throw new NoAddressException("주소는 최소한 1개 이상 저장되어야 합니다");
        }

        if (addressData.size() == 1) {
            List<Address> result = addressData.stream().map(a -> new Address(a)).collect(Collectors.toList());
            result.add(1, Address.CreateAddress()
                    .city(DEFAULT_ADDRESS_CITY)
                    .district(DEFAULT_ADDRESS_DISTRICT)
                    .town(DEFAULT_ADDRESS_TOWN)
                    .build());
            return result;
        }

        if (addressData.size() == 2) {
            List<Address> result = addressData.stream().map(a -> new Address(a)).collect(Collectors.toList());
            return result;
        }

        if (addressData.size() > 2) {
            throw new ToManyAddressException("주소는 2개 이상 저장할 수 없습니다.");
        }

        return null;
    }

    /**
     * 회원 정보 수정 기능
     *
     * @param member
     * @return
     */
    public Member modifiedMember(Member member) {
        this.contact = member.getContact();
        this.email = member.getEmail();
        this.address = member.getAddress();
        return member;
    }

    /**
     * 프로필 수정 기능
     *
     * @param nickname
     * @param upLoadImageName
     * @param storedImageName
     */
    public void updateProfile(String nickname, String upLoadImageName, String storedImageName) {
        this.nickname = nickname;
        this.upLoadImageName = upLoadImageName;
        this.storedImageName = storedImageName;
    }

    /**
     * 닉네임 변경 기능
     *
     * @param nickname
     */
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 프로필 이미지 변경 기능
     *
     * @param upLoadImageName
     * @param storedImageName
     */
    public void updateProfileImage(String upLoadImageName, String storedImageName) {
        this.upLoadImageName = upLoadImageName;
        this.storedImageName = storedImageName;
    }

    /**
     * 회원 인가 정보
     */
    public List<String> getRoll() {
        return List.of(memberRoll.getRollValue());
    }
}



