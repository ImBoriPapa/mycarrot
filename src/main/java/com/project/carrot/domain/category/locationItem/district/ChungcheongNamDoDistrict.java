package com.project.carrot.domain.category.locationItem.district;





public enum ChungcheongNamDoDistrict implements Districts {

    CHUNGCHEONGNAM_DO1("충청남도1"),
    CHUNGCHEONGNAM_DO2("충청남도2"),
    CHUNGCHEONGNAM_DO3("충청남도3"),
    CHUNGCHEONGNAM_DO4("충청남도4"),
    CHUNGCHEONGNAM_DO5("충청남도5"),
    CHUNGCHEONGNAM_DO6("충청남도6"),
    CHUNGCHEONGNAM_DO7("충청남도7"),
    CHUNGCHEONGNAM_DO8("충청남도8"),
    CHUNGCHEONGNAM_DO9("충청남도9");




    private String title;


     ChungcheongNamDoDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
