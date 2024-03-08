package com.spring.blog.excpetions;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	String errorCode;
	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String errorCode, String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s error code %s not found with %s : %s",errorCode, resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.errorCode = errorCode;
	}
	
}
