package com.example.notificationservice.notification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/sse-test")
    public String sseTestPage() {
        return "sse-test";
    }
}

