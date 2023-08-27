package com.example.deadlockdemo.deadlock.controller;

import com.example.deadlockdemo.deadlock.model.Result;
import com.example.deadlockdemo.deadlock.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    @Autowired
    private GeneralService generalService;

    @GetMapping("/deadLockTest")
    public Result<Boolean> deadLockTest(Integer uid1,Integer uid2){
        return generalService.deadLockTest(uid1,uid2);
    }

    @GetMapping("/hello")
    public Result<String> hello(String data){
        return generalService.hello(data);
    }

}
