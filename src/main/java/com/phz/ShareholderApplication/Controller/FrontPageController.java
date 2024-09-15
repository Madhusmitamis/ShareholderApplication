package com.phz.ShareholderApplication.Controller;


import org.springframework.web.bind.annotation.GetMapping;

public class FrontPageController {
     @GetMapping("/")
    public String frontPage() {
        return "index";
    }
}
