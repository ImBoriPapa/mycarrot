package com.project.carrot.domain.member.entity;

/**
 * 사용자의 등급
 */
public enum MemberRoll {
    R0LL_USER("ROLL_USER"),
    ROLL_ADMIN("ROLL_ADMIN");

    private String rollValue;

    MemberRoll(String rollValue) {
        this.rollValue = rollValue;
    }

    public String getRollValue(){
        return rollValue;
    }
}
