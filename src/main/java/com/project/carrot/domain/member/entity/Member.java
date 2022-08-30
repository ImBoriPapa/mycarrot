package com.project.carrot.domain.member.entity;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.trade.entity.Trade;
import com.project.carrot.web.controller.member.ImagePath;
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

    @Column(name = "LOGIN_ID",unique = true)
    private String loginId;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "NICKNAME",unique = true)
    private String nickname;

    @Column(name = "EMAIL",unique = true)
    private String email;

    @Column(name = "UPLOAD_IMAGE_NAME")
    private String upLoadImageName;

    @Column(name = "STORED_IMAGE_NAME")
    private String storedImageName;
    @Column(name ="CONTACT",unique = true)
    private String contact;

    @Column(name = "ROLL")
    @Enumerated(value = EnumType.STRING)
    private MemberRoll memberRoll;

    @OneToMany(mappedBy ="member")
    private List<Trade> tradeList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//일대다 관계 주소를 1개 혹은 2개를 저장하고 수정이 가능
    @JoinColumn(name = "MEMBER_ID")
    private List<Address> address = new ArrayList();

    @Column(name = "SIGN_UP_DATE",nullable = false) //회원 등록일
    private LocalDateTime signUpDate;

    @Column(name = "MODIFY_DATE") //회원정보 수정일
    private LocalDateTime modifyDate;

    protected Member() {
    }

    /**
     * 최초 가입시
     * signUpDate 설정
     * memberRoll 설정
     * 주소 설정
     */
    public static Member createMember(Member member, String encodedPassword,ImagePath path,MemberRoll roll) {

        Member createdMember = Member.builder()
                .loginId(member.getLoginId())
                .password(encodedPassword)
                .nickname(member.getNickname())
                .email(member.getEmail())
                .address(setAddress(member.getAddress()))
                .storedImageName(path.PATH)
                .contact(member.getContact())
                .memberRoll(roll)
                .signUpDate(LocalDateTime.now())
                .build();

        return createdMember;
    }

    protected static List<Address> setAddress(List<Address> address) {
        List<Address> result = new ArrayList<>();

        if(address.isEmpty()){
            throw new IllegalArgumentException("주소는 최소한 1개 이상 저장되어야 합니다.");
        }

        if(address.size() ==1){
            result.add(0,address.stream().findFirst().get());
            result.add(1,new Address("no","no","2번째 동네를 설정할수 있습니다."));
        }
        if(address.size() ==2){
            result.add(address.get(0));
            result.add(address.get(1));
        }
        return result;
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



