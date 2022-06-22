package com.project.carrot.entity.locationItem.district;





public enum IncheonDistrict implements Districts {

    INCHEON1("인천1"),
    INCHEON2("인천2"),
    INCHEON3("인천3"),
    INCHEON4("인천4"),
    INCHEON5("인천5");



    private String title;


     IncheonDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
