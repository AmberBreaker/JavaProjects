package com.blairscott.controller;

import com.blairscott.service.WrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private WrapService service;

    @RequestMapping("/handle/{param}")
    public String handle(@PathVariable("param") String param) {
        return service.wrap(param);
    }
}
