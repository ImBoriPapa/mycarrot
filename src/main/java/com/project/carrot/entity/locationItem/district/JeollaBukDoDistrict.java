package com.project.carrot.entity.locationItem.district;





public enum JeollaBukDoDistrict implements Districts {
    JEOLLBUK_DO1("전라북도1"),
    JEOLLBUK_DO2("전라북도2"),
    JEOLLBUK_DO3("전라북도3"),
    JEOLLBUK_DO4("전라북도4"),
    JEOLLBUK_DO5("전라북도5");



    private String title;


     JeollaBukDoDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
