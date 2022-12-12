package com.sunil.userservice.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sunil.userservice.entites.User;
import com.sunil.userservice.exceptions.DuplicateResourceFoundException;
import com.sunil.userservice.exceptions.ResourceNotFoundException;
import com.sunil.userservice.payloads.HotelDto;
import com.sunil.userservice.payloads.RatingDto;
import com.sunil.userservice.payloads.RatingListResponse;
import com.sunil.userservice.payloads.UserDto;
import com.sunil.userservice.payloads.UserListResponse;
import com.sunil.userservice.repositories.UserRepo;
import com.sunil.userservice.services.UserService;
import com.sunil.userservice.services.feign.HotelService;
import com.sunil.userservice.services.feign.RatingService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private HotelService hotelService;

    @Override
    public UserDto saveUser(UserDto userDto) {

        /// check if user is already present
        Optional<User> checkUser = this.userRepo.findByEmail(userDto.getEmail());
        if (checkUser.isPresent()) {
            throw new DuplicateResourceFoundException("User", "Email", userDto.getEmail());
        }

        String randomId = UUID.randomUUID().toString();
        userDto.setId(randomId);
        User user = this.modelMapper.map(userDto, User.class);
        User savedUser = this.userRepo.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserListResponse getAllUsers(Pageable pageable) {
        Page<User> userPage = this.userRepo.findAll(pageable);
        List<User> users = userPage.getContent();
        List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return this.createUserListResponse(userDtos, userPage);
    }

    @Override
    public UserDto getUserById(String userId) {

        /// Getting user from database
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        /// Get rating

        RatingListResponse ratingListResponse = this.ratingService.getRatingByUserId(user.getId());

        List<RatingDto> ratingDtos = ratingListResponse.getContent().stream().map(ratingDto -> {
            /// Get hotel by id
            HotelDto hotelDto = this.hotelService.getHotelById(ratingDto.getHotelId());
            ratingDto.setHotelDto(hotelDto);
            return ratingDto;
        }).collect(Collectors.toList());

        user.setRatings(ratingDtos);

        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(String id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        this.userRepo.delete(user);

    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    private UserListResponse createUserListResponse(List<UserDto> userDtos, Page<User> page) {
        return UserListResponse.builder().content(userDtos).isLastPage(page.isLast())
                .totalElements(page.getTotalElements()).pageNumber(page.getNumber()).totalPages(page.getTotalPages())
                .pageSize(page.getSize()).build();
    }
}
