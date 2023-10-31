package com.dexcode.apiserver.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dexcode.apiserver.Model.RespCommon;
import com.dexcode.apiserver.Model.SingletonDb;
import com.dexcode.apiserver.Model.User;

@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public RespCommon<?> getUsers() {
        SingletonDb singletonDb = SingletonDb.getInstance();
        if(singletonDb.getUsers().size() == 0){
            return buildRespFailed("Users Empty", HttpStatus.GONE, null);
        }
        return buildRespSuccess("Success Get Users", singletonDb.getUsers());
    }

    @Override
    public RespCommon<?> getUsersById(Integer id) {
        SingletonDb singletonDb = SingletonDb.getInstance();
        User user = singletonDb.getUsers()
                .stream()
                .filter(val -> val.getId().equals(id))
                .collect(Collectors.toList())
                .get(0);
        return buildRespSuccess("Success Get User By Id", user);
    }

    @Override
    public RespCommon<?> updateUser(User user) {
        SingletonDb singletonDb = SingletonDb.getInstance();
        List<User> users = singletonDb.getUsers();
        for (User item : users) {
            if (item.getId().equals(user.getId())) {
                item.setAge   (user.getAge   () != null ? user.getAge   () : item.getAge   () );
                item.setEmail (user.getEmail () != null ? user.getEmail () : item.getEmail () );
                item.setName  (user.getName  () != null ? user.getName  () : item.getName  () );
                return buildRespSuccess("Success Update User", user);
            }
        }
        return buildRespFailed("Failed to update user. User ID not found!", HttpStatus.NOT_FOUND, user);
    }

    @Override
    public RespCommon<?> insertUser(List<User> usersNew) {
        if(usersNew.size() == 0){
            return buildRespFailed("Request to insert users cannot be empty", HttpStatus.NOT_FOUND, usersNew);
        }

        SingletonDb singletonDb = SingletonDb.getInstance();
        List<User> oriUsersNow = singletonDb.getUsers();
        List<User> usersNow = new ArrayList<>(singletonDb.getUsers());
        usersNow.sort(Comparator.comparingInt(User::getId));

        int nextId = usersNow.size() == 0 ? 0 : usersNow.get(usersNow.size() - 1).getId() + 1;

        for (User newUser : usersNew) {
            newUser.setId(nextId++);
            oriUsersNow.add(newUser);
        }

        return buildRespSuccess("Success Insert User", oriUsersNow);
    }

    @Override
    public RespCommon<?> deleteUser(Integer id) {
        SingletonDb singletonDb = SingletonDb.getInstance();
        Boolean isRemoved = singletonDb.getUsers().removeIf(val-> val.getId().equals(id));
        if (!isRemoved){
            return buildRespFailed("Failed to delete user. User ID not found!", HttpStatus.NOT_FOUND, id);
        }
        return buildRespSuccess("Success Delete User", singletonDb.getUsers());
    }
    
    private <T> RespCommon<?>  buildRespSuccess(String msg, T data){
        return RespCommon.builder()
                .error(false)
                .status(HttpStatus.OK)
                .message(msg)
                .data(data)
                .build();
    }

    private <T> RespCommon<?> buildRespFailed(String msg, HttpStatus status, T data) {
        return RespCommon.builder()
                .error(true)
                .status(status)
                .message(msg)
                .data(data)
                .build();
    }
}
