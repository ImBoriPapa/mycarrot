package com.project.carrot.entity.locationItem.district;





public enum SeoulDistrict implements Districts {

    JONGNO("종로"),
    JUNG("정동"),
    YONGSAN("용산"),
    SEONGDONG("성동"),
    GWANGJIN("광진"),
    DONGDAEMUN("동대문"),
    JUNGNANG("정남"),
    SENGBUK("성북"),
    DOBONG("도봉"),
    NOWON("노원"),
    EUNPYEONG("은평"),
    SEODAEMUN("서대문"),
    MAPO("마포"),
    YANGCHEON("양천"),
    GANGSEO("강서"),
    GURO("구로"),
    GUEMCHEON("금천"),
    YENONDEUNGPO("영등포"),
    DONGJAK("동작"),
    GWANAK("관악"),
    SEOCHO("서초"),
    GANGNAM("강남"),
    SONGPA("송파"),
    GANGDONG("강동");


    private String title;


     SeoulDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
