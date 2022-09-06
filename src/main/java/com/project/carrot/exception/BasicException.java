package com.project.carrot.exception;

import com.project.carrot.api.response.CustomResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicException extends RuntimeException{
    private CustomResponseStatus status;
}
