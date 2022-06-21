package com.project.carrot.entity.locationItem.district;





public enum District {

    JONGNO("종로"),
    JUNG("종로"),
    YONGSAN("종로"),
    SEONGDONG("종로"),
    GWANGJIN("종로"),
    DONGDAEMUN("종로"),
    JUNGNANG("종로"),
    SENGBUK("종로"),
    DOBONG("종로"),
    NOWON("종로"),
    EUNPYEONG("종로"),
    SEODAEMUN("종로"),
    MAPO("종로"),
    YANGCHEON("종로"),
    GANGSEO("종로"),
    GURO("종로"),
    GUEMCHEON("종로"),
    YENONDEUNGPO("종로"),
    DONGJAK("종로"),
    GWANAK("종로"),
    SEOCHO("종로"),
    GANGNAM("종로"),
    SONGPA("종로"),
    GANGDONG("종로");


    private String title;


     District(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
