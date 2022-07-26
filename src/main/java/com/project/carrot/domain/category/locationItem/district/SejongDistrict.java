package com.project.carrot.domain.category.locationItem.district;





public enum SejongDistrict implements Districts {

    SEJONG1("세종1"),
    SEJONG2("세종2"),
    SEJONG3("세종3"),
    SEJONG4("세종4"),
    SEJONG5("세종5");



    private String title;


     SejongDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
