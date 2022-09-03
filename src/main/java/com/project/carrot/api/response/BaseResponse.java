package com.project.carrot.api.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Stream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"code","customResponseCode", "httpStatus", "message", "count", "result"})
public class BaseResponse<T> {

    private Integer code;

    private Integer customResponseCode;
    private HttpStatus httpStatus;
    private T message;
    private Integer count;
    private T result;

    public BaseResponse successResponse(CustomResponseStatus customResponseStatus,List<T> result) {
        return BaseResponse.builder()
                .code(customResponseStatus.getHttpStatusCode())
                .httpStatus(customResponseStatus.getHttpStatus())
                .message(customResponseStatus.getMessage())
                .count(result.size())
                .result(result)
                .build();
    }

    public BaseResponse failValidationByBeanResponse(HttpStatus httpStatus, CustomResponseStatus customResponseStatus, List<T> result, BindingResult bindingResult){

        Stream<String> message = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage());

        return BaseResponse.builder()
                .code(httpStatus.value())
                .customResponseCode(customResponseStatus.getCustomCode())
                .httpStatus(httpStatus)
                .message(List.of(message))
                .count(bindingResult.getErrorCount())
                .result(result)
                .build();
    }

    public BaseResponse failByExceptionResponse(CustomResponseStatus customResponseStatus){
        return BaseResponse.builder()
                .code(customResponseStatus.getHttpStatusCode())
                .customResponseCode(customResponseStatus.getCustomCode())
                .httpStatus(httpStatus.BAD_REQUEST)
                .message(List.of(message))
                .count(0)
                .result(result)
                .build();
    }
}
