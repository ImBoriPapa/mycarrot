package com.project.carrot.domain.profile.service;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.web.controller.profile.dto.ImageForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {

    private final MemberRepository memberRepository;

    /**
     * resources/static/profileImages
     * profile image 저장위치
     */
    private final String PROFILE_DIR = "${file.dir}";
    @Value(PROFILE_DIR)
    public String saveDir;


    public String getFullPath(String fileName) {
        return saveDir + fileName;
    }

    public ImageForm storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();

        String ext = getExt(originalFilename);
        String storeFileName = generateStoreName(ext);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new ImageForm(originalFilename, storeFileName);
    }

    private static String generateStoreName(String ext) {
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + ext;
        return storeFileName;
    }

    private static String getExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        return ext;
    }

    public Long updateProfile(Long id, MultipartFile multipartFile, String nickname) throws IOException {
        Optional<Member> findMember = memberRepository.findById(id);

        if (multipartFile.isEmpty()) {
            findMember.orElseThrow().updateNickname(nickname);
            return id;
        }

        ImageForm imageForm = storeFile(multipartFile);

        if (nickname == null) {
            findMember.orElseThrow(() -> new IllegalArgumentException("닉네임은 공백일수 없습니다.")).updateProfileImage(imageForm.getUploadName(), imageForm.getStoreName());
            return id;
        }

        findMember.orElseThrow(() -> new IllegalArgumentException("존재하는 회원이 없습니다.")).updateProfile(nickname,
                imageForm.getUploadName(),
                imageForm.getStoreName()
        );
        return id;
    }

    public void updateProfileImage(Long id, ImageForm form) {
        Optional<Member> findMember = memberRepository.findByMemberId(id);
        findMember.orElseThrow(() -> new IllegalArgumentException("존재하는 회원이 없습니다."))
                .updateProfileImage(form.getUploadName(), form.getStoreName());
    }

    public void updateNickname(Long id, String nickname) {
        Optional<Member> findMember = memberRepository.findByMemberId(id);
        findMember.orElseThrow(() -> new IllegalArgumentException("존재하는 회원이 없습니다."))
                .updateNickname(nickname);
    }
}
