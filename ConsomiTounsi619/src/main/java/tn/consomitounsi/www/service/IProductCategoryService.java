package tn.consomitounsi.www.service;



import java.util.List;
import java.util.Optional;

import tn.consomitounsi.www.entity.ProductCategory;

public interface IProductCategoryService {
	
	public List<ProductCategory> findAll();
	public ProductCategory findOneByName(String name);
	public ProductCategory addProductCategory(ProductCategory productCategory);
	public ProductCategory updateProductCategoryById(ProductCategory productCategory, Long id);
	public ProductCategory getProductCategoryById(Long id);
	public boolean removeProductCategory(Long id);
	boolean existsById(Long id);
	ProductCategory getOne(Long id);
	List<ProductCategory> findCategoryByName(String name);
	Optional<ProductCategory> findById(Long id);

}
