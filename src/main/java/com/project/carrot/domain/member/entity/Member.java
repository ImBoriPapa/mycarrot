package com.project.carrot.domain.member.entity;

import com.project.carrot.domain.address.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Transactional
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "UPLOAD_IMAGE_NAME")
    private String upLoadImageName;

    @Column(name = "STORED_IMAGE_NAME")
    private String storedImageName;
    @Column(name ="CONTACT")
    private String contact;

    @Column(name = "ROLL")
    @Enumerated(value = EnumType.STRING)
    private MemberRoll memberRoll;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//일대다 관계 주소를 1개 혹은 2개를 저장하고 수정이 가능
    @JoinColumn(name = "MEMBER_ID")
    private List<Address> address = new ArrayList();

    @Column(name = "SIGN_UP_DATE") //회원 등록일
    private LocalDateTime signUpDate;

    @Column(name = "MODIFY_DATE") //회원정보 수정일
    private LocalDateTime modifyDate;

    protected Member() {
    }

    /**
     * 최초 가입시
     * signUpDate 설정
     * memberRoll - USER
     * Address 는 하나의 주소만 저장 2번째 주소는 수정에서 추가
     */

    public static Member createMember(String loginId,String password,String nickname,String email ,String contact,List<Address> address) {
        Address secondAddress = new Address("no","no","2번째 동네를 설정할수 있습니다.");
        List<Address> saveAddress = new ArrayList<>();

        final String DEFAULT_PROFILE_IMAGE = "default.jpeg";

        saveAddress.add(address.get(0));
        saveAddress.add(secondAddress);

        Member member = Member.builder()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .address(saveAddress)
                .storedImageName(DEFAULT_PROFILE_IMAGE)
                .contact(contact)
                .memberRoll(MemberRoll.USER)
                .signUpDate(LocalDateTime.now())
                .build();

        return member;
    }

    public Member modifiedMember(Member member) {
        this.contact = member.getContact();
        this.email = member.getEmail();
        this.address = member.getAddress();
        return member;
    }

    public void updateProfile(String nickname, String upLoadImageName, String storedImageName) {
        this.nickname = nickname;
        this.upLoadImageName = upLoadImageName;
        this.storedImageName = storedImageName;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileImage(String upLoadImageName, String storedImageName) {
        this.upLoadImageName = upLoadImageName;
        this.storedImageName = storedImageName;
    }

}



