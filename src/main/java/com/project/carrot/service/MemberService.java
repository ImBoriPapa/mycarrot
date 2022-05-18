package com.project.carrot.service;

import com.project.carrot.dto.MemberDTO;

public interface MemberService {



    void join(MemberDTO dto);

    MemberDTO login(MemberDTO dto);

}
