package com.dexcode.apiserver.service;

import java.util.List;

import com.dexcode.apiserver.Model.RespCommon;
import com.dexcode.apiserver.Model.User;

public interface CommonService {
    RespCommon<?> getUsers();
    RespCommon<?> insertUser(List<User> users);
    RespCommon<?> getUsersById(Integer id);
    RespCommon<?> updateUser(User user);
    RespCommon<?> deleteUser(Integer id);
}
