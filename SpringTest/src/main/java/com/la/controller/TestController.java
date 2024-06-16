package com.la.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LA
 * @createDate 2024-06-16-21:07
 * @description
 */
@RestController
public class TestController {

    @GetMapping("/liao/go")
    public String test(){
        return "翱哥的测试网站";
    }
}
