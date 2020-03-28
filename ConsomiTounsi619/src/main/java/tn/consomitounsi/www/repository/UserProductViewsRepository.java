package tn.consomitounsi.www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.consomitounsi.www.entity.Product;
import tn.consomitounsi.www.entity.User;
import tn.consomitounsi.www.entity.UserProductViews;

public interface UserProductViewsRepository extends JpaRepository< UserProductViews,Long>{
	@Query("select up from UserProductViews up "
			+ " where up.user=:user and "
			+ "up.product =:product" )
	Optional <UserProductViews> getUserViews(@Param("user")User user,@Param("product")Product product);
}
