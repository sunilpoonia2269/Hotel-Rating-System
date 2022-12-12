package com.sunil.userservice.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.userservice.config.AppConstants;
import com.sunil.userservice.payloads.ApiResponse;
import com.sunil.userservice.payloads.UserDto;
import com.sunil.userservice.payloads.UserListResponse;
import com.sunil.userservice.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        UserDto savedUserDto = this.userService.saveUser(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<UserListResponse> getAllUsers(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir,
            @RequestParam(defaultValue = AppConstants.DEFAULT_UNPAGED) boolean unpaged) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Direction.fromString(sortDir), sortBy));
        if (unpaged) {
            pageable = Pageable.unpaged();
        }
        UserListResponse userDtos = this.userService.getAllUsers(pageable);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        UserDto userDto = this.userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable String id) {
        this.userService.deleteUser(id);
        ApiResponse apiResponse = ApiResponse.builder().message("User deleted Successfully").status(HttpStatus.OK)
                .success(true).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
