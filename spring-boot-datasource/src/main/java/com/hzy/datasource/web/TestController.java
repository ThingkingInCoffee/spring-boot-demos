package com.hzy.datasource.web;

import com.hzy.datasource.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/datasource")
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public void testDatasource() {
        log.info("into test datasource");
        testService.queryData();
    }

    @GetMapping("/testTx")
    public void testTransactional() {
        log.info("into test Transactional");
        testService.testTx();
    }

}
