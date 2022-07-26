package com.project.carrot.domain.category.locationItem.town;

public enum Town  {

    DAECHI("대치동"),//""
    CHEONGDAM("청담동"),//,
    GAEPO("개포동"),//개포동,
    APGUJEONG("압구정"),//압구정동,
    ILWON("일원동"),//일원동,
    SAMSEONG("삼성동"),//삼성동,
    NONHYEON("논현동"),//논현동,
    YULHYEON("율현동"),//율현동,
    YEOKSAM("역삼동"),//역삼동,
    JAGOK("자곡동"),//자곡동,
    DOGOK("도곡동"),//도곡동,
    SUSEO("수서동"),//수서동,
    SEGOK("세곡동"),//세곡동,
    SINSA("신사동");//신사동

    private String name;

    Town(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
