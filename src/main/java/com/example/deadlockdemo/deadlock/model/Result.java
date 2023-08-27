package com.example.deadlockdemo.deadlock.model;

import lombok.Data;

@Data
public class Result<T> {
    private T model;
    private boolean success;
    private String msg;
}
