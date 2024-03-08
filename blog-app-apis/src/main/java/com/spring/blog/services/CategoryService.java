
package com.spring.blog.services;

import java.util.List;

import com.spring.blog.payloads.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto); 
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	//delete
	void deleteCategory(Integer categoryId);
	//getAllCategoryList
	List<CategoryDto> getAllCategoryList();
	//getCategoryById
	CategoryDto getCategoryById(Integer categoryDtoId);
}
