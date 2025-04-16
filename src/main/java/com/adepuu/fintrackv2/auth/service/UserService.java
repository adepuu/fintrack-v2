package com.adepuu.fintrackv2.auth.service;

import com.adepuu.fintrackv2.auth.dto.LoginResponse;
import com.adepuu.fintrackv2.auth.entity.User;

public interface UserService {
    User registerUser(User request);
    LoginResponse loginUser(String email, String password);
}
