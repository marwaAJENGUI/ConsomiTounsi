package tn.esprit.spring.sevice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.sevice.interfece.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void addProduct(Product product) {
			productRepository.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		productRepository.save(product);
	}
}


