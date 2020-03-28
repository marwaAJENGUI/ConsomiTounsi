package tn.consomitounsi.www.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.consomitounsi.www.entity.Product;
import tn.consomitounsi.www.entity.UserProductViews;

public interface UserProductViewsRepository extends JpaRepository< UserProductViews,Long>{
	@Query("select up from UserProductViews up Joine up.user u   where u.username=?1 and up.product =?2" )
	Optional <UserProductViews> getUserViews(String userName,Product product);
}
