package com.project.carrot.api.member;

import com.project.carrot.api.member.form.ApiCreateSignUpForm;
import com.project.carrot.api.response.BaseResponse;
import com.project.carrot.api.response.CustomResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    @GetMapping("/login-page")
    public ModelAndView accessLoginPage() {
        log.info("access/success api/login-page");
        return new ModelAndView("api/member/api-loginPage");
    }

    @GetMapping("/signUp-page")
    public ModelAndView accessSignUpPage() {
        log.info("access/success api/signUp-page");
        return new ModelAndView("api/member/api-signUpPage");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody @Valid ApiCreateSignUpForm form, BindingResult bindingResult) {
        log.info("call sign up");
        if (bindingResult.hasErrors()) {
            BaseResponse baseResponse = new BaseResponse<>().failValidationByBeanResponse(HttpStatus.BAD_REQUEST, CustomResponseStatus.REQUEST_ERROR, List.of(form), bindingResult);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
        BaseResponse baseResponse = new BaseResponse<>().successResponse(CustomResponseStatus.SUCCESS,List.of(form));

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }



}