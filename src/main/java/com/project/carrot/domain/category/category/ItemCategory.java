package com.project.carrot.domain.category.category;

public enum ItemCategory {

    POPULAR_ITEM("인기매물"),
    USED_CAR("중고차"),
    DIGITAL_DEVICE("디저털기기"),
    HOME_APPLIANCES("생활가전"),
    FURNITURE("가구"),
    CHILDREN_BOOK("유아도서"),
    INFANT_CHILD("유아동"),
    LIFE("생활"),
    SPORTS("스포츠"),
    WOMEN_ACCESSORIES("여성잡화"),
    WOMEN_CLOTHING("여성의류"),
    MAN_FASHION("남성패션"),
    GAME("게임"),
    BEAUTY("뷰티"),
    PET("반려동물"),
    BOOKS("도서"),
    PLANT("식물"),
    ETC("기타"),
    BUY("삽니다");

    private String displayName;

    ItemCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
