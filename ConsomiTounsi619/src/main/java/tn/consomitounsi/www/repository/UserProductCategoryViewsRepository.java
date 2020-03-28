package tn.consomitounsi.www.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.UserProductCategoryViews;


public interface UserProductCategoryViewsRepository  extends JpaRepository<UserProductCategoryViews,Long>{
	@Query("select upc from UserProductCategoryViews upc Joine upc.user u   where u.username=?1 and upc.productCategory =?2" )	
	public Optional< UserProductCategoryViews> getUserViews(String userName, ProductCategory category);
}
