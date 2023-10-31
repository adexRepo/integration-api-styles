package com.dexcode.apiserver.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dexcode.apiserver.ApiServerApplication;
import com.dexcode.apiserver.service.CommonService;

@SpringBootTest(classes = ApiServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestfulApiController {
    
    @MockBean
    private CommonService services;

}
