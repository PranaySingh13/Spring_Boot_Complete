package com.gk.blog.service;

import java.util.List;

import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	List<CategoryDto> getAllCategories();

	CategoryDto getCategoryById(int categoryId) throws ResourceNotFoundException;

	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) throws ResourceNotFoundException;

	CategoryDto deleteCategory(int categoryId) throws ResourceNotFoundException;

}
