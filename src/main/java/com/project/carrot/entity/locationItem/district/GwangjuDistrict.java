package com.project.carrot.entity.locationItem.district;





public enum GwangjuDistrict implements Districts {
    GWANGJU1("광주1"),
    GWANGJU2("광주2"),
    GWANGJU3("광주3"),
    GWANGJU4("광주4"),
    GWANGJU5("광주5"),
    GWANGJU6("광주6"),
    GWANGJU7("광주7"),
    GWANGJU8("광주8"),
    GWANGJU9("광주9");



    private String title;


     GwangjuDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
