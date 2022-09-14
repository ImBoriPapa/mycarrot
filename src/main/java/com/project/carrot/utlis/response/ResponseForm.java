package com.project.carrot.utlis.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ResponseForm<T> {

    private CustomResponseStatus status;
    private String message;
    private T result;

    @Builder
    public ResponseForm(CustomResponseStatus status, T result) {
        this.status = status;
        this.message = status.getMessage();
        this.result = result;
    }

}
