package com.example.data.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userAccount;
    private String password;
}
