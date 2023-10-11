package com.sporty.shoes.SportyShoes.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.SportyShoes.entity.Category;
import com.sporty.shoes.SportyShoes.repository.ICategoryRepo;

@Service
public class CategoryService implements ICategoryImpl {

	Logger logger = LoggerFactory.getLogger(CategoryService.class);
	@Autowired
	private ICategoryRepo categoryRepo;
	@Override
	public Category createNewCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> getAllCategorys() {
		
		return categoryRepo.findAll();
	}

	@Override
	public Optional<Category> getCategoryInfo(int categoryId) {
		return categoryRepo.findById(categoryId);
	}

	@Override
	public String deleteCategory(int categoryId) {
		 categoryRepo.deleteById(categoryId);
		 return "Category Id " + categoryId + " deleted SuccessFully";
	}

}
