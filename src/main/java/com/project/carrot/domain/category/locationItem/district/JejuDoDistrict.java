package com.project.carrot.domain.category.locationItem.district;





public enum JejuDoDistrict implements Districts {
    JEJU_DO1("제주도1"),
    JEJU_DO2("제주도2"),
    JEJU_DO3("제주도3"),
    JEJU_DO4("제주도4"),
    JEJU_DO5("제주도5");



    private String title;


     JejuDoDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
