package com.dexcode.apiserver.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SingletonDb {
    private static SingletonDb instance;
    private List<User> users = new ArrayList<>();

    private SingletonDb(){}

    public static SingletonDb getInstance() {
        if (instance == null) {
            instance = new SingletonDb();
        }
        return instance;
    }
}