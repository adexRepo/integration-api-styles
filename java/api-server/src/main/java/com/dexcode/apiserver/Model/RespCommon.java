package com.dexcode.apiserver.Model;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespCommon<T> {
    private HttpStatus status;
    private String message;
    private Boolean error;
    private T data;
}
