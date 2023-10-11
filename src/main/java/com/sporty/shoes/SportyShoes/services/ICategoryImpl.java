package com.sporty.shoes.SportyShoes.services;

import java.util.List;
import java.util.Optional;

import com.sporty.shoes.SportyShoes.entity.Category;

public interface ICategoryImpl {
	
	Category createNewCategory(Category Category);
	Category updateCategory(Category Category);
	List<Category> getAllCategorys();
	Optional<Category> getCategoryInfo(int categoryId);
	String deleteCategory(int categoryId);

}
