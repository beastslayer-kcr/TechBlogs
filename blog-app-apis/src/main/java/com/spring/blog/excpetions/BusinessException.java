package com.spring.blog.excpetions;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class BusinessException extends RuntimeException{

	private String errorCode;
	private String errorMessage;
	public BusinessException(String errorMessage,String errorCode) {
		super(String.format("%s error as method related to this error code is empty",errorCode));
		// TODO Auto-generated constructor stu
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	
	
}
