package com.zhgo.community.controller;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(String id){

        return "index";
    }
}
