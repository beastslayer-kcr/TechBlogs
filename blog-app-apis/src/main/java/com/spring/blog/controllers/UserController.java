package com.spring.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.payloads.ApiResponse;
import com.spring.blog.payloads.UserDto;
import com.spring.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	//Post -- create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	//Put -- update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> putUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
		UserDto updateUserDto = this.userService.updateUser(userDto,userId);
//		ResponseEntity.ok(updateUserDto); both can be used
		return new ResponseEntity<>(updateUserDto, HttpStatus.OK);
		
	}
	
	//Delete -- delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") int uid){
		this.userService.deleteUserById(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted sucessfully",true),HttpStatus.OK);
	}
		
	
	//Get -- get user by id
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int uid){
		UserDto getUser = this.userService.getUserById(uid);
		return new ResponseEntity<>(getUser,HttpStatus.OK);
	}
	//Get --get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> getAllUserList = this.userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(getAllUserList,HttpStatus.OK);
		
	}
	
}
