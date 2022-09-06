package com.project.carrot.api.header;

import lombok.Getter;

@Getter
public enum ResponseHeader {


    /**
     * Accept:  application/json
     * Content-Type:  application/json
     */
    ACCEPT("Accept","application/json"),
    CONTENT_TYPE("Content-Type", "application/json");

    private final String KEY;
    private final String VALUE;

    ResponseHeader(String KEY, String VALUE) {
        this.KEY = KEY;
        this.VALUE = VALUE;
    }
}
