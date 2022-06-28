package com.project.carrot.domain.entity.locationItem.district;





public enum JeollanamDoDistrict implements Districts {


    JEOLLANAM_DO1("전라남도1"),
    JEOLLANAM_DO2("전라남도2"),
    JEOLLANAM_DO3("전라남도3"),
    JEOLLANAM_DO4("전라남도4"),
    JEOLLANAM_DO5("전라남도5");


    private String title;


     JeollanamDoDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
