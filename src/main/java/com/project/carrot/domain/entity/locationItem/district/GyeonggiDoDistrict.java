package com.project.carrot.domain.entity.locationItem.district;





public enum GyeonggiDoDistrict implements Districts {

    GYEONGGI_DO1("경기도1"),
    GYEONGGI_DO2("경기도2"),
    GYEONGGI_DO3("경기도3"),
    GYEONGGI_DO4("경기도4"),
    GYEONGGI_DO5("경기도5"),
    GYEONGGI_DO6("경기도6"),
    GYEONGGI_DO7("경기도7"),
    GYEONGGI_DO8("경기도8"),
    GYEONGGI_DO9("경기도9");



    private String title;


     GyeonggiDoDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
