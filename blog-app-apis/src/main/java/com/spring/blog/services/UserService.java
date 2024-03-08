package com.spring.blog.services;


import java.util.List;

import com.spring.blog.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer userDtoId);
	UserDto getUserById(Integer userDtoId);
	List<UserDto> getAllUsers();
	void deleteUserById(Integer userDtoId);
}
