package com.gk.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.blog.entity.Category;
import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.CategoryDto;
import com.gk.blog.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = modelMapper.map(categoryDto, Category.class);
		Category createdCategory = catRepo.save(category);
		return modelMapper.map(createdCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = catRepo.findAll();
		return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) throws ResourceNotFoundException {
		Category category = catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) throws ResourceNotFoundException {

		Category category = catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = catRepo.save(category);
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto deleteCategory(int categoryId) throws ResourceNotFoundException {
		Category category = catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		catRepo.delete(category);
		return modelMapper.map(category, CategoryDto.class);
		
	}

}
