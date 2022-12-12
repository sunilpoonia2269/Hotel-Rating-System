package com.sunil.userservice.services;

import org.springframework.data.domain.Pageable;

import com.sunil.userservice.payloads.UserDto;
import com.sunil.userservice.payloads.UserListResponse;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    UserListResponse getAllUsers(Pageable pageable);

    UserDto getUserById(String userId);

    void deleteUser(String id);

    UserDto updateUser(UserDto userDto, String userId);
}
