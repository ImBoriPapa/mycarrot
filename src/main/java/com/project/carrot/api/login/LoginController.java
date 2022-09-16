package com.project.carrot.api.login;

import com.project.carrot.api.login.form.RequestLoginForm;
import com.project.carrot.domain.Login.service.LoginService;
import com.project.carrot.domain.Login.service.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    @ResponseBody
    public ResponseEntity login(@RequestBody RequestLoginForm form){
        log.info("form loginId={}, password={}",form.getLoginId(),form.getPassword());
        LoginDto login = loginService.login(form.getLoginId(), form.getPassword());
        log.info("authentication getToken={}",login.getToken());
        log.info("authentication getRefreshToken={}",login.getRefreshToken());

        return ResponseEntity.ok().body(login);
    }

}
