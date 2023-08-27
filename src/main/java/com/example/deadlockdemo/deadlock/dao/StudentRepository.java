package com.example.deadlockdemo.deadlock.dao;

import com.example.deadlockdemo.deadlock.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Modifying
    @Query(value = "update Student set score = score + 10 where uid = :uid")
    int increaseScoreById(Integer uid);

    @Modifying
    @Query(value = "update Student set score = score - 10 where uid = :uid")
    int decreaseScoreById(Integer uid);
}
