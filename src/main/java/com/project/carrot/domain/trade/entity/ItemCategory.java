package com.project.carrot.domain.trade.entity;

import java.util.Arrays;

public enum ItemCategory {
    NO_SELECT("99", "전체 매물"),
    POPULAR_ITEM("100", "인기매물"),
    USED_CAR("101", "중고차"),
    DIGITAL_DEVICE("102", "디저털기기"),
    HOME_APPLIANCES("103", "생활가전"),
    FURNITURE("104", "가구"),
    CHILDREN_BOOK("106", "유아도서"),
    INFANT_CHILD("107", "유아동"),
    LIFE("108", "생활"),
    SPORTS("109", "스포츠"),
    WOMEN_ACCESSORIES("110", "여성잡화"),
    WOMEN_CLOTHING("111", "여성의류"),
    MAN_FASHION("112", "남성패션"),
    GAME("113", "게임"),
    BEAUTY("114", "뷰티"),
    PET("115", "반려동물"),
    BOOKS("116", "도서"),
    PLANT("117", "식물"),
    ETC("118", "기타"),
    BUY("119", "삽니다");

    private String categoryCode;
    private String displayName;

    ItemCategory(String categoryCode, String displayName) {
        this.categoryCode = categoryCode;
        this.displayName = displayName;
    }

    public static ItemCategory convert(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Enum Convertor No Accept Null");
        }

        return Arrays.stream(ItemCategory.values())
                .filter(c -> c.categoryCode.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치 하는 카테고리 코드가 없습니다."));
    }

    public String getDisplayName() {
        return displayName;
    }
}
