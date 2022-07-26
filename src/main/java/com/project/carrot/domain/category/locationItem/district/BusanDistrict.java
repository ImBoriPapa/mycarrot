package com.project.carrot.domain.category.locationItem.district;





public enum BusanDistrict implements Districts {

    BUSAN1("부산1"),
    BUSAN2("부산2"),
    BUSAN3("부산3"),
    BUSAN4("부산4"),
    BUSAN5("부산5"),
    BUSAN6("부산6"),
    BUSAN7("부산7"),
    BUSAN8("부산8"),
    BUSAN9("부산9");



    private String title;


     BusanDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
