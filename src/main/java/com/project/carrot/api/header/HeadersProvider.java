package com.project.carrot.api.header;

import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;

public class HeadersProvider {
    private String accept;
    private String URI;
    private String contentType;
    private String method;
    private String cacheControl;
    private String authorization;
    private String refresh;

    public static HttpHeaders defaultResponseHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("URL","LocalHost:8080/api/member");
        headers.add("HTTP_METHOD","POST");
        headers.add(HttpHeaders.ACCEPT,"application/json");
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0, must-revalidate");
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json; charset=utf-8");
        headers.add(HttpHeaders.DATE,String.valueOf(LocalDateTime.now()));
        return headers;
    }
}
