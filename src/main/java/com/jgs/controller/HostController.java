package com.jgs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HostController {

    @RequestMapping(path = "/host", method = RequestMethod.GET)
    String home() {
        return "47.103.199.109:8811";
    }
}
