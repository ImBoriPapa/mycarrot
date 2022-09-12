package com.project.carrot.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


@Getter
@NoArgsConstructor
public class ResponseForm<T> extends RepresentationModel {

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
