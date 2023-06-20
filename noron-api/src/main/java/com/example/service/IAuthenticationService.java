package com.example.service;

import com.example.data.request.UserLoginRequest;
import com.example.data.request.UserSignInRequest;

public interface IAuthenticationService {
    public String checkValidUser(UserLoginRequest userLoginRequest);
    public String authenticate(UserLoginRequest userLoginRequest);
    public boolean insertUSer(UserSignInRequest userSignInAndUpdateRequest);
}
