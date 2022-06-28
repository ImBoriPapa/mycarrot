package com.project.carrot.domain.entity.locationItem.district;





public enum UlsanDistrict implements Districts {
    ULSAN1("울산1"),
    ULSAN2("울산2"),
    ULSAN3("울산3"),
    ULSAN4("울산4"),
    ULSAN5("울산5");


    private String title;


     UlsanDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
