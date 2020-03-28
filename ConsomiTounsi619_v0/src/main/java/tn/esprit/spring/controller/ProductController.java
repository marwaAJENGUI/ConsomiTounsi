package tn.esprit.spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.sevice.interfece.IProductService;

@RestController
public class ProductController {
	IProductService iProductService;
	
	@PostMapping("/addProduct")
	@ResponseBody
	public Product ajouterEmploye(@RequestBody Product product)
	{
		iProductService.addProduct(product);
		return product;
	}
	
	@PostMapping(value = "/updateProduct") 
	@ResponseBody
	public void updateProduct(@RequestBody Product product) {
		iProductService.updateProduct(product);
		
	}
}
