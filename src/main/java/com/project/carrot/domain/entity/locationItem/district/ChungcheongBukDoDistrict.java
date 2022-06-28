package com.project.carrot.domain.entity.locationItem.district;





public enum ChungcheongBukDoDistrict implements Districts {

    CHUNGCHEONG_BUK_DO1("충청북도1"),
    CHUNGCHEONG_BUK_DO2("충청북도2"),
    CHUNGCHEONG_BUK_DO3("충청북도3"),
    CHUNGCHEONG_BUK_DO4("충청북도4"),
    CHUNGCHEONG_BUK_DO5("충청북도5"),
    CHUNGCHEONG_BUK_DO6("충청북도6"),
    CHUNGCHEONG_BUK_DO7("충청북도7"),
    CHUNGCHEONG_BUK_DO8("충청북도8"),
    CHUNGCHEONG_BUK_DO9("충청북도9");



    private String title;


     ChungcheongBukDoDistrict(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
