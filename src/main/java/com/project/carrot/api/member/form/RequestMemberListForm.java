package com.project.carrot.api.member.form;

import com.project.carrot.api.member.form.response.MemberList;
import com.project.carrot.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestMemberListForm  {

    private int totalPage;
    private boolean hasPrevious;
    private boolean hasNext;
    private int currentPageNumber;
    private int nextPageNumber;
    private int previousPageNumber;
    private List<MemberList> memberList = new ArrayList<>();
    @Builder
    public RequestMemberListForm(Page<Member> page) {
        this.totalPage = page.getTotalPages();
        this.hasPrevious = page.hasPrevious();
        this.hasNext = page.hasNext();
        this.currentPageNumber = page.getNumber();
        this.nextPageNumber = page.getNumber() + 1;
        this.previousPageNumber = page.getNumber() - 1;
        this.memberList = page.getContent().stream().map(MemberList::new).collect(Collectors.toList());
    }


}
