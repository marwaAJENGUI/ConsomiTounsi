package tn.consomitounsi.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.consomitounsi.www.entity.Product;
import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.ProductSearch;
import tn.consomitounsi.www.service.IProductCategoryService;
import tn.consomitounsi.www.service.IProductService;


@RestController
public class ProductController {
	@Autowired
	IProductService iProductService;
	@Autowired
	IProductCategoryService iProductCategoryService;
	
	@GetMapping("/view/Products")
	@ResponseBody
	public List<Product> getProducts() {
		return iProductService.findAll();   
	}
	
	@GetMapping("/view/Product/{barCode}")
	@ResponseBody
    public Product getProduct(@PathVariable("barCode") Long barCode){
		Product product = iProductService.getProductBybarCode(barCode);
		return product;
    }
	
	@PutMapping("/view/searchProduct")
	@ResponseBody
    public List<Product> searchProducts(@RequestBody ProductSearch productSearch){
		String productName=productSearch.getProductName();
		String categoryName= productSearch.getCategoryName();
		List<Product> products = iProductService.searchProducts(productName,categoryName);
		return products;
    }
	
	@PostMapping("/manage/addProduct")
	@ResponseBody
	public Product addProduct(@RequestBody Product product)
	{
		if (!iProductService.existsById(product.getBarCode())) {
			product.setCategory(validCategory(product.getCategory()));
			iProductService.addProduct(product);
			return product;
		}else {
			throw new IllegalArgumentException("Product already exist");
		}
	}
	
	@PutMapping(value = "/manage/updateProduct/{barCode}") 
	@ResponseBody
	public Product updateProduct(@PathVariable("barCode") Long barCode,@RequestBody Product product) {
		Product p= product;
		Product prod= iProductService.getOne(p.getBarCode());
		prod.setPrice(p.getPrice());
		prod.setName(p.getName());
		prod.setCategory(validCategory(p.getCategory()));
		return iProductService.updateProduct(p, barCode);
	}
	
	@DeleteMapping("/manage/removeProduct/{barCode}")
	@ResponseBody
    public boolean removeProductCategory(@PathVariable("barCode") Long barCode){
		return iProductService.removeProduct(barCode);
    }
	
	ProductCategory validCategory(ProductCategory category) {
		if (category==null) throw new IllegalArgumentException("Ad Category can not be empty");
		if((category.getId()!=null)&&(iProductCategoryService.existsById(category.getId()))) {
			return iProductCategoryService.getOne(category.getId());
		}else if ((category.getName()!=null)&&(iProductCategoryService.findCategoryByName(category.getName()).size()>0)) {
			return iProductCategoryService.findCategoryByName(category.getName()).get(0);			
		}else throw new IllegalArgumentException("Invalid Ad Category, could not find reference");
	}
	
}
