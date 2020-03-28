package tn.consomitounsi.www.service;

import java.util.List;

import tn.consomitounsi.www.entity.Product;

public interface IProductService {
	
	public Product addProduct(Product product);

	public Product updateProduct(Product product,Long barCode);

	public  List<Product> findAll();

	public Product getProductBybarCode(Long barCode);

	public boolean removeProduct(Long barCode);

	public List<Product> searchProducts(String productName, String categoryName);

	public boolean existsById(Long id);

	public Product getOne(Long id);

	public List<Product> findProductByName(String name);

	//List<Product> getProductsByCategory(Long idCategory);

}
