package com.sporty.shoes.SportyShoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.SportyShoes.entity.Product;
import com.sporty.shoes.SportyShoes.repository.IProductRepo;

@Service
public class ProductService implements IProductImpl {
    @Autowired
	private IProductRepo prodRepo;
    
	@Override
	public Product createNewProduct(Product product) {
		return prodRepo.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return prodRepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return prodRepo.findAll();
	}

	@Override
	public Optional<Product> getProductInfo(int prodId) {
		return prodRepo.findById(prodId);
	}

	@Override
	public String deleteProduct(int prodId) {
		prodRepo.deleteById(prodId);
		 return "Product Id " + prodId + " deleted SuccessFully";
	}

}
