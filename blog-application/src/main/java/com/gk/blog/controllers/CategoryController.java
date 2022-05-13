package com.gk.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.ApiResponse;
import com.gk.blog.payloads.CategoryDto;
import com.gk.blog.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService catService;

	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

		return new ResponseEntity<CategoryDto>(catService.createCategory(categoryDto), HttpStatus.CREATED);
	}

	// Authorization Handled in SecurityConfig.class for get mapping
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryId(@PathVariable int categoryId) throws ResourceNotFoundException {
		return new ResponseEntity<CategoryDto>(catService.getCategoryById(categoryId), HttpStatus.OK);
	}

	// Authorization Handled in SecurityConfig.class for get mapping
	@GetMapping("/allCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return new ResponseEntity<List<CategoryDto>>(catService.getAllCategories(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable int categoryId) throws ResourceNotFoundException {
		return new ResponseEntity<CategoryDto>(catService.updateCategory(categoryDto, categoryId), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId) throws ResourceNotFoundException {
		CategoryDto deletedCategory = catService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Category " + deletedCategory.getCategoryTitle() + " deleted successfully", true),
				HttpStatus.OK);
	}

}
