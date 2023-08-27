package com.example.deadlockdemo.deadlock.provider;

import com.example.deadlockdemo.deadlock.model.Student;
import com.example.deadlockdemo.deadlock.dao.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Slf4j
public class GeneralProvider {

    private final StudentRepository studentRepository;

    @Autowired
    public GeneralProvider(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String getData(String data) {
        return "data:" + data;
    }

    @Transactional //注意不要在同一个类中调用事务方法，因为声明式事务基于AOP实现，是基于代理的，如果在同一个类中无法拦截到方法，因此事务无法起作用
    public void singleUpdate(Integer uid1, Integer uid2) {
        int affectedRows1 = studentRepository.increaseScoreById(uid1);
        int affectedRows2 = studentRepository.decreaseScoreById(uid2);
        log.info("[GeneralProvider.singleUpdate.info]affectedRows1 = {}", affectedRows1);
        log.info("[GeneralProvider.singleUpdate.info]affectedRows2 = {}", affectedRows2);

    }
}
