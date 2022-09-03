package com.project.carrot.api.Validation;

import lombok.Data;

import java.util.List;
@Data
public class ValidationResponse {

    private String statusCode;
    private String errorContent;
    private List<String> message;

    public ValidationResponse(String statusCode, String errorContent, String message) {
        this.statusCode = statusCode;
        this.errorContent = errorContent;
        this.message.add(message);
    }

    public ValidationResponse(String statusCode, String errorContent, List<String> message) {
        this.statusCode = statusCode;
        this.errorContent = errorContent;
        this.message = message;
    }
}
