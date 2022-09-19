package com.project.carrot.api.login;

import com.project.carrot.api.login.form.RequestLoginForm;
import com.project.carrot.api.login.form.ResponseLoginForm;
import com.project.carrot.domain.Login.service.LoginService;
import com.project.carrot.domain.Login.service.dto.LoginDto;
import com.project.carrot.exception.customEx.RequestValidationException;
import com.project.carrot.exception.errorCode.ErrorCode;
import com.project.carrot.utlis.jwt.JwtHeader;
import com.project.carrot.utlis.response.CustomResponseStatus;
import com.project.carrot.utlis.response.ResponseForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@Valid @RequestBody RequestLoginForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestValidationException(ErrorCode.VALID_FAIL_ERROR, bindingResult);
        }

        log.info("form loginId={}, password={}", form.getLoginId(), form.getPassword());
        LoginDto login = loginService.loginProcess(form.getLoginId(), form.getPassword());
        log.info("authentication getToken={}", login.getToken());
        log.info("authentication getRefreshToken={}", login.getRefreshToken());

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtHeader.AUTHORIZATION_HEADER, login.getToken());
        headers.add(JwtHeader.REFRESH_HEADER, login.getRefreshToken());

        WebMvcLinkBuilder linkTo = linkTo(LoginController.class);

        ResponseLoginForm responseLoginForm = new ResponseLoginForm(login.getMemberId());

        responseLoginForm.add(linkTo.slash("logout").withRel("GET : Logout"));

        ResponseForm<Object> responseForm = new ResponseForm<>(CustomResponseStatus.SUCCESS, responseLoginForm);

        return ResponseEntity.ok().headers(headers).body(responseForm);
    }
}
