package com.hzy.redis.controller;

import com.hzy.redis.service.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/demo")
@RestController
public class SingleController {
    @Autowired
    private SingleService singleService;

    @GetMapping(value = "/getCache")
    public Integer getCache(@RequestParam("key") String key) {
        Integer valid = singleService.getValid(key);
        return valid;
    }

    @GetMapping(value = "/setCache")
    public Integer setCache(@RequestParam("key") String key, Integer val) {
        Integer valid = singleService.setValid(key, val);
        return valid;
    }

    @GetMapping(value = "/setCacheRandom")
    public Integer setCacheRandom(@RequestParam("key") String key) {
        Integer valid = singleService.setValid(key);
        return valid;
    }

}
