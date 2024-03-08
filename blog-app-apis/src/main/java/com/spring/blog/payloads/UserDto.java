package com.spring.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min=4,message = "size of characters in name should nnot be less than 4")
	private String name;
	@Email(message = "given email address is not valid")
	private String email;
	@NotEmpty
	@Size(min=10, message="about section can't be less than 10 characters")
	private String about;
	@NotEmpty
	@Size(min=3,max=10, message="password can't be less than 10 characters")
	//@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,10}$")
	private String password;
}
