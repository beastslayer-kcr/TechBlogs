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
import com.spring.blog.payloads.CategoryDto;
import com.spring.blog.services.impl.CategoryServiceImpl;



@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> cretaeCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createdCategory = this.categoryServiceImpl.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.OK);
	}
	
	@PutMapping("/{myId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("myId") int categoryId){
		CategoryDto updatedCategoryDto = this.categoryServiceImpl.updateCategory(categoryDto,categoryId);
		return new  ResponseEntity<CategoryDto>(updatedCategoryDto,HttpStatus.OK);
	}
	
	@DeleteMapping("{myId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("myId") Integer categoryId) {
		this.categoryServiceImpl.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted category",true),HttpStatus.OK);
	}
	
	@GetMapping("{myId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("myId") Integer categoryId){
		CategoryDto categoryDto=this.categoryServiceImpl.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@GetMapping("{myId}")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategoryList = this.categoryServiceImpl.getAllCategoryList();
		return new ResponseEntity<List<CategoryDto>>(allCategoryList,HttpStatus.OK);
		
	}
	
}
