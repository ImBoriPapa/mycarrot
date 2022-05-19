package com.project.carrot.service;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.entity.Member;

import java.util.List;

public interface MemberService {



    void join(MemberDTO dto);

    void login(MemberDTO dto);

}
