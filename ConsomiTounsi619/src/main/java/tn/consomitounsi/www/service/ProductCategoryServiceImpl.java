package tn.consomitounsi.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.consomitounsi.www.entity.Product;
import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.repository.ProductCategoryRepository;
import tn.consomitounsi.www.repository.ProductRepository;


@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
	@Autowired
	ProductCategoryRepository 	productCategoryRepository;
	@Autowired
	ProductRepository 	productRepository;

	@Override
	public ProductCategory findOneByName(String name) {
		return productCategoryRepository.findOneByName(name).get(0);
	}

	@Override
	public ProductCategory addProductCategory(ProductCategory productCategory) {
		return 		productCategoryRepository.save(productCategory);
	}

	@Override
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}

	@Override
	public ProductCategory updateProductCategoryById( ProductCategory productCategory,Long id) {
		ProductCategory category = productCategoryRepository.getOne(id);
		productCategoryRepository.updateProductCategoryById(productCategory.getName(),id);
		return category;
	}

	@Override
	public ProductCategory getProductCategoryById(Long id) {
		return productCategoryRepository.getOne(id);
	}

	@Override
	public boolean removeProductCategory(Long id) {
				ProductCategory category=new ProductCategory();
		if (productCategoryRepository.existsById(id)) {
			if(productCategoryRepository.findById(id).get().getName().equals("No one")) return false;
			category=productCategoryRepository.getOne(id);
			if(category.getProducts().size()>0) {
				List <ProductCategory> list=productCategoryRepository.findCategoryByName("No one");
				if(list.size()==0) {
					category.setName("No one");
					category=productCategoryRepository.saveAndFlush(category);
				} else {
					category=list.get(0); 
					List <Product> products=productRepository.findProductsByCategory(id);
					for (Product product:products) {
						product.setCategory(category);
						productRepository.save(product);
					}
			}
		}
			productCategoryRepository.delete(productCategoryRepository.getOne(id));
		}
		return true;
	}
	
	@Override
	public boolean existsById(Long id){
		return productCategoryRepository.existsById(id);
	}
	@Override
	public ProductCategory getOne(Long id) {
		return productCategoryRepository.getOne(id);
	}
	@Override
	public List< ProductCategory> findCategoryByName(String name){
		return productCategoryRepository.findCategoryByName(name);
	}
}
