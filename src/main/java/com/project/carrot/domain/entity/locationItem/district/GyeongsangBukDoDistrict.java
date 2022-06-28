package com.project.carrot.domain.entity.locationItem.district;





public enum GyeongsangBukDoDistrict implements Districts {

    GYEONGSANGBUK_DO1("경상북도1"),
    GYEONGSANGBUK_DO2("경상북도2"),
    GYEONGSANGBUK_DO3("경상북도3"),
    GYEONGSANGBUK_DO4("경상북도4"),
    GYEONGSANGBUK_DO5("경상북도5");



    private String title;


     GyeongsangBukDoDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
