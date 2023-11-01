package com.dexcode.apiserver.controller.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dexcode.apiserver.Model.RespCommon;
import com.dexcode.apiserver.Model.User;
import com.dexcode.apiserver.service.CommonService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "User Management")
public class RestfulApiController {

    private final CommonService commService;

    @GetMapping("/users")
    public RespCommon<?> fetchAllUser() {
        return commService.getUsers();
    }

    @GetMapping("/user/{id}")
    public RespCommon<?> fetchUserById(@PathVariable Integer id) {
        return commService.getUsersById(id);
    }

    @PostMapping("/user")
    public RespCommon<?> saveUsers(@RequestBody List<User> newUsers) {
        return commService.insertUser(newUsers);
    }

    @PutMapping("/user")
    public RespCommon<?> updateUserInfo(@RequestBody User userUpdated) {
        return commService.updateUser(userUpdated);
    }

    @DeleteMapping("/user/{id}")
    public RespCommon<?> deleteUserById(@PathVariable Integer id) {
        return commService.deleteUser(id);
    }

}
