package com.project.carrot.domain.category.locationItem.district;





public enum GyeongsangNamDoDistrict implements Districts {
    GYEONGSANGBUK_DO1("경상남도1"),
    GYEONGSANGBUK_DO2("경상남도2"),
    GYEONGSANGBUK_DO3("경상남도3"),
    GYEONGSANGBUK_DO4("경상남도4"),
    GYEONGSANGBUK_DO5("경상남도5");



    private String title;


     GyeongsangNamDoDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
