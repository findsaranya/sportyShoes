package com.sporty.shoes.SportyShoes.services;

import java.util.List;
import java.util.Optional;

import com.sporty.shoes.SportyShoes.entity.Product;

public interface IProductImpl {
	Product createNewProduct(Product product);
	Product updateProduct(Product product);
	List<Product> getAllProducts();
	Optional<Product> getProductInfo(int prodId);
	String deleteProduct(int prodId);
}
