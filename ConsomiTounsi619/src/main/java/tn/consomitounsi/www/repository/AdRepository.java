package tn.consomitounsi.www.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.consomitounsi.www.entity.Ad;
import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.User;

public interface AdRepository extends JpaRepository<Ad,Long>{
	@Query("Select DISTINCT a From Ad  a "
			+ "JOIN  a.category c"
			+ " where c.id=:id") 
	List <Ad> findAdsByCategory(@Param("id") Long id);
	
	@Query("Select DISTINCT a From Ad  a "
			+ "JOIN  a.product p "
			+"join p.category c "
			+ "join p.productUsersViews upv "
			+ "where c=:category "
			+ "and upv.user=:user "
			+ "and a.endDate>=:today "
			+ "Order By upv.views Desc")
	List <Ad> findUserAdsByProductCategory(@Param("user") User user, @Param("category")ProductCategory category, @Param("today")Date today );
	@Query("Select DISTINCT a From Ad  a "
			+ "JOIN a.product p "
			+ "join p.productUsersViews upv "
			+ "Where upv.user=:user "
			+ "and a.endDate>=:today "
			+ "Order By upv.views Desc, a.endDate Asc")
	List <Ad> findUserAds(@Param("user")User user, @Param("today")Date today );	
@Query("Select DISTINCT a From Ad  a "
			+ "JOIN  a.product p "
			+"join p.category c "
			+ "where c=:category "
			+ "and a.endDate>=:today "
			+ "Order By a.endDate Asc")
	List <Ad> findAdsByProductCategory(@Param("category")ProductCategory category, @Param("today")Date today );

	
	@Query("Select DISTINCT a From Ad  a "
			+ "Where a.endDate>=:today "
			+ "Order By a.views Desc")
	List <Ad> findAllByViews(@Param("today")Date today );	
	

}
