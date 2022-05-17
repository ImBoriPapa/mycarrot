package com.project.carrot.validator;

import com.project.carrot.dto.BoardDTO;
import com.project.carrot.entity.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;
@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BoardDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        BoardDTO b = (BoardDTO) obj;
        if(StringUtils.isEmpty(b.getContent())){
            errors.rejectValue("content","key","내용을 입력하세요");
        }
    }
}
