package com.project.carrot.entity.locationItem;

public enum City {

    SEOUL("서울"),
    BUSAN("부산"),
    DAEGU("대구"),
    GWANGJU("광주"),
    INCHEON("인천"),
    DAEJEON("대전"),
    ULSAN("울산"),
    SEJONG("세종"),
    GYEONGGI_DO("경기도"),
    GANGWON_DO("강원도"),
    CHUNGCHEONGNAM_DO("충청북도"),
    CHUNGCHEONGBUK_DO("충청남도"),
    JEOLLANAM_DO("전라남도"),
    JEOLLBUK_DO("전라북도"),
    GYEONGSANGNAM_DO("경상남도"),
    GYEONGSANGBUK_DO("경상북도"),
    JEJU_DO("제주도");

    private final String explain;

    City(String explain) {
        this.explain = explain;
    }
}
