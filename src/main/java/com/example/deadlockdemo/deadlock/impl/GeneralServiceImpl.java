package com.example.deadlockdemo.deadlock.impl;

import com.example.deadlockdemo.deadlock.model.Result;
import com.example.deadlockdemo.deadlock.provider.GeneralProvider;
import com.example.deadlockdemo.deadlock.service.GeneralService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeneralServiceImpl implements GeneralService {
    @Autowired
    private GeneralProvider generalProvider;
    public Result<String> hello(String data){
        Result<String> result = new Result<>();
        try{
            String dataRes = generalProvider.getData(data);
            result.setSuccess(true);
            result.setModel(dataRes);
        }catch (Exception ex){
            log.error("[GeneralServiceImpl.hello] error = {}", ex.getMessage());
            result.setSuccess(false);
            result.setMsg(ex.getMessage());
        }
        return result;
    }
    class MyThread extends Thread{
        private Integer id1;
        private Integer id2;
        public MyThread(Integer id1,Integer id2){
            this.id1 = id1;
            this.id2 = id2;
        }
        @Override
        public void run(){
            try {
                generalProvider.singleUpdate(id1, id2);
            }catch (Exception ex){
                log.error("[MyThread.run.error] error = {}",ex.getMessage());
            }
        }
    }
    //deaLock test
    public Result<Boolean> deadLockTest(Integer uid1,Integer uid2){
        Result<Boolean> result = new Result<>();
        try {
            MyThread thread1 = new MyThread(uid1,uid2);
            MyThread thread2 = new MyThread(uid2,uid1);
            thread1.start();
            thread2.start();
            result.setSuccess(true);
            result.setModel(Boolean.TRUE);
        }catch (Exception ex){
            log.error("[GeneralServiceImpl.deadLockTest] error = {}", ex.getMessage());

            result.setModel(Boolean.FALSE);
            result.setSuccess(false);
            result.setMsg(ex.getMessage());
        }
        return result;
    }
}
