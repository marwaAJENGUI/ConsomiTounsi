package tn.consomitounsi.www.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.consomitounsi.www.entity.ProductCategory;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long>{
	
	@Query("Select c from ProductCategory c where c.name= :name ")
	 List <ProductCategory> findOneByName(@Param("name") String name);
    @Transactional
    @Modifying
    @Query("update ProductCategory c set c.name =:name where c.id = :id")
    void updateProductCategoryById(@Param("name")String name,@Param("id") Long id);
    
    @Query("Select c from ProductCategory c where c.name=:name") 
	List <ProductCategory> findCategoryByName(@Param("name")String name);
	Optional <ProductCategory> findById(Long id);    

}
