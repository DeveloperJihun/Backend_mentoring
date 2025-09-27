package com.jihuni.backend_mentoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // 회원가입 화면 보여주기
    @GetMapping("/signup-form")
    public String signupForm() {
        return "signup"; // → templates/signup.html
    }

    // 로그인 화면 보여주기
    @GetMapping("/login-form")
    public String loginForm() {
        return "login"; // → templates/login.html
    }
}
