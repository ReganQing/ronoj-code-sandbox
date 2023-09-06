package com.ron.ronojcodesandbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ron_567
 */
@RestController("/")
public class MainContoller {
    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }
}
