package com.example.deadlockdemo.deadlock.service;


import com.example.deadlockdemo.deadlock.model.Result;

public interface GeneralService {
   Result<String> hello(String data);

   Result<Boolean> deadLockTest(Integer uid1,Integer uid2);

}
