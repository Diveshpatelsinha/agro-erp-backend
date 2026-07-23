package com.agroerp.modules.user.service;

import com.agroerp.common.enums.UserRole;
import com.agroerp.exception.BusinessException;
import com.agroerp.exception.ResourceNotFoundException;
import com.agroerp.modules.auth.entity.User;
import com.agroerp.modules.auth.repository.UserRepository;
import com.agroerp.modules.user.dto.ChangePasswordRequest;
import com.agroerp.modules.user.dto.UpdateUserRequest;
import com.agroerp.modules.user.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementServiceImpl
        implements UserManagementService {

    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserManagementServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository  = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User", "id", id));
    }

    @Override
    @Transactional
    public UserResponse update(Long id,
                               UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User", "id", id));

        userRepository.findAll().stream()
                .filter(u ->
                        u.getEmail().equalsIgnoreCase(
                                request.getEmail())
                                && !u.getId().equals(id))
                .findAny()
                .ifPresent(u -> {
                    throw new BusinessException(
                            "Email '" + request.getEmail()
                                    + "' is already in use."
                    );
                });

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setRole(UserRole.valueOf(request.getRole()));

        return UserResponse.fromEntity(
                userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse toggleActive(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User", "id", id));

        if (user.getRole().name().equals("ADMIN")) {
            long adminCount = userRepository.findAll()
                    .stream()
                    .filter(u ->
                            u.getRole().name().equals("ADMIN")
                                    && u.isActive())
                    .count();

            if (adminCount <= 1 && user.isActive()) {
                throw new BusinessException(
                        "Cannot deactivate the last active admin."
                );
            }
        }

        user.setActive(!user.isActive());

        return UserResponse.fromEntity(
                userRepository.save(user));
    }

    @Override
    @Transactional
    public void changePassword(Long id,
                               ChangePasswordRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User", "id", id));

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);
    }
}