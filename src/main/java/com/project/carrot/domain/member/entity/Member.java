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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "MEMBER")
@AllArgsConstructor
@EqualsAndHashCode(of = "memberId")
@Transactional
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//일대다 관계 주소를 1개 혹은 2개 까지 저장하고 수정이 가능
    @JoinColumn(name = "MEMBER_ID")
    private List<Address> address = new ArrayList(2);

    @Column(name = "SIGN_UP_DATE", nullable = false) //회원 등록일
    private LocalDateTime signUpDate;

    @Column(name = "MODIFY_DATE") //회원정보 수정일
    private LocalDateTime modifyDate;

    protected Member() {
    }

    /**
     * 최초 가입시
     * signUpDate 설정
     * memberRoll 설정
     * 주소 설정 -> 1개의 주소를 설정할 수 있습니다.  addressSaveProcessing()
     */
    public static Member createMember(Member member, String encodedPassword, ImagePath path, MemberRoll roll, String fullAddress) {

        Member createdMember = Member.builder()
                .loginId(member.getLoginId())
                .password(encodedPassword)
                .nickname(member.getNickname())
                .email(member.getEmail())
                .address(addressSaveProcessing(fullAddress))
                .storedImageName(path.PATH)
                .contact(member.getContact())
                .memberRoll(roll)
                .signUpDate(LocalDateTime.now())
                .build();

        return createdMember;
    }

    /**
     * 주소 설정 기능
     * 전체 주소 문자열을 splitAddress(),setAddress()을 호출하여 주소를 세팅후 반환
     *
     * @param fullAddress
     * @return List<Address>
     */
    public static List<Address> addressSaveProcessing(String fullAddress) {
        List<Address> addresses = splitAddress(fullAddress);
        List<Address> result = setAddress(addresses);
        return result;
    }

    /**
     * 주소 컨버팅 기능
     * 전체 주소 문자열을 city, district, town 으로 spilt 하여 반환
     * Address 가 null 이면 NoAddressException
     *
     * @param fullAddress
     * @return
     */
    protected static List<Address> splitAddress(String fullAddress) {

        if (fullAddress == null) {
            throw new NoAddressException("주소는 최소한 1개 이상 저장되어야 합니다");
        }

        List<Address> firstAddress = new ArrayList<>();
        String[] splitAddress = fullAddress.split(" ", 3);
        firstAddress.add(Address.builder().city(splitAddress[0]).district(splitAddress[1]).town(splitAddress[2]).build());
        return firstAddress;
    }

    /**
     * 주소 세팅 기능
     * Address 가 null 이면 NoAddressException
     * 주소가 1개일때 -> 2번째 주소에 city,district 에 "NOT SETTING VALUE" 저장 town 에 "두번째 주소를 설정할수 있습니다." 를 저장
     * 주소가 2개일때 -> 정상 저장
     * 주소가 3개 이거나 이상이면 ToManyAddressException
     * @param address
     * @return
     */
    protected static List<Address> setAddress(List<Address> address) {
        List<Address> result = new ArrayList<>();

        if (address.isEmpty()) {
            throw new NoAddressException("주소는 최소한 1개 이상 저장되어야 합니다");
        }

        if (address.size() == 1) {
            result.add(0, address.stream().findFirst().get());
            result.add(1, Address.builder()
                    .city("NOT SETTING VALUE")
                    .district("NOT SETTING VALUE")
                    .town("두번째 주소를 설정할수 있습니다.")
                    .build());
        }

        if (address.size() >= 3) {
            throw new ToManyAddressException("주소는 2개까지 설정할 수 있습니다.");
        }

        if (address.size() == 2) {
            result.add(address.get(0));
            result.add(address.get(1));
        }

        return result;
    }

    /**
     * 회원 정보 수정 기능
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
     * @param nickname
     */
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 프로필 이미지 변경 기능
     * @param upLoadImageName
     * @param storedImageName
     */
    public void updateProfileImage(String upLoadImageName, String storedImageName) {
        this.upLoadImageName = upLoadImageName;
        this.storedImageName = storedImageName;
    }

}



