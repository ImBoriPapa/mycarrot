package com.project.carrot.domain.entity.locationItem.district;





public enum GangwonDoDistrict implements Districts {

    GANGWON_DO1("강원도1"),
    GANGWON_DO2("강원도2"),
    GANGWON_DO3("강원도3"),
    GANGWON_DO4("강원도4"),
    GANGWON_DO5("강원도5"),
    GANGWON_DO6("강원도6"),
    GANGWON_DO7("강원도7"),
    GANGWON_DO8("강원도8"),
    GANGWON_DO9("강원도9");


    private String title;


     GangwonDoDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
