package com.agroerp.modules.user.service;

import com.agroerp.modules.user.dto.ChangePasswordRequest;
import com.agroerp.modules.user.dto.UpdateUserRequest;
import com.agroerp.modules.user.dto.UserResponse;
import java.util.List;

public interface UserManagementService {

    List<UserResponse> getAll();

    UserResponse getById(Long id);

    UserResponse update(Long id, UpdateUserRequest request);

    UserResponse toggleActive(Long id);

    void changePassword(Long id, ChangePasswordRequest request);
}