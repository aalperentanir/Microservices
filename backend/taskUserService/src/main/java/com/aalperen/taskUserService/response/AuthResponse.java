package com.aalperen.taskUserService.response;


import lombok.Data;

@Data
public class AuthResponse {

    private String status;

    private String message;

    private String jwt;

}