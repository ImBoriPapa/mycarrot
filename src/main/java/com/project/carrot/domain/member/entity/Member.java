package com.project.carrot.domain.member.entity;

import com.project.carrot.domain.address.entity.Address;
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
    protected Member(String loginId, String password, String nickname, String email, String contact, String address) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.storedImageName = ImagePath.DEFAULT_PROFILE_IMAGE.PATH;
        this.contact = contact;
        this.memberRoll = MemberRoll.R0LL_USER;
        this.address = addressSaveProcessing(address);
        this.signUpDate = LocalDateTime.now();
    }

    /**
     * 첫번째 주소 변경
     */
    protected void updateFirstAddress(String firstAddress) {
        String[] update = new String[2];
        update[0] = firstAddress;
        update[1] = this.address.get(1).toString();
        this.address = addressSaveProcessing(update);
    }
    /**
     * 두번째 주소 변경
     */
    protected void updateSecondAddress(String secondAddress) {
        String[] update = new String[2];
        update[0] = this.address.get(0).toString();
        update[1] = secondAddress;
        this.address = addressSaveProcessing(update);
    }
    /**
     * 첫번째 두번째 주소 둘다 변경
     */
    protected void updateBothAddress(String firstAddress,String secondAddress){
        String[] update = new String[2];
        update[0] = firstAddress;
        update[1] = secondAddress;
        this.address = addressSaveProcessing(update);
    }
    /**
     * 첫번째 주소 삭제
     * 두번째 주소가 첫번째 주소로 저장
     */
    protected void deleteFirstAddress(){
        String[] update = new String[1];
        update[0] = this.address.get(1).toString();
        this.address = addressSaveProcessing(update);
    }
    /**
     * 두번째 주소 변경
     * 두번째 주소가 기본 second address 로 변경
     */
    protected void deleteSecondAddress(){
        String[] update = new String[1];
        update[0] = this.address.get(0).toString();
        this.address = addressSaveProcessing(update);
    }

    /**
     * 주소 설정 기능
     * 전체 주소 문자열을 setAddress()을 호출하여 주소를 세팅후 반환
     *
     * @param fullAddress
     * @return List<Address>
     */
    private List<Address> addressSaveProcessing(String... fullAddress) {

        String firstAddress = "";
        String secondAddress = "";

        if (fullAddress == null) {
            throw new NoAddressException("주소는 최소한 1개 이상 저장되어야 합니다");
        }

        if (fullAddress.length == 1) {
            firstAddress = fullAddress[0];
            List<Address> result = setAddress(firstAddress);
            return result;
        }

        if (fullAddress.length == 2) {
            firstAddress = fullAddress[0];
            secondAddress = fullAddress[1];
            List<Address> result = setAddress(firstAddress, secondAddress);
            return result;
        }

        throw new IllegalArgumentException("어떠한 이유로 주소를 저장하는대 실패하였습니다.");
    }

    /**
     * 주소 컨버팅 기능
     * 전체 주소 문자열을 city, district, town 으로 spilt 하여 반환
     */
    private String[] splitAddress(String fullAddress) {
        return fullAddress.split(" ", 3);
    }

    /**
     * 주소 세팅 기능
     * Address 가 없으면 NoAddressException
     * 주소가 1개일때 -> 2번째 주소에 city,district 에 "NOT SETTING VALUE" 저장 town 에 "두번째 주소를 설정할수 있습니다." 를 저장
     * 주소가 2개일때 -> 정상 저장
     * 주소가 3개 이거나 이상이면 ToManyAddressException
     *
     * @param address
     * @return
     */
    private List<Address> setAddress(String... address) {
        final String DEFAULT_ADDRESS_CITY = "NOT SETTING VALUE";
        final String DEFAULT_ADDRESS_DISTRICT = "NOT SETTING VALUE";
        final String DEFAULT_ADDRESS_TOWN = "두번째 주소를 설정할수 있습니다.";

        List<Address> result = new ArrayList<>();

        if (address.length == 0 || address.length <= 0) {
            throw new NoAddressException("주소는 최소한 1개 이상 저장되어야 합니다");
        }

        if (address.length == 1) {
            String[] first = splitAddress(address[0]);
            result.add(0, Address.CreateAddress()
                    .city(first[0])
                    .district(first[1])
                    .town(first[2])
                    .build());
            result.add(1, Address.CreateAddress()
                    .city(DEFAULT_ADDRESS_CITY)
                    .district(DEFAULT_ADDRESS_DISTRICT)
                    .town(DEFAULT_ADDRESS_TOWN)
                    .build());
        }

        if (address.length == 2) {
            String[] first = splitAddress(address[0]);
            String[] second = splitAddress(address[1]);

            result.add(0, Address.CreateAddress()
                    .city(first[0])
                    .district(first[1])
                    .town(first[2])
                    .build());

            result.add(1, Address.CreateAddress()
                    .city(second[0])
                    .district(second[1])
                    .town(second[2])
                    .build());

        }
        if (address.length > 2) {
            throw new ToManyAddressException("주소는 2개 이상 저장할 수 없습니다.");
        }
        return result;
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

}



