package com.spring.blog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.blog.entities.User;
import com.spring.blog.payloads.UserDto;
import com.spring.blog.repositories.UserRepo;
import com.spring.blog.services.UserService;
import com.spring.blog.excpetions.*;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	@Autowired 
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		
		try {
			if(userDto.getName().isEmpty()||userDto.getName().length()==0) {
				throw new BusinessException("601","Name not available");
			}
			User user = this.dtoToUser(userDto);
			User savedUser = this.userRepo.save(user);
			return this.userToDto(savedUser);
		}catch(IllegalArgumentException ex){
			throw new BusinessException("602","given employee is null"+ex.getMessage());
		}catch(Exception e) {
			throw new BusinessException("603","something went wrong in service layer"+e.getMessage());
		}
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("101","User","User Id",userId));
		user.setId(userId);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updatedUser = this.userRepo.save(user);
		return userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("102","User","User Id",userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> userList = this.userRepo.findAll();
//		List<UserDto> userDtoList = new ArrayList<>();
//		for(int i=0;i<userList.size();i++) {
//			userDtoList.add(this.userToDto(userList.get(i)));
//		}
		List<UserDto> userDtoList = userList.stream().map(user->this.userToDto(user))
				.collect(Collectors.toList());
		try {
			if(userDtoList.isEmpty()) {
				throw new BusinessException("604","No users are present in list");
			}
				return userDtoList;
		}catch(Exception ex) {
			throw new BusinessException("605","some thing went wrong in service layer"+ex.getMessage());
		}
		
	}

	@Override
	public void deleteUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("103","User","User Id",userId));
		this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;	
	}
	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return userDto;	
	}
}
