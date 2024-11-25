package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/book")
    public String getBook(Model model) {
        var params = new String[] {"홍길동"};
        String title = messageSource.getMessage("home.welcome", params, LocaleContextHolder.getLocale());
        String description = "스프링 기초에서 실무까지...";
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        return "book-info";
    }

    @GetMapping("/message")
    public String getMessage(Model model) {
        model.addAttribute("guest", "김희선");
        return "message";
    }

    @GetMapping("/model")
    public String getModel(Model model) {
        var member = new Member(1L, "홍길동", "hong@hanbit.co.kr", 10);
        model.addAttribute("member", member);
        return "model";
    }

    @GetMapping("/utility")
    public String getUtility(Model model) {
        Date date = Calendar.getInstance().getTime();
        model.addAttribute("date", date);

        model.addAttribute("productPrice", 345620.5226);
        model.addAttribute("productCount", 3502340);

        return "utility";
    }

    @GetMapping("/condition")
    public String getCondition(Model model) {
        model.addAttribute("showHelloOne", true);
        model.addAttribute("showHelloTwo", false);
        return "condition";
    }

    @GetMapping("/list")
    public String getList(Model model) {
        var members = List.of(Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).build(),
                Member.builder().name("공미영").email("MiyeongKong@hanbit.co.kr").age(23).build(),
                Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").age(10).build());
        model.addAttribute("members", members);
        return "list-with-common";
    }

    @GetMapping("/link")
    public String getLink(Model model) {
        model.addAttribute("id", "PD102");
        return "link";
    }
}
