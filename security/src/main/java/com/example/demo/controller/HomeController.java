package com.example.demo.controller;

import com.example.demo.model.MemberUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/home")
    public String getHome(@AuthenticationPrincipal MemberUserDetails memberUserDetails) {
        log.info("MemberUserDetails {}", memberUserDetails);
        if (memberUserDetails != null) {
            // articleRepository.getArticles(memberUserDetails.memberId);
        }
        return "home";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "logout";
    }

    @GetMapping
    public String getRoot() {
        return "redirect:/member/list";
    }
}
