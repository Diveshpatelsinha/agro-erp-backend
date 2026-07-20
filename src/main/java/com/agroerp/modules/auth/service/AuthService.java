package com.agroerp.modules.auth.service;

import com.agroerp.modules.auth.dto.LoginRequest;
import com.agroerp.modules.auth.dto.LoginResponse;
import com.agroerp.modules.auth.dto.RegisterRequest;
import com.agroerp.modules.auth.entity.User;


public interface AuthService {


    LoginResponse login(LoginRequest request);


    User register(RegisterRequest request);
}