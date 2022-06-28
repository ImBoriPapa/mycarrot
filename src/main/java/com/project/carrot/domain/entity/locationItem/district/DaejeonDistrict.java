package com.project.carrot.domain.entity.locationItem.district;





public enum DaejeonDistrict implements Districts {

    DAEJEON1("대전1"),
    DAEJEON2("대전2"),
    DAEJEON3("대전3"),
    DAEJEON4("대전4"),
    DAEJEON5("대전5"),
    DAEJEON6("대전6"),
    DAEJEON7("대전7"),
    DAEJEON8("대전8"),
    DAEJEON9("대전9");




    private String title;


     DaejeonDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
