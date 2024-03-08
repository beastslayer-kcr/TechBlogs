package com.spring.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.blog.entities.Category;
import com.spring.blog.excpetions.ResourceNotFoundException;
import com.spring.blog.payloads.CategoryDto;
import com.spring.blog.repositories.CategoryRepository;
import com.spring.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("701","Category ","Category Id",categoryId));
		//Category updateCategory = this.categoryRepository.save(category);
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		Category updatedCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	public void deleteCategory(Integer categoryId) {
		Category deleteCategory = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("702","Category ","Category Id",categoryId));
		this.categoryRepository.delete(deleteCategory);
	}

	@Override
	public List<CategoryDto> getAllCategoryList() {
		List<Category> categoryList = this.categoryRepository.findAll();
		List<CategoryDto> categoryDtoList = categoryList.stream().map((category)-> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());	
		return categoryDtoList;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("703","Category ","Category Id",categoryId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

}
