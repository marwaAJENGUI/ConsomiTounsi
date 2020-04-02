package tn.consomitounsi.www.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.consomitounsi.www.entity.Ad;
import tn.consomitounsi.www.entity.Product;
import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.User;
import tn.consomitounsi.www.entity.UserProductCategoryViews;


public interface UserProductCategoryViewsRepository  extends JpaRepository<UserProductCategoryViews,Long>{
	@Query("select upc from UserProductCategoryViews upc where upc.user=:user and upc.productCategory =:category" )	
	public Optional< UserProductCategoryViews> getUserViews(@Param("user")User user,@Param("category")ProductCategory category);
	
	 List<UserProductCategoryViews>findAllByUserAndProductCategory(User user,ProductCategory category);
}
