package com.project.carrot.domain.entity.locationItem.district;





public enum DaeguDistrict implements Districts {
    DAEGU1("대구1"),
    DAEGU2("대구2"),
    DAEGU3("대구3"),
    DAEGU4("대구4"),
    DAEGU5("대구5"),
    DAEGU6("대구6"),
    DAEGU7("대구7"),
    DAEGU8("대구8"),
    DAEGU9("대구9");



    private String title;


     DaeguDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
