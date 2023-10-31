package com.dexcode.apiserver.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.dexcode.apiserver.ApiServerApplication;
import com.dexcode.apiserver.Model.RespCommon;
import com.dexcode.apiserver.Model.User;

@SpringBootTest(classes = ApiServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class CommonServiceTest {
    
    @Autowired
    private CommonService service;


    @Test
    void testGetUsers(){
        RespCommon<?> response = service.getUsers();
        Assertions.assertTrue(response.getError());
    }

    @Test 
    void testInsertUser() {
        List<User> users = dummyUsers();
        RespCommon<?> respInsert = service.insertUser(users);
        Assertions.assertFalse(respInsert.getError());
        
        RespCommon<?> respGet = service.getUsers();
        Assertions.assertFalse(respGet.getError());
        
        List<User> users2 = dummyUsers();
        RespCommon<?> respInsert2 = service.insertUser(users2);
        Assertions.assertFalse(respInsert2.getError());
    }

    @Test
    void testUpdateUser(){
        final Integer target_index = 0;
        List<User> users = dummyUsers();
        RespCommon<?> respInsert = service.insertUser(users);
        Assertions.assertFalse(respInsert.getError());
        System.out.println("respInsert : " + users.get(target_index));
        
        User userUpdate = User.builder()
        .id(target_index)
        .name("Uchia Sasuke")
        .build();
        RespCommon<?> respUpdate = service.updateUser(userUpdate);
        Assertions.assertFalse(respUpdate.getError());
        System.out.println("respUpdate : " + respUpdate.getData());

        RespCommon<?> respGet = service.getUsersById(target_index);
        Assertions.assertFalse(respGet.getError());
        System.out.println("respGet : " + respGet.getData());
    }

    @Test
    void testDeleteUser(){
        final Integer target_index = 0;
        List<User> users = dummyUsers();
        RespCommon<?> respInsert = service.insertUser(users);
        Assertions.assertFalse(respInsert.getError());
        System.out.println("respInsert : " + users);
        
        RespCommon<?> respDelete = service.deleteUser(target_index);
        Assertions.assertFalse(respDelete.getError());
        System.out.println("respDelete : " + respDelete.getData());
        
        RespCommon<?> respGetUsers = service.getUsers();
        Assertions.assertFalse(respGetUsers.getError());
        System.out.println("respGetUsers : " + respGetUsers.getData());
    }



    /* --------------------------------- helper --------------------------------- */
    private List<User> dummyUsers() {
        List<User> users = new ArrayList<>();
        User user1 = User.builder()
                .age(30)
                .email("itachi@test.com")
                .name("Uciha Itachi")
                .build();
        User user2 = User.builder()
                .age(25)
                .email("adexx@test.com")
                .name("Adek Kristiyanto")
                .build();
        User user3 = User.builder()
                .age(21)
                .email("levi@test.com")
                .name("Levi Ackerman")
                .build();

        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }

}
