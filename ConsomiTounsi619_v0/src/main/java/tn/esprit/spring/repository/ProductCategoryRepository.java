package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long>{

}
