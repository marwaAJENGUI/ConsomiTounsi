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

import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.service.IProductCategoryService;


@RestController
public class ProductCategoryController {
	@Autowired
	IProductCategoryService iProductCategoryService;
	
	@GetMapping("/view/ProductCategories")
	@ResponseBody
	public List<ProductCategory>getProductCategories() {
		return iProductCategoryService.findAll();  
		}
	
	@PostMapping("/manage/addProductCategory")
	@ResponseBody
	public ProductCategory addProductCategory(@RequestBody ProductCategory productCategory){
		if (!iProductCategoryService.existsById(productCategory.getId())) {
			return iProductCategoryService.addProductCategory(productCategory);
		}else {
			throw new IllegalArgumentException("Product Category already exist");
		}		
	}
	
	@GetMapping("/view/ProductCategory/{id}")
	@ResponseBody
    public ProductCategory  getProductCategory(@PathVariable("id") Long id){
		return iProductCategoryService.getProductCategoryById(id);
    }
	
	@DeleteMapping("/manage/removeProductCategory/{id}")
	@ResponseBody
    public boolean removeProductCategory(@PathVariable("id") Long id){
		return iProductCategoryService.removeProductCategory(id);	
	}
	

	@PutMapping("/manage/updateProductCategory/{id}")
    public ProductCategory  updateProductCategory(@PathVariable("id") Long id, @RequestBody ProductCategory productCategory){
		return  iProductCategoryService.updateProductCategoryById( productCategory,  id);
    }
}
